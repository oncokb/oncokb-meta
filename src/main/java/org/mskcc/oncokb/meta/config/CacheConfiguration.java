package org.mskcc.oncokb.meta.config;


import org.mskcc.oncokb.meta.config.application.ApplicationProperties;
import org.mskcc.oncokb.meta.config.application.RedisType;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.context.annotation.Bean;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

public abstract class CacheConfiguration {
    @Bean
    public RedissonClient redissonClient(ApplicationProperties applicationProperties) throws Exception {
        Config config = new Config();
        if (applicationProperties.getRedis().getType().equals(RedisType.SINGLE.getType())) {
            config.useSingleServer()
                    .setAddress(applicationProperties.getRedis().getAddress())
                    .setPassword(applicationProperties.getRedis().getPassword());
        } else if (applicationProperties.getRedis().getType().equals(RedisType.SENTINEL.getType())) {
            config.useSentinelServers()
                    .setMasterName(applicationProperties.getRedis().getSentinelMasterName())
                    .setCheckSentinelsList(false)
                    .addSentinelAddress(
                            applicationProperties.getRedis()
                                    .getAddress()
                    )
                    .setPassword(applicationProperties.getRedis().getPassword());
        } else {
            throw new Exception("The redis type " + applicationProperties.getRedis().getType() + " is not supported. Only single and sentinel are supported.");
        }
        return Redisson.create(config);
    }

    @Bean
    public javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration(ApplicationProperties applicationProperties, RedissonClient redissonClient) {
        MutableConfiguration<Object, Object> jcacheConfig = new MutableConfiguration<>();
        jcacheConfig.setStatisticsEnabled(true);
        jcacheConfig.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, applicationProperties.getRedis().getExpiration())));
        return RedissonConfiguration.fromInstance(redissonClient, jcacheConfig);
    }

    protected void createCache(javax.cache.CacheManager cm, String cacheName, javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration, CacheNameResolver cacheNameResolver) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheNameResolver.getCacheName(cacheName), jcacheConfiguration);
        }
    }
}

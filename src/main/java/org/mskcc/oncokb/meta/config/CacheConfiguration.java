package org.mskcc.oncokb.meta.config;


import org.mskcc.oncokb.meta.config.application.RedisProperties;
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
    public RedissonClient redissonClient(RedisProperties redisProperties) throws Exception {
        Config config = new Config();
        if (redisProperties.getType().equals(RedisType.SINGLE.getType())) {
            config.useSingleServer()
                    .setAddress(redisProperties.getAddress())
                    .setPassword(redisProperties.getPassword());
        } else if (redisProperties.getType().equals(RedisType.SENTINEL.getType())) {
            config.useSentinelServers()
                    .setMasterName(redisProperties.getSentinelMasterName())
                    .setCheckSentinelsList(false)
                    .addSentinelAddress(
                            redisProperties
                                    .getAddress()
                    )
                    .setPassword(redisProperties.getPassword());
        } else {
            throw new Exception("The redis type " + redisProperties.getType() + " is not supported. Only single and master-slave are supported.");
        }
        return Redisson.create(config);
    }

    @Bean
    public javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration(RedisProperties redisProperties, RedissonClient redissonClient) {
        MutableConfiguration<Object, Object> jcacheConfig = new MutableConfiguration<>();
        jcacheConfig.setStatisticsEnabled(true);
        jcacheConfig.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, redisProperties.getExpiration())));
        return RedissonConfiguration.fromInstance(redissonClient, jcacheConfig);
    }

    protected void createCache(javax.cache.CacheManager cm, String cacheName, javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration, CacheNameResolver cacheNameResolver) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheNameResolver.getCacheName(cacheName), jcacheConfiguration);
        }
    }
}

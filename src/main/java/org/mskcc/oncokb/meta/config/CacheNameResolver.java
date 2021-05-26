package org.mskcc.oncokb.meta.config;

import org.mskcc.oncokb.meta.config.application.ApplicationProperties;
import org.springframework.stereotype.Component;

@Component
public class CacheNameResolver {

    ApplicationProperties applicationProperties;

    public CacheNameResolver(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public String getCacheName(String cacheKey) {
        return applicationProperties.getName() + "-" + cacheKey;
    }
}

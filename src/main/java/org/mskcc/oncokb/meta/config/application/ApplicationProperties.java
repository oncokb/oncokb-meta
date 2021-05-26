package org.mskcc.oncokb.meta.config.application;

import org.mskcc.oncokb.meta.enumeration.ProjectProfile;

public class ApplicationProperties {
    private String name;
    private ProjectProfile profile;
    private RedisProperties redis;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectProfile getProfile() {
        return profile;
    }

    public void setProfile(ProjectProfile profile) {
        this.profile = profile;
    }

    public RedisProperties getRedis() {
        return redis;
    }

    public void setRedis(RedisProperties redis) {
        this.redis = redis;
    }
}

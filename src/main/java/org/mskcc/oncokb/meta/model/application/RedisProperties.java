package org.mskcc.oncokb.meta.model.application;

import org.mskcc.oncokb.meta.enumeration.RedisType;

public class RedisProperties {
    Boolean enabled = false;
    String type;
    String password;
    String address;
    String sentinelMasterName;
    Integer expiration = 300;
    Integer timeout = 3000;
    Integer retryAttempts = 3;
    Integer retryInterval = 1500;

    public RedisProperties() {
        this.type = RedisType.SINGLE.getType();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSentinelMasterName() {
        return sentinelMasterName;
    }

    public void setSentinelMasterName(String sentinelMasterName) {
        this.sentinelMasterName = sentinelMasterName;
    }

    public Integer getExpiration() {
        return expiration;
    }

    public void setExpiration(Integer expiration) {
        this.expiration = expiration;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(Integer retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public Integer getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }
}

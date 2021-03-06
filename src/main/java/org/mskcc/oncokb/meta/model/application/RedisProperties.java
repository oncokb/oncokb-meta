package org.mskcc.oncokb.meta.model.application;

import org.mskcc.oncokb.meta.enumeration.RedisType;

public class RedisProperties {
    Boolean enabled = false;
    String type;
    String password;
    String address;
    String sentinelMasterName;
    private int expiration;

    public RedisProperties() {
        this.type = RedisType.SINGLE.getType();
        this.expiration = 300;
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

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }
}

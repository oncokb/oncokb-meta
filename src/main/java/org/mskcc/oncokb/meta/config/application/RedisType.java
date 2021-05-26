package org.mskcc.oncokb.meta.config.application;

public enum RedisType {
    SINGLE("single"),
    SENTINEL("sentinel");

    private final String type;

    RedisType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

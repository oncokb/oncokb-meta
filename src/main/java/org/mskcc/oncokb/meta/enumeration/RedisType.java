package org.mskcc.oncokb.meta.enumeration;

public enum RedisType {
    SINGLE("single"),
    SENTINEL("sentinel"),
    CLUSTER("cluster");

    private final String type;

    RedisType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

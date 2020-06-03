package com.inayat.yourrooms.dto;
enum Roles {

    ADMIN("ROLE_ADMIN", 1), CONSUMER("ROLE_CONSUMER", 2), SUPERADMIN("ROLE_SUPERADMIN", 3);

    private final String key;
    private final Integer value;

    Roles(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }
    public Integer getValue() {
        return value;
    }
}
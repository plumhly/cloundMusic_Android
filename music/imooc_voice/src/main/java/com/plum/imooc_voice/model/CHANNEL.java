package com.plum.imooc_voice.model;

public enum CHANNEL {
    MY("我的", 0x01),
    DISCOVER("发现", 0x02),
    FRIEND("朋友", 0x03);

    private final String key;
    private final int value;

    public static final int MY_ID = 0x01;
    public static final int DISCOVER_ID = 0x02;
    public static final int FRIEND_ID = 0x03;

    private CHANNEL(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}

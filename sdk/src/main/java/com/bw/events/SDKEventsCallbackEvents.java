package com.bw.events;

/**
 * Created by Administrator on 2017/5/25.
 */

public class SDKEventsCallbackEvents {
    private int code;
    private String id;
    private int port;

    public SDKEventsCallbackEvents(int code, String id, int port) {
        this.code = code;
        this.id = id;
        this.port = port;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

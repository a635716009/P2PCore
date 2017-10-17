package com.bw.events;

/**
 * Created by Administrator on 2017/1/6.
 * SDK 回调消息事件
 */

public class SdkCallbackEvents
{
    private byte[] data;
    private int lengthXML;
    private String deviceId;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getLengthXML() {
        return lengthXML;
    }

    public void setLengthXML(int lengthXML) {
        this.lengthXML = lengthXML;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

package com.bw.events;

import com.bw.p2plibrary.clien.FrameHead;

/**
 * Created by Administrator on 2017/1/16.
 */

public class AVFrameCallbackEvents
{
    private byte[] data;
    private int len;
    private int type;
    private int totalFrame;
    private int frameRate;
    private String deviceId;
    private String fileName;
    private FrameHead frameHead;

    public FrameHead getFrameHead() {
        return frameHead;
    }

    public void setFrameHead(FrameHead frameHead) {
        this.frameHead = frameHead;
    }

    public byte[] getData()
    {
        return data;
    }

    public void setData(byte[] data)
    {
        this.data = data;
    }

    public int getLen()
    {
        return len;
    }

    public void setLen(int len)
    {
        this.len = len;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getTotalFrame()
    {
        return totalFrame;
    }

    public void setTotalFrame(int totalFrame)
    {
        this.totalFrame = totalFrame;
    }

    public int getFrameRate()
    {
        return frameRate;
    }

    public void setFrameRate(int frameRate)
    {
        this.frameRate = frameRate;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

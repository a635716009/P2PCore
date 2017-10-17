package com.bw.events;

/**
 * Created by Administrator on 2016/12/26.
 */

public class AppEvents
{
    protected String  msgId;
    protected String  msgContent;

    public String getMsgId()
    {
        return msgId;
    }

    public void setMsgId(String msgId)
    {
        this.msgId = msgId;
    }

    public String getMsgContent()
    {
        return msgContent;
    }

    public void setMsgContent(String msgContent)
    {
        this.msgContent = msgContent;
    }
}

package com.bw.p2plibrary.P2PClient;

import com.bw.events.AVFrameCallbackEvents;
import com.bw.events.AppBus;
import com.bw.events.SDKEventsCallbackEvents;
import com.bw.events.SdkCallbackEvents;
import com.bw.p2plibrary.clien.FrameHead;
import com.bw.p2plibrary.clien.ImageHead;

/**
 * Created by Administrator on 2016/12/30.
 */

class PPCSP2PInterface {
    public static void sdkXmlCB(byte[] xml, int lenXML, String deviceId) {
        SdkCallbackEvents events = new SdkCallbackEvents();
        events.setData(xml);
        events.setLengthXML(lenXML);
        events.setDeviceId(deviceId);
        AppBus.getInstance().appPost(events);
    }

    public static void sdkDataCB(String deviceId, int type, Object head, byte[] data) {
        AVFrameCallbackEvents events = new AVFrameCallbackEvents();
        events.setDeviceId(deviceId);
        events.setType(type);
        events.setData(data);
        events.setLen(data.length);
        switch (type) {
            case P2PConstants.DataType.D_P_JPG:
                ImageHead imageHead = (ImageHead) head;
                events.setFileName(imageHead.filename);
                break;
            default:
                FrameHead frameHead = (FrameHead) head;
                events.setFrameHead(frameHead);
                break;
        }

        AppBus.getInstance().appPost(events);
    }

    public static void sdkEventsCB(int code, String id, int port, Object obj) {
        AppBus.getInstance().appPost(new SDKEventsCallbackEvents(code, id, port));
    }

    /**
     * 初始化sdk
     */
    public static native int sdkInit(String initString, int len);

    /**
     * 反初始化sdk
     */
    public static native int sdkUnInit();

    /**
     * @param id_ip    服务IP或ID(p2p)
     * @param port     服务器监听端口， p2p为0
     * @param protocol 协议类型 TCP = 0,UDP=1,P2P_PPCS=2,ONVIF=3,
     * @param getType  获取流的方式 1:主动获取 2:回调
     *                 打开设备
     */
    public static native int sdkOpenDev(String id_ip, int port, int protocol, int getType, Object xmlParam, Object avParam);

    /**
     * @param id_ip 服务IP或ID(p2p)
     * @param port  服务器监听端口， p2p为0
     * @return F_SDK_OK：成功 -1:失败
     * 关闭设备
     */
    public static native int sdkCloseDev(String id_ip, int port, String xmlParam);

    /**
     * @param id_ip    服务IP或ID(p2p)
     * @param port     服务器监听端口， p2p为0
     * @param msg      要发送的数据
     * @param len      数据长度
     * @param dataType 数据类型  DATA_STR = 1,DATA_XML=2,DATA_JSON=3,DATA_V_H264 = 6,DATA_V_H265=7,DATA_A_PCM = 10,DATA_A_AAC=11,
     * @param channel  数据通道 0 xml data  1 返回实时流， 通道2 对讲 通道3 图片
     * @return >0：成功 -1:失败
     * 向设备发送数据
     * 返回失败时（可以关闭设备）
     * 如需再发送数据，可以再次打开设备
     */
    public static native int sdkSendDataToDev(String id_ip, int port, String msg, int len, int dataType, int channel);

    /**
     * @param id_ip    服务IP或ID(p2p)
     * @param port     服务器监听端口， p2p为0
     * @param data     要发送的数据
     * @param len      数据长度
     * @param dataType 数据类型  DATA_STR = 1,DATA_XML=2,DATA_JSON=3,DATA_V_H264 = 6,DATA_V_H265=7,DATA_A_PCM = 10,DATA_A_AAC=11,
     * @param channel  数据通道 0 xml data  1 返回实时流， 通道2 对讲 通道3 图片
     * @return >0：成功 -1:失败
     * 向设备发送数据
     * 返回失败时（可以关闭设备）
     * 如需再发送数据，可以再次打开设备
     */
    public static native int sdkSendByteDataToDev(String id_ip, int port, byte[] data, int len, int dataType, int channel);

    /**
     * 设备是否在线
     *
     * @param did 参数的长度
     * @return 1：在线 0：离线
     * <p>
     * 1. 设备不在线，每调用一次需要3秒的时间（即会阻塞3秒--window客户端测试得到）
     * 2. 设备一旦上线，马上就可以返回在线
     * 3. 在线后，如果此时设备离线，5分钟内检测还是在线（服务器机制），5分钟后才会得到离线状态
     */
    public static native int sdkDevNetOnline(String did);


    public static native int sdkSearchLanStart(int interval, Object obj);

    public static native int sdkSearchLanStop();

}

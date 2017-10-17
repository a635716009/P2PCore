package com.bw.p2plibrary.P2PClient;

/**
 * Created by Administrator on 2017/7/14.
 */

public interface IP2PControler {
    int init();

    int devOpen(String devId);

    int devOpen(String devId,String ip,int port);

    int devClose(String devId);

    int devSdkSend(String devId, String data);

    int devSdkSend(String devId, String ip,int port,String data);

    int devSdkSend(String ip_id, byte[] data, int len, int type, int channel);

    int devIsOnline(String did);

    int unInit();

    int searchDevStart(int interval, Object obj);

    int searchDevStop();
}

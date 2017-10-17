package com.bw.p2plibrary.P2PClient;

/**
 * Created by Administrator on 2017/7/14.
 */

public class P2PControlerProxy implements IP2PControler {

    private final IP2PControler mControler;

    public P2PControlerProxy(){
        this.mControler=new PPCSP2PControler();
    }


    @Override
    public int init() {
        return mControler.init();
    }

    @Override
    public int devOpen(String devId) {
        return mControler.devOpen(devId);
    }

    @Override
    public int devOpen(String devId, String ip, int port) {
        return mControler.devOpen(devId,ip,port);
    }

    @Override
    public int devClose(String devId) {
        return mControler.devClose(devId);
    }

    @Override
    public int devSdkSend(String devId, String data) {
        return mControler.devSdkSend(devId,data);
    }

    @Override
    public int devSdkSend(String devId, String ip, int port, String data) {
        return mControler.devSdkSend(devId,ip,port,data);
    }

    @Override
    public int devSdkSend(String devId, byte[] data, int len, int type, int channel) {
        return mControler.devSdkSend(devId,data,len,type,channel);
    }

    @Override
    public int devIsOnline(String devId) {
        return mControler.devIsOnline(devId);
    }

    @Override
    public int unInit() {
        return mControler.unInit();
    }

    @Override
    public int searchDevStart(int interval, Object obj) {
        return mControler.searchDevStart(interval,obj);
    }

    @Override
    public int searchDevStop() {
        return mControler.searchDevStop();
    }
}

package com.bw.p2plibrary.P2PClient;


/**
 * Created by Administrator on 2016/12/30.
 */

class PPCSP2PControler implements IP2PControler{

    @Override
    public int init() {
        String iniXml ="<param>\n" +
                "<ppcs>\n" +
                "<initString>\n" +
                "EEGDFHBBKBIFGCICFPHIEIAEHHICHNIEHIBEBJCKFCNKKHPDHNBLHGPBDGLHNLKNFLJNKPCLKKNLECGM\n" +
                "</initString>\n" +
                "<license>\n" +
                "ZPPVRK:EasyView\n" +
                "</license>\n" +
                "</ppcs>\n" +
                "</param>";
        System.loadLibrary("fcP2P");
        return  PPCSP2PInterface.sdkInit(iniXml, iniXml.length());
    }



    @Override
    public int devOpen(String devId) {
        return PPCSP2PInterface.sdkOpenDev(devId, 0, 2, 2, devId, null);
    }

    @Override
    public int devOpen(String devId,String ip, int port) {
        return PPCSP2PInterface.sdkOpenDev(ip, port, 0, 2, devId, null);
    }

    @Override
    public int devClose(String devId) {
        return PPCSP2PInterface.sdkCloseDev(devId, 0, devId);
    }


    @Override
    public int devSdkSend(String devId, String data) {
        return PPCSP2PInterface.sdkSendDataToDev(devId, 0, data, data.getBytes().length, 2,0);
    }

    @Override
    public int devSdkSend(String devId, String ip, int port, String data) {
        return PPCSP2PInterface.sdkSendDataToDev(ip, port, data, data.getBytes().length, 2,0);
    }

    @Override
    public int devSdkSend(String ip_id, byte[] data, int len,int type,int channel) {
        return PPCSP2PInterface.sdkSendByteDataToDev(ip_id, 0, data, len, type,channel);
    }

    @Override
    public int  devIsOnline(String did){
        return PPCSP2PInterface.sdkDevNetOnline(did);
    }

    @Override
    public int unInit() {
        return PPCSP2PInterface.sdkUnInit();
    }

    @Override
    public int searchDevStart(int interval, Object obj) {
        return PPCSP2PInterface.sdkSearchLanStart(interval,obj);
    }

    @Override
    public int searchDevStop() {
        return PPCSP2PInterface.sdkSearchLanStop();
    }
}

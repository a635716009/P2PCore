package com.bw.p2plibrary.P2PClient;

import android.media.AudioFormat;

/**
 * Created by Administrator on 2017/4/21.
 */

public interface P2PConstants {
    interface PTZDirection {
        int PTZ_RESET = 0;                     /* 复位：先转到最大再转到最小位置 */
        int PTZ_RESTART = 1;                        /* 复位：转到最小位置 */
        int PTZ_UP = 2;                             /* 向上 */
        int PTZ_DOWN = 3;                           /* 向下 */
        int PTZ_LEFT = 4;                           /* 向左 */
        int PTZ_RIGHT = 5;                          /* 向右 */
        int PTZ_UP_LEFT = 6;                        /* 向上/向左 */
        int PTZ_UP_RIGHT = 7;                       /* 向上/向右 */
        int PTZ_DOWN_LEFT = 8;                      /* 向下/向左 */
        int PTZ_DOWN_RIGHT = 9;                     /* 向下/向右 */
        int PTZ_RUNPRESET = 10;                  /* 向下/向右 */
    }

    interface PTZSpeed {
        int PTZ_SPEEDDEFAULT = 0;               /* 系统默认的速度 */
        int PTZ_SPEED1 = 1;                         /* 1级速度 */
        int PTZ_SPEED2 = 2;                         /* 2级速度 */
        int PTZ_SPEED3 = 3;                         /* 3级速度 */
        int PTZ_SPEED4 = 4;                         /* 4级速度 */
        int PTZ_SPEED5 = 5;
    }

    interface PTZDirectionReverse {
        int PTZ_UPDOWNREV = 0;                  /* 上下反相 */
        int PTZ_LEFTRIGHTREV = 1;              /* 左右反相 */
    }

    interface DataType{
        int  DATA_STR = 1;
        int  DATA_XML=2;
        int  DATA_JSON=3;
        int  DATA_V_H264 = 6;
        int  DATA_V_H265=7;
        int  DATA_A_PCM = 10;
        int  DATA_A_AAC=11;
        int  DATA_A_G711=12;
        int  D_P_JPG = 20;
    }

    interface DataChannel{
        int  CHANNEL_XML = 1;
        int  CHANNEL_BYTE=2;
    }

    interface TalkConfig {
        int TALK_PCM_HEAD_LENGTH=8;
        int TALK_PCM_LENGTH=1920;
        int frequence = 8000;//采样率
        int channelInConfig = AudioFormat.CHANNEL_IN_MONO;
        int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;
    }

    interface VideoRate{
        String AUTO_RATE="1";
        String LD_RATE="2";
        String SD_RATE="3";
        String HD_RATE="4";
    }
}

package com.bw.p2plibrary.clien;

/**
 * Created by Administrator on 2017/10/7.
 */

public class FrameHead {
    //    typedef struct FCAVFrameHead{
//        char flag[4];				//标识码FCFC|MLD
//        int frameType;                              //帧类型 见 EM_Frame_Type
//        int frameLen;				//帧长度
//    long long int ts;                           //时间戳
//        union {
//            struct {
//                int width;				//宽
//                int height;				//高
//                int frameRate;                      //帧率
//                int isKeyFrame;                     //是否为关键帧
//            }video;
//
//            struct {
//                int bitRate;			//位宽
//                int sampleRate;                     //采样率
//                int channel;			//通道数
//            }audio;
//
//        }frame;
//        int revert0;				//预留值1  在录像流的时候:总帧数
//        int revert1;				//预留值2  在录像流的时候:当前帧数
//        int revert2;				//预留值3  在录像流的时候:结束标识　０:正常　1:结束
//        char data[0];
//    } GNC_PACKED AV_FRAME_HEAD_S ;

    public String flag;
    public int frameType;
    public  int frameLen;
    public long ts;

    public int width;
    public int height;
    public  int frameRate;
    public  int isKeyFrame;

    public int bitRate;
    public  int sampleRate;
    public int channel;

    public  int revert0;
    public int revert1;
    public  int revert2;


}

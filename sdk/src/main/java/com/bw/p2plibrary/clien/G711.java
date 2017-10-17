package com.bw.p2plibrary.clien;

/**
 * Created by Administrator on 2017/1/16.
 */

public class G711 {
    public static native byte[] decoder(byte[] src,int offset,int len);
    public static native byte[] encoder(byte[] src,int len);
}

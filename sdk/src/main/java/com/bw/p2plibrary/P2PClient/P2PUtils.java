package com.bw.p2plibrary.P2PClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Administrator on 2017/5/3.
 */

public class P2PUtils {
    /**
     * byte数组中取int数值，本方法适用于(低位在前，高位在后)的顺序，和和intToBytes（）配套使用
     *
     * @param src
     *            byte数组
     * @param offset
     *            从数组的第offset位开始
     * @return int数值
     */
    public static int bytesToInt(byte[] src, int offset) {
        return ((src[offset] & 0xFF)
                | ((src[offset+1] & 0xFF)<<8)
                | ((src[offset+2] & 0xFF)<<16)
                | ((src[offset+3] & 0xFF)<<24));
    }

    /**
     * byte数组中取int数值，本方法适用于(低位在后，高位在前)的顺序。和intToBytes2（）配套使用
     */
    public static int bytesToInt2(byte[] src, int offset) {
        return  ( ((src[offset] & 0xFF)<<24)
                |((src[offset+1] & 0xFF)<<16)
                |((src[offset+2] & 0xFF)<<8)
                |(src[offset+3] & 0xFF));
    }

    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(低位在前，高位在后)的顺序。 和bytesToInt（）配套使用
     * @param value
     *            要转换的int值
     * @return byte数组
     */
    public static byte[] intToBytes( int value )
    {
        byte[] src = new byte[4];
        src[3] =  (byte) ((value>>24) & 0xFF);
        src[2] =  (byte) ((value>>16) & 0xFF);
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }
    /**
     * 将int数值转换为占四个字节的byte数组，本方法适用于(高位在前，低位在后)的顺序。  和bytesToInt2（）配套使用
     */
    public static byte[] intToBytes2(int value)
    {
        byte[] src = new byte[4];
        src[0] = (byte) ((value>>24) & 0xFF);
        src[1] = (byte) ((value>>16)& 0xFF);
        src[2] = (byte) ((value>>8)&0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * 将8个字节的byte[]转换为long，本方法适用于(低位在前，高位在后)的顺序。
     * @param value
     *            要转换的byte[]
     * @param offset
     *              偏移量
     * @return long
     */

    public static long bytesToLong(byte[] value,int offset) {
        return ((((long) value[ 7+offset] & 0xff) << 56)
                | (((long) value[ 6+offset] & 0xff) << 48)
                | (((long) value[ 5+offset] & 0xff) << 40)
                | (((long) value[ 4+offset] & 0xff) << 32)
                | (((long) value[ 3+offset] & 0xff) << 24)
                | (((long) value[ 2+offset] & 0xff) << 16)
                | (((long) value[ 1+offset] & 0xff) << 8)
                | (((long) value[ offset] & 0xff)));
    }

    /**
     * 将8个字节的byte[]转换为long，本方法适用于(高位在前，低位在后)的顺序。
     * @param value
     *            要转换的byte[]
     * @param offset
     *              偏移量
     * @return long
     */

    public static long bytesToLong2(byte[] value,int offset) {
        return ((((long) value[ offset] & 0xff) << 56)
                | (((long) value[ 1+offset] & 0xff) << 48)
                | (((long) value[ 2+offset] & 0xff) << 40)
                | (((long) value[ 3+offset] & 0xff) << 32)
                | (((long) value[ 4+offset] & 0xff) << 24)
                | (((long) value[ 5+offset] & 0xff) << 16)
                | (((long) value[ 6+offset] & 0xff) << 8)
                | (((long) value[ 7+offset] & 0xff)));
    }


    /**
     * 数组转对象
     * @param bytes
     * @return
     */
    public static Object bytesToObject (byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream (bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }
}

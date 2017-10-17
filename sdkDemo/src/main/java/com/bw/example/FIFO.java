package com.bw.example;

import com.bw.events.AVFrameCallbackEvents;

import java.util.concurrent.LinkedBlockingQueue;

public class FIFO {
    private int bytSize = 0;        //size in byte
    private int nNum = 0;
    private final LinkedBlockingQueue<AVFrameCallbackEvents> listData = new LinkedBlockingQueue<>();

    public int getSize() {
        return bytSize;
    }

    public int getCount() {
        return nNum;
    }

    boolean isEmpty() {

        return listData.isEmpty();
    }

    void addLast(AVFrameCallbackEvents node, int nodeSizeInBytes) {
        try {
            bytSize += nodeSizeInBytes;
            nNum++;
//		byte[] bytNode=new byte[nodeSizeInBytes];
//		System.arraycopy(node, 0, bytNode, 0, nodeSizeInBytes);
            listData.put(node);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    AVFrameCallbackEvents removeHead() {
        try {
            AVFrameCallbackEvents node = listData.take();
            bytSize -= node.getLen();
            nNum--;
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    void removeAll() {
        listData.clear();
        bytSize = 0;
        nNum = 0;
    }
}

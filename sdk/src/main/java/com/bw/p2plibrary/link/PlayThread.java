package com.bw.p2plibrary.link;


import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Message;

public class PlayThread extends Thread {

    private final Handler mHandler;
    private byte[] buffer;
    private long delay = 0;
    private AudioTrack atrack;
    private long playTime;

    PlayThread(byte[] b, long d, Handler handler) {
        buffer = new byte[b.length * 2];
        delay = d;
        this.mHandler=handler;
        // convert from 8 bit per sample to little-endian 16 bit per sample, IOW 16-bit PCM
        int j = 0;
        for (byte data : b) {
            buffer[j++] = 0;
            buffer[j++] = data;
        }
        playTime=((buffer.length / (int)(Constants.kSamplingFrequency * 2))+1)*1000 + d;
        start();
    }

    public void stopAudio() {
        if (atrack!=null &&atrack.getPlayState() == AudioTrack.PLAYSTATE_PLAYING) {
            atrack.stop();
            atrack.release();
        }
        this.interrupt();
    }

    public void run() {
        while (delay > 0) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_URGENT_AUDIO);
        atrack = new AudioTrack(AudioManager.STREAM_MUSIC,
                (int) Encoder.kSamplingFrequency,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                buffer.length,
                AudioTrack.MODE_STATIC);
        try {
            atrack.write(buffer, 0, buffer.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atrack.play();
        try{
            Thread.sleep(playTime);
            if(mHandler!=null) {
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

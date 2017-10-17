package com.bw.p2plibrary.link;


import android.os.Handler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class AudioUtils {

    public static void encodeFileToWav(File inputFile, File outputFile)
	throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	Encoder.encodeStream(new FileInputStream(inputFile), baos);
	// patched out for Android
	// writeWav(outputFile, baos.toByteArray());
    }



	//播放PCM数据
    public static PlayThread performData(byte[] data, long delay, Handler handler)
	throws IOException {
    	return new PlayThread( data, delay ,handler);

    }


    public static void performFile(File file, long delay,Handler handler)
	throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	Encoder.encodeStream(new FileInputStream(file), baos);
	performData(baos.toByteArray(), delay,handler);
    }
    public static PlayThread play(String wifiName,String wifiPassword, Handler handler)
	{
		PlayThread playThread=null;
		String playString = String.format("sSiD%sdIsSCoDe%seDoC", wifiName, wifiPassword);
		try {
			playThread=AudioUtils.performArray(playString.getBytes(), 0, handler);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
		return playThread;
	}

    public static PlayThread performArray(byte[] array, long delay,Handler handler)
	throws IOException {
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	Encoder.encodeStream(new ByteArrayInputStream(Encoder.appendCRC(array)), baos);
       return performData(baos.toByteArray(), delay,handler);
    }
    
    public static void performSOS(long delay,Handler handler) throws IOException {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	Encoder.generateSOS(baos);
    	performData(baos.toByteArray(), delay,handler);
    }
}

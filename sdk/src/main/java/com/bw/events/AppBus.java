package com.bw.events;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by Administrator on 2016/12/23.
 */
public class AppBus extends Bus
{

    private static AppBus bus;

    public static AppBus getInstance() {
        if (bus == null) {
            bus = new AppBus();
        }
        return bus;
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper());                                                                                     //其实这里相当于重写post方法，但在runnable线程中不能再通过super调用Bus里面的post方法，所以重新命名
    public void appPost(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            bus.post(event);
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    bus.post(event);
                }
            });
        }
    }

}

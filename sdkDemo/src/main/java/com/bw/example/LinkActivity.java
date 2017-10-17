package com.bw.example;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bw.events.AppBus;
import com.bw.events.SdkCallbackEvents;
import com.bw.p2plibrary.link.AudioUtils;
import com.bw.p2plibrary.link.PlayThread;
import com.squareup.otto.Subscribe;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/19.
 */

public class LinkActivity extends Activity {
    private LinkActivity activity;
    private PlayThread playThread;
    private EditText wifi_name, wifi_pwd;
    private Button playButton;
    private int countPlay; //播放次数
    private TextView tvCallback;
    private ScrollView mScrollView;
    private Handler mHandler = new Handler();

    private static class MHandler extends Handler {
        private final WeakReference<LinkActivity> mActivity;

        MHandler(LinkActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LinkActivity theActivity = mActivity.get();
            if (theActivity != null) {
                theActivity.finishPlay();
            }
        }
    }

    public synchronized void play(View view)
    {
        playButton=(Button)view;
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
        if(!clearPlayThread())
        {
            String ssid = wifi_name.getText().toString();
            if (TextUtils.isEmpty(ssid))
                return;
            String password = wifi_pwd.getText().toString();
            playButton.setText(R.string.playing);
            appendLog("开始播放声波...");
            playThread = AudioUtils.play(ssid, password, new MHandler(activity));
        }
    }

    //播放之前搜索到的所有局域网设备
    private List<SdkCallbackEvents> listLocal=new ArrayList<>();
    public void finishPlay()
    {
        playButton.setText(getString(R.string.play_again));
        appendLog("播放声波结束！");
        clearPlayThread();
        countPlay++;
    }

    private boolean clearPlayThread()
    {
        if(playThread!=null) {
            playThread.stopAudio();
            playThread = null;
            playButton.setText(R.string.play);
            appendLog("停止播放声波！");
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = this;
        setContentView(R.layout.activity_link);
        initTitieView();
        wifi_name = (EditText) findViewById(R.id.wifi_name);
        wifi_pwd = (EditText) findViewById(R.id.wifi_pwd);
        tvCallback = (TextView) findViewById(R.id.tv_callback);
        tvCallback.setMovementMethod(ScrollingMovementMethod.getInstance());
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        AppBus.getInstance().register(this);
//        OnvifJni.onvifDiscoverStart(5);//onvif 5秒搜索一次
    }


    private void initTitieView() {
        TextView title_left = (TextView) findViewById(R.id.title_left_btn);
        TextView title_middle = (TextView) findViewById(R.id.title_content);
        TextView title_right = (TextView) findViewById(R.id.title_right_btn);

        title_right.setCompoundDrawablePadding(4);
        title_left.setCompoundDrawablePadding(4);
        Drawable drawableLeft = ContextCompat.getDrawable(this, R.mipmap.ic_action_back);
        drawableLeft.setBounds(0, 0, 45, 45);
        title_left.setCompoundDrawables(drawableLeft, null, null, null);
        title_middle.setText(R.string.app_name);
        title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);
//        OnvifJni.onvifDiscoverStop();
        clearPlayThread();
    }


    @Subscribe
    public void searchResultOnEvent(SdkCallbackEvents events) {
//        if(countPlay>0)
//        {
//            if(!listLocal.contains(events) && !TextUtils.isEmpty(events.getP2pId()))
//            {
//                Toast.makeText(activity,"Device:"+events.getP2pId()+" Link success!",Toast.LENGTH_SHORT).show();
//                appendLog("发现新设备:\n        IP:"+events.getIpAddress()+"\n       port:"+events.getPort()+"\n       DID:"+events.getP2pId());
//            }
//        }else
//        {
//            listLocal.add(events);
//            appendLog("\n        IP:"+events.getIpAddress()+"\n       port:"+events.getPort()+"\n       DID:"+events.getP2pId());
//        }
    }

    private void appendLog(String log)
    {
        tvCallback.append(DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis())+log+"\n");
        scrollToBottom(mScrollView, tvCallback);
    }

    private void scrollToBottom(final ScrollView scrollView, final View view) {

        mHandler.post(new Runnable() {

            @Override
            public void run() {
                if (scrollView == null || view == null) {
                    return;
                }
                int offset = view.getMeasuredHeight()
                        - scrollView.getMeasuredHeight();
                if (offset < 0) {
                    offset = 0;
                }
                scrollView.scrollTo(0, offset);
            }
        });
    }
}

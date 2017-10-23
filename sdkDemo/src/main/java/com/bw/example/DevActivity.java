package com.bw.example;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.events.AVFrameCallbackEvents;
import com.bw.events.AppBus;
import com.bw.events.SdkCallbackEvents;
import com.bw.p2plibrary.P2PClient.IP2PControler;
import com.bw.p2plibrary.P2PClient.P2PConstants;
import com.bw.p2plibrary.P2PClient.P2PControlerProxy;
import com.bw.p2plibrary.P2PClient.P2PMsgDef;
import com.bw.p2plibrary.P2PClient.P2PRespCode;
import com.bw.p2plibrary.P2PClient.P2PUtils;
import com.bw.p2plibrary.clien.G711;
import com.squareup.otto.Subscribe;

import java.nio.ByteBuffer;


@SuppressWarnings("deprecation")
public class DevActivity extends Activity implements View.OnClickListener {
    private TextView tvCallback, tvSession;
    private String mSessionId;
    private ProgressDialog myDialog;
    private SurfaceView monitor;
    private boolean m_bRunning;


    private Handler mHandler = new Handler();
    private FIFO m_fifoAudio = new FIFO();
    private FIFO m_fifoVideo = new FIFO();

    private Dev mDev;

    private Button playButton, loginButton, openButton;


    private ScrollView mScrollView;
    private boolean audioRecordInit;
    private AudioTrack m_AudioTrack;
    private String liveStreamId;
    private final IP2PControler controler = new P2PControlerProxy();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDev = MainActivity.mDev;
        initView();
        initTitieView();
        AppBus.getInstance().register(this);
        controler.init();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_open:
                open(view);
                break;
            case R.id.btn_login:
                login(view);
                break;
            case R.id.btn_play:
                play(view);
                break;
        }
    }

    /**
     * open or close dev
     */
    public void open(final View view) {
        openButton = (Button) view;
        open();
    }

    private void open() {
        if (mDev.opened) {

            // close streaming
            if (mDev.isPlaying) {
                play();
            }
            // login out
            if (mDev.logined) {
                login();
            }

            appendLog("正在断开设备连接...");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final int ret = controler.devClose(mDev.getDID());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (ret == 0) {
                                appendLog("断开设备连接失败！");
                            } else if (ret == 1) {
                                appendLog("断开设备连接成功！");
                                mDev.opened = false;
                            }
                            monitor.setVisibility(View.INVISIBLE);
                            tvSession.setText("");
                            openButton.setText(R.string.open);
                        }
                    });
                }
            }).start();
        } else {
            myDialog = new ProgressDialog(this);
            myDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            myDialog.show();
            appendLog("正在连接设备...");
            new Thread(new Runnable() {

                @Override
                public void run() {
                    final int ret = controler.devOpen(mDev.getDID());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (ret == 0) {
                                appendLog("连接设备失败！");
                            } else if (ret == 1) {
                                appendLog("连接设备成功！");
                                mDev.opened = true;
                                openButton.setText(R.string.close);
                            }
                            myDialog.cancel();
                        }
                    });
                }

            }).start();
        }
    }

    /**
     * login or login out dev
     */
    public void login(View view) {
        loginButton = (Button) view;
        login();

    }

    private void login() {
        if (mDev.logined) {
            // close streaming
            if (mDev.isPlaying) {
                play();
            }

            appendLog("注销");
//            controler.devSdkSend(mDev.getDID(), TestXml.getLoginOutXml(mSessionId));
            mDev.logined = false;
            tvSession.setText("");
            m_bRunning = false;
            if (monitor != null)
                monitor.setVisibility(View.INVISIBLE);
            if (loginButton != null)
                loginButton.setText(R.string.login);
        } else {
            appendLog("正在登陆...");
            controler.devSdkSend(mDev.getDID(), TestXml.getLoginXml(mDev.getUserName(), mDev.getPassword()));
        }
    }

    /**
     * play video or close
     */
    public void play(View view) {
        playButton = (Button) view;
        play();
    }

    private void play() {
        m_fifoVideo.removeAll();
        m_fifoAudio.removeAll();
        if (mDev.isPlaying) {
            m_bRunning = false;
            appendLog("正在关闭视频...");
            controler.devSdkSend(mDev.getDID(), TestXml.getStopVideoXml(mSessionId, liveStreamId));
            mDev.isPlaying = false;
            if (monitor != null)
                monitor.setVisibility(View.INVISIBLE);
            if (playButton != null)
                playButton.setText(R.string.play);
        } else {
            appendLog("正在开启视频...");
            controler.devSdkSend(mDev.getDID(), TestXml.getStartVideoXml(mSessionId));
            m_bRunning = true;
            if (monitor != null)
                monitor.setVisibility(View.VISIBLE);
            ThreadPlayAudio m_threadPlayAudio = new ThreadPlayAudio();
            m_threadPlayAudio.start();
            ThreadPlayVideo m_threadPlayVideo = new ThreadPlayVideo(monitor);
            m_threadPlayVideo.start();
        }
    }


    private void initView() {
        setContentView(R.layout.activity_dev);
        openButton = findViewById(R.id.btn_open);
        loginButton = findViewById(R.id.btn_login);
        playButton = findViewById(R.id.btn_play);
        openButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        playButton.setOnClickListener(this);

        tvCallback = findViewById(R.id.tv_callback);
        tvCallback.setMovementMethod(ScrollingMovementMethod.getInstance());
        tvSession = findViewById(R.id.tv_session);
        monitor = findViewById(R.id.monitor_view);
        mScrollView = findViewById(R.id.scrollView);
        //云台控制
        monitor.setOnTouchListener(new View.OnTouchListener() {
            private float startX, startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        float x = event.getX() - startX;
                        float y = event.getY() - startY;
                        int derection = P2PConstants.PTZDirection.PTZ_RIGHT;    //移动方向
                        int motorLevel = 100;                                //水平移动距离
                        int motorVertical = 0;                               //垂直移动距离
                        int motorSpeed = P2PConstants.PTZSpeed.PTZ_SPEED1;      //移动速度
                        String xml;
                        if (Math.abs(x) > 50 || Math.abs(y) > 50) {
                            if (Math.abs(x) > 50 && Math.abs(x) > Math.abs(y)) {
                                if (x > 0)
                                    derection = P2PConstants.PTZDirection.PTZ_RIGHT;
                                else
                                    derection = P2PConstants.PTZDirection.PTZ_LEFT;
                                motorLevel = (int) Math.abs(x) * 2;
                                motorVertical = 0;
                            } else if (Math.abs(y) > 50 && Math.abs(y) > Math.abs(x)) {
                                if (y > 0)
                                    derection = P2PConstants.PTZDirection.PTZ_DOWN;
                                else
                                    derection = P2PConstants.PTZDirection.PTZ_UP;
                                motorLevel = 0;
                                motorVertical = (int) Math.abs(y);
                            }
                            xml = TestXml.getPtzCmdXml(mSessionId, derection, motorSpeed, motorLevel, motorVertical);
                            if (mDev.opened && mDev.logined)
                                controler.devSdkSend(mDev.getDID(), xml);
                            return true;
                        }
                }
                return true;
            }
        });


        Button btn_voice = findViewById(R.id.voice);
        btn_voice.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isRecording = true;
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (isRecording)
                                    checkAudioRecord();
                            }
                        }, 300);
                        break;
                    case MotionEvent.ACTION_UP:
                        isRecording = false;
                        stopAudioRecord();
                        break;
                }
                return true;
            }
        });

    }


    private final static int PERMISSION_RECORD_AUDIO = 103;

    /**
     * 注册权限申请回调
     *
     * @param requestCode  申请码
     * @param permissions  申请的权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_RECORD_AUDIO:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    appendLog("已获得麦克风权限！");
                } else {
                    appendLog("没有获得麦克风权限！");
                    Toast.makeText(this, R.string.permission_mic_tip, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    protected boolean checkPermission(String[] permissions, int requestCode) {
        int permission = ActivityCompat.checkSelfPermission(this,
                permissions[0]);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            appendLog("已获得麦克风权限！");
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
            return false;
        }
        return false;
    }

    private void checkAudioRecord() {
        if (checkPermission(new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSION_RECORD_AUDIO)) {
            cmdStartAudioStart();
        }
    }

    private void cmdStartAudioStart() {
        String xml = TestXml.getStartTalkXml(mSessionId);
        if (mDev.opened && mDev.logined) {
            appendLog("启动对讲...");
            controler.devSdkSend(mDev.getDID(), xml);
        }
    }

    private void cmdStopAudioStart() {
        String xml = TestXml.getStopTalkXml(mSessionId);
        if (mDev.opened && mDev.logined)
            controler.devSdkSend(mDev.getDID(), xml);
    }

    private void startAudioRecord() {
        appendLog("初始化对讲...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                iniAudioRecord();
                startRecord();
            }
        }).start();
    }

    private void stopAudioRecord() {
        if (m_AudioTrack != null)
            m_AudioTrack.setStereoVolume(1.0f, 1.0f);
        appendLog("关闭对讲...");
        audioRecordInit = false;
        try {
            if (audioRecord != null &&
                    audioRecord.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
                audioRecord.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmdStopAudioStart();
    }

    private void iniAudioRecord() {
        if (!audioRecordInit) {
            int bufferSize = 1920;
            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequence, channelInConfig, audioEncoding, bufferSize);
            buffer = new byte[bufferSize];
            audioRecordInit = true;
        }
    }

    private AudioRecord audioRecord;
    private static final int frequence = 8000;//采样率
    private static final int channelInConfig = AudioFormat.CHANNEL_IN_MONO;
    private static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT;//
    private byte[] buffer = null;
    private byte[] bufferSend = new byte[1928];
    private boolean isRecording;

    private void startRecord() {
        if (m_AudioTrack != null)
            m_AudioTrack.setStereoVolume(0.0f, 0.0f);
        if (audioRecordInit) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    appendLog("开始对讲，可以说话了...");
                }
            });
            audioRecord.startRecording();//开始录制
            while (isRecording) {
                //录制内容放置到buffer中，result代表存储长度
                int result = audioRecord.read(buffer, 0, buffer.length);
                // PCM encode to  G711a
//                byte[] encodeData = G711.encoder(buffer, result);
//                if(encodeData!=null && encodeData.length>0)
//                sendData(encodeData, encodeData.length);
                if (result > 0)
                    sendData(buffer, result);
            }
        }

    }

    private void sendData(byte[] encodeData, int len) {
        byte[] b = P2PUtils.intToBytes(len);
        System.arraycopy(b, 0, bufferSend, 0, 4);
        System.arraycopy(encodeData, 0, bufferSend, 4, len);
        controler.devSdkSend(mDev.getDID(), bufferSend, bufferSend.length, P2PConstants.DataType.DATA_A_PCM, P2PConstants.DataChannel.CHANNEL_BYTE);
    }


    private void initTitieView() {
        TextView title_left = findViewById(R.id.title_left_btn);
        TextView title_middle = findViewById(R.id.title_content);
        TextView title_right = findViewById(R.id.title_right_btn);
        title_right.setCompoundDrawablePadding(4);
        title_left.setCompoundDrawablePadding(4);
        Drawable drawableLeft = getResources().getDrawable(R.mipmap.ic_action_back);//ContextCompat.getDrawable(this, R.mipmap.ic_action_back);
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


    @Subscribe
    public void sdkAVFrameCallback(AVFrameCallbackEvents events) {
        if (!m_bRunning) return;
        Log.d("Test==", "   type  " + events.getType());
        if (events.getType() == P2PConstants.DataType.DATA_A_G711) {
            m_fifoAudio.addLast(events, events.getLen());
        } else if (events.getType() == P2PConstants.DataType.DATA_V_H264) {
            Log.d("Test==", "byte put");
            m_fifoVideo.addLast(events, events.getLen());
        }
    }

    private void appendLog(String log) {
        tvCallback.append(DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()) + log + "\n");
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

    @Subscribe
    public void sdkCallback(SdkCallbackEvents events) {
        byte[] data = events.getData();
        if (data != null && tvCallback != null) {
            String s = new String(data);
            //
            int start = s.indexOf("<msg_id>");
            int end = s.indexOf("</msg_id>");
            String msg_id = s.substring(start + 8, end).trim();
            if (P2PMsgDef.g_str_msg_dev_search_lan.equals(msg_id))
                return;
            //
            int result_msg_start = s.indexOf("<result_msg>");
            int result_msg_end = s.indexOf("</result_msg>");
            String result_msg = s.substring(result_msg_start + 12, result_msg_end).trim();
            //
            int result_start = s.indexOf("<result_code>");
            int result_end = s.indexOf("</result_code>");
            int resultCode = -1;
            String result_code = s.substring(result_start + 13, result_end).trim();

            if (!TextUtils.isEmpty(result_code))
                resultCode = Integer.parseInt(result_code);
            switch (msg_id) {
                case P2PMsgDef.g_str_msg_dev_login: {
                    if (resultCode == P2PRespCode.ERROR_MSG_SDKSERVER_IPC_USER_LOGIN_SUCCESS) {
                        appendLog("登陆成功");
                        int session_id_start = s.indexOf("<session_id>");
                        int session_id_end = s.indexOf("</session_id>");
                        mSessionId = s.substring(session_id_start + 12, session_id_end).trim();
                        mDev.logined = true;
                        tvSession.setText(getString(R.string.session_is, mSessionId));
                        tvSession.setVisibility(View.VISIBLE);
                        loginButton.setText(R.string.loginout);
                        keepLive();
                    } else if (resultCode == P2PRespCode.ERROR_MSG_SDKSERVER_IPC_USER_LOGIN_USER_NAME_INEXISTENT) {
                        appendLog("登陆失败：用户名不存在");
                    } else if (resultCode == P2PRespCode.ERROR_MSG_SDKSERVER_IPC_USER_LOGIN_USER_PASSWORD_ERROR) {
                        appendLog("登陆失败：密码错误");
                    }
                    break;
                }
                case P2PMsgDef.g_str_msg_dev_record_play_record: {
                    if (resultCode == P2PRespCode.ERROR_MSG_COMMON_SUCCESS) {
                        int play_recode_id_start = s.indexOf("<play_recode_id>");
                        int play_recode_id_end = s.indexOf("</play_recode_id>");
                        String playRecodeId = s.substring(play_recode_id_start + 16, play_recode_id_end).trim();
                    }
                    break;
                }
                case P2PMsgDef.g_str_msg_dev_start_main_stream:
                    if (resultCode == P2PRespCode.ERROR_MSG_COMMON_SUCCESS) {
                        int live_stream_id_start = s.indexOf("<live_stream_id>");
                        int live_stream_id_end = s.indexOf("</live_stream_id>");
                        liveStreamId = s.substring(live_stream_id_start + 16, live_stream_id_end).trim();
                        playButton.setText(R.string.stop);
                        mDev.isPlaying = true;
                        appendLog("开启视频成功！");
                    } else {
                        appendLog("开启视频失败：" + resultCode + result_msg);
                    }
                    break;
                case P2PMsgDef.g_str_msg_dev_stop_main_stream:
                    if (resultCode == P2PRespCode.ERROR_MSG_COMMON_SUCCESS) {
                        playButton.setText(R.string.play);
                        mDev.isPlaying = false;
                        appendLog("关闭视频成功！");
                    } else {
                        appendLog("关闭视频失败：" + resultCode + result_msg);
                    }
                    break;
                case P2PMsgDef.g_str_msg_dev_start_talk:
                    if (resultCode == P2PRespCode.ERROR_MSG_COMMON_SUCCESS) {
                        startAudioRecord();
                    } else {
                        appendLog("开启对讲失败！");
                    }
                    break;
                case P2PMsgDef.g_str_msg_dev_stop_talk:
                    if (resultCode == P2PRespCode.ERROR_MSG_COMMON_SUCCESS) {
                        appendLog("关闭对讲成功！");
                    } else {
                        appendLog("关闭对讲失败！");
                    }
                    break;
                default: {
                    if (resultCode == P2PRespCode.ERROR_MSG_COMMON_SUCCESS) {
                        appendLog("操作成功！");
                    } else {
                        if (!TextUtils.isEmpty(result_msg)) {
                            appendLog(result_msg);
                        } else {
                            appendLog("操作失败！");
                        }
                    }
                    break;
                }

            }
        }
    }


    private void keepLive() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mDev.logined) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            appendLog("发送心跳包...");
                        }
                    });
                    controler.devSdkSend(mDev.getDID(), TestXml.getKeepAliveXml(mSessionId));
                    try {
                        Thread.sleep(50000);//每个50秒，不能超过1分钟
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }).start();
    }


    @Override
    protected void onDestroy() {
        if (mDev != null && mDev.opened) {
            open();
            mDev.opened = false;
        }
        controler.unInit();
        AppBus.getInstance().unregister(this);
        super.onDestroy();

    }

    @SuppressWarnings("deprecation")
    private class ThreadPlayVideo extends Thread {
        private final SurfaceView surface;
        private boolean mCodecInit;
        private MediaCodec.BufferInfo info;
        private MediaCodec mCodec;
        private MediaFormat mCodecFormat;
        private SurfaceHolder holder;
        private ByteBuffer[] inputBuffers;

        private long localStartTime = 0;
        private long streamStartTime = 0;

        private ThreadPlayVideo(SurfaceView s) {
            this.surface = s;
        }

        @Override
        public void run() {
            try {
                if (mCodec != null) {
                    mCodec.stop();
                    mCodec.release();
                    mCodec = null;
                }
                holder = surface.getHolder();
                initDecoder();
                if (mCodecInit) {
                    info = new MediaCodec.BufferInfo();
                    inputBuffers = mCodec.getInputBuffers();
                    while (m_bRunning) {
                        AVFrameCallbackEvents events = m_fifoVideo.removeHead();
                        if (events == null) {
                            Thread.sleep(30);
                            continue;
                        }
                        if (localStartTime == 0) {
                            localStartTime = System.currentTimeMillis();
                            streamStartTime = events.getFrameHead().ts;
                        }

                        long playTime = events.getFrameHead().ts - streamStartTime;
                        long clockTime = System.currentTimeMillis() - localStartTime;
                        Log.d("Test==", "PTS  " + playTime + "   " + clockTime);
                        if (playTime < clockTime) {
                        }
                        byte[] videoData = events.getData();
                        decode(videoData, 0, videoData.length, clockTime);
                        Thread.sleep(30);
                    }
                    if (mCodec != null) {
                        mCodec.stop();
                        mCodec.release();
                        mCodec = null;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        // Video P2PConstants
        private final static String MIME_TYPE = "video/avc"; // H.264 Advanced Video
        private final static int VIDEO_WIDTH = 640;
        private final static int VIDEO_HEIGHT = 360;

        private void initDecoder() {
            try {
                mCodec = MediaCodec.createDecoderByType(MIME_TYPE);
                mCodecFormat = MediaFormat.createVideoFormat(MIME_TYPE,
                        VIDEO_WIDTH, VIDEO_HEIGHT);
                mCodec.configure(mCodecFormat, holder.getSurface(), null, 0);
                mCodec.start();
                mCodecInit = true;
            } catch (Exception e) {
                mCodecInit = false;
            }
        }

        private void decode(byte[] buf, int offset, int length, long pts) {
            int inputBufferIndex = mCodec.dequeueInputBuffer(10000);
            if (inputBufferIndex >= 0) {
                ByteBuffer inputBuffer = inputBuffers[inputBufferIndex];
                if (inputBuffer != null) {
                    inputBuffer.clear();
                    inputBuffer.put(buf, offset, length);
                    mCodec.queueInputBuffer(inputBufferIndex, 0, length, pts, 0);
                    int outIndex = mCodec.dequeueOutputBuffer(info, 10000);
                    if (outIndex >= 0) {
                        mCodec.releaseOutputBuffer(outIndex, true);
                    }
                }
            }
        }
    }


    @SuppressWarnings("deprecation")
    private class ThreadPlayAudio extends Thread {

        private static final int ADPCM_DECODE_BYTE_SIZE = 640;

        private boolean m_bInitAudio = false;
        private int channelConfig = AudioFormat.CHANNEL_OUT_MONO;//单声道
        private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        private int streamType = AudioManager.STREAM_MUSIC;
        private int sampleRateInHz = 8000;
        private int bufferSizeInBytes = 0;
        private int mode = AudioTrack.MODE_STREAM;

        @Override
        public void run() {
            byte[] audioData;
            m_fifoAudio.removeAll();

            if (!m_bInitAudio) {
                if (initAudioDev()) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        m_AudioTrack.setVolume(1.0f);
                    } else {
                        m_AudioTrack.setStereoVolume(1.0f, 1.0f);
                    }
                    m_AudioTrack.play();
                }
            }
            while (m_bRunning && m_bInitAudio) {
                if (m_fifoAudio.isEmpty()) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                audioData = m_fifoAudio.removeHead().getData();
                if (audioData != null) {
                    myDoAudioData(audioData);
                }
            }
            deinitAudioDev();
        }

        private void myDoAudioData(byte[] bytAudioData) {
            int pcmDataSize = bytAudioData.length;
            int readDataSize = 0;
            int leftOverDataSize = bytAudioData.length;
            for (int i = 0; i < pcmDataSize / ADPCM_DECODE_BYTE_SIZE; i++) {
                int read = leftOverDataSize > ADPCM_DECODE_BYTE_SIZE ? ADPCM_DECODE_BYTE_SIZE : leftOverDataSize;
                byte[] data = G711.decoder(bytAudioData, readDataSize, read);
                m_AudioTrack.write(data, 0, data.length);
                readDataSize += read;
                leftOverDataSize = pcmDataSize - readDataSize;
            }
        }

        private synchronized boolean initAudioDev() {
            if (!m_bInitAudio) {
                bufferSizeInBytes = AudioTrack.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);

                if (bufferSizeInBytes == AudioTrack.ERROR_BAD_VALUE || bufferSizeInBytes == AudioTrack.ERROR)
                    return false;
                try {
                    m_AudioTrack = new AudioTrack(streamType, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes, mode);
                } catch (IllegalArgumentException iae) {
                    iae.printStackTrace();
                    return false;
                }
                m_bInitAudio = true;
                return true;

            } else return false;
        }

        private synchronized void deinitAudioDev() {
            if (m_bInitAudio) {
                if (m_AudioTrack != null) {
                    try {
                        m_AudioTrack.stop();
                        m_AudioTrack.release();
                        m_AudioTrack = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                m_bInitAudio = false;
            }
        }
    }


}

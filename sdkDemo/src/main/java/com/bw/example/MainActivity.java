package com.bw.example;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bw.events.AppBus;
import com.bw.events.SdkCallbackEvents;
import com.bw.p2plibrary.P2PClient.IP2PControler;
import com.bw.p2plibrary.P2PClient.P2PControlerProxy;
import com.bw.p2plibrary.P2PClient.P2PMsgDef;
import com.squareup.otto.Subscribe;


public class MainActivity extends Activity {
    public final static String DATA_NAME = "bw";
    public final static String DATA_TABLE_NAME = "DevList";
    private static final int PERMISSION_EXTERNAL_STORAGE_REQUES_CODE = 100;
    private String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public static Dev mDev;

    private MainActivity activity;

    public static ArrayMap<String, Dev> mList;
    private DevAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.activity = this;
        setContentView(R.layout.activity_main);
        initTitieView();
        SharedPreferences sp = getSharedPreferences(DATA_NAME, MODE_PRIVATE);
        mList = DataUtil.stringToList(sp.getString(DATA_TABLE_NAME, ""));
        ListView dev_list = (ListView) findViewById(R.id.dev_list);
        adapter = new DevAdapter();
        dev_list.setAdapter(adapter);
        dev_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDev = mList.valueAt(position);
                startActivity(new Intent(activity, DevActivity.class));
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, EditActivity.class));
            }
        });

        // 注册消息接收
        AppBus.getInstance().register(this);
        IP2PControler  controler = new P2PControlerProxy();
        controler.init();
        //  启用局域网搜索功能
        controler.searchDevStart(3000,null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppBus.getInstance().unregister(this);
    }

    // 局域网搜索回调
    @Subscribe
    public void sdkCallback(SdkCallbackEvents events) {
        byte[] data = events.getData();
        if (data != null) {
            String s = new String(data);
            //
            int start = s.indexOf("<msg_id>");
            int end = s.indexOf("</msg_id>");
            String msg_id = s.substring(start + 8, end).trim();
            if (P2PMsgDef.g_str_msg_dev_search_lan.equals(msg_id)) {
                int didStart = s.indexOf("p2pid=\"");
                int didEnd = s.indexOf("\"",didStart+7);

                String DID = s.substring(didStart + 7, didEnd).trim();
                if (!mList.containsKey(DID)) {

                    Dev dev = new Dev(DID, "admin", "admin");
                    mList.put(DID, dev);
                    adapter.notifyDataSetChanged();
                }

            }
        }
    }


    private void initTitieView() {
        TextView title_left = (TextView) findViewById(R.id.title_left_btn);
        TextView title_middle = (TextView) findViewById(R.id.title_content);
        TextView title_right = (TextView) findViewById(R.id.title_right_btn);
        title_right.setCompoundDrawablePadding(4);
        title_left.setCompoundDrawablePadding(4);
        title_middle.setText(R.string.app_name);
        title_right.setText(R.string.link);

        title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, LinkActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDev = null;
        if (adapter != null)
            adapter.notifyDataSetChanged();
        verifyStoragePermissions(PERMISSIONS_STORAGE, PERMISSION_EXTERNAL_STORAGE_REQUES_CODE);
    }

    public boolean verifyStoragePermissions(String[] permissions, int requestCode) {
        int permission = ActivityCompat.checkSelfPermission(this, permissions[0]);
        if (permission != PackageManager.PERMISSION_GRANTED && Build.VERSION.SDK_INT >= 23) {
            requestPermissions(permissions,
                    requestCode);
            return false;
        }
        return true;
    }

    private class DevAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.valueAt(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(activity).inflate(R.layout.item_dev_list, parent, false);
            convertView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.removeAt(position);
                    SharedPreferences sp = getSharedPreferences(MainActivity.DATA_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString(MainActivity.DATA_TABLE_NAME, DataUtil.listToString(MainActivity.mList));
                    editor.apply();
                    notifyDataSetChanged();
                }
            });
            convertView.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDev = mList.valueAt(position);
                    startActivity(new Intent(activity, EditActivity.class));
                }
            });
            TextView title = (TextView) convertView.findViewById(R.id.name);
            title.setText(mList.valueAt(position).toString());
            return convertView;
        }
    }
}

package com.bw.example;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/4/19.
 */

public class EditActivity extends Activity {
    private EditText et_did, et_name, et_pwd;
    private Dev mDev;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        initTitieView();
        et_did = (EditText) findViewById(R.id.et_did);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);

        if (MainActivity.mDev != null) {
            this.mDev = MainActivity.mDev;
            et_did.setText(mDev.getDID());
            et_name.setText(mDev.getUserName());
            et_pwd.setText(mDev.getPassword());
        }

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
        title_right.setText(R.string.save);

        title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        title_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getSharedPreferences(MainActivity.DATA_NAME, MODE_PRIVATE);
                String did = et_did.getText().toString();
                if (TextUtils.isEmpty(did))
                    return;
                String name = et_name.getText().toString();
                if (TextUtils.isEmpty(name))
                    return;
                String pwd = et_pwd.getText().toString();
                if (TextUtils.isEmpty(pwd))
                    return;
                if (mDev == null) {
                    mDev = new Dev(did, name, pwd);
                    MainActivity.mList.put(did,mDev);
                }
                else{
                    mDev.setDID(did);
                    mDev.setUserName(name);
                    mDev.setPassword(pwd);
                }
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(MainActivity.DATA_TABLE_NAME, DataUtil.listToString(MainActivity.mList));
                editor.apply();
                finish();
            }
        });
    }
}

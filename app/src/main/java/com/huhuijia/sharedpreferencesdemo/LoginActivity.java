package com.huhuijia.sharedpreferencesdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mUsername;
    private EditText mPassword;
    private CheckBox mChk;
    private Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        restoreInfo();

    }

    private void initView() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mChk = (CheckBox) findViewById(R.id.chk);
        mBtnLogin = (Button) findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (mChk.isChecked()) {
                    String usr = mUsername.getText().toString();
                    String pwd = mPassword.getText().toString();
                    boolean b=true;
                    memInfo(usr, pwd,b);
                } else {
                    SharedPreferences.Editor et = getSharedPreferences("data", 0).edit();
                    et.clear();
                    et.commit();
                }
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void memInfo(String usr, String pwd,boolean b) {
        SharedPreferences.Editor editor = getSharedPreferences("data", 0).edit();
        editor.putString("username", usr);
        editor.putString("password", pwd);
        editor.putBoolean("b",b);
        editor.commit();
    }

    private void restoreInfo() {
        SharedPreferences sp = getSharedPreferences("data", 0);
        mUsername.setText(sp.getString("username", ""));
        mPassword.setText(sp.getString("password", ""));
        mChk.setChecked(sp.getBoolean("b",false));
    }


}

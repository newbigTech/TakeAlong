package com.zingfon.socket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zingfon.socket.util.SerialPortUtil;

public class SPUActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SerialPortUtil spu = SerialPortUtil.getInstance();
        
    }

}

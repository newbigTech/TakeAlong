package com.zingfon.socket;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import android_serialport_api.SerialPort;
import android_serialport_api.SerialPortFinder;

public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     */
    EditText mReception;
    FileOutputStream mOutputStream;
    FileInputStream mInputStream;
    SerialPort sp;
    ReadThread mReadThread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        SerialPortFinder finder = new SerialPortFinder();
        String[] allDevices = finder.getAllDevices();
        Log.d("SerialPort", "allDevices:" + Arrays.toString(allDevices));
        String[] allDevicesPath = finder.getAllDevicesPath();
        Log.d("SerialPort", "allDevicesPath:" + Arrays.toString(allDevicesPath));

        try {
            sp = new SerialPort(new File("/dev/ttyGS2"), 9600, 0);
            mInputStream = (FileInputStream) sp.getInputStream();
            mOutputStream = (FileOutputStream) sp.getOutputStream();
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
            DisplayError(e.getMessage());
        }

        mReception = (EditText) findViewById(R.id.EditTextEmission);

        final Button buttonSetup = (Button) findViewById(R.id.ButtonSent);
        buttonSetup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mReadThread = new ReadThread();
                mReadThread.start();
                Toast.makeText(getApplicationContext(), "connect", Toast.LENGTH_SHORT).show();
            }
        });


        final Button buttonsend = (Button) findViewById(R.id.ButtonSent1);
        buttonsend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mOutputStream == null) return;
                try {
                    mOutputStream.write("send".getBytes());
                    mOutputStream.write('\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "send", Toast.LENGTH_SHORT).show();
            }
        });


        final Button buttonrec = (Button) findViewById(R.id.ButtonRec);
        buttonrec.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int size;

                try {
                    byte[] buffer = new byte[64];
                    if (mInputStream == null) return;
                    size = mInputStream.read(buffer);
                    if (size > 0) {
                        onDataReceived(buffer, size);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void DisplayError(String msg) {
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setTitle("Error");
        b.setMessage(msg);
        b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.this.finish();
            }
        });
        b.show();
    }

    private class ReadThread extends Thread {

        @Override
        public void run() {
            super.run();
            while (!isInterrupted()) {
                int size;
                try {
                    byte[] buffer = new byte[64];
                    if (mInputStream == null) return;
                    size = mInputStream.read(buffer);
                    if (size > 0) {
                        onDataReceived(buffer, size);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                mReception.append("recive");
            }
        }
    }


    private void onDataReceived(final byte[] buffer, final int size) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (mReception != null) {
                    mReception.append(new String(buffer, 0, size));
                }
            }
        });
    }

}

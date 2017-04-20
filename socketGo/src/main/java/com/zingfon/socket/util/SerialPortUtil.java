package com.zingfon.socket.util;

import com.zingfon.socket.android_serialport_api.SerialPort;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 李荣修 on 2016/11/9 9:56
 */
public class SerialPortUtil {
    private static SerialPortUtil portUtil;
    private SerialPort mSerialPort = null;
    private OutputStream mOutputStream;
    private InputStream mInputStream;
    private ReadThread mReadThread;
    //private OnDataReceiveListener mSerialPortListener = null;

//    public interface OnDataReceiveListener {
//        public void onDataReceive(byte[] buffer, int size);
//    }

    public static SerialPortUtil getInstance() {
        if (null == portUtil) {
            portUtil = new SerialPortUtil();
            portUtil.onCreate();
        }
        return portUtil;
    }

    /**
     * 初始化串口信息
     */
    public void onCreate() {
        if (mSerialPort == null) {
            /* Open the serial port */
            String DEV_PATH = "/dev/ttyHSL0";//0是计价器，1是广告灯
            try {
                mSerialPort = new SerialPort(new File(DEV_PATH), 115200, 0);
                mOutputStream = mSerialPort.getOutputStream();
                mInputStream = mSerialPort.getInputStream();
                mReadThread = new ReadThread();
//              isStop = false;
                mReadThread.start();
            } catch (SecurityException | IOException exception) {
//                CommonUtil.showToast(MyApplication.getApplication(), "初始化串口失败" + exception.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public void setOnDataReceiveListener(OnDataReceiveListener dataReceiveListener) {
//        mSerialPortListener = dataReceiveListener;
//    }

    private class ReadThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
                int size;
                try {
                    byte[] buffer = new byte[64];//512
                    if (mInputStream == null) {
                        return;
                    }
                    size = mInputStream.read(buffer);
                    if (size > 0) {// && mSerialPortListener != null
//                        OkLogger.d("SerialPortUtil:" + SocketUtil.byte2HexStr(buffer));
                        onDataReceive(buffer, size);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private void onDataReceive(byte[] buffer, int size) {

    }

    /**
     * 发送指令到串口
     *
     * @param cmd
     * @return
     */
    public boolean sendCmd(String cmd) {
        return sendCmd(cmd.getBytes());
    }

    public boolean sendCmd(byte[] mBuffer) {
        boolean result = true;
        try {
            if (mOutputStream != null) {
                mOutputStream.write(mBuffer);
            } else {
                result = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public void closeSerialPort() {
        if (mReadThread != null) {
            mReadThread.interrupt();
        }
        if (mSerialPort != null) {
            mSerialPort.close();
            mSerialPort = null;
        }
    }
}

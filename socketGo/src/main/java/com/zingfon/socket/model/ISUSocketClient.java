package com.zingfon.socket.model;

import com.zingfon.socket.model.cmd.ISU_CMD;
import com.zingfon.socket.util.SocketUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 李荣修 on 2017/3/13 15:31
 */
public class ISUSocketClient {
    private static String tag = ISUSocketClient.class.getSimpleName();
    private static ISUSocketClient ISUSocketClient;
    private static Socket socketClient;
    private static OutputStream outputStream;
    private static InputStream inputStream;
    private static boolean isConnected = false;
    private static AtomicInteger atomicId = new AtomicInteger();

    private ISUSocketClient() {
        try {
            socketClient = new Socket("183.62.157.52", 30002);
            //设置read()方法的阻塞时间，单位：毫秒，超时将引发SocketTimeoutException
            socketClient.setSoTimeout(15000);
            //关闭Nagle算法，产生数据后立即发送
            socketClient.setTcpNoDelay(true);
            inputStream = socketClient.getInputStream();
            outputStream = socketClient.getOutputStream();
            isConnected = true;
        } catch (IOException e) {
            isConnected = false;
            e.printStackTrace();
        }
    }

    public static ISUSocketClient getInstance() {
        if (ISUSocketClient == null) {
            synchronized (ISUSocketClient.class) {
                if (ISUSocketClient == null) {
                    ISUSocketClient = new ISUSocketClient();
                }
            }
        }
        return ISUSocketClient;
    }

    /**
     * 消息流水号,两个字节
     *
     * @return
     */
    private static int getSerialNumber() {
        if (atomicId == null) {
            atomicId = new AtomicInteger();
        }
        if (atomicId.get() == Short.MAX_VALUE) {
            return atomicId.getAndSet(0);
        } else {
            return atomicId.getAndIncrement();
        }
    }

    public void sendHeartBeat() {
        byte[] heartBeat = buildMessage(ISU_CMD.D_0x0002, null);
        sendBytes(heartBeat);
    }

    public void sendBytes(byte[] bytes) {
        try {
            //TODO 是否需要使用BufferedWriter
            outputStream.write(bytes);
            outputStream.flush();
        } catch (IOException e) {
            //TODO 是否需要？
            isConnected = false;
            e.printStackTrace();
        }
    }

    private void receiveData() {
        while (isConnected) {
            //DataInputStream dis = new DataInputStream(inputStream);
            BufferedInputStream bis = new BufferedInputStream(inputStream);

            byte[] buffer = new byte[1];
            try {
                while (bis.read(buffer) != -1) {
                    //判断是否读到消息末尾
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//    ByteArrayInputStream bais=new ByteArrayInputStream(buffer);
        }
    }

    /**
     * 构造消息，由消息头+消息体+校验码组成，不包括消息前后标识位
     *
     * @param msgId
     * @param data
     * @return
     */
    public static byte[] buildMessage(short msgId, byte[] data) {
        int dataLen = 0;
        if (data != null) {
            dataLen = data.length;
        }
        //消息头，需要做转义处理
        byte[] msgHead = getMessageHead(msgId, dataLen);
        //消息体需要做转义处理
        byte[] msg = new byte[msgHead.length + dataLen + 1];
        //消息头+消息体
        byte[] msg1 = SocketUtil.mergeByteArray(msgHead, data);
        //对消息头+消息体计算校验位
        byte checkCode = SocketUtil.calcCheckBit(msg1);
        //合并消息头、消息体、校验位
        System.arraycopy(msg1, 0, msg, 0, msg1.length);
        System.arraycopy(new byte[]{checkCode}, 0, msg, msg1.length, 1);
        return msg;
    }

    private static byte[] getMessageHead(short msgId, int dataLen) {
        //消息头
        byte[] msgHead = new byte[12];
        int destStartIndex = 0;
        //消息ID
        System.arraycopy(SocketUtil.int2Bytes(msgId), 0, msgHead, destStartIndex, 2);
        destStartIndex += 2;
        //消息体属性
        System.arraycopy(SocketUtil.int2Bytes(dataLen, 2), 0, msgHead, destStartIndex, 2);
        destStartIndex += 2;

        //ISU标识
        System.arraycopy(getIdentify(), 0, msgHead, destStartIndex, 6);
        destStartIndex += 6;

        //消息流水号
        //byte[] serialId = SocketUtil.int2Bytes(getSerialNumber(), 2);
        byte[] serialId = new byte[2];
        serialId[0] = 0x01;
        serialId[1] = 0x13;
        System.arraycopy(serialId, 0, msgHead, destStartIndex, 1);
        return msgHead;
    }

    public static byte[] getIdentify() {
        byte[] identify = new byte[6];
        identify[0] = 0x10;//固定为10
        identify[1] = 0x10;//厂商编号
        identify[2] = 0x00;//设备类型，此处为ISU

        identify[3] = 0x67;
        identify[4] = 0x00;
        identify[5] = 0x31;

//        byte[] serial = BCDUtil.str2Bcd(Build.SERIAL);
//        System.arraycopy(serial, 0, identify, 3, 3);
//        OkLogger.d(tag, "serial: " + SocketUtil.byte2HexStr(serial));
        return identify;
    }

    public static void connect() {
        if (socketClient != null && !socketClient.isConnected()) {
            //socketClient.connect();
        }
    }

    public static void close() {
        if (socketClient != null) {
            if (socketClient.isConnected()) {
                try {
                    socketClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            socketClient = null;
        }
    }
}

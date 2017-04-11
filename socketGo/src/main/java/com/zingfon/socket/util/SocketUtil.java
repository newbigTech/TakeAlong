package com.zingfon.socket.util;

import java.math.BigInteger;

/**
 * Created by 李荣修 on 2016/11/11 10:19
 */
public class SocketUtil {
    /**
     * 使用异或计算校验位
     *
     * @param bytes
     * @return
     */
    public static byte calcCheckBit(byte[] bytes) {
        byte result = bytes[0];
        for (byte b : bytes) {
            result ^= b;
        }
        return result;
    }

    /**
     * 合并两个byte数组到新的数组中
     *
     * @param bytes1
     * @param bytes2
     * @return
     */
    public static byte[] mergeByteArray(byte[] bytes1, byte[] bytes2) {
        if (bytes1 == null && bytes2 == null) {
            return null;
        } else if (bytes1 == null) {
            return bytes2;
        } else if (bytes2 == null) {
            return bytes1;
        } else {
            byte[] result = new byte[bytes1.length + bytes2.length];
            System.arraycopy(bytes1, 0, result, 0, bytes1.length);
            System.arraycopy(bytes2, 0, result, bytes1.length, bytes2.length);
            return result;
        }
    }

    public static String byte2HexStr(byte[] bytes) {
        String temp = "";
        StringBuilder sb = new StringBuilder("");

        for (byte b : bytes) {
            temp = Integer.toHexString(b & 0xFF);
            sb.append((temp.length() == 1) ? "0" + temp : temp);
            sb.append(" ");
        }
        return sb.toString().toUpperCase().trim();
    }

    public static String str2HexStr(String str) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public static byte[] getHexBytes(String message) {
        int len = message.length() / 2;
        char[] chars = message.toCharArray();
        String[] hexStr = new String[len];
        byte[] bytes = new byte[len];

        for (int i = 0, j = 0; j < len; i += 2, j++) {
            hexStr[j] = "" + chars[i] + chars[i + 1];
            bytes[j] = (byte) Integer.parseInt(hexStr[j], 16);
        }
        return bytes;
    }

    public static byte[] getByteArray(String hexString) {
        return new BigInteger(hexString, 16).toByteArray();
    }

    public static byte[] int2Bytes(int intValue, int bytesLength) {
        byte[] bytes = new byte[bytesLength];
        bytes[0] = (byte) intValue;
        return bytes;
    }

    //此方法有误？
    public static byte[] int2Bytes(int s) {
        byte[] shortBuf = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (shortBuf.length - 1 - i) * 8;
            shortBuf[i] = (byte) ((s >>> offset) & 0xff);
        }
        return shortBuf;
    }

    public static byte[] shortToByteArray(short s) {
        byte[] shortBuf = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (shortBuf.length - 1 - i) * 8;
            shortBuf[i] = (byte) ((s >>> offset) & 0xff);
        }
        return shortBuf;
    }
}

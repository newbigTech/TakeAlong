package com.zingfon.socket.protocol;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.protocol
 *  @文件名:   BaseProtocol
 *  @创建者:   cjf
 *  @创建时间:  2017/4/14 17:17
 *  @描述：    TODO 协议类型，版本的概念有没有？，
 */
public abstract class BaseProtocol {

    //长度均以字节（byte）为单位
    public static final int LENGTH_LEN = 4; //记录整条数据长度数值的长度
    private static final int VERSION_LEN = 1; //协议的版本长度
    private static final int TYPE_LEN = 1; //协议的数据类型长度

    /**
     * 获取整条数据长度
     * 单位：字节（byte）
     *
     * @return
     */
    protected int getLength() {
        return LENGTH_LEN + VERSION_LEN + TYPE_LEN;
    }

    /**
     * 拼接发送数据，此处拼接了数据长度, 协议版本, 协议类型，具体内容子类中再拼接
     * 按顺序拼接
     *
     * @return
     */
    public byte[] genContentData() {
        return null;
    }

    /**
     * 解析接收数据，此处解析了协议版本、协议类型和数据长度，具体内容子类中再解析
     *
     * @param data
     * @return
     */
    public int parseContentData(byte[] data) /*throws ProtocolException*/ {
        /*if (false) {
            throw new ProtocolException("input version is error: " + -1);
        }*/
        return 0;
    }

    /**
     * 解析出整条数据长度
     *
     * @param data
     * @return
     */
    protected int parseLength(byte[] data) {
        return 0;
    }

    /**
     * 解析协议类型
     *
     * @return
     */
    public abstract int parseProtocolType(int protocolID);

    /**
     * 解析消息ID
     *
     * @return
     */
    public abstract int parseMessageID(int protocolID);

    @Override
    public String toString() {
        return super.toString();
    }
}

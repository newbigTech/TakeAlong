package com.zingfon.socket.model.cmd;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.model.cmd
 *  @文件名:   IsuCmd
 *  @创建者:   cjf
 *  @创建时间:  2017/4/21 14:11
 *  @描述：    TODO ISU发出的消息体，D_
 *              数据要忽略掉符号
 */
public class IsuCmd {

    /**
     * ISU升级结果报告信息  p21,表18
     */
    public class D_0x0105 {
        public byte deviceType;//设备类型
        public byte manufacturerId;//厂商标识
        public byte bcd_hardwareVersion;//硬件版本号
        public byte[] bcd_softwareVersion = new byte[2];//软件版本号。0：主版本号，1：副版本号
        public byte updateResult;//升级结果。见表18，p21
    }

}

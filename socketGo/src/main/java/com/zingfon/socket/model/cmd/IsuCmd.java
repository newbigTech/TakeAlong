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

    /**
     * 位置基本信息，P22，表19
     */
    public class D_0x0200 {
        //位置基本信息
        public int alarmFlag;//报警标志。见表20，p22
        public int stateFlag;//状态。见表21，p23
        public int latitude;//纬度，0.001'
        public int longitude;//经度，0.001'
        public short speed;//速度，0.1km/h
        public byte bearing;//方向。0-179，每刻度为两度，正北为0，顺时针
        public byte[] bcd_gpsTime = new byte[6];//6位BCD时间。YYMMDDmmss
        public LBSExtra mLBSExtra = null;//根据消息头中的长度字段，确定是否存在位置附加信息项列表。

        //位置附加信息项
        public class LBSExtra {
            public byte extraId;//附加信息ID。1~255
            public byte extraLength;//附加信息长度。
            public Object extraMsg;//附加信息。见表23，p24
        }

    }

    /**
     * 位置跟踪信息汇报
     */
    public class D_0x0202 extends D_0x0200 {

    }

}

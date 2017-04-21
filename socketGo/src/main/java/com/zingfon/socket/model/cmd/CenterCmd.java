package com.zingfon.socket.model.cmd;

import java.util.List;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.model.cmd
 *  @文件名:   CenterCmd
 *  @创建者:   cjf
 *  @创建时间:  2017/4/21 14:22
 *  @描述：    TODO 中心发出的消息体，C_
 *              数据要忽略掉符号
 */
public class CenterCmd {

    /**
     * 设置参数，p16，10.3.5.4
     */
    public class C_0x8103 {
        List<ParamOpt> optList;//参数项列表。若多值参数，则消息中使用多个相同ID的参数项

        //参数项数据格式
        class ParamOpt {
            public short optionId;//参数ID，见表12，p17
            public byte optionLength;//参数长度
            public Object optionValue;//参数值，数据类型与ID对应，见表12，p17
        }
    }

    /**
     * 查询ISU参数  p20,表13
     * 专用应答D_0x0104
     */
    public class C_0x8104 {
        public short[] queryIds;//参数ID
    }

    /**
     * ISU控制  p20,表15
     */
    public class C_0x8105 {
        public byte cmdId;//命令字。见表16，p21
        public Object cmdOpt;//命令参数。命令1见表17，其他无

        public class CmdId_01 {
            public byte deviceType;//设备类型
            public byte manufacturerId;//厂商标识
            public byte bcd_hardwareVersion;//硬件版本号
            public byte[] bcd_softwareVersion = new byte[2];//软件版本号。0：主版本号，1：副版本号
            public String APN;//APN
            public String dialName;//拨号用户名
            public String dialPsw;//拨号密码
            public String upgradeUrl;//升级服务器地址
            public short upgradePort;//升级服务器端口
        }
    }

}

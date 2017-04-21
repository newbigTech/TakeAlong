package com.zingfon.socket.model.cmd;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.model.cmd
 *  @文件名:   IsuComAck
 *  @创建者:   cjf
 *  @创建时间:  2017/4/21 14:20
 *  @描述：    TODO ISU通用应答消息体，D_
 *              数据要忽略掉符号
 */
public class IsuComAck {

    /**
     * ISU通用应答，p16，表8
     */
    public class D_0x0001 {
        public short responseNum;//应答流水号。对应的中心消息的流水号
        public short responseId;//应答ID。对应的中心消息的ID
        public byte responseResult;//结果。0：成功、确认；1：失败；2：消息有误
    }

}

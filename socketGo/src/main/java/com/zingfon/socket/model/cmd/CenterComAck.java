package com.zingfon.socket.model.cmd;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.model.cmd
 *  @文件名:   CenterComAck
 *  @创建者:   cjf
 *  @创建时间:  2017/4/21 14:28
 *  @描述：    TODO 中心通用应答，C_
 *              数据要忽略掉符号
 */
public class CenterComAck {

    /**
     * 中心通用应答  p16,表9
     */
    public class C_0x8001 {
        public short responseNum;//应答流水号。对应的ISU消息的流水号
        public short responseId;//应答ID。对应的ISU消息的ID
        public byte responseResult;//结果。0：成功、确认；1：失败；2：消息有误
    }

}

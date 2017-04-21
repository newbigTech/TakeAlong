package com.zingfon.socket.model.cmd;

import java.util.List;

/*
 *  @项目名：  TakeAlong 
 *  @包名：    com.zingfon.socket.model.cmd
 *  @文件名:   IsuAck
 *  @创建者:   cjf
 *  @创建时间:  2017/4/21 14:16
 *  @描述：    TODO ISU专门应答的消息体，D_
 */
public class IsuAck {

    /**
     * 查询ISU参数应答  p20,表14
     */
    public class D_0x0104 {
        public short responseNum;//应答流水号。对应的查询ISU参数消息的流水号
        List<ParamOpt> optList;//参数项列表

        //参数项数据格式
        class ParamOpt {
            public short optionId;//参数ID，见表12，p17
            public byte optionLength;//参数长度
            public Object optionValue;//参数值，数据类型与ID对应，见表12，p17
        }
    }

}

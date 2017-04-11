package com.zingfon.socket.model.cmd;

/**
 * Created by 李荣修 on 2017/2/28 10:49
 * 指令命名规则：指令发出方（服务器端为C, 智能服务终端为D , 计价器为M(eter) , 服务评价器为S(service)，顶灯为L(ight)）+ 下划线 + 消息ID
 * 如指令重复，则根据接收对象修改为D2M_等形式
 * 本文件为服务器端发出的指令，指令用C_开头
 * 数据类型：UINT8=byte（1字节）、UINT16=short（2字节）、UINT32=int（4字节）、BCD[n]=byte[n]，加前缀bcd_
 */
public class Server_CMD {
    /**
     * 中心通用应答  p16,表9
     */
    public class C_0x8001 {
        public short responseNo;//应答流水号
        public short responseId;//应答ID
        public byte responseResult;//结果
    }

    /**
     * 设置参数，p16，10.3.5.4
     */
    public class C_0x8103 {
        //public UNKOWN paraList;//参数项列表
    }
    /**
     * 查询ISU参数  p20,表13
     */
    public class C_0x8104 {
        public short[] queryId;//参数ID
    }

    /**
     * ISU控制  p20,表15
     */
    public class C_0x8105 {
        public byte cmdId;//命令字
        //public unknown;//命令参数
    }

    /**
     * 位置信息查询  p24，10.3.5.10
     */
    public final int C_0x8201 = 0x8201;

    /**
     * 位置跟踪控制  p24,表25
     */
    public class C_0x8202 {
        public byte attribute;//属性
        public short timeSpaceOrDistanceSpace;//时间间隔或距离间隔
        public int timeContinueOrDistanceContinue;//持续时间或持续距离
    }

    /**
     * 文本信息下发  p25,表27
     */
    public class C_0x8300 {
        public byte mark;//标志
        public String text;//文本信息
    }

    /**
     * 事件设置  p26，表29
     */
    public class C_0x8301 {
        public byte eventCount; //事件个数。0：删除ISU现有事件
        // public unknown; //事件项列表。长度不大于499byte，否则分多条消息下发；见表30（内部类）

//        public List<EventData> eventList = new ArrayList<>(eventCount);
//        /**
//         * p26，表30 事件项组成数据格式
//         */
//        public class EventData {
//            public byte eventId; //事件ID。若ISU已有同ID的事件，则被覆盖
//            public String eventContent; //事件内容。最长为20byte
//        }
    }

    /**
     * 提问下发  p26，表32
     */
    public class C_0x8302 {
        public byte questionTag; //标志。位0:1紧急；位3:1语音合成（TTS）播报；位4:1广告屏显示
        public int questionId; //问题ID。
        public String questionData; //问题。最长100byte，组成见表34
        // public unkown; //候选答案列表。需保证消息体长度不大于500byte；见表34

//        public List<AnswerData> answerList;
//        /**
//         * 候选答案组成  p27，表34 
//         */
//        public class AnswerData {
//            public byte answerId; //答案ID。
//            public String answerContent; //答案内容。最长为20byte
//        }
    }

    /**
     * 电话回拨  p27，表36
     */
    public class C_0x8400 {
        public int callTag; //标志。0：普通通话，1：监听
        public String phoneNumber; //电话号码。最长为20byte
    }

    /**
     * 设置电话本消息数据格式  p27，表37
     */
    public class C_0x8401 {
        public int contactsCount; //联系人总数。本条消息中联系人总数，0：删除ISU所有存储的联系人
        // public unkown; //联系人项。最长为499byte，超过则采用多条消息，见表38

//        public List<ContactsData> contactsList;
//        /**
//         * 电话本联系人项数据格式  p28，表38 
//         */
//        public class ContactsData {
//            public byte phoneBookTag; //标志。1：呼入；2：呼出；3：呼入呼出
//            public String phoneNumber; //电话号码。最长为20byte
//            public String contacts; //联系人。最长为10byte
//        }
    }

    /**
     * 车辆控制  p28，表39
     */
    public class C_0x8500 {
        public byte contrlItem; //控制项。位0：恢复/断开车辆油路；位1：恢复/断开车辆电路；位2：车门解锁/加锁；位3：车辆解除锁定/锁定
        public byte contrlOrder; //控制命令。
    }

    /**
     * 摄像头立即拍摄命令  p29，表43
     */
    public class C_0x8801 {
        public byte cameraId; //摄像头ID。>0
        public short photographOrder; //拍摄命令。0：停止拍摄；0xFFFF表示录像；其他表示拍摄张数
        public short photographTime; //拍照间隔/录像时间。秒（s），0表示按最小间隔拍照或一直录像
        public byte saveTag; //保存标志。1：保存；0：实时上传
        public byte resolutionRatio; //分辨率。0:320x240；1:640x480；2:800x600；其他保留
        public byte quality; //图像/视频质量。1~10,1最好
        public byte brightness; //亮度。0~255
        public byte contrast; //对比度。0~127
        public byte saturability; //饱和度。0~127
        public byte chroma; //色度。
    }

    /**
     * 存储图像检索命令  p30，表44
     */
    public class C_0x8802 {
        public byte cameraId; //摄像头ID。0表示检索所有摄像头
        public byte photographReason; //拍照原因。0：进入重车拍照；1：服务评价拍照；2：报警拍照；3：中心主动拍照
        public byte[] bcd_startTime = new byte[6]; //起始时间。YYMMDDhhmmss
        public byte[] bcd_endTime = new byte[6]; //结束时间。YYMMDDhhmmss
    }

    /**
     * 存储图像/音视频上传命令  p31，表47
     */
    public class C_0x8803 {
        public byte fileType; //类型。0x00：照片；0x01：音频；0x02：视频；其他：RFU
        public int fileId; //文件ID。
        public int dataOffset; //起始位置。本数据包在整个位置图像数据中的偏移量，第一包数据为0
    }

    /**
     * 订单任务下发命令  p31，表48
     */
    public class C_0x8B00 {
        public int businessId; //业务ID。
        public byte businessType; //业务类型。0：即时召车；1：预约召车；2：车辆指派
        public byte[] bcd_requestTime = new byte[6]; //要车时间。YYMMDDhhmmss
        public String businessDescribe; //业务描述。对乘客要车大概地点的描述
    }

    /**
     * 驾驶员抢答结果命令  p32，表50
     */
    public class C_0x8B01 {
        public int businessId; //业务ID。根据消息体长度若无后面字段表示未中标
        public byte businessType; //业务类型。0：即时召车；1：预约召车；2：车辆指派
        public byte[] bcd_usedTime = new byte[6]; //用车时间。YYMMDDhhmmss，全零表示不启用
        public int passengerLongitude; //乘客位置经度。0.0001'，填充时间为零表示不启用
        public int passengerLatitude; //乘客位置纬度。0.0001'，填充时间为零表示不启用
        public int destinationLongitude; //目的地位置经度。0.0001'，填充时间为零表示不启用
        public int destinationLatitude; //目的地位置纬度。0.0001'，填充时间为零表示不启用
        public byte[] bcd_serviceFee = new byte[2]; //电召服务费。格式为XXX-X，全0表示无服务费
        public String passengerPhone; //乘客电话号码。
        public String businessDescribe; //业务描述。对乘客要车详细地点的描述
    }

    /**
     * 中心取消订单命令  p32，表53
     */
    public class C_0x8B09 {
        public int businessId; //业务ID。
    }

    /**
     * 外围设备指令下行透传命令  p35，表57
     */
    public class C_0x8B10 {
        public byte typeId; //TypeID。见表5
        public short dataType; //DataType。位0~2：压缩算法描述：000：数据无压缩；001：gz压缩；其他RFU；位3：1：密文；0：明文；4~15预留
        public byte[] dataPackage;  //数据包。采用加密模式时，不超过384byte；采用非加密时，不超过512byte。
                                    //数据内容为通信协议体（命令字2byte+数据区）的明文或密文，ISU负责协议的组包
    }

    /**
     * 音频检索  p35，表60
     */
    public class C_0x8805 {
        public byte recordReason; //录音原因。0：正常录音；1：乘客投诉；2：报警录音
        public byte[] startTime = new byte[6]; //起始时间。YYMMDDhhmmss
        public byte[] endTime = new byte[6]; //结束时间。YYMMDDhhmmss
    }

    /**
     * 中心确认报警  p37，10.3.5.43
     */
    public final int C_0x8B0A = 0x8B0A; //无消息体，ISU回复通用应答

    /**
     * 中心解除警报  p37，10.3.5.44
     */
    public final int C_0x8B0B = 0x8B0B; //无消息体，ISU回复通用应答

    /**
     * 中心巡检设备  p37，表63
     */
    public class C_0x8B11 {
        public byte[] deviceId; //巡检设备类型代码。数组大小n根据巡检设备的数量而定：
                                // n=0时，表示对所有设备巡检；n≠0时，表示对指定的一个或多个设备进行巡检
    }


}

package com.zingfon.socket.model.cmd;

/**
 * Created by 李荣修 on 2017/2/28 10:51
 * 指令命名规则：指令发出方（服务器端为C, 智能服务终端为D, 计价器为M(eter) , 服务评价器为S(service)，顶灯为L(ight)）+ 下划线 + 消息ID
 * 如指令重复，则根据接收对象修改为D2M_等形式
 * 本文件为智能服务终端发出的指令，指令用D_开头
 * 数据类型：UINT8=byte（1字节）、UINT16=short（2字节）、UINT32=int（4字节）、BCD[n]=byte[n]加前缀bcd_
 */
public class ISU_CMD {
    

    /**
     * 运价参数查询指令  p45,10.4.2.2
     */
    public final int D_0x0004 = 0x0004;
    /**
     * 智能顶灯复位指令  p54,10.4.3.2（与计价器校时指令重复  p53,表105）
     */
    public final int D2M_0x0001 = 0x0001;
    /**
     * 智能顶灯取消防伪密标显示指令   p57,10.4.3.8
     */
    public final int D_0x0014 = 0x0014;

    

    

    

    /**
     * 位置汇报数据补传  p25,表26
     */
    public class D_0x0203 {
        public byte[] location;//位置信息块
    }

    /**
     * 事件报告  p26，表31
     */
    public class D_0x0301 {
        public byte eventId; //事件ID。
    }

    /**
     * 提问应答  p27，表35
     */
    public class D_0x0302 {
        public int questionId; //问题ID。
        public byte answerId; //答案ID。
    }

    /**
     * 车辆控制应答  p28，表41
     */
    public class D_0x0500 {
        public short responseId; //应答流水号。对应的车辆控制消息的流水号
        public IsuCmd.D_0x0200 responseLocation; //位置信息汇报（0x0200）消息体。根据对应状态位判断控制成功与否
    }

    /**
     * 摄像头图像上传  p29，表42
     */
    public class D_0x0800 {
        public short uploadReason; //上传原因。该命令如果是查询上传的后续，该字段填充0；如果是立即拍照命令的后续，则填充立即拍照命令的命令流水号
        public int picture; //图像。>0
        public byte cameraId; //摄像头ID。在此默认为0x00
        public int pictureSize; //位置图像数据大小。
        public int addressOffset; //起始地址。本包数据在整个位置图像数据中心的偏移量，第一包数据为0
        // public unkown; //位置图像数据包。ISU根据自身硬件性能确定数据包的大小；后台管理系统应能自适应，在网络条件不好的情况下每个数据包不能超过512byte
    }

    /**
     * 存储图像应答  p30，表45
     */
    public class D_0x0802 {
        public short responseId; //应答流水号。对应的车辆控制消息的流水号
        public int totalSize; //检索总项数据包大小。满足检索条件的图像/视频总项包数据大小，其值为总项数x4
        public int sizeOffset; //当前检索项在总项数据中的偏移量。本数据在整个图像/视频总项中的偏移量。第一包数据为0
        public int[] inspectItem; //检索项。ISU根据自身硬件性能确定数据包的大小；后台管理系统应能自适应，在网络条件不好的情况下每个数据包不能超过512byte，数据格式见表46
    }

    /**
     * 驾驶员抢答命令 p31，表49
     */
    public class D_0x0B01 {
        public int businessId; //业务ID。对应简明业务下发（0x8B00）消息中的业务ID
    }

    /**
     * 驾驶员电召任务完成确认命令  p32，表51
     */
    public class D_0x0B07 {
        public int businessId; //业务ID。对应简明业务下发（0x8B00）消息中的业务ID
    }

    /**
     * 驾驶员取消订单  p32，表52
     */
    public class D_0x0B08 {
        public int businessId; //业务ID。
        public byte cancelReason; //取消原因。0：事故；1：路堵；2：其他
    }

    /**
     * 上班签到信息上传命令  p33，表54
     */
    public class D_0x0B03 {
        public IsuCmd.D_0x0200 locationData; //位置基本信息。详见0x0200交易
        public byte[] businessLicense = new byte[16]; //企业经营许可证。ASCII字符，长度不足16byte，右边补0x00
        public byte[] driverLicense = new byte[19]; //驾驶员从业资格证。ASCII字符，长度不足19byte，右边补0x00
        public byte[] plateNumber = new byte[6]; //车牌号。车牌号，ASCII字符，不包含汉子
        public byte[] bootTime = new byte[6]; //开机时间。YYMMDDhhmmss
        public byte[] expand; //扩展属性。可根据实际管理需要进行扩展，当有扩展需求时，该项才有内容
    }

    /**
     * 下班签退信息上传命令  p33，表55
     */
    public class D_0x0B04 {
        public IsuCmd.D_0x0200 locationData; //位置基本信息。详见0x0200交易
        public byte[] businessLicense = new byte[16]; //企业经营许可证。ASCII字符，长度不足16byte，右边补0x00
        public byte[] driverLicense = new byte[19]; //驾驶员从业资格证。ASCII字符，长度不足19byte，右边补0x00
        public byte[] plateNumber = new byte[6]; //车牌号。车牌号，ASCII字符，不包含汉子
        public byte[] bcd_taximeterK = new byte[2]; //计价器K值。格式为XXXX，最大9999
        public byte[] bcd_dutyBootTime = new byte[6]; //当班开机时间。YYMMDDhhmmss
        public byte[] bcd_dutyOffTime = new byte[6]; //当班关机时间。YYMMDDhhmmss
        public byte[] bcd_dutyMileage = new byte[3]; //当班里程。格式为XXXXX.X，单位为千米（km）
        public byte[] bcd_serviceMileage = new byte[3]; //当班营运里程。格式为XXXXX.X，单位为千米（km）
        public byte[] bcd_carNumber = new byte[2]; //车次。格式为XXXX，最大9999
        public byte[] bcd_timingTime = new byte[3]; //计时时间。格式为hhmmss
        public byte[] bcd_totalPrice = new byte[3]; //总计金额。格式为XXXXX.X，单位为元
        public byte[] bcd_cardPrice = new byte[3]; //卡收金额。格式为XXXXX.X，单位为元
        public byte[] bcd_cardNumber = new byte[2]; //卡次。格式为XXXX，最大9999
        public byte[] bcd_restMileage = new byte[2]; //班间里程。格式为XXX.X（上一班签退到本班签到距离），单位为千米（km）
        public byte[] bcd_totalMileage = new byte[4]; //总计里程。格式为XXXXXXX.X（计价器安装后累计的里程），单位为千米（km）
        public byte[] bcd_totalServiceMileage = new byte[4]; //总营运里程。格式为XXXXXXX.X（计价器安装后累计的里程），单位为千米（km）
        public byte[] bcd_unitPrice = new byte[2]; //单价。格式为XX.XX，单位为元
        public int serviceTimes; //总运营次数。高位在前，低位在后
        public byte signOutWay; //签退方式。0x00：正常签退；0x01：强制签退
        public byte[] expand; //扩展属性。可根据实际管理需要进行扩展，当有扩展需求时，该项才有内容
    }

    /**
     * 运营数据上传命令  p34，表56
     */
    public class D_0x0B05 {
        public IsuCmd.D_0x0200 toHeavyLocation; //空转重时车位置信息。见0x0200消息体数据格式
        public IsuCmd.D_0x0200 toLightLocation; //重转空时车位置基本信息。见0x0200消息体数据格式
        public int transportId; //营运ID。开始营运时的时间戳，按10.2.2营运ID的生成规则生成
        public int commentId; //评价ID。评价时的时间戳，按10.2.2营运ID的生成规则生成。若没有评价，则以0x00填充
        public byte commentOption; //评价选项。0x00：没有做出评价；0x01：满意；0x02：一般；0x03：不满意；0x04：投诉
        public short commentExpand; //评价选项扩展。保留，默认0x0000
        public int callOrderId; //电召订单ID。0：正常营运数据，非0标识电召营运数据
        // public Unknown 计价器营运数据。内容严格按照“ISU与计价器通信协议”单次营运结束后营运数据发送指令，计价器发往ISU的数据区
    }

    /**
     * 存储音频检索  p36，表61
     */
    public class D_0x0805 {
        public short responseId; //应答流水号。对应的车辆控制消息的流水号
        public int totalSize; //检索总项数据包大小。满足检索条件的音频总项包数据长度其值为总项数x4
        public int sizeOffset; //当前检索项在总项数据中的偏移量。本数据在整个图像/视频总项中的偏移量。第一包数据为0
        public int[] inspectItem; //检索项。ISU根据自身硬件性能确定数据包的大小；后台管理系统应能自适应，在网络条件不好的情况下每个数据包不能超过512byte
    }

    /**
     * 音视频上传命令  p36，表62
     */
    public class D_0x0806 {
        public short uploadId; //上传命令流水号。
        public int videoId; //音视频ID。>0
        public int dataSize; //位置/音视频数据大小。
        public int addressOffset; //起始地址。本包数据在整个位置图像数据中心的偏移量，第一包数据为0
        // public Unknown 位置/音视频图像数据包。ISU根据自身硬件性能确定数据包的大小；后台管理系统应能自适应，在网络条件不好的情况下每个数据包不能超过512byte
    }

    /**
     * 设备巡检应答  p37，表64
     */
    public class D_0x0B11 {
        // public unkown; //应答数据。TLV嵌套形式[……]（支持同时对多种设备的巡检，当对多设备同时进行巡检时数据格式为多组TLV数据）。TLV见原表备注及表65
    }

    /**
     * 计价器状态查询指令  p45,表81
     */
    public class D_0x0000 {
        public byte[] bcd_isuTime = new byte[7];//ISU当前时间  6位BCD时间
    }

    /**
     * 运价参数设置指令  p46,表84
     */
    public class D_0x0005 {
        public byte[] parameterSetting;//参数设置
    }

    /**
     * 单程运营开始通知指令应答  p47,表87
     */
    public class D_0x00E7 {
        public byte result;//操作结果
    }

    /**
     * 单程运营结束后营运数据发送指令应答数据区域  p48,表89
     */
    public class D_0x00E8 {
        public byte result;//操作结果
    }

    /**
     * 计价器运营数据补传指令应答数据区域  p48,表91
     */
    public class D_0x00F2 {
        public byte result;//操作结果
    }

    /**
     * 计价器开机指令应答数据区域  p49,表93
     */
    public class D_0x00E0 {
        public byte[] carNumber = new byte[6];//车牌号
        public byte[] businessLicenses = new byte[16];//经营许可证号
        public byte[] driverLicenses = new byte[19];//驾驶员从业资格证
        public byte[] bcd_swipingCardTime = new byte[6];//刷卡时间
        public short isuState;//ISU状态
        public byte[] bcd_timeLimit = new byte[5];//时间限制
        public byte[] bcd_timesLimit = new byte[2];//次数限制
        public byte result;//操作结果
    }

    /**
     * 计价器关机指令应答数据区域  p50,表96
     */
    public class D_0x00E3 {
        public byte[] carNumber = new byte[6];//车牌号
        public byte[] businessLicenses = new byte[16];//经营许可证号
        public byte[] driverLicenses = new byte[19];//驾驶员从业资格证
        public byte[] bcd_swipingCardTime = new byte[6];//刷卡时间
        public short isuState;//ISU状态
        public byte[] bcd_timeLimit = new byte[5];//时间限制
        public byte[] bcd_timesLimit = new byte[2];//次数限制
        public byte result;//操作结果
    }

    /**
     * 计价器关机成功应答数据区域  p51,表98
     */
    public class D_0x00E4 {
        public byte result;//操作结果
    }

    /**
     * 计价器心跳指令应答数据区域  p52,表102
     */
    public class D_0x00E9 {
        public short isuState;//ISU状态
        public byte[] bcd_timeLimit = new byte[5];//时间限制
        public byte[] bcd_timesLimit = new byte[2];//次数限制
        public short rfu;//RFU
    }

    /**
     * 运营记录查询指令  p52,表103
     */
    public class D_0x0006 {
        public int operateCarTimes;//运营车次
    }

    /**
     * 计价器校时指令  p53,表105
     */
    public class D2M_0x0001 {
        public byte[] bcd_currentTime = new byte[7];//ISU当前时间
    }

    /**
     * 计价器固件升级指令  p53,表107
     */
    public class D_0x00FF {
        public byte[] manufacturerId;//厂商标识
        public byte[] bcd_hardwareVersion;//硬件版本号
    }

    /**
     * 智能顶灯状态查询指令  p54,表109 （与计价器状态查询指令重复  p45,表81）
     */
    public class D2L_0x0000 {
        public byte[] bcd_currentTime = new byte[7];//ISU当前时间
    }

    /**
     * 智能顶灯状态查询指令应答数据区域  p54,表110
     */
    public class D2L_0x0000_back {
        public byte[] bcd_deviceId = new byte[5];//设备编号
        public byte[] bcd_hardwareVersion;//设备硬件版本号
        public byte[] bcd_softwareMainVersion;//软件主版本号
        public byte[] bcd_softwareSecondVersion;//软件次版本号
        public byte deviceState;//设备状态
        public byte showState;//显示状态
        public byte isNightMode;//是否进入夜间模式
        public byte[] rfu = new byte[5];
    }

    /**
     * 智能顶灯复位指令应答数据区域  p54,表111
     */
    public class D2L_0x0001_back {
        public byte result;//操作结果
    }

    /**
     * 智能顶灯通讯波特率设置指令 p55,表112
     */
    public class D_0x0003 {
        public byte baudRate;//波特率
    }

    /**
     * 智能顶灯通讯波特率设置指令应答数据区域   p55,表113
     */
    public class D_0x0003_back {
        public byte result;//操作结果
    }

    /**
     * 智能顶灯固件升级指令  p55,表114(与计价器固件升级指令重复  p53,表107)
     */
    public class D2L_0x00FF {
        public byte[] manufacturerId;//厂商标识
        public byte[] bcd_hardwareVersion;//硬件版本号
        public byte[] bcd_softwareMainVersion;//软件主版本号
        public byte[] bcd_softwareSecondVersion;//软件次版本号
    }

    /**
     * 智能顶灯固件升级指令应答数据区域   p56,表115
     */
    public class D2L_0x00FF_back {
        public byte result;//操作结果
    }

    /**
     * 智能顶灯运营状态设置指令   p56,表116
     */
    public class D_0x0010 {
        public byte[] showState;//显示状态
    }

    /**
     * 智能顶灯运营状态设置指令应答数据区域   p56,表117
     */
    public class D_0x0010_back {
        public byte result;//操作结果
    }

    /**
     * 智能顶灯星级状态设置指令   p56,表118
     */
    public class D_0x0011 {
        public byte manufacturerId;//厂商标识
    }

    /**
     * 智能顶灯星级状态设置指令应答数据区域   p57,表119
     */
    public class D_0x0011_back {
        public byte result;//操作结果
    }

    /**
     * 智能顶灯防伪密标显示指令   p57,表120
     */
    public class D_0x0013 {
        public byte manufacturerId;//厂商标识
    }

    /**
     * 智能顶灯防伪密标显示指令应答数据区域   p57,表121
     */
    public class D_0x0013_back {
        public byte result;//操作结果
    }

    /**
     * 智能顶灯取消防伪密标显示指令应答数据区域   p58,表122
     */
    public class D_0x0014_back {
        public byte result;//操作结果
    }

    /**
     * 智能顶灯夜间工作模式设置指令   p58,表123
     */
    public class D_0x0012 {
        public byte switchState;//开启或关闭
    }

    /**
     * 智能顶灯夜间工作模式设置指令应答数据区域   p58,表124
     */
    public class D_0x0012_back {
        public byte result;//操作结果
    }

    /**
     * 智能顶灯夜间工作模式参数设置指令   p58,表125
     */
    public class D_0x0020 {
        public byte[] bcd_switchTime = new byte[4];//开启或关闭时间
    }

    /**
     * 智能顶灯夜间工作模式参数设置指令应答数据区域   p59,表126
     */
    public class D_0x0020_back {
        public byte result;//操作结果
    }
}

package com.zingfon.socket.model.cmd;

/**
 * Created by 张达枝 on 2017/3/01 17:41
 * 指令命名规则：指令发出方（服务器端为C, 智能服务终端为D , 计价器为M(eter) , 服务评价器为S(service)，顶灯为L(ight)）+ 下划线 + 消息ID
 * 如指令重复，则根据接收对象修改为D2M_等形式
 * 本文件为计价器发出的指令，指令用M_开头
 * 数据类型：UINT8=byte（1字节）、UINT16=short（2字节）、UINT32=int（4字节）、BCD[n]=byte[n]，加前缀bcd_
 */
public class Meter_CMD {

    /**
     * 计价器状态查询指令应答数据区域  p45,表82
     */
    public class M_0x0000 {
        public byte[] bcd_deviceId = new byte[5];//设备编号
        public byte[] bcd_hardwareVersion;//设备硬件版本号
        public byte[] bcd_softwareMainVersion;//软件主版本号
        public byte[] bcd_softwareSecondVersion;//软件次版本号
        public byte deviceState;//设备状态
        public byte workState;//计价器工作状态
        public byte[] carNumber = new byte[6];//车牌号
        public byte[] businessLicenses = new byte[16];//经营许可证号
        public byte[] driverLicenses = new byte[19];//驾驶员从业资格证
        public int operatingNumber;//总运营次数
    }

    /**
     * 运价参数查询指令应答数据区域  p45,表83
     */
    public class M_0x0004 {
        public byte[] bcd_parameterStartTime = new byte[5];//参数启用时间
        public byte[] bcd_dayShuttlePrice = new byte[2];//白天往返单价
        public byte[] bcd_nightShuttlePrice = new byte[2];//夜间往返单价
        public byte[] bcd_dayPrice = new byte[2];//白天单程单价
        public byte[] bcd_nightPrice = new byte[2];//夜间单程单价
        public byte[] bcd_daySecondEmptyPrice = new byte[2];//白天二次空贴单价
        public byte[] bcd_nightSecondEmptyPrice = new byte[2];//夜间二次空贴单价
        public byte[] bcd_dayStartingCost = new byte[2];//白天起步价
        public byte[] bcd_nightStartingCost = new byte[2];//夜间起步价
        public byte[] bcd_continueMileage = new byte[2];//续程里程数
        public byte[] bcd_startMileage = new byte[2];//启程公里
        public byte[] bcd_singleMileage = new byte[2];//单程公里
        public byte[] bcd_secondEmptyMileage = new byte[2];//二次空贴公里
        public byte[] bcd_dayWaitPrice = new byte[2];//白天等后时间单价
        public byte[] bcd_nightWaitPrice = new byte[2];//夜间等后时间单价
        public byte[] bcd_freeWaitTime = new byte[2];//免费等候时间
        public byte[] bcd_raisePriceTime = new byte[2];//加价时间
        public byte[] bcd_nightBeginTime = new byte[2];//夜间开始时间
        public byte[] bcd_nightEndTime = new byte[2];//夜间结束时间
        public byte[] bcd_RFU = new byte[22];//系统预留
        public byte[] bcd_custom = new byte[64];//由厂商自定义拓展
    }

    /**
     * 运价参数设置指令应答数据区域  p46,表85
     */
    public class M_0x0005 {
        public byte[] result;//操作结果
        public byte[] startTime;//启用时间
    }

    /**
     * 单程运营开始通知指令  p47,表86
     */
    public class M_0x00E7 {
        public byte[] bcd_startHeavyTime = new byte[7];//进入重车时间
    }

    /**
     * 单程运营结束后营运数据发送指令  p47,表88
     */
    public class M_0x00E8 {
        public byte[] carNumber = new byte[6];//车牌号
        public byte[] businessLicenses = new byte[16];//经营许可证号
        public byte[] driverLicenses = new byte[19];//驾驶员从业资格证
        public byte[] bcd_getOnCarTime = new byte[5];//上车时间
        public byte[] bcd_getOffCarTime = new byte[2];//下车时间
        public byte[] bcd_mileage = new byte[3];//计程公里
        public byte[] bcd_emptyMileage = new byte[2];//空使里程
        public byte[] bcd_additionalCharge = new byte[3];//附加费
        public byte[] bcd_waitTime = new byte[2];//等待计时时间
        public byte[] bcd_money = new byte[3];//交易金额
        public int currentCarTimes;//当前车次
        public byte payType;//交易类型
        public byte[] oneCard;//一卡通交易数据
    }

    /**
     * 计价器运营数据补传指令  p48,表90
     */
    public class M_0x00F2 {
        public byte[] carNumber = new byte[6];//车牌号
        public byte[] businessLicenses = new byte[16];//经营许可证号
        public byte[] driverLicenses = new byte[19];//驾驶员从业资格证
        public byte[] bcd_getOnCarTime = new byte[5];//上车时间
        public byte[] bcd_getOffCarTime = new byte[2];//下车时间
        public byte[] bcd_mileage = new byte[3];//计程公里
        public byte[] bcd_emptyMileage = new byte[2];//空使里程
        public byte[] bcd_additionalCharge = new byte[3];//附加费
        public byte[] bcd_waitTime = new byte[2];//等待计时时间
        public byte[] bcd_money = new byte[3];//交易金额
        public int currentCarTimes;//当前车次
        public byte payType;//交易类型
        public byte[] oneCard;//一卡通交易数据
    }

    /**
     * 计价器开机指令  p48,表92
     */
    public class M_0x00E0 {
        public byte openState;//开机标识

    }

    /**
     * 计价器开机成功数据区域  p48,表94
     */
    public class M_0x00E1 {
        public byte[] carNumber = new byte[6];//车牌号
        public byte[] businessLicenses = new byte[16];//经营许可证号
        public byte[] driverLicenses = new byte[19];//驾驶员从业资格证
        public byte[] bcd_openTime = new byte[6];//计价器开机时间
        public int operateTimes;//总运营次数
        public byte result;//操作结果

    }

    /**
     * 计价器关机指令  p49,表95
     */
    public class M_0x00E3 {
        public byte closeState;//关机标识

    }

    /**
     * 计价器关机成功数据区域  p50,表97
     */
    public class M_0x00E4 {
        public byte[] carNumber = new byte[6];//车牌号
        public byte[] businessLicenses = new byte[16];//经营许可证号
        public byte[] driverLicenses = new byte[19];//驾驶员从业资格证
        public byte[] bcd_meterKValue = new byte[2];//计价器K值
        public byte[] bcd_openTime = new byte[6];//当班开机时间
        public byte[] bcd_closeTime = new byte[6];//当班关机时间
        public byte[] bcd_onDutyMileage = new byte[3];//当班里程
        public byte[] bcd_onDutyOperateMileage = new byte[3];//当班运营里程
        public byte[] bcd_carTimes = new byte[2];//车次
        public byte[] bcd_calcTimes = new byte[3];//计时时间
        public byte[] bcd_sumMoney = new byte[3];//总计金额
        public byte[] bcd_cardMoney = new byte[3];//卡收金额
        public byte[] bcd_cardTimes = new byte[2];//卡次
        public byte[] bcd_dutySpaceMileage = new byte[2];//班间里程，上班结束到本班开始之间的里程
        public byte[] bcd_sumMileage = new byte[4];//总计里程
        public byte[] bcd_sumOperateMileage = new byte[4];//总运营里程
        public byte[] bcd_price = new byte[2];//单价
        public int bcd_sumOperateTimes;//总运营次数
    }

    /**
     * 计价器关机当班运营数据汇总补传指令  p51,表99
     */
    public class M_0x00F1 {
        public byte[] businessLicenses = new byte[16];//经营许可证号，长度不足16byte，右补0x00
        public byte[] driverLicenses = new byte[19];//驾驶员从业资格证，长度不足19byte，右补0x00
        public byte[] carNumber = new byte[6];//车牌号
        public byte[] bcd_meterKValue = new byte[2];//计价器K值
        public byte[] bcd_openTime = new byte[6];//当班开机时间
        public byte[] bcd_closeTime = new byte[6];//当班关机时间
        public byte[] bcd_onDutyMileage = new byte[3];//当班里程
        public byte[] bcd_onDutyOperateMileage = new byte[3];//当班运营里程
        public byte[] bcd_carTimes = new byte[2];//车次
        public byte[] bcd_calcTimes = new byte[3];//计时时间
        public byte[] bcd_sumMoney = new byte[3];//总计金额
        public byte[] bcd_cardMoney = new byte[3];//卡收金额
        public byte[] bcd_cardTimes = new byte[2];//卡次
        public byte[] bcd_dutySpaceMileage = new byte[2];//班间里程，上班结束到本班开始之间的里程
        public byte[] bcd_sumMileage = new byte[4];//总计里程
        public byte[] bcd_sumOperateMileage = new byte[4];//总运营里程
        public byte[] bcd_price = new byte[2];//单价
        public int bcd_sumOperateTimes;//总运营次数
    }

    /**
     * 计价器关机当班运营数据汇总补传指令应答数据区域  p51,表100
     */
    public class M_0x00F1_back {
        public byte result;//操作结果
    }

    /**
     * 计价器心跳指令  p51,表101
     */
    public class M_0x00E9 {
        public byte meterState;//计价器当前状态
        public byte[] businessLicenses = new byte[16];//经营许可证号  数据类型ASCII[16]
        public byte[] driverLicenses = new byte[19];//驾驶员从业资格证  数据类型ASCII[16]

    }

    /**
     * 运营记录查询指令应答数据区域  p52,表104
     */
    public class M_0x0006 {
        public byte[] result;//操作结果
    }

    /**
     * 计价器校时指令应答数据区域  p53,表106
     */
    public class M_0x0001 {
        public byte result;//操作结果
    }

    /**
     * 计价器固件升级指令应答数据区域  p53,表108
     */
    public class M_0x00FF {
        public byte result;//操作结果
    }
}

package com.gongsibao.entity.acount;

public enum AccountWxMsg {
    WORK_PROCESS_CHANGE("办理进度提醒OPENTM415261101", "pi8F4rdmMScNCvns2ZlW_D-35yeqH5KeNR8LBkL1qEQ"),
    BUY_SUCCESS("订单支付成功TM00015", "vPLuZxpnOtDicrzQI8fj2v2Y-2t-caV3b-BIz7A-CmI"),
    ORDER_SUCCESS("中台下单成功OPENTM407170106", "PO465wpUOS-PJpglWPzagdVAu0x85Txicgx4RMfG7aU"),
    ORDER_STATE_CHANGE("订单状态更新TM00017", "GDr6CbXaPxy-oDpXaJGZgvxeOk6v09uvoe6qJ1Up6pM");
    // 成员变量
    private String name;
    private String empId;

    // 构造方法
    private AccountWxMsg(String name, String empId) {
        this.name = name;
        this.empId = empId;
    }

    // 普通方法
    public static String getName(String empId) {
        for (AccountWxMsg c : AccountWxMsg.values()) {
            if (c.getEmpId() == empId) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
}

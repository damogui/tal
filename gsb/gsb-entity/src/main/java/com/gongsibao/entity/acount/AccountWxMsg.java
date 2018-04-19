package com.gongsibao.entity.acount;

public enum AccountWxMsg {
    WORK_PROCESS_CHANGE("办理进度提醒", "OPENTM415261101"), BUY_SUCCESS("订单支付成功", "TM00015"), ORDER_STATE_CHANGE("订单状态更新", "TM00017");
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

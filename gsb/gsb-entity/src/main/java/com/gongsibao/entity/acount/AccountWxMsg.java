package com.gongsibao.entity.acount;

public enum AccountWxMsg {
    WORK_PROCESS_CHANGE("办理进度提醒", "kIAcKYx1X8nTEm6Nnxy9I5pW_4v5oVui0nYWsJW3BVw"), BUY_SUCCESS("订单支付成功", "yyqIjsNihLrrAM4Tf0q4Bid8J38FpkwOd4okyYxXBJo"), ORDER_STATE_CHANGE("订单状态更新", "_UfH-wArYr9Rsv8Kd9_tyjNXWiFIgxplia93auuO41w");
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

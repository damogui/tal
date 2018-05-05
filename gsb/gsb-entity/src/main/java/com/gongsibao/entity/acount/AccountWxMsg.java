package com.gongsibao.entity.acount;

public enum AccountWxMsg {
    WORK_PROCESS_CHANGE("办理进度提醒OPENTM415261101", "4YVwtOH6hE4p9JmmpWVJadPY0Ld_2igrsd02mTqpPGU"),
    BUY_SUCCESS("订单支付成功TM00015", "jWkVQgQOaHRs9aLFKnAbThc7WzBZAdGmWud1q9B7a3w"),
    ORDER_SUCCESS("中台下单成功OPENTM407170106", "DV5lEKthAF3vpZwC56SYz-az-ZwK3Rzrr8kLtCaOgyc"),
    ORDER_STATE_CHANGE("订单状态更新TM00017", "g2XaRQi0xzJ0dMD6WAPppUVvYEjAAZzxUPRXw9rqqus");
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

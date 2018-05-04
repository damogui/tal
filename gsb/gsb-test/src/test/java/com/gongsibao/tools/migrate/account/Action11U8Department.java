package com.gongsibao.tools.migrate.account;

/**
 * Created by zhangchao on 2018/4/12.
 */
public class Action11U8Department extends AbstractActionService {

    @Override
    public void run() {
        pm.executeNonQuery("TRUNCATE TABLE u8_department;", null);
        String cmdText = "INSERT INTO u8_department(CODE,salesman_id) SELECT u8_dept_id,pkid FROM uc_user WHERE NOT EXISTS(SELECT * FROM u8_department WHERE salesman_id = uc_user.pkid);";
        dao.executeInsert(cmdText, null);
    }
}

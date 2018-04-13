package com.gongsibao.tools.migrate.account;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.supplier.base.ISupplierService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import java.util.List;

/**
 * @ClassName: Action6ImportSuppler
 * @Description:TODO
 * @author: 韩伟
 * @date: 2018年3月26日 下午2:58:18 8.将 uc_suppler 导入 sp_suppler(uc_user_id 对应
 * admin_id) 9.自动创建开通模块（客户管理、订单管理、系统设置） 10.执行 sp_suppler
 * 开户动作（检查是否自动创建部门：sp_department、业务员：sp_salesman），调用现有代码。
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class Action4ImportSuppler extends AbstractActionService {

    @Override
    public void run() {


//        //修改sp_supplier表字段长度
//        pm.executeNonQuery("ALTER TABLE  `sp_supplier` CHANGE `service_declaration` `service_declaration` VARCHAR(200) CHARSET utf8mb4 COLLATE utf8mb4_general_ci NULL, CHANGE `service_intro` `service_intro` VARCHAR(2000) CHARSET utf8mb4 COLLATE utf8mb4_general_ci NULL;", null);
//
//
//        // 8.将 uc_suppler 导入 sp_supplier(uc_user_id 对应 admin_id)
//        String cmdText = "INSERT INTO sp_supplier (contact,mobile_phone,type, qq, weixin, email, sex, NAME, head_portrait, create_time, creator_id, identity_card, identity_card_photo_front, identity_card_photo_reverse, response_time, service_declaration, service_intro, consignee_address, bank_account_type, bank_name, bank_num, bank_account_name, STATUS ) SELECT NAME, mobile_phone, 2 AS type, qq, weixin, email, sex, company_name, head_thumb_file_id, add_time, add_user_id, id_card, id_card_front_file_id, id_card_back_file_id, ( CASE response_time WHEN '5分钟内' THEN '5' WHEN '10分钟内' THEN '10' WHEN '20分钟内' THEN '20' WHEN '30分钟内' THEN '30' WHEN '1小时内' THEN '60' WHEN '4小时内' THEN '240' WHEN '8小时内' THEN '480' ELSE NULL END ) AS response_time, declar, intro, receive_address, ( CASE bank_type WHEN '单位帐户' THEN 1 WHEN '个人帐户' THEN 2 ELSE NULL END ) AS bank_type, bank_account, bank_no, bank_account, ( CASE is_enabled WHEN 0 THEN 3 WHEN 1 THEN 1 ELSE NULL END ) AS is_enabled FROM uc_suppler; ";
//        dao.executeInsert(cmdText, null);
//
//        // 9.自动创建开通模块（客户管理、订单管理、系统设置） sp_supplier_function_module
//        cmdText = "INSERT INTO sp_supplier_function_module (function_module_id,supplier_id) SELECT 1,id from sp_supplier where `status`=1;";
//        dao.executeInsert(cmdText, null);
//
//        cmdText = "INSERT INTO sp_supplier_function_module (function_module_id,supplier_id) SELECT 2,id from sp_supplier where `status`=1;";
//        dao.executeInsert(cmdText, null);

        // 10.执行 sp_suppler
        // 开户动作（检查是否自动创建部门：sp_department、业务员：sp_salesman），调用现有代码。
        ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
        Oql oql = new Oql();
        {
            oql.setType(Supplier.class);
            oql.setSelects("*");
            oql.setFilter("status=1");
        }
        List<Supplier> list = supplierService.queryList(oql);
        for (Supplier supplier : list) {

            supplierService.openAccount(supplier.getId());
            System.out.println(supplier.getName() + "开户成功");
        }
    }

}

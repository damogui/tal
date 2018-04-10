package com.gongsibao.panda.supplier.crm;

import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.dic.AllocationType;
import com.gongsibao.entity.crm.dic.Important;
import com.gongsibao.entity.crm.dic.Sex;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;

import java.util.ArrayList;
import java.util.Date;

public class CrmTest {
    INCustomerService service = ServiceFactory.create(INCustomerService.class);
    /**
     * 测试crm客户保存
     */
    @Test
    public void save(){
        NCustomer customer = new NCustomer();
        customer.setRealName("zhou2");
        customer.setSex(Sex.SECRECY);
        customer.setMobile("18607092232");
        customer.setIsMember(false);
        customer.setTelephone("");
        customer.setEmail("");
        customer.setQq("");
        customer.setWeixin("");
        customer.setImportant(Important.COMMON);
        customer.setRemark("");
        customer.setAllocationType(AllocationType.NATURAL);
        customer.setSupplierId(4);
        customer.setDepartmentId(8);
        customer.setTaskCount(0);
        customer.setTasks(new ArrayList<>());
        customer.setProducts(new ArrayList<>());
        customer.setCompanys(new ArrayList<>());
        customer.setFollows(new ArrayList<>());
        customer.setNotifys(new ArrayList<>());
        customer.setChanges(new ArrayList<>());
        customer.setCreator("zhou");
        customer.setCreatorId(3397);
        customer.setCreateTime(new Date());
        customer.setEntityState(EntityState.New);
        try{
            customer = service.save(customer);
            System.out.println(customer.getId()+"   new");
        }catch (BusinessException e){
            customer = service.getByMobile(customer.getMobile());
            System.out.println(customer.getId()+"   old");
        }
    }
}

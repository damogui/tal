package com.gongsibao.crm.test.ImportData;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.test.ImportData.Enity.ImNCustomerTaskFoolow;

/**
 * 导入数据使用
 */
@Service
public class MNCustomerTaskFoolowService extends SupplierPersistableService<ImNCustomerTaskFoolow> implements
        ImNCustomerTaskFoolowService {

    public MNCustomerTaskFoolowService() {
        super();
        this.type = ImNCustomerTaskFoolow.class;
    }

}

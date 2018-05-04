package com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskFoolowService;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.Enity.ImNCustomerTaskFoolow;
import org.netsharp.communication.Service;

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

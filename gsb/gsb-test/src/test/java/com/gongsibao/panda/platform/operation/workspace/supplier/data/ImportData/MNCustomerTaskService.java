package com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskService;
import com.gongsibao.entity.crm.NCustomerTask;
import org.netsharp.communication.Service;

/**
 * 导入使用
 */
@Service
public class MNCustomerTaskService extends SupplierPersistableService<NCustomerTask> implements IMNCustomerTaskService {
    public MNCustomerTaskService() {
        super();
        this.type = NCustomerTask.class;
    }

}

package com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerService;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.Enity.ImNCustomer;

import org.netsharp.communication.Service;

/**
 * Created by win on 2018/2/10.
 */
@Service
public class ImportNCustomerService  extends SupplierPersistableService<ImNCustomer> implements IImportNCustomerService {

    public ImportNCustomerService() {
        super();
        this.type = ImNCustomer.class;
    }


}

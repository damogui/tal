package com.gongsibao.crm.test.ImportData;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.test.ImportData.Enity.ImNCustomer;

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

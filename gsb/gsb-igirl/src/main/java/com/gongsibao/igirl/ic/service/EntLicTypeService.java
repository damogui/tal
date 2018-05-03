package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.EntLicType;
import com.gongsibao.igirl.ic.base.IEntLicTypeService;
import org.netsharp.communication.Service;

/**
 * @Description: java类作用描述
 * @UpdateDate: 2018/4/12 14:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class EntLicTypeService extends GsbPersistableService<EntLicType> implements IEntLicTypeService {
    public EntLicTypeService() {
        super();
        this.type = EntLicType.class;
    }
}

package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.Nationality;
import com.gongsibao.igirl.ic.base.INationalityService;
import org.netsharp.communication.Service;

/**
 * @Description: java类作用描述
 * @UpdateDate: 2018/4/12 15:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class NationalityService extends GsbPersistableService<Nationality> implements INationalityService{
    public NationalityService() {
        super();
        this.type = Nationality.class;
    }
}

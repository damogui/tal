package com.gongsibao.u8.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.u8.base.IU8BankService;

@Service
public class U8BankService extends PersistableService<U8Bank> implements IU8BankService {

    public U8BankService() {
        super();
        this.type = U8Bank.class;
    }

    @Override
    public U8Bank save(U8Bank entity) {
        entity.setSetOfBooksId(entity.getSetOfBooksId() == null ? 0 : entity.getSetOfBooksId());
        return super.save(entity);
    }

    public U8Bank byId(Object id) {

        U8Bank entity = super.byId(id);
        Integer prepaySubjectId = entity.getPrepaySubjectId();
        if (prepaySubjectId > 0) {

            U8Bank prepaySubject = byId(prepaySubjectId);
            entity.setPrepaySubject(prepaySubject);
        }
        return entity;
    }

}

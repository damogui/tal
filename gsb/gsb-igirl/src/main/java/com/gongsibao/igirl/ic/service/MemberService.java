package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.Member;
import com.gongsibao.igirl.ic.base.IcMemberService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import java.sql.Types;

@Service
public class MemberService extends GsbPersistableService<Member> implements IcMemberService {
    public MemberService() {
        super();
        this.type = Member.class;
    }
    @Override
    public Member byMobile(String mobile) {
        Oql oql = new Oql();
        oql.setType(Member.class);
        oql.setSelects("*");
        oql.setFilter("mobile=?");
        oql.getParameters().add("mobile",mobile, Types.VARCHAR);
        return this.pm.queryFirst(oql);
    }
}

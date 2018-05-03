package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.Member;
import com.gongsibao.igirl.ic.base.IcMemberService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

public class MemberDetailPart extends DetailPart {
    IcMemberService service = ServiceFactory.create(IcMemberService.class);
    public Member byMobile(String mobile){
        return service.byMobile(mobile);
    }
}

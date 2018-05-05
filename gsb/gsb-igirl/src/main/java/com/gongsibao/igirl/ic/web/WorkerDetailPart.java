package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.Member;
import com.gongsibao.igirl.ic.base.IcMemberService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

public class WorkerDetailPart extends DetailPart {
    public Member byMobile(String mobile){
        IcMemberService memberService = ServiceFactory.create(IcMemberService.class);
        return memberService.byMobile(mobile);
    }
}

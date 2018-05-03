package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.Member;
import com.gongsibao.igirl.ic.base.IcMemberService;
import com.gongsibao.igirl.ic.base.IcShareholderService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

public class ShareholderDetailPart extends DetailPart {
    IcShareholderService service = ServiceFactory.create(IcShareholderService.class);
    public Member byMobile(String mobile){
        IcMemberService memberService = ServiceFactory.create(IcMemberService.class);
        return memberService.byMobile(mobile);
    }
}

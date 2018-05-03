package com.gongsibao.igirl.ic.base;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.Member;
import org.netsharp.base.IPersistableService;

public interface IcMemberService extends IPersistableService<Member> {
    Member byMobile(String mobile);
}

package com.gongsibao.igirl.ic.base;

import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface IcExRegisterService extends IPersistableService<IcExRegisterCase> {
    Integer updateState(String id, Integer state);

    List<IcExRegisterCase> getIcCaseByType(ApprovalType wait);

    IcExRegisterCase updateIcCase(String name, Integer state);

    IcExRegisterCase updateOwner(Integer id, Integer toUserId);
}

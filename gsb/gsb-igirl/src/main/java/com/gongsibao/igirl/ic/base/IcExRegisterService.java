package com.gongsibao.igirl.ic.base;

import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import com.gongsibao.entity.igirl.ic.ex.dict.OperatorType;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface IcExRegisterService extends IPersistableService<IcExRegisterCase> {
    Integer updateState(String id, Integer state);

    List<IcExRegisterCase> getIcCaseByType(ApprovalType wait, OperatorType operator);

    IcExRegisterCase updateIcCase(String name, Integer state);

    IcExRegisterCase updateOwner(Integer id, Integer toUserId,Integer type);

    IcExRegisterCase findCom(String approvalName);

    String fetchQrCodeUrl(String url, String casecode,Integer id);

    String findMoblie(String customerMobile);

    IcExRegisterCase fetchInfoByCode(String code);
}

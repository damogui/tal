package com.gongsibao.igirl.ic.web;

import com.gongsibao.igirl.ic.base.IcExLogService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

public class IcExLogListPart extends ListPart {
    IcExLogService service = ServiceFactory.create(IcExLogService.class);
}

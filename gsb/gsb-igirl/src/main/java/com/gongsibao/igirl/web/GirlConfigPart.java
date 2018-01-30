package com.gongsibao.igirl.web;

import com.gongsibao.igirl.base.IGirlConfigService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

public class GirlConfigPart  extends FormPart {
    IGirlConfigService service = ServiceFactory.create(IGirlConfigService.class);
}

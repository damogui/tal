package com.gongsibao.igirl.tm.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.igirl.tm.base.IGirlConfigService;

public class GirlConfigPart  extends FormPart {
    IGirlConfigService service = ServiceFactory.create(IGirlConfigService.class);
}

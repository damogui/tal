package com.gongsibao.igirl.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.igirl.base.IGirlConfigService;

public class GirlConfigPart  extends FormPart {
    IGirlConfigService service = ServiceFactory.create(IGirlConfigService.class);
}

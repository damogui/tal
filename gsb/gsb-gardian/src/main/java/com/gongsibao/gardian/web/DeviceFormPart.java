package com.gongsibao.gardian.web;

import com.gongsibao.gardian.base.IDeviceService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

public class DeviceFormPart extends FormPart {
    IDeviceService deviceService= ServiceFactory.create(IDeviceService.class);
}

package org.netsharp.wx.pa.response.subscribe;

import org.netsharp.wx.mp.message.ResponseMessage;
import org.netsharp.wx.mp.message.request.event.EventRequest;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;

public interface IWeixinSubscriber {

    boolean validate(EventRequest request,Fans fans,PublicAccount publicAccount);

    ResponseMessage reply(EventRequest request,Fans fans,PublicAccount publicAccount,int sceneId);

}

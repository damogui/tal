package org.netsharp.wx.mp.message.request.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.util.StringManager;
import org.netsharp.wx.mp.message.EventType;

public class SubscribeEvent extends EventRequest {
    protected static Log logger = LogFactory.getLog(SubscribeEvent.class);
    private String ticket;

    public final String getTicket() {
        return ticket;
    }

    public final void setTicket(String value) {
        ticket = value;
    }

    public final int getSceneId() {
        int    sceneId  = 0;
        String eventKey = this.getEventKey();
        try {

            String scenceString = null;

            if (this.getEvent().toLowerCase().equals(EventType.Subscribe)) {
                if (!StringManager.isNullOrEmpty(eventKey) && eventKey.indexOf('_') > 0) {
                    String[] param = eventKey.split("[_]", -1);
                    scenceString = param[1];
                }
            } else if (this.getEvent().toLowerCase().equals(EventType.Scan)) {
                scenceString = eventKey;
            } else {
                return sceneId;
            }

            if (!StringManager.isNullOrEmpty(scenceString)) {
                sceneId = Integer.valueOf(scenceString);
            }
        } catch (Exception ex) {
            logger.error("微信关注异常消息：" + this.toString());
            logger.error("", ex);
        }
        return sceneId;
    }
}
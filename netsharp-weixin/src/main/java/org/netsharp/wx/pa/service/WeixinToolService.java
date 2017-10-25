package org.netsharp.wx.pa.service;

import org.netsharp.communication.Service;
import org.netsharp.wx.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.mp.api.accesstoken.PAccount;
import org.netsharp.wx.mp.api.accesstoken.PaConfiguration;
import org.netsharp.wx.mp.api.shorturl.ShortUrlRequest;
import org.netsharp.wx.pa.base.IWeixinToolService;

@Service
public class WeixinToolService implements IWeixinToolService {
    @Override
    public String getShortUrl(String longUrl,String originalId) {
    	
    	PAccount pa = PaConfiguration.get(originalId);
    	
        AccessToken at = AccessTokenManage.getTokenByAppId( pa.getAppId() );

        ShortUrlRequest request = new ShortUrlRequest();
        request.setTokenInfo(at);
        request.setLongUrl(longUrl);

        return request.getShortUrl();
    }
}

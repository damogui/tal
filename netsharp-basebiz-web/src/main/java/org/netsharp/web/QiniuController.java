package org.netsharp.web;

import org.netsharp.appconfig.IAppconfigService;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import com.qiniu.util.Auth;

public class QiniuController {

	public String getToken() {

		UserPermission permission = UserPermissionManager.getUserPermission();
		if (permission == null) {
			throw new BusinessException("验证失败,请刷新重试!");
		}
		
		//暂时到数据库中取，后期可放在缓存中
		IAppconfigService appConfigService = ServiceFactory.create(IAppconfigService.class);
		String accessKey = appConfigService.byCode("environment.qiniu.accessKey").getValue();
		String secretKey = appConfigService.byCode("environment.qiniu.secretKey").getValue();
		String bucket = appConfigService.byCode("environment.qiniu.bucket").getValue();
		Auth auth = Auth.create(accessKey, secretKey);
		String token = auth.uploadToken(bucket);
		return token;
	}

}

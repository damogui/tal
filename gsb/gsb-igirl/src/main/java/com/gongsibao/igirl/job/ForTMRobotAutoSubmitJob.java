package com.gongsibao.igirl.job;

import org.netsharp.cache.base.CacheCommandFactory;
import org.netsharp.cache.base.ICacheCommand;
import org.netsharp.cache.base.dict.CacheType;
import org.netsharp.job.core.IJob;
/**
 * 定时把待提交数据按照服务商id放到缓存中
 * id_date
 * @author root
 *
 */
public class ForTMRobotAutoSubmitJob implements IJob{

	@Override
	public void execute(String par) {
		// TODO Auto-generated method stub
		ICacheCommand cmd=CacheCommandFactory.create(CacheType.redis);
		
	}

}

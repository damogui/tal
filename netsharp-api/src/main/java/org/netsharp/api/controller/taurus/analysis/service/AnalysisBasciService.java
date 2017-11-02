package org.netsharp.api.controller.taurus.analysis.service;

import java.util.Date;

import org.netsharp.api.controller.taurus.analysis.AnalysisContext;
import org.netsharp.api.controller.taurus.analysis.IAnalysisService;
import org.netsharp.api.controller.taurus.dic.RunPhase;
import org.netsharp.api.controller.taurus.dto.CompanyDTO;
import org.netsharp.api.util.ApiException;
import org.netsharp.util.DateManage;

import com.gongsibao.taurus.entity.EntMember;
import com.gongsibao.taurus.entity.EntRegistry;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.service.TaurusApiService;

/**
 * @ClassName: AnalysisBasciInfoService
 * @Description:TODO 基本信息分析
 * @author: 韩伟
 * @date: 2017年10月30日 下午9:08:28
 * 
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved.
 */
public class AnalysisBasciService implements IAnalysisService {

	@Override
	public void run(AnalysisContext context) {

		CompanyDTO dto = new CompanyDTO();
		try {
			
			EntRegistry entRegistry = context.getEntRegistry();

			// 成立日期
			Date foundationDate = DateManage.parse(entRegistry.getFoundation());

			String runPhase = "初创期";
			if (foundationDate != null) {

				int distinceYear = DateManage.getDistinceYear(foundationDate, new Date());
				// 成立时间小于1年=初创期，小于2年=发展期，小于3年=增长期，小于5年=成长期，大于5年成熟期，大于10年，转型期，大于20年=多元化
				runPhase = RunPhase.getItem(distinceYear).getText();
			}

			// 主要人员
			ResponseMessage<EntMember> entMemberList = TaurusApiService.getEntMemberList(entRegistry.getName(), 0, Integer.MAX_VALUE);
			dto.setCompanyName(entRegistry.getName());
			dto.setTotalAmount(0);
			dto.setPotentialDemandCount(0);
			dto.setDecisionMakerCount(entMemberList.getTotalSize());
			dto.setRunPhase(runPhase);
			context.getAnalysisDTO().setCompany(dto);
			
			// 加入缓存
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new ApiException(-1, "分析出错");
		}
	}

}

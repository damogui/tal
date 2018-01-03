package com.gongsibao.taurus.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.JnzUserBehaviorStatistics;
import com.gongsibao.taurus.base.IBdUserBehaviorStatistics;

/**
 * 用户行为统计
 * @author Administrator
 *
 */
@Service
public class BdUserBehaviorStatistics extends PersistableService<JnzUserBehaviorStatistics> implements IBdUserBehaviorStatistics{
	
	/**
	 * 统计用户行为数据
	 */
	public void saveJnzUserBehaviorStatistics () {
		//封装查询sql
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT DATE_FORMAT(bubl.add_time, '%Y-%m-%d') as addTime,ju.mobile as mobile, ");
		sqlBuilder.append("SUM(CASE bubl.behavior_act WHEN '点击短信验证或账号密码页的“登录”按钮' THEN 1 ELSE 0 END ) AS loginButton, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '登录成功' THEN 1 ELSE 0 END ) AS loginSuccess, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '点击“注册”按钮' THEN 1 ELSE 0 END ) AS registerButton, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '注册成功' THEN 1 ELSE 0 END ) AS registerSuccess, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '在注册中完成全部个人资料信息' THEN 1 ELSE 0 END ) AS improvingPersonalData, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '进入首页' THEN 1 ELSE 0 END ) AS homePage, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '查询关键词完成，进入企业列表' THEN 1 ELSE 0 END ) AS enterpriseList, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '从企业列表选择要分析的企业' THEN 1 ELSE 0 END ) AS enterpriseAnalysis, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '从企业列表选择要分析的企业，并成功扣费' THEN 1 ELSE 0 END ) AS chargingSuccess, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '从企业列表选择要分析的企业，未扣费' THEN 1 ELSE 0 END ) AS noDeductions, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '点击冲值（余额不足充值）' THEN 1 ELSE 0 END ) AS balanceInsufficientRecharge, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '点击充值（个人中心充值）' THEN 1 ELSE 0 END ) AS personalCenterRecharge, ");
		sqlBuilder.append("SUM( CASE bubl.behavior_act WHEN '冲值完成' THEN 1 ELSE 0 END ) AS rechargeSuccess ");
		sqlBuilder.append("FROM bd_user_behavior_log bubl,jnz_user ju where bubl.user_id = ju.pkid GROUP BY DATE_FORMAT(bubl.add_time, '%Y-%m-%d'),ju.mobile;");
		//执行查询sql方法
		DataTable dataTable = this.pm.executeTable(sqlBuilder.toString(), null);
		JnzUserBehaviorStatistics jnzUserBehaviorStatistics = null;
		
		if (dataTable.size() > 0) {
			this.pm.executeNonQuery(" delete from jnz_user_behavior_statistics where 1=1",null);
			for (IRow row : dataTable) {
				jnzUserBehaviorStatistics = new JnzUserBehaviorStatistics();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					//重新封装用户行为统计数据
					Date date = sdf.parse(row.getString("addTime"));
					jnzUserBehaviorStatistics.setAddTime(date);
					jnzUserBehaviorStatistics.setMobile(row.getString("mobile"));
					jnzUserBehaviorStatistics.setLoginButton(row.getString("loginButton"));
					jnzUserBehaviorStatistics.setLoginSuccess(row.getString("loginSuccess"));
					jnzUserBehaviorStatistics.setRegisterButton(row.getString("registerButton"));
					jnzUserBehaviorStatistics.setRegisterSuccess(row.getString("registerSuccess"));
					jnzUserBehaviorStatistics.setImprovingPersonalData(row.getString("improvingPersonalData"));
					jnzUserBehaviorStatistics.setHomePage(row.getString("homePage"));
					jnzUserBehaviorStatistics.setEnterpriseList(row.getString("enterpriseList"));
					jnzUserBehaviorStatistics.setEnterpriseAnalysis(row.getString("enterpriseAnalysis"));
					jnzUserBehaviorStatistics.setChargingSuccess(row.getString("chargingSuccess"));
					jnzUserBehaviorStatistics.setNoDeductions(row.getString("noDeductions"));
					jnzUserBehaviorStatistics.setBalanceInsufficientRecharge(row.getString("balanceInsufficientRecharge"));
					jnzUserBehaviorStatistics.setPersonalCenterRecharge(row.getString("personalCenterRecharge"));
					jnzUserBehaviorStatistics.setRechargeSuccess(row.getString("rechargeSuccess"));
					//此处重点必填，否则save方法不生效
					jnzUserBehaviorStatistics.toNew();
					//保存用户行为统计数据
					this.pm.save(jnzUserBehaviorStatistics);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}

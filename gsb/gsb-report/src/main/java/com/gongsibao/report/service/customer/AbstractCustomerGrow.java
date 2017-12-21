package com.gongsibao.report.service.customer;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.report.customer.BaseCustomerGrowView;
import com.gongsibao.entity.report.dic.ReportDateType;


public abstract class AbstractCustomerGrow {
	protected IPersister<Customer> pm = PersisterFactory.create();

	protected abstract ReportDateType getDateType();
	
	public void queryList(){
		
		ReportDateType dateType = this.getDateType();
		getCustomerGrowList(dateType);
	}
	
	public List<BaseCustomerGrowView> getCustomerGrowList(ReportDateType type) {
		
		List<BaseCustomerGrowView> resultList = new ArrayList<BaseCustomerGrowView>();
		// 根据sql统计值
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT c.pkid as id,");
		sqlBuilder.append("DATE_FORMAT(c.add_time,'%Y-%u') dates,");
		sqlBuilder.append("count(distinct c.pkid) as 'newCustomer',");
		sqlBuilder.append("count(distinct s.customer_id) as 'newShareCustomer',");
		sqlBuilder.append("c.follow_user_id userId");
		sqlBuilder.append(" from crm_customer c"); 
		sqlBuilder.append(" LEFT JOIN crm_customer_share s"); 
		sqlBuilder.append(" on c.pkid=s.customer_id");
		sqlBuilder.append(" group by dates");
		sqlBuilder.append(" ORDER BY c.add_time desc");
		DataTable dataTable = this.pm.executeTable(sqlBuilder.toString(), null);

		
		return resultList;
	}
}

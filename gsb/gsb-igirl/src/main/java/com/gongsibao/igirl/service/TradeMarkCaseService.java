package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import org.joda.time.DateTime;
import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
@Service
public class TradeMarkCaseService extends GsbPersistableService<TradeMarkCase> implements ITradeMarkCaseService {

	public TradeMarkCaseService() {
		super();
		this.type = TradeMarkCase.class;
	}

	@Override
	public TradeMarkCase save(TradeMarkCase entity) {

		//设置编码样式	和所在的代理公司
		if(entity.getEntityState()==EntityState.New) {
			entity.setCode(DateTime.now().toString("yyyyMMddHHmmss"));
			//IUserService userService=ServiceFactory.create(IUser)
			
			
		}
			
		
		entity = super.save(entity);
		return entity;
	}
	
	
	@Override
	public TradeMarkCase newInstance() {
		// TODO Auto-generated method stub
		return super.newInstance();
	}
//
//	/**
//	 * @Title: hasMobile
//	 * @Description: TODO(判断手机号是否存在)
//	 * @param: @param mobile
//	 * @param: @return
//	 * @return: Boolean
//	 * @throws
//	 */
//	private Boolean hasMobile(String mobile) {
//
//		if(StringManager.isNullOrEmpty(mobile)){
//
//			return false;
//		}
//		Oql oql = new Oql();
//		{
//			oql.setType(this.type);
//			oql.setSelects("*");
//			oql.setFilter(" mobile=? ");
//			oql.getParameters().add("mobile", mobile, Types.VARCHAR);
//		}
//
//		return this.queryCount(oql) > 0;
//
//	}
//
//	private Employee getEmployee() {
//
//		Oql oql = new Oql();
//		{
//			oql.setType(Employee.class);
//			oql.setSelects("employee.{id,loginName}");
//			oql.setFilter(" id=? ");
//			oql.getParameters().add("id", SessionManager.getUserId(), Types.INTEGER);
//		}
//		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
//		Employee employee = employeeService.queryFirst(oql);
//		return employee;
//	}
//
//	@Override
//	public Customer validationContactWay(Integer id, String contactWay, String type) {
//
//		Oql oql = new Oql();
//		{
//			oql.setType(this.type);
//			oql.setSelects("*");
//		}
//
//		List<String> ss = new ArrayList<String>();
//		ss.add(type + "=?");
//		oql.getParameters().add("contactWay", contactWay, Types.VARCHAR);
//		if (id != null) {
//			ss.add("id<>?");
//			oql.getParameters().add("id", id, Types.INTEGER);
//		}
//		String filter = StringManager.join(" AND ", ss);
//		oql.setFilter(filter);
//
//		return this.queryFirst(oql);
//	}
//
//	@Override
//	public Customer byId(Object id) {
//
//		String selectFields = getSelectFullFields();
//		Oql oql = new Oql();
//		{
//			oql.setType(this.type);
//			oql.setSelects(selectFields);
//			oql.setFilter("id=?");
//			oql.getParameters().add("id", id, Types.INTEGER);
//		}
//
//		Customer entity = this.queryFirst(oql);
//		if (entity.getAccountId() != null && entity.getAccountId() != 0) {
//
//			List<SoOrder> orders = getOrderList(entity.getAccountId());
//			entity.setOrders(orders);
//		}
//		return entity;
//	}
//
//	@Override
//	public Customer bySwtCustomerId(String swtCustomerId) {
//
//		String selectFields = getSelectFullFields();
//		Oql oql = new Oql();
//		{
//			oql.setType(this.type);
//			oql.setSelects(selectFields);
//			oql.setFilter("swtCustomerId=?");
//			oql.getParameters().add("swtCustomerId", swtCustomerId, Types.VARCHAR);
//		}
//
//		Customer entity = this.queryFirst(oql);
//
//		if (entity != null && entity.getAccountId() != null && entity.getAccountId() != 0) {
//
//			List<SoOrder> orders = getOrderList(entity.getAccountId());
//			entity.setOrders(orders);
//		}
//		return entity;
//	}
//
//	private List<SoOrder> getOrderList(int accountId) {
//
//		Oql oql = new Oql();
//		{
//			oql.setType(SoOrder.class);
//			oql.setSelects("*");
//			oql.setFilter("accountId=?");
//			oql.getParameters().add("accountId", accountId, Types.INTEGER);
//		}
//
//		IPersister<SoOrder> orderPm = PersisterFactory.create();
//		return orderPm.queryList(oql);
//	}
//
//	@Override
//	public Customer byContactWay(String contactWay, String type) {
//
//		String selectFields = getSelectFullFields();
//		Oql oql = new Oql();
//		{
//			oql.setType(this.type);
//			oql.setSelects(selectFields);
//			oql.setFilter(type + "=?");
//			oql.getParameters().add("contactWay", contactWay, Types.VARCHAR);
//		}
//
//		Customer entity = this.queryFirst(oql);
//
//		if (entity != null && entity.getAccountId() != null && entity.getAccountId() != 0) {
//
//			List<SoOrder> orders = getOrderList(entity.getAccountId());
//			entity.setOrders(orders);
//		}
//		return entity;
//	}
//
//	/**
//	 * <p>Title: bindSwtCustomerId</p>
//	 * <p>Description:绑定商务通Id </p>
//	 * @param swtCustomerId
//	 * @param customerId
//	 * @return
//	 * @see com.gongsibao.crm.base.ICustomerService#bindSwtCustomerId(String, int)
//	 */
//	@Override
//	public Customer bindSwtCustomerId(String swtCustomerId, int customerId) {
//
//		UpdateBuilder updateBuilder = new UpdateBuilder();
//		{
//			updateBuilder.update(MtableManager.getMtable(this.type).getTableName());
//			updateBuilder.set("swt_customer_id", swtCustomerId);
//			updateBuilder.where("pkid =" + customerId);
//		}
//		this.pm.executeNonQuery(updateBuilder.toSQL(), null);
//		Customer customer = byId(customerId);
//		return customer;
//	}
//
//	private String getSelectFullFields() {
//
//		StringBuilder builder = new StringBuilder();
//		builder.append("Customer.*,");
//		builder.append("Customer.allocationOrg.*,");
//		builder.append("Customer.prodDetails.*,");
//		builder.append("Customer.prodDetails.product.{id,name},");
//		builder.append("Customer.prodDetails.dProvince.*,");
//		builder.append("Customer.prodDetails.dCity.*,");
//		builder.append("Customer.prodDetails.dCounty.*,");
//		builder.append("Customer.companys.*,");
//		builder.append("Customer.companys.company.{id,companyName},");
//		builder.append("Customer.follows.*,");
//		builder.append("Customer.orders.*");
//		return builder.toString();
//	}
}
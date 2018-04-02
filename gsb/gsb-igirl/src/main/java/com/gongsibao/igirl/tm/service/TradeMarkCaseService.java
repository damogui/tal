package com.gongsibao.igirl.tm.service;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.dic.*;
import com.gongsibao.entity.igirl.OrderProdCase;
import com.gongsibao.entity.igirl.dict.CaseConvertType;
import com.gongsibao.entity.igirl.res.ConvertToOrderResult;
import com.gongsibao.entity.igirl.tm.DownloadAttachment;
import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.UploadAttachment;
import com.gongsibao.entity.igirl.tm.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.tm.dict.ApplierType;
import com.gongsibao.entity.igirl.tm.dict.CaseType;
import com.gongsibao.entity.igirl.tm.dict.ConfigType;
import com.gongsibao.entity.igirl.tm.dict.TMCState;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdItem;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.CostStatus;
import com.gongsibao.entity.trade.dic.OrderPlatformSourceType;
import com.gongsibao.entity.trade.dic.OrderSourceType;
import com.gongsibao.gardian.base.IAccountService;
import com.gongsibao.igirl.base.IOrderProdCaseService;
import com.gongsibao.igirl.tm.base.*;
import com.gongsibao.igirl.tm.service.builder.TradeMarkCaseAttachmentBuiler;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.taurus.util.StringManager;
import com.gongsibao.trade.base.ICompanyIntentionService;
import com.gongsibao.trade.base.ICustomerService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.utils.NumberUtils;
import com.gongsibao.utils.RegexUtils;
import com.gongsibao.utils.SupplierSessionManager;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.session.SessionManager;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TradeMarkCaseService extends GsbPersistableService<TradeMarkCase> implements ITradeMarkCaseService {
    ISupplierService supplierServcie = ServiceFactory.create(ISupplierService.class);
    IUploadAttachmentService upattachementService = ServiceFactory.create(IUploadAttachmentService.class);
    IDownloadAttachmentService downattachementService = ServiceFactory.create(IDownloadAttachmentService.class);
    ITradeMarkService tradeMarkService = ServiceFactory.create(ITradeMarkService.class);

    IOrderService orderService = ServiceFactory.create(IOrderService.class);

    IAccountService accountService = ServiceFactory.create(IAccountService.class);

    IOrderProdCaseService orderProdCaseService = ServiceFactory.create(IOrderProdCaseService.class);

    ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

    ICompanyIntentionService companyIntentionService = ServiceFactory.create(ICompanyIntentionService.class);

    IDictService dictService = ServiceFactory.create(IDictService.class);

    private static TradeMarkCaseAttachmentBuiler tradeMarkCaseAttachmentBuiler = new TradeMarkCaseAttachmentBuiler();
	// 附件营业执照商标ID赋值为-1，因为多个商标共享
	public final static Integer TradeMarkBizLienseID = -1;
	// 身份证明商标ID为赋予值为-3，因为多个商标共享
	public final static Integer PersonMarkProofID = -3;

	// 付款证明商标ID为赋予值为-2，因为多个商标共享
	public final static Integer TradeMarkPayProofID = -2;

	public TradeMarkCaseService() {
		super();
		this.type = TradeMarkCase.class;
	}

	/**
	 * 填充加盟商信息
	 *
	 * @param entity
	 */
	private TradeMarkCase fillSupplierInfo(TradeMarkCase entity) {
		Integer sid = SupplierSessionManager.getSupplierId();
		entity.setSupplierId(sid);
		// 设置加盟商信息
		Supplier sl = supplierServcie.byId(sid);
		if (sl != null) {
			entity.setProxyCompanyName(sl.getName());
			entity.setAccountNo(sl.getBankNum());
			entity.setSupplierId(sid);
//		  entity.setYwPhone(sl.getFixPhone());
//		  entity.setMailCode(sl.getPostcode());
//			entity.setFax(sl.getFax());

		}
		// 设置商标的服务商id
	  String tmp="";
		for (TradeMark tm : entity.getTradeMarks()) {
			tm.setSupplierId(sid);
			//设置
			tmp+=tm.getNclOne().getCode()+" ";
		}
		entity.setTradeOptions(tmp);
		return entity;
	}

	/**
	 * 填充部门信息
	 *
	 * @param entity
	 */
	private TradeMarkCase fillDepartmentInfo(TradeMarkCase entity) {
		Integer departmentId = SupplierSessionManager.getDepartmentId();
		entity.setDepartmentId(departmentId);
		// 设置商标的服务商id
		String tmp="";
		int n=0;
		BigDecimal bd=BigDecimal.ZERO;
		for (TradeMark tm : entity.getTradeMarks()) {
			tm.setProxyCode( DateTime.now().toString("yyyyMMddHHmmssSSS")+n);
			n++;
			tm.setDepartmentId(departmentId);
			//设置
			tmp+=tm.getNclOne().getCode()+" ";
			
			if(tm.getEntityState()!=EntityState.Deleted) {
				bd=bd.add(tm.getCost());
				bd=bd.add(tm.getCharge());
			}
		}
		entity.setCaseAmount(bd);
		entity.setTradeOptions(tmp);
		return entity;
	}
//hw 2018-03-11 未使用方法
//	private Map<ShareGroup, Integer> buildShareGroupCountMap(TradeMarkCase tmc) {
//		Map<ShareGroup, Integer> shareGroupCountMap = new HashMap<ShareGroup, Integer>();
//		for (TradeMark tm : tmc.getTradeMarks()) {
//			if (!shareGroupCountMap.containsKey(tm.getShareGroup())) {
//				shareGroupCountMap.put(tm.getShareGroup(), 1);
//			} else {
//				int n = shareGroupCountMap.get(tm.getShareGroup()) + 1;
//				shareGroupCountMap.put(tm.getShareGroup(), n);
//			}
//		}
//		return shareGroupCountMap;
//	}

	@Override
	public TradeMarkCase save(TradeMarkCase entity) {
		if (entity.getEntityState() == EntityState.New) {
			//entity.setCode(DateTime.now().toString("yyyyMMddHHmmss"));
            //初始化当前业务员为当前登陆账号者
            entity.setOwnerId(SessionManager.getUserId());
            entity.setOwnerName(SessionManager.getUserName());
			// 填充加盟商信息
			entity = fillSupplierInfo(entity);
			entity = fillDepartmentInfo(entity);
			if (entity.getApplierType() == ApplierType.PUBLIC) {
				entity.setApplier(entity.getCompanyName());
			}
		}
		// //附件商标图样因为色彩而变化
		if (entity.getEntityState() == EntityState.Persist) {
			Integer sid = SupplierSessionManager.getSupplierId();
			Integer departmentId = SupplierSessionManager.getDepartmentId();

			String tmp="";
			BigDecimal bd=BigDecimal.ZERO;
			int m=1;
			for (TradeMark tm : entity.getTradeMarks()) {
				if(tm.getEntityState()!=EntityState.Deleted) {
					bd=bd.add(tm.getCost());
					bd=bd.add(tm.getCharge());
				}
				if(StringManager.isNullOrEmpty(tm.getProxyCode())){
					tm.setProxyCode( DateTime.now().toString("yyyyMMddHHmmssSSS")+entity.getId()+m);
					m++;
				}	else {
					m++;
				}
				if(sid!=null && sid!=-1){
					tm.setSupplierId(sid);
				}
				if(departmentId!=null && departmentId!=-1) {
					tm.setDepartmentId(departmentId);
				}
				if (tm.getEntityState() != EntityState.Deleted) {
					tmp+=tm.getNclOne().getCode()+" ";
			    }
			}
			entity.setCaseAmount(bd);
			entity.setTradeOptions(tmp);
		}
		// 删除附件明细
		if (entity.getEntityState() == EntityState.Deleted) {

			Oql oql = new Oql();
			{
				oql.setType(this.type);
				oql.setSelects(
						"TradeMarkCase.id,TradeMarkCase.uploadAttachments.*,TradeMarkCase.downLoadAttaments.*,TradeMarkCase.tradeMarks.*");
				oql.setFilter(" id=? ");
				oql.getParameters().add("id", entity.getId(), Types.INTEGER);
			}
			entity = this.queryFirst(oql);

			entity.setEntityState(EntityState.Deleted);

			List<UploadAttachment> ups = entity.getUploadAttachments();
			for (int i = 0; i < ups.size(); i++) {
				UploadAttachment uploadAttachment = ups.get(i);
				uploadAttachment.setEntityState(EntityState.Deleted);

			}
			List<DownloadAttachment> ds = entity.getDownLoadAttaments();
			for (int i = 0; i < ds.size(); i++) {
				DownloadAttachment downloadAttachment = ds.get(i);
				downloadAttachment.setEntityState(EntityState.Deleted);

			}
			List<TradeMark> tms = entity.getTradeMarks();
			for (int i = 0; i < tms.size(); i++) {
				TradeMark tm = tms.get(i);
				tm.setEntityState(EntityState.Deleted);
			}

		}
		// 默认设置为联系人电话
		entity.setToken(entity.getMobile());
		// 设置tokenImageUrl
		entity = super.save(entity);
		return entity;
	}

	@Override
	public TradeMarkCase newInstance() {
		// TODO Auto-generated method stub
		Integer sid = SupplierSessionManager.getSupplierId();
		Supplier sl = supplierServcie.byId(sid);
		TradeMarkCase tc = super.newInstance();
//		tc.setYwPhone("010-84927588");
		tc.setMailCode("100000");
//		tc.setFax("010-84927588");
		if(sl!=null)
		{
			if(sl.getTelePhone()!=null && sl.getTelePhone().length()<=0){
				tc.setYwPhone("010-84927588");
			}
			else
			{
				tc.setYwPhone(sl.getTelePhone());
			}
			if(sl.getFax()!=null && sl.getFax().length()<=0)
			{
				tc.setFax("010-84927588");
			}else
			{
				tc.setFax(sl.getFax());
			}
			//tc.setMailCode(sl.getPostCode());

		}
		else {
			tc.setYwPhone("010-84927588");
			tc.setFax("010-84927588");
		}

		// 查出当前登陆人办的最后一个案子，取出案件联系人，然后赋予初值
		Oql oql = new Oql();
		{
			oql.setType(TradeMarkCase.class);
			oql.setSelects("TradeMarkCase.{creator,caseProxyContactPerson}");
			oql.setFilter("creator=?");
			oql.setOrderby("createTime desc");
			oql.getParameters().add("creator", SessionManager.getUserName(), Types.VARCHAR);
			Paging p = new Paging();
			p.pageNo = 1;
			p.pageSize = 10;
			oql.setPaging(p);
		}
		TradeMarkCase tmc = this.queryFirst(oql);
		if (tmc != null) {
			tc.setCaseProxyContactPerson(tmc.getCaseProxyContactPerson());
		}
		return tc;
	}

	@Override
	public TradeMarkCase getTradeMarkCaseModelByMobile(String mobile) {
		// TODO Auto-generated method stub
		Oql oql = new Oql();
		{
			oql.setType(TradeMarkCase.class);
			oql.setSelects("TradeMarkCase.id,TradeMarkCase.code,TradeMarkCase.caseAmount,TradeMarkCase.tradeMarks.*");
			oql.setFilter("mobile=?");
			oql.getParameters().add("mobile", mobile, Types.VARCHAR);
		}
		return this.queryFirst(oql);
	}

	@Override
	public String fetchQrCodeUrl(String url,String casecode) {
		// TODO Auto-generated method stub
		IGirlConfigService girlConf=ServiceFactory.create(IGirlConfigService.class);
		Oql oql=new Oql();{
			oql.setType(IGirlConfig.class);
			oql.setSelects("IGirlConfig.*");
			oql.setFilter("configType=? or configType=?");
			oql.getParameters().add("configType",ConfigType.IGIRL_QR_URL.getValue(),Types.INTEGER);
			oql.getParameters().add("configType",ConfigType.IGIRL_MOBILE_TESTURL.getValue(),Types.INTEGER);
		}
		List<IGirlConfig> configs=girlConf.queryList(oql);
		String qcurl="";
		if(configs.size()==1) {
			//qcurl="{qrServiceUrl}/qc?detailLink= {currentDomain}/gsb/igirl/tmcase.html?mobile="+mobile;
			//URLEncoder.encode(s)
			qcurl="{qrServiceUrl}/qc?detailLink=|{currentDomain}/gsb/igirl/mobile/main.html#/?spid="+SupplierSessionManager.getSupplierId()+"&casecode="+casecode+"&source=case";
			qcurl=qcurl.replace("{qrServiceUrl}", configs.get(0).getConfigValue()).replace("{currentDomain}", url);
			try {
				qcurl=qcurl.split("\\|")[0]+URLEncoder.encode(qcurl.split("\\|")[1],"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(configs.size()==2) {
			qcurl="{qrServiceUrl}/qc?detailLink=|{currentDomain}/gsb/igirl/mobile/main.html#/?spid="+SupplierSessionManager.getSupplierId()+"&casecode="+casecode+"&source=case";
			qcurl=qcurl.replace("{qrServiceUrl}", configs.get(0).getConfigValue()).replace("{currentDomain}", configs.get(1).getConfigValue());
			try {
				qcurl=qcurl.split("\\|")[0]+URLEncoder.encode(qcurl.split("\\|")[1],"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return qcurl;
	}

	@Override
	public int denyAdvice(String caseid, String advice) {
		// TODO Auto-generated method stub
		try {
			String cmdstr = "update ig_trade_mark_case set tmc_state=?,advice=? where id=?";
			Oql oql=new Oql();
			{
				oql.setFilter("tmc_state=?");
				oql.setFilter("advice=?");
				oql.setFilter("id=?");
				oql.getParameters().add("tmc_state",TMCState.ADVICE.getValue(),Types.INTEGER);
				oql.getParameters().add("advice",advice,Types.VARCHAR);
				oql.getParameters().add("id",Integer.parseInt(caseid),Types.INTEGER);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			return 0;
		}catch(BusinessException e) {
			return -1;
		}

	}

	@Override
	public int confirmCase(String caseid) {
		// TODO Auto-generated method stub
		try {
			String cmdstr = "update ig_trade_mark_case set tmc_state=? where id=?";
			Oql oql=new Oql();
			{
				oql.setFilter("tmc_state=?");
				oql.setFilter("id=?");
				oql.getParameters().add("tmc_state",TMCState.CONFIRMED.getValue(),Types.INTEGER);
				oql.getParameters().add("id",Integer.parseInt(caseid),Types.INTEGER);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			return 0;
		}catch(BusinessException e) {
			return -1;
		}
	}

	@Override
	public int attachmentMake(String caseid) {
		// TODO Auto-generated method stub
		//先删除当前案件的所有附件
		try {
			String cmdstr = "delete from ig_up_attachment where trade_mark_caseid=?";
			Oql oql=new Oql();
			{
				oql.setFilter("trade_mark_caseid=?");
				oql.getParameters().add("trade_mark_caseid",Integer.parseInt(caseid),Types.INTEGER);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			cmdstr = "delete from ig_down_attachment where trade_mark_caseid=?";
			oql=new Oql();
			{
					oql.setFilter("trade_mark_caseid=?");
					oql.getParameters().add("trade_mark_caseid",Integer.parseInt(caseid),Types.INTEGER);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			//生成附件
			Oql oqlx = new Oql();
			{
				oqlx.setType(TradeMarkCase.class);
				oqlx.setSelects("TradeMarkCase.*,TradeMarkCase.tradeMarks.*,TradeMarkCase.tradeMarks.nclOne.*,TradeMarkCase.uploadAttachments.*,TradeMarkCase.downLoadAttaments.*");
				oqlx.setFilter(" id=? ");
				oqlx.getParameters().add("id", Integer.parseInt(caseid), Types.INTEGER);
			}
			TradeMarkCase tmc=this.queryFirst(oqlx);
			//创建新的附件
			List<UploadAttachment> caseUps = tradeMarkCaseAttachmentBuiler.buildCaseShareUploads(tmc);	// 构建案件共享上传
			tmc.getUploadAttachments().addAll(caseUps);
			List<UploadAttachment> markShareGroupUps = tradeMarkCaseAttachmentBuiler.buildMarkShareGroupUploads(tmc);
			tmc.getUploadAttachments().addAll(markShareGroupUps);
			List<DownloadAttachment> markShareGroupDowns = tradeMarkCaseAttachmentBuiler.buildDownloads(tmc);
			tmc.getDownLoadAttaments().addAll(markShareGroupDowns);
			tmc.toPersist();
			this.save(tmc);
			return 0;
		}catch(BusinessException e) {
			return -1;
		}
	}
	@Override
	public TradeMarkCase updateOwner(Integer ttmId, Integer ownerId) {
		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		Oql oql = new Oql();
		oql.setSelects("TradeMarkCase.*");
		oql.setType(TradeMarkCase.class);
		oql.setFilter("id=?");
		oql.getParameters().add("id",ttmId,Types.INTEGER);
		TradeMarkCase tradeMarkCase=this.queryFirst(oql);
        if (tradeMarkCase!=null){
            tradeMarkCase.setOwnerId(ownerId);
            Employee employee = employeeService.byId(ownerId);
            tradeMarkCase.setOwnerName(employee.getName());
            tradeMarkCase.toPersist();
            tradeMarkCase = super.save(tradeMarkCase);
        }
		return tradeMarkCase;
	}

	@Override
	public int fetchCaseState(String casecode) {
		// TODO Auto-generated method stub
		Oql oql = new Oql();
		oql.setType(TradeMarkCase.class);
		oql.setSelects("TradeMarkCase.*");
		oql.setType(TradeMarkCase.class);
		oql.setFilter("code=?");
		oql.getParameters().add("code",casecode,Types.VARCHAR);
		TradeMarkCase tradeMarkCase=this.queryFirst(oql);
		return tradeMarkCase.getTmcState().getValue();
	}

	@Override
	public int updateCaseState(String casecode,int state) {
		// TODO Auto-generated method stub
		try {
			String cmdstr = "update ig_trade_mark_case set tmc_state=? where code=?";
			Oql oql=new Oql();
			{
				oql.setFilter("tmc_state=?");
				oql.setFilter("code=?");
				oql.getParameters().add("tmc_state",state,Types.INTEGER);
				oql.getParameters().add("code",casecode,Types.VARCHAR);
			}
			this.pm.executeNonQuery(cmdstr, oql.getParameters());
			return 0;
		}catch(BusinessException e) {
			return -1;
		}
	}

    @Override
    public ConvertToOrderResult convertToOrder(String caseid) {
        TradeMarkCase tradeMarkCase = byId(caseid);
        if (null == tradeMarkCase) {
            // 方案不存在
            return new ConvertToOrderResult(CaseConvertType.ERROR_0);
        }

        // 是否已下单，已下单直接返回
		List<OrderProdCase> caseList = orderProdCaseService.byCaseId(tradeMarkCase.getId());
		if (null != caseList && caseList.size() > 0) {
			OrderProdCase orderProdCase = caseList.get(0);
			ConvertToOrderResult result = new ConvertToOrderResult(CaseConvertType.SUCCESS);
			result.getExtend().put("accountId", orderProdCase.getAccountId());
			result.getExtend().put("orderId", orderProdCase.getOrderId());
			return result;
		}

		// 方案明细
        List<TradeMark> tradeMarks = tradeMarkCase.getTradeMarks();
        if (CollectionUtils.isEmpty(tradeMarks)) {
            // 缺少子方案
			return new ConvertToOrderResult(CaseConvertType.ERROR_1);
        }

        Integer productId = tradeMarkCase.getProductId();
        if (null == productId || productId == 0) {
            // 产品不存在
			return new ConvertToOrderResult(CaseConvertType.ERROR_2);
        }

        String mobile = tradeMarkCase.getMobile();
        if (StringManager.isNullOrEmpty(mobile) || RegexUtils.isNotPhone(mobile)) {
            // 客户手机号码错误
			return new ConvertToOrderResult(CaseConvertType.ERROR_3);
        }

        String companyName = tradeMarkCase.getCompanyName();
        if (StringManager.isNullOrEmpty(companyName) && tradeMarkCase.getApplierType().getValue() == ApplierType.PUBLIC.getValue()) {
            // 公司名称不存在
			return new ConvertToOrderResult(CaseConvertType.ERROR_4);
        }

        // 目前商标注册，都是大陆地区
        Dict dict = dictService.byId(101900101);

		// 完善公司信息
		CompanyIntention company = null;
		if (tradeMarkCase.getApplierType().getValue() == ApplierType.PRIVATE.getValue()) {
			company = new CompanyIntention();
			company.setId(0);
			company.setCompanyName(null == companyName ? "" : companyName);
		} else{
			company = getAndSaveCompany(companyName, tradeMarkCase, dict.getId());
		}

        // 创建会员及客户
        Account account = accountService.byMobile(mobile);
        Customer customer = null;
        if (null == account) {
            // 保存客户
            account = getAndSaveAccount(tradeMarkCase, company);
            // 保存会员
            customer = getAndSaveCustomer(account, tradeMarkCase);
        }

        if (null == customer) {
            customer = customerService.byAccountId(account.getId());
        }

        // 转换订单实体
        SoOrder order = convertToOrderEntity(tradeMarkCase, account, customer, company, dict);

        // 保存订单
        order = orderService.save(order);

        // 订单信息关联方案回写
        List<OrderProdCase> orderProdCaseList = convertToOrderProdCaseList(tradeMarkCase, order);
        orderProdCaseService.saves(orderProdCaseList);

		ConvertToOrderResult result = new ConvertToOrderResult(CaseConvertType.SUCCESS);
		{
			result.getExtend().put("accountId", account.getId());
			result.getExtend().put("orderId", order.getId());
		}
		return result;
    }

    /**
     * 生成明细订单关联商标注册方案实体
     *
     * @param tradeMarkCase
     * @param order
     * @return
     */
    private List<OrderProdCase> convertToOrderProdCaseList(TradeMarkCase tradeMarkCase, SoOrder order) {
        List<TradeMark> tradeMarks = tradeMarkCase.getTradeMarks();
        List<OrderProd> products = order.getProducts();
        List<OrderProdCase> orderProdCaseList = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            OrderProd orderProd = products.get(i);
            TradeMark tradeMark = tradeMarks.get(i);

            OrderProdCase orderProdCase = new OrderProdCase();
            {
                orderProdCase.toNew();
				orderProdCase.setOrderId(order.getId());
				orderProdCase.setOrderProdId(orderProd.getId());
				orderProdCase.setCaseType(CaseType.TRADEMARK_REG);
				orderProdCase.setCaseId(tradeMarkCase.getId());
				orderProdCase.setCaseItemId(tradeMark.getId());
				orderProdCase.setCreatorId(order.getAccountId());
				orderProdCase.setAccountId(order.getAccountId());
            }
            orderProdCaseList.add(orderProdCase);
        }
        return orderProdCaseList;
    }

    /**
     * 生成订单实体
     *
     * @param tradeMarkCase
     * @param account
     * @param customer
     * @param company
     * @param dict
     * @return
     */
    private SoOrder convertToOrderEntity(TradeMarkCase tradeMarkCase, Account account, Customer customer, CompanyIntention company, Dict dict) {
        List<TradeMark> tradeMarks = tradeMarkCase.getTradeMarks();
        Integer productId = tradeMarkCase.getProductId();
        int totalPrice = 0;
        // 构建so_order_prod信息
        List<OrderProd> orderProdList = new ArrayList<>();
        for (TradeMark tradeMark : tradeMarks) {
            int cost = NumberUtils.doubleRoundInt(tradeMark.getCost().doubleValue() * 100);
            int charge = NumberUtils.doubleRoundInt(tradeMark.getCharge().doubleValue() * 100);

            int orderProdPrice = cost + charge;

            OrderProd orderProd = new OrderProd();
            {
                orderProd.toNew();
                orderProd.setNo("");
                orderProd.setOrderId(0);
                orderProd.setProductId(productId);
                orderProd.setProductName(tradeMarkCase.getProduct().getName());
                orderProd.setCityId(dict.getId());
                orderProd.setCity(dict);
                orderProd.setCityName(dict.getName());
                orderProd.setQuantity(1);
                orderProd.setCompanyId(company.getId());
                orderProd.setTrademarkId(0);
                orderProd.setPrice(orderProdPrice);
                orderProd.setPriceOriginal(orderProdPrice);
                orderProd.setInvoiceTitle("");
                orderProd.setApplyNo("");// 暂时先设置成这样
                orderProd.setHandleName("");
                orderProd.setCostStatus(CostStatus.NOENTRY);
                orderProd.setSettleIdInteger(0);
                orderProd.setSettlePrice(0);
                orderProd.setSettleTime(null);
                orderProd.setOwnerId(tradeMarkCase.getOwnerId());
                orderProd.setTaskId(0);
                orderProd.setSupplierId(tradeMarkCase.getSupplierId());
                orderProd.setDepartmentId(tradeMarkCase.getDepartmentId());
                orderProd.setCustomerId(customer.getId());
            }


            List<OrderProdItem> items = new ArrayList<>();
            OrderProdItem costItem = new OrderProdItem();
            {
                costItem.toNew();
                costItem.setServiceName("成本");
                costItem.setPrice(cost);
                costItem.setPriceOriginal(cost);
                costItem.setPriceRefund(0);
            }

            OrderProdItem chargeItem = new OrderProdItem();
            {
                chargeItem.toNew();
                chargeItem.setServiceName("服务费");
                chargeItem.setPrice(charge);
                chargeItem.setPriceOriginal(charge);
                chargeItem.setPriceRefund(0);
            }

            items.add(costItem);
            items.add(chargeItem);
            orderProd.setItems(items);
            orderProdList.add(orderProd);

            // 计算总价
            totalPrice = totalPrice + cost + charge;
        }
        // 构建so_order信息
        SoOrder order = new SoOrder();
        {
            order.toNew();
            order.setProducts(orderProdList);
            order.setTaskId(0);
            order.setSupplierId(tradeMarkCase.getSupplierId());
            order.setDepartmentId(tradeMarkCase.getDepartmentId());
            order.setAccountId(account.getId());
            order.setAccountName(StringManager.isNullOrEmpty(account.getRealName()) ? account.getMobilePhone() : account.getRealName());
            order.setAccountMobile(account.getMobilePhone());
            order.setTotalPrice(totalPrice);
            order.setPayablePrice(totalPrice);
            order.setChannelOrderNo("");
            order.setSourceType(OrderSourceType.IGIRL_TM);
            order.setIsInstallment(false);
            order.setInstallmentAuditStatusId(AuditStatusType.wu);
            order.setAddUserId(tradeMarkCase.getOwnerId());
            order.setOwnerId(tradeMarkCase.getOwnerId());
            order.setCompanyId(company.getId());
            order.setPlatformSource(OrderPlatformSourceType.Gsb);
            order.setCustomerId(customer.getId());
            order.setCustomerName(customer.getRealName());
        }
        return order;
    }

    /**
     * 保存客户
     *
     * @param account
     * @return
     */
    private Customer getAndSaveCustomer(Account account, TradeMarkCase tradeMarkCase) {
        Customer customer = new Customer();
        {
            customer.toNew();
            customer.setAccountId(account.getId());
            customer.setRealName(account.getRealName());
            customer.setMobile(account.getMobilePhone());
            customer.setEmail(account.getEmail());
            customer.setSex(Sex.SECRECY);
            customer.setTelephone("");
            customer.setQq("");
            customer.setWeixin("");

            customer.setBirdthday(null);
            customer.setAddr("");
            customer.setCityId(0);
            customer.setFollowUserId(tradeMarkCase.getOwnerId());
            customer.setFollowStatus(FollowStatus.FOLLOW_STATUS_1);
            customer.setUnvalidRemark("");

            customer.setLastFollowTime(new Date());
            customer.setBackNum(0);
            customer.setCustomerSource(dictService.byId(4110215));
            customer.setConsultWay(ConsultWay.CONSULT_WAY_4215);
            customer.setImportant(Important.COMMON);

            customer.setIntroducerUserId(tradeMarkCase.getOwnerId());
            customer.setIntroducerId(0);
            customer.setRemark("");
            customer.setCreatorId(tradeMarkCase.getOwnerId());
            customer.setUpdatorId(tradeMarkCase.getOwnerId());
        }
		return customerService.save(customer);
    }

    /**
     * 保存会员
     *
     * @param tradeMarkCase
     * @return
     */
    private Account getAndSaveAccount(TradeMarkCase tradeMarkCase, CompanyIntention company) {
        Account account;
        account = new Account();
        {
            account.toNew();
            account.setName("IGIRL_TRADEMARK_" + tradeMarkCase.getMobile());
            account.setPasswd("");
            account.setTicket(UUID.randomUUID().toString());
            account.setEmail("");
            account.setMobilePhone(tradeMarkCase.getMobile());
            account.setTelephone("");
            account.setHeadThumbFileId(0);
            account.setRealName(tradeMarkCase.getContactName());
            account.setSourceClientId(0);
            account.setIdentityCard("");
            account.setCompanyId(company.getId());
        }

        account = accountService.save(account);
        return account;
    }

    /**
     * 保存公司实体
     *
     * @param companyName
     * @param tradeMarkCase
     * @param cityId
     * @return
     */
    private CompanyIntention getAndSaveCompany(String companyName, TradeMarkCase tradeMarkCase, Integer cityId) {
        CompanyIntention company = companyIntentionService.byCompanyName(companyName);
        if (null == company) {
            company = new CompanyIntention();
            {
                company.toNew();
                company.setName(companyName);
                company.setOrgType(CompanyOrgType.TYPE_44101);
                company.setOptionalName("");
                company.setFullName(companyName);
                company.setCompanyName(companyName);
                company.setCompanyType(CompanyType.CompanyType_0);
                company.setCode(tradeMarkCase.getCreditCode());
                company.setOrderProdId(0);
                company.setOrderContactName(tradeMarkCase.getContactName());
                company.setOrderContactMobile(tradeMarkCase.getMobile());
                company.setOrderContactEmail("");
                company.setSetupStatus(1);
                company.setCityId(cityId);
                company.setIsSelfAddress(1);
                company.setAddress(tradeMarkCase.getApplierAddress());
                company.setCapitalType(CapitalType.CapitalType_1);
                company.setRegisterCapital(0);
                company.setRegisterCapitalType(RegisterCapitalType.CompanyType_0);
                company.setIsSelfCapital(1);
                company.setIsExpress(0);
                company.setIsNameVerify(0);
                company.setNameVerifyFileId(0);
                company.setVerifyNo("");
                company.setBusinessTypeId(0);
                company.setOwnedBusinessType("");
                company.setBusinessScopeSupply("");
                company.setHasDirectorate(false);
                company.setStreet("");
                company.setPoliceStation("");
                company.setIsDelete(false);
                company.setArea("");
                company.setOrganizationNo("");
                company.setLegalPerson("");
                company.setSetUpDate(null);
                company.setPaidYears("");
                company.setOperatingLife("");
                company.setTelephone("");
                company.setOrderContractQq("");
                company.setOrderContractWechat("");
                company.setResidenceType(0);
                company.setHouseOwner("");
                company.setHouseSpace(0);
                company.setLogoUrl("");
                company.setNationTax("");
                company.setLocalTax("");
                company.setRemark("IGIRL-商标方案创建");
                company.setDescription("");
                company.setFinishTime(null);
            }

            company = companyIntentionService.save(company);
        }
        return company;
    }
    @Override
	public List<TradeMark> findTradeMarksByCode(String caseCode) {
		Oql oql=new Oql();
		{
			oql.setType(TradeMarkCase.class);
			oql.setSelects("TradeMarkCase.tradeMarks.*");
			oql.setFilter("code=?");
			oql.getParameters().add("code",caseCode,Types.VARCHAR);
		}
		TradeMarkCase tradeMarkCase=this.queryFirst(oql);
		List<TradeMark> tradeMarks = tradeMarkCase.getTradeMarks();
		return tradeMarks;
		
	}
}
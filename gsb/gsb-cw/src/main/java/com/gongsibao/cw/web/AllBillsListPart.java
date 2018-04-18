package com.gongsibao.cw.web;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.cw.base.IPaymentService;
import com.gongsibao.cw.tools.HttpClientUtil;
import com.gongsibao.entity.cw.CostDetail;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.dict.FinanceDict;

public class AllBillsListPart extends ListPart{

	private  static final String VOUCHERURL = "http://t1.gongsibao.com/gongsibao-sys/u8/voucher/addVoucher";
	 //借款服务
	ILoanService loanService = ServiceFactory.create(ILoanService.class);
	//报销服务
	IExpenseService expenseService = ServiceFactory.create(IExpenseService.class);
	//付款服务
	IPaymentService paymentService = ServiceFactory.create(IPaymentService.class);
		
	/**
	 * 凭证生成
	* @Title: createVoucher  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param formId
	* @param @param formType
	* @param @return    参数  
	* @return JSONObject    返回类型  
	* @throws
	 */
	public JSONObject createVoucher(Integer formId, Integer formType){
		JSONObject result = null;
		if(formType == FinanceDict.FormType.JKD.getValue()){ //借款单
			Loan loan = loanService.getBillByFormId(formId);
			JSONObject jsonObject = loanVoucher(loan);
			result = HttpClientUtil.doPost(VOUCHERURL, jsonObject);
		}
		return result;
	}
	/**
	 * 借款凭证
	* @Title: loanVoucher  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param loan
	* @param @return    参数  
	* @return JSONObject    返回类型  
	* @throws
	 */
	private JSONObject loanVoucher (Loan loan){
		JSONObject josnObject = new JSONObject();
		josnObject.put("senderNo",5);  //测试5 
		//josnObject.put("senderNo", loan.getSetOfBooks().getSenderNo());
		josnObject.put("enterName",loan.getSetOfBooks().getEnterName());
		josnObject.put("setOfBooksId", loan.getSetOfBooksId());
		josnObject.put("type", loan.getType().getValue().intValue());
		josnObject.put("payId", loan.getId());
		
		String abstractStr = loan.getDepartmentName()+"部门，"+loan.getCreator()+"申请";
		//借方分录
		JSONArray inEntryList = new JSONArray();
		List<CostDetail> costList=loan.getCostDetailItem();
		if(costList != null && costList.size()>0){
			for(CostDetail costDetail : costList){
				JSONObject inEntryJson = new JSONObject();
				inEntryJson.put("accountCode", costDetail.getCostType().getCode());
				inEntryJson.put("naturalDebitCurrency", costDetail.getDetailMoney()/100);
				if(loan.getU8Department() != null){
					inEntryJson.put("operator",loan.getU8Department().getSalesmanId());
					inEntryJson.put("deptId", loan.getU8Department().getCode());
					inEntryJson.put("personnelId",loan.getU8Department().getPersonnelCode());
				}else{
					throw new BusinessException("申请人未在U8部门表配置部门code");
				}
				inEntryJson.put("cashItem", costDetail.getCostType().getCashItem());
				inEntryJson.put("remarkIId", costDetail.getCostType().getCode());
				inEntryJson.put("cashFlowNaturalDebitCurrency", "0");
				inEntryList.add(inEntryJson);
				abstractStr +=costDetail.getCostTypeName()+","; //摘要凭借科目名称
			}
		}
		josnObject.put("inEntryList", inEntryList);
		//贷方分录
		JSONArray outEntryList = new JSONArray();
		JSONObject outEntryJson = new JSONObject();
		outEntryJson.put("accountCode", loan.getU8Bank().getCode());
		outEntryJson.put("naturalCreditCurrency", loan.getAmount()/100);
		outEntryJson.put("operator", "");
		outEntryJson.put("personnelId", "");
		outEntryJson.put("deptId", "");
		if(loan.getU8Bank() != null){
			outEntryJson.put("cashItem", loan.getU8Bank().getCode());
			outEntryJson.put("remarkIId", loan.getU8Bank().getCode());
		}else{
			throw new BusinessException("银行科目Code获取失败！");
		}
		outEntryJson.put("cashFlowNaturalCreditCurrency", loan.getAmount()/100);
		outEntryList.add(outEntryJson);
		josnObject.put("outEntryList", outEntryList);
		
		josnObject.put("abstractStr", abstractStr +"借款。"); //摘要
		return josnObject;
	}
	
	/**
	 * 报销凭证
	* @Title: expenseVoucher  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @return    参数  
	* @return JSONObject    返回类型  
	* @throws
	 */
	public JSONObject expenseVoucher(Expense expense){
		
		JSONObject josnObject = new JSONObject();
		josnObject.put("senderNo",5);  //测试5 
		//josnObject.put("senderNo", loan.getSetOfBooks().getSenderNo());
		josnObject.put("enterName",expense.getSetOfBooks().getEnterName());
		josnObject.put("setOfBooksId", expense.getSetOfBooksId());
		josnObject.put("type", expense.getType().getValue().intValue());
		josnObject.put("payId", expense.getId());
		/**
		 * 1、当报销金额大于借款金额  贷方为公司   借方为报销人  现金流挂公司
		 * 2、当报销金额等于借款金额  贷方为公司   借方为报销人  没有现金流
		 * 3、当报销金额小于借款金额  贷方为报销人  借方方为公司  现金流挂公司
		 */
		//借方分录
		JSONArray inEntryList = new JSONArray();
		List<CostDetail> costList=expense.getCostDetailItem();
		String abstractStr = expense.getDepartmentName()+"部门，"+expense.getCreator()+"申请";
		if(costList != null && costList.size()>0){
			for(CostDetail costDetail : costList){
				JSONObject inEntryJson = new JSONObject();
				inEntryJson.put("accountCode", costDetail.getCostType().getCode());
				inEntryJson.put("naturalDebitCurrency", costDetail.getDetailMoney()/100);
				if(expense.getU8Department() != null){
					inEntryJson.put("operator",expense.getU8Department().getSalesmanId());
					inEntryJson.put("deptId", expense.getU8Department().getCode());
					inEntryJson.put("personnelId",expense.getU8Department().getPersonnelCode());
				}else{
					throw new BusinessException("申请人未在U8部门表配置部门code");
				}
				inEntryJson.put("cashItem", costDetail.getCostType().getCashItem());
				inEntryJson.put("remarkIId", costDetail.getCostType().getCode());
				inEntryJson.put("cashFlowNaturalDebitCurrency", "0");
				inEntryList.add(inEntryJson);
				abstractStr +=costDetail.getCostTypeName()+",";
			}
		}
		josnObject.put("abstractStr", abstractStr+"报销"); //摘要
		return josnObject;
	}
}

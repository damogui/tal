package com.gongsibao.cw.web;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
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

	private  Logger logger = Logger.getLogger(AllBillsListPart.class);
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
			Loan loan = loanService.getBillByFormId(formId,true);
			JSONObject jsonObject = loanVoucher(loan);
			System.out.println("凭证请求参数："+jsonObject.toString());
			result = HttpClientUtil.doPost(FinanceDict.U8_VOUCHER_, jsonObject);
			
		}else if(formType == FinanceDict.FormType.BXD.getValue()){
			Expense expense = expenseService.getBillByFormId(formId,true);
			JSONObject jsonObject = expenseVoucher(expense);
			System.out.println("凭证请求参数："+jsonObject.toString());
			result = HttpClientUtil.doPost(FinanceDict.U8_VOUCHER_, jsonObject);
			logger.info("凭证返回参数："+result.toString());
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
		if(loan.getSetOfBooks()!= null){
			josnObject.put("enterName",loan.getSetOfBooks().getEnterName());
		}else{
			 throw new BusinessException("帐套对应制单人为空");
		}
		josnObject.put("setOfBooksId", loan.getSetOfBooksId());
		josnObject.put("type", 3);
		josnObject.put("payId", loan.getId());
		
		String abstractStr = loan.getDepartmentName()+"部门，"+loan.getCreator()+"申请";
		//借方分录
		JSONArray inEntryList = new JSONArray();
		
		JSONObject inEntryJson = new JSONObject();
		if(loan.getU8Bank() != null ){
			inEntryJson.put("accountCode", loan.getU8Bank().getCode());
		}else{
			 throw new BusinessException("帐套对应科目银行为空！");
		}
		
		inEntryJson.put("naturalDebitCurrency", loan.getAmount()/100);
		
		if(loan.getU8Department() !=null){
			inEntryJson.put("operator",loan.getU8Department().getSalesmanId());
			inEntryJson.put("deptId", loan.getU8Department().getCode());
			inEntryJson.put("personnelId",loan.getU8Department().getPersonnelCode());
		}else{
			 throw new BusinessException("U8系统部门信息为空！");
		}
		inEntryJson.put("remarkIId", loan.getU8Bank().getCode()); 
		String cashItem = "07" ;
		//其他挂07
		if(FinanceDict.LoanBillType.LoanType_4.getValue().intValue() == loan.getType().getValue().intValue()){
			cashItem = "05";
		}
		inEntryJson.put("cashItem", cashItem);
		inEntryJson.put("cashFlowNaturalDebitCurrency", "0");
		inEntryList.add(inEntryJson);
		josnObject.put("inEntryList", inEntryList);
		
		//贷方分录
		JSONArray outEntryList = new JSONArray();
		JSONObject outEntryJson = new JSONObject();
		outEntryJson.put("naturalCreditCurrency", loan.getAmount()/100);
		if(loan.getU8Department() !=null){
			outEntryJson.put("operator",loan.getU8Department().getSalesmanId());
			outEntryJson.put("deptId", loan.getU8Department().getCode());
			outEntryJson.put("personnelId",loan.getU8Department().getPersonnelCode());
		}else{
			 throw new BusinessException("U8系统部门信息为空！");
		}
		outEntryJson.put("accountCode", loan.getPayeeType().getAccountCode());
		outEntryJson.put("cashItem", "");
		outEntryJson.put("remarkIId", "");
		outEntryJson.put("cashFlowNaturalCreditCurrency", "");
		outEntryList.add(outEntryJson);
		josnObject.put("outEntryList", outEntryList);
		abstractStr +=loan.getType().getText()+","; //摘要凭借科目名称
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

		//借方分录
		JSONArray inEntryList = new JSONArray();
		List<CostDetail> costList=expense.getCostDetailItem();
		String abstractStr = expense.getDepartmentName()+"部门，"+expense.getCreator()+"申请";
		String cashItem = "07";
		if(costList != null && costList.size()>0){
			for(CostDetail costDetail : costList){
				JSONObject inEntryJson = new JSONObject();
				cashItem = costDetail.getCostType().getCashItem();
				inEntryJson.put("accountCode", costDetail.getCostType().getCode());
				inEntryJson.put("naturalDebitCurrency", costDetail.getDetailMoney()/100);
				if(expense.getU8Department() != null){
					inEntryJson.put("operator",expense.getU8Department().getSalesmanId());
					inEntryJson.put("deptId", expense.getU8Department().getCode());
					inEntryJson.put("personnelId",expense.getU8Department().getPersonnelCode());
				}else{
					throw new BusinessException("申请人未在U8部门表配置部门code");
				}
				inEntryJson.put("cashItem", "");
				inEntryJson.put("remarkIId", "");
				inEntryJson.put("cashFlowNaturalDebitCurrency", "0");
				inEntryList.add(inEntryJson);
				abstractStr +=costDetail.getCostTypeName()+",";
			}
		}
		//是否冲抵借款
		if(expense.getIsOffset()){
			//报销金额大于借款金额
			if(expense.getAmount() > 0){
				JSONArray outEntryList = getOutEntry(expense,cashItem);
				josnObject.put("outEntryList", outEntryList);
			}
			//报销金额等于借款金额
			if(expense.getAmount() == 0){
				JSONArray outEntryList = getOutEntry(expense);
				josnObject.put("outEntryList", outEntryList);
			}
			//报销金额小于借款金额
			if(expense.getAmount() < 0){
				JSONArray outEntryList = getOutEntry(expense,inEntryList,cashItem);
				josnObject.put("outEntryList", outEntryList);
			}
		}else{//不冲抵贷款
			//贷方分录
			JSONArray outEntryList = new JSONArray();
			JSONObject outEntryJson = new JSONObject();
			outEntryJson.put("naturalCreditCurrency", expense.getTotalAmount()/100);
			if(expense.getU8Department() != null){
				outEntryJson.put("operator",expense.getU8Department().getSalesmanId());
				outEntryJson.put("deptId", expense.getU8Department().getCode());
				outEntryJson.put("personnelId",expense.getU8Department().getPersonnelCode());
			}else{
				throw new BusinessException("申请人未在U8部门表配置部门code");
			}
			if(expense.getU8Bank() != null ){
				outEntryJson.put("accountCode", expense.getU8Bank().getCode());
				outEntryJson.put("cashItem", cashItem); 
				outEntryJson.put("remarkIId", expense.getU8Bank().getCode());
			}else{
				throw new BusinessException("帐套对应科目银行为空");
			}
			outEntryJson.put("cashFlowNaturalCreditCurrency", expense.getTotalAmount()/100);
			outEntryList.add(outEntryJson);
			josnObject.put("outEntryList", outEntryList);
		}
		josnObject.put("abstractStr", abstractStr+"报销"); //摘要
		return josnObject;
	}
	/**
	 * 贷方分录信息
	* @Title: getOutEntry  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param expense
	* @param @return    参数  
	* @return JSONArray    返回类型  
	* @throws
	 */
	public JSONArray getOutEntry(Expense expense){
		//贷方分录
		JSONArray outEntryList = new JSONArray();
		JSONObject outEntryJson = new JSONObject();
		outEntryJson.put("naturalCreditCurrency", expense.getTotalAmount()/100);
		if(expense.getU8Department() != null){
			outEntryJson.put("operator",expense.getU8Department().getSalesmanId());
			outEntryJson.put("deptId", expense.getU8Department().getCode());
			outEntryJson.put("personnelId",expense.getU8Department().getPersonnelCode());
		}else{
			throw new BusinessException("申请人未在U8部门表配置部门code");
		}
		if(expense.getU8Bank() != null ){
			outEntryJson.put("accountCode", expense.getU8Bank().getCode());
			outEntryJson.put("cashItem", ""); 
			outEntryJson.put("remarkIId", "");
		}else{
			throw new BusinessException("帐套对应科目银行为空");
		}
		outEntryJson.put("cashFlowNaturalCreditCurrency", "0");
		outEntryList.add(outEntryJson);
		return outEntryList;
	}
	//报销金额大于借款金额
	public JSONArray getOutEntry(Expense expense,String cashItem){
		//贷方分录
		JSONArray outEntryList = new JSONArray();
		JSONObject outEntryJson = new JSONObject();
		//借款分录
		{
			outEntryJson.put("naturalCreditCurrency", expense.getLoanAmount()/100);
			if(expense.getU8Department() != null){
				outEntryJson.put("operator",expense.getU8Department().getSalesmanId());
				outEntryJson.put("deptId", expense.getU8Department().getCode());
				outEntryJson.put("personnelId",expense.getU8Department().getPersonnelCode());
			}else{
				throw new BusinessException("申请人未在U8部门表配置部门code");
			}
			if(expense.getU8Bank() != null ){
				outEntryJson.put("accountCode", expense.getU8Bank().getCode());
				outEntryJson.put("cashItem", ""); 
				outEntryJson.put("remarkIId", "");
			}else{
				throw new BusinessException("帐套对应科目银行为空");
			}
			outEntryJson.put("cashFlowNaturalCreditCurrency", "0");
			outEntryList.add(outEntryJson);
		}
		
		//多余部分公司打给报销人
		{
			outEntryJson.put("naturalCreditCurrency", expense.getAmount()/100);
			outEntryJson.put("cashItem", cashItem); 
			outEntryJson.put("remarkIId", expense.getU8Bank().getCode());
			outEntryJson.put("cashFlowNaturalCreditCurrency",  expense.getAmount()/100);
			outEntryList.add(outEntryJson);
		}
		return outEntryList;
	}
	
	/**
	 * 报销金额小于借款金额
	* @Title: getOutEntry  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param expense
	* @param @param cashItem
	* @param @param dd
	* @param @return    参数  
	* @return JSONArray    返回类型  
	* @throws
	 */
	public JSONArray getOutEntry(Expense expense,JSONArray inEntryList,String cashItem){
		//当报销金额小于借款金额  报销人需要将多余部分还给公司
		JSONObject inEntryJson = new JSONObject();
		Integer amount = expense.getLoanAmount()-expense.getTotalAmount();
		inEntryJson.put("naturalCreditCurrency", amount/100);
		if(expense.getU8Department() != null){
			inEntryJson.put("operator",expense.getU8Department().getSalesmanId());
			inEntryJson.put("deptId", expense.getU8Department().getCode());
			inEntryJson.put("personnelId",expense.getU8Department().getPersonnelCode());
		}else{
			throw new BusinessException("申请人未在U8部门表配置部门code");
		}
		if(expense.getU8Bank() != null ){
			inEntryJson.put("accountCode", expense.getU8Bank().getCode());
			inEntryJson.put("cashItem", cashItem); 
			inEntryJson.put("remarkIId", expense.getU8Bank().getCode());
		}else{
			throw new BusinessException("帐套对应科目银行为空");
		}
		inEntryJson.put("cashFlowNaturalCreditCurrency",amount/100);
		inEntryList.add(inEntryJson);
		
		//贷方分录
		JSONArray outEntryList = new JSONArray();
		JSONObject outEntryJson = new JSONObject();
		{
			outEntryJson.put("naturalCreditCurrency", expense.getLoanAmount()/100);
			if(expense.getU8Department() != null){
				outEntryJson.put("operator",expense.getU8Department().getSalesmanId());
				outEntryJson.put("deptId", expense.getU8Department().getCode());
				outEntryJson.put("personnelId",expense.getU8Department().getPersonnelCode());
			}else{
				throw new BusinessException("申请人未在U8部门表配置部门code");
			}
			if(expense.getU8Bank() != null ){
				outEntryJson.put("accountCode", expense.getPayeeType().getAccountCode()); //个人或企业对应科目编码
				outEntryJson.put("cashItem", ""); 
				outEntryJson.put("remarkIId", "");
			}else{
				throw new BusinessException("帐套对应科目银行为空");
			}
			outEntryJson.put("cashFlowNaturalCreditCurrency", "0");
			outEntryList.add(outEntryJson);
		}
		
		return outEntryList;
	}
}

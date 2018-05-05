package com.gongsibao.panda.supplier.igirl;

import com.gongsibao.entity.igirl.ic.baseinfo.AreaOne;
import com.gongsibao.entity.igirl.ic.baseinfo.AreaTwo;
import com.gongsibao.entity.igirl.ic.baseinfo.*;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.*;
import com.gongsibao.entity.igirl.tm.*;
import com.gongsibao.entity.igirl.tm.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.tm.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.tm.baseinfo.NCLTwo;
import com.gongsibao.entity.igirl.tm.baseinfo.NclBatch;
import com.gongsibao.entity.igirl.tm.baseinfo.NclMap;
import com.gongsibao.entity.igirl.tm.baseinfo.SupplierNewInfo;
import com.gongsibao.entity.igirl.tm.baseinfo.SupplierSiteInfo;
import com.gongsibao.entity.product.Product;
import com.gongsibao.igirl.ic.base.*;
import com.gongsibao.igirl.ic.base.IAreaOneService;
import com.gongsibao.igirl.ic.base.IAreaTwoService;
import com.gongsibao.igirl.tm.base.*;
import com.gongsibao.igirl.tm.service.HelpBookService;
import com.gongsibao.product.base.IProductService;
import org.junit.Test;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceTest extends ResourceCreationBase {

//	@Before
//	public void setup() {
//
//		parentNodeName = "IGIRL";
//		parentNodeCode = "GSB_IGIRL";
//		pluginName = "IGIRL";
//		seq = 3;
//		entityClass = ResourceNode.class;
//	}
	
	@Test
	public void run() {
		
		ResourceNode node = resourceNodeService.byCode("Gsb_Supplier_System");
		this.createResourceNodeVouchers(node);
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {
		
		ResourceNode node1 = null;

		ResourceNode nodeBase=node;
	
		
		node = this.createResourceNodeCategory("智能商标", "GSB_TRADE_AI", node.getId());

		
		node1 = this.createResourceNodeCategory("商标申请", "GSB_IGIRL_TM", node.getId());
		{
			this.createResourceNodeVoucher(TradeMarkCase.class.getName(), "方案生成", "IGIRL_All_" + TradeMarkCase.class.getSimpleName(), ITradeMarkCaseService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(UploadAttachment.class.getName(), "上传附件", "IGIRL_UPLOAD_" + UploadAttachment.class.getSimpleName(), IUploadAttachmentService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(DownloadAttachment.class.getName(), "下载附件", "IGIRL_DOWNLOAD_" + DownloadAttachment.class.getSimpleName(), IDownloadAttachmentService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(TradeMarkCase.class.getName(), "我的方案", "IGIRL_My_" + TradeMarkCase.class.getSimpleName(), ITradeMarkCaseService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TradeMark.class.getName(), "进度跟进", "IGIRL_All_"+TradeMark.class.getSimpleName(), ITradeMarkService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TradeMark.class.getName(), "我的跟进", "IGIRL_My_"+TradeMark.class.getSimpleName(), ITradeMarkService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(TradeMarkCase.class.getName(), "部门方案", "IGIRL_Dp_" + TradeMarkCase.class.getSimpleName(), ITradeMarkCaseService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TradeMark.class.getName(), "部门跟进", "IGIRL_Dp_"+TradeMark.class.getSimpleName(), ITradeMarkService.class.getName(), node1.getId());

			this.createResourceNodeVoucher(Product.class.getName(), "商标注册产品参照", "IGIRL_Product_" + TradeMarkCase.class.getSimpleName(), IProductService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TradeMark.class.getName(), "异常商标", "IGIRL_AllFollowNotice_"+TradeMark.class.getSimpleName(), ITradeMarkService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TradeMark.class.getName(), "部门异常", "IGIRL_DpFollowNotice_"+TradeMark.class.getSimpleName(), ITradeMarkService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TradeMark.class.getName(), "我的异常", "IGIRL_FollowNotice_"+TradeMark.class.getSimpleName(), ITradeMarkService.class.getName(), node1.getId());
		}

		node1 = this.createResourceNodeCategory("商标变更", "GSB_IGIRL_CTM", node.getId());
		{
			this.createResourceNodeVoucher(ChangeTradeMark.class.getName(), "方案生成", "IGIRL_All_" + ChangeTradeMark.class.getSimpleName(), IChangeTradeMarkService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ChangeTradeMark.class.getName(), "我的跟进", "IGIRL_My_" + ChangeTradeMark.class.getSimpleName(), IChangeTradeMarkService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ChangeTradeMark.class.getName(), "部门跟进", "IGIRL_Dp_" + ChangeTradeMark.class.getSimpleName(), IChangeTradeMarkService.class.getName(), node1.getId());
		}
		node1 = this.createResourceNodeCategory("商标转让", "GSB_IGIRL_TTM", node.getId());
		{
			this.createResourceNodeVoucher(TransferTradeMark.class.getName(), "方案生成", "IGIRL_All_" + TransferTradeMark.class.getSimpleName(), ITransferTradeMarkService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TransferTradeMark.class.getName(), "我的跟进", "IGIRL_My_" + TransferTradeMark.class.getSimpleName(), ITransferTradeMarkService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TransferTradeMark.class.getName(), "部门跟进", "IGIRL_Dp_" + TransferTradeMark.class.getSimpleName(), ITransferTradeMarkService.class.getName(), node1.getId());
		}
		node1 = this.createResourceNodeCategory("微门户", "GSB_IGIRL_SITE", node.getId());
		{
			this.createResourceNodeVoucher(SupplierSiteInfo.class.getName(), "站点信息", "IGRIL_SITE_" + SupplierSiteInfo.class.getSimpleName(), ISupplierSiteInfoService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(SupplierNewInfo.class.getName(), "最新资讯", "IGRIL_SITE_" + SupplierNewInfo.class.getSimpleName(), ISupplierNewInfoService.class.getName(), node1.getId());
		}
		node1 = this.createResourceNodeCategory("基础信息", "GSB_IGIRL_BASE", node.getId());
		{
			this.createResourceNodeVoucher(NCLOne.class.getName(), "商标大类", "IGRIL_BASE_" + NCLOne.class.getSimpleName(), INCLOneService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NCLTwo.class.getName(), "商标小类", "IGRIL_BASE_" +NCLTwo.class.getSimpleName(), INCLTwoService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NclBatch.class.getName(), "尼斯期间", "NCL_All_" + NclBatch.class.getSimpleName(), INclBatchService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(IGirlConfig.class.getName(), "参数设置", "IGRIL_BASE_" + IGirlConfig.class.getSimpleName(), IGirlConfigService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(NclMap.class.getName(), "尼斯映射", "NCL_All_" + NclMap.class.getSimpleName(), INclMapService.class.getName(), node1.getId());
			
		}
		node1 = this.createResourceNodeCategory("关于", "GSB_IGIRL_ABOUT", node.getId());
		{
			this.createResourceNodeVoucher(HelpBook.class.getName(), "帮助手册", "IGRIL_ABOUT_" + HelpBook.class.getSimpleName(), HelpBookService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(HelpBook.class.getName()+"administrator", "管理员篇", "IGRIL_ABOUT_ADMINISTRATOR", HelpBookService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(HelpBook.class.getName()+"_salesman", "业务员篇", "IGRIL_ABOUT_SALESMAN", HelpBookService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(HelpBook.class.getName()+"_manager", "部门负责人篇", "IGRIL_ABOUT_MANAGER", HelpBookService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(HelpBook.class.getName()+"_customer", "客户篇", "IGRIL_ABOUT_CUSTOMER", HelpBookService.class.getName(), node1.getId());
			
		}
		node=nodeBase;
		node = this.createResourceNodeCategory("智能工商", "GSB_IC_AI", node.getId());

		node1 = this.createResourceNodeCategory("基础信息", "GSB_IGIRL_IC_BASE", node.getId());
		{
			this.createResourceNodeVoucher(AreaOne.class.getName(), "省级列表", "IGRIL_IC_BASE_" + AreaOne.class.getSimpleName(), IAreaOneService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AreaTwo.class.getName(), "地市列表", "IGRIL_IC_BASE_" +AreaTwo.class.getSimpleName(), IAreaTwoService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ChapterOne.class.getName(), "刻章区", "IGRIL_IC_BASE_" +ChapterOne.class.getSimpleName(), IChapterOneService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ChapterTwo.class.getName(), "刻章公司", "IGRIL_IC_BASE_" +ChapterTwo.class.getSimpleName(), IChapterTwoService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(EntLicType.class.getName(), "证照类型", "IGRIL_IC_BASE_" +EntLicType.class.getSimpleName(), IEntLicTypeService.class.getName(), node1.getId());
            this.createResourceNodeVoucher(Nationality.class.getName(), "国籍", "IGRIL_IC_BASE_" +Nationality.class.getSimpleName(), INationalityService.class.getName(), node1.getId());

		}

		node1 = this.createResourceNodeCategory("信息注册", "GSB_IGIRL_IC_REGIST", node.getId());
		{
			this.createResourceNodeVoucher(ExcelBaseInfo.class.getName(), "核名信息", "IGRIL_IC_REGIST_" + ExcelBaseInfo.class.getSimpleName(), IcExcelBaseInfoService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CompanyName.class.getName(), "公司名称", "IGRIL_IC_REGIST_" + CompanyName.class.getSimpleName(), IcCompanyNameService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Member.class.getName(), "成员信息", "IGRIL_IC_REGIST_" + Member.class.getSimpleName(), IcMemberService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Shareholder.class.getName(), "股东信息", "IGRIL_IC_REGIST_" + Shareholder.class.getSimpleName(), IcShareholderService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CorporateAddress.class.getName(), "企业住址", "IGRIL_IC_REGIST_" + CorporateAddress.class.getSimpleName(), IcCorporateAddressService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Worker.class.getName(), "职员信息", "IGRIL_IC_REGIST_" + Worker.class.getSimpleName(), IcWorkerService.class.getName(), node1.getId());
		}
		node1 = this.createResourceNodeCategory("工商状态", "GSB_IGIRL_IC_STATE", node.getId());
		{
			this.createResourceNodeVoucher(IcExRegisterCase.class.getName(), "工商状态", "IGRIL_IC_STATE_" + IcExRegisterCase.class.getSimpleName(), IcExRegisterService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(IcExLog.class.getName(), "更新日志", "IGRIL_IC_STATE_" + IcExLog.class.getSimpleName(), IcExLogService.class.getName(), node1.getId());
		}

	}
}







package com.gongsibao.panda.platform.basic;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.IResourceNodeService;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.gardian.baseinfo.Device;
import com.gongsibao.entity.gardian.baseinfo.Provides;
import com.gongsibao.entity.gardian.baseinfo.ProvidesEnv;
import com.gongsibao.entity.trade.dto.ManualVoucherOrderDTO;
import com.gongsibao.entity.trade.dto.PayReceiptCheckDTO;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.entity.u8.VoucherLog;
import com.gongsibao.gardian.base.IDeviceService;
import com.gongsibao.gardian.base.IProvidesEnvService;
import com.gongsibao.gardian.base.IProvidesService;
import com.gongsibao.u8.base.IManualVoucherOrderDTOService;
import com.gongsibao.u8.base.IPayReceiptCheckDTOService;
import com.gongsibao.u8.base.ISetOfBooksService;
import com.gongsibao.u8.base.IU8BankService;
import com.gongsibao.u8.base.IVoucherLogService;

public class ResourceTest extends ResourceCreationBase {

	public static String resourcePrefix = "GSB_Basic";

	IResourceNodeService service = ServiceFactory.create(IResourceNodeService.class);

	// 初始化资源 init
	@Before
	public void setup() {
		parentNodeName = "基础配置";
		parentNodeCode = resourcePrefix;
		pluginName = "基础配置";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	// 创建资源节点
	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		String prefix = ResourceTest.resourcePrefix;
		ResourceNode node1 = null;
		
		node1 = this.createResourceNodeCategory("基础信息", prefix + "_Config", node.getId());
		{
			this.createResourceNodeVoucher(Dict.class.getName(), "数据字典", node1.getCode() + "_" + Dict.class.getSimpleName(), IDictService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("U8配置", prefix + "_U8", node.getId());
		{
			this.createResourceNodeVoucher(SetOfBooks.class.getName(), "账套列表", node1.getCode() + "_" + SetOfBooks.class.getSimpleName(), ISetOfBooksService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(U8Bank.class.getName(), "科目银行", node1.getCode() + "_" + U8Bank.class.getSimpleName(), IU8BankService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(VoucherLog.class.getName(), "凭证日志", node1.getCode() + "_" + VoucherLog.class.getSimpleName(), IVoucherLogService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(PayReceiptCheckDTO.class.getName(), "回单核对", node1.getCode() + "_" + PayReceiptCheckDTO.class.getSimpleName(), IPayReceiptCheckDTOService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ManualVoucherOrderDTO.class.getName(), "手动凭证", node1.getCode() + "_" + ManualVoucherOrderDTO.class.getSimpleName(), IManualVoucherOrderDTOService.class.getName(), node1.getId());
			//this.createResourceNodeVoucher(ReceivablesAuditDTO.class.getName(), "收款审核列表", node1.getCode() + "_" + ReceivablesAuditDTO.class.getSimpleName(), IReceivablesAuditDTOService.class.getName(), node1.getId());
		}
		
		node1 = this.createResourceNodeCategory("基础信息", "GSB_GARDIAN_BASE", node.getId());
		{
			this.createResourceNodeVoucher(Device.class.getName(), "设备信息", "GARDIAN_BASE_" + Device.class.getSimpleName(), IDeviceService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Provides.class.getName(), "服务信息", "GARDIAN_BASE_" + Provides.class.getSimpleName(), IProvidesService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ProvidesEnv.class.getName(), "服务环境", "GARDIAN_BASE_" + ProvidesEnv.class.getSimpleName(), IProvidesEnvService.class.getName(), node1.getId());
			//this.createResourceNodeVoucher(NCLTwo.class.getName(), "服务信息", "GARDIAN_BASE_" +NCLTwo.class.getSimpleName(), INCLTwoService.class.getName(), node1.getId());
		}
	}
}
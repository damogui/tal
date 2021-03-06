package com.gongsibao.panda.platform.basic;

import com.gongsibao.entity.u8.U8Department;
import org.junit.Before;
import org.netsharp.meta.base.NavigationBase;
import org.netsharp.panda.plugin.entity.PNavigation;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.gardian.baseinfo.Device;
import com.gongsibao.entity.gardian.baseinfo.Provides;
import com.gongsibao.entity.trade.dto.ManualVoucherOrderDTO;
import com.gongsibao.entity.trade.dto.PayReceiptCheckDTO;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.entity.u8.VoucherLog;

public class NavigationTest extends NavigationBase {

	//初始化导航信息
	@Before
	public void setup() {
		this.treeName = "基础配置";
		this.treePath = "panda/gsb/u8";
		this.resourceNode = "GSB_Basic";
	}

	public void createAccodions() {

		this.doCreateAccodions("GSB_Basic", "基础配置", "fa fa-file fa-fw", 1);//顶部和右侧顶部（配置菜单名称和图标、个数等）
	}

	//创建菜单树节点
	@Override
	protected void doCreateTree(PNavigation tree) {

		createPTreeNode(tree, null, "fa fa-file-o fa-fw", "GSB_Basic_Config", "基础信息", "", 1);//一级菜单
		{
			createPTreeNode(tree, "GSB_Basic_Config", null, "GSB_Basic_Config_" + Dict.class.getSimpleName(), "数据字典", "/basic/config/dict/list", 1);//二级菜单
		}
		
		createPTreeNode(tree, null, "fa fa-file-o fa-fw", "GSB_Basic_U8", "U8配置", "", 2);//一级菜单
		{
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + SetOfBooks.class.getSimpleName(), "账套列表", "/basic/u8/setofBbooks/list", 10);//二级菜单
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + U8Bank.class.getSimpleName(), "科目银行", "/basic/u8/bank/list", 20);//二级菜单
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + U8Department.class.getSimpleName(), "部门编码", "/basic/u8/department/list", 30);//二级菜单
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + VoucherLog.class.getSimpleName(), "凭证日志", "/basic/u8/voucherLog/list", 40);//二级菜单
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + PayReceiptCheckDTO.class.getSimpleName(), "回单管理", "/basic/u8/receiptcheck/list", 50);//二级菜单
			createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + ManualVoucherOrderDTO.class.getSimpleName(), "手动凭证", "/basic/u8/manualvoucherorder/list", 60);//二级菜单
			//createPTreeNode(tree, "GSB_Basic_U8", null, "GSB_Basic_U8_" + ReceivablesAuditDTO.class.getSimpleName(), "收款审核列表", "/basic/u8/receivablesaudit/list", 3);//二级菜单
		}
		
		createPTreeNode(tree, null, null, "GSB_GARDIAN_BASE", "Gardian", "", 3);
		{
			createPTreeNode(tree, "GSB_GARDIAN_BASE", null, "GARDIAN_BASE_" + Device.class.getSimpleName(), "设备列表", "/gardian/device/list", 1);
			createPTreeNode(tree, "GSB_GARDIAN_BASE", null, "GARDIAN_BASE_" + Provides.class.getSimpleName(), "服务列表", "/gardian/provides/list", 1);
			//createPTreeNode(tree, "GSB_IGIRL_BASE", null, "IGRIL_BASE_" + NCLTwo.class.getSimpleName(), "商标小类", "/igirl/ncltwo/all/list", 2);

		}
	}
}
package com.gongsibao.panda.supplier.order.action.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.supplier.crm.action.BaseActionTest;
import com.gongsibao.trade.service.action.order.invoice.ActionApplyInvoiceAudit;
import com.gongsibao.trade.service.action.order.invoice.ActionApplyInvoicePersist;
import com.gongsibao.trade.service.action.order.invoice.ActionApplyInvoiceSendMessage;
import com.gongsibao.trade.service.action.order.invoice.ActionApplyInvoiceVerify;

/**   
 * @ClassName:  ApplyInvoiceActionTest   
 * @Description:TODO 申请发票
 * @author: 韩伟
 * @date:   2018年3月12日 下午5:06:40   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ApplyInvoiceActionTest  extends BaseActionTest{

	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/order/invoice";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("申请发票");
		}

		createBean(beanPath, "1.验证", ActionApplyInvoiceVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.保存", ActionApplyInvoicePersist.class.getName(), resourceNode, 200);
		createBean(beanPath, "4.审核", ActionApplyInvoiceAudit.class.getName(), resourceNode, 300);
		createBean(beanPath, "5.通知", ActionApplyInvoiceSendMessage.class.getName(), resourceNode, 400);
		beanPathService.save(beanPath);
	}
}

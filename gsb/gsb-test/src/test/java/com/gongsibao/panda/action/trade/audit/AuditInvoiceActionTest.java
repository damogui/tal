package com.gongsibao.panda.action.trade.audit;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.plugin.bean.BeanPath;

import com.gongsibao.panda.action.BaseActionTest;
import com.gongsibao.trade.service.action.audit.invoice.ActionAuditInvoiceSendMessage;
import com.gongsibao.trade.service.action.audit.invoice.ActionAuditInvoiceVerify;
import com.gongsibao.trade.service.action.audit.invoice.ActionAuditInvoiceWriteBack;

public class AuditInvoiceActionTest  extends BaseActionTest{
	@Before
	public void setup() {

		resourceNodeCode = "Gsb_Supplier_Order_Salesman_All";
		super.setup();
	}

	@Test
	public void save() {
		
		String pathName = "gsb/crm/audit/invoice";
		BeanPath beanPath = new BeanPath();
		{
			beanPath.toNew();
			beanPath.setPath(pathName);
			beanPath.setResourceNode(resourceNode);
			beanPath.setName("发票审核");
		}

		createBean(beanPath, "1.验证", ActionAuditInvoiceVerify.class.getName(), resourceNode, 100);
		createBean(beanPath, "2.回写", ActionAuditInvoiceWriteBack.class.getName(), resourceNode, 200);
		createBean(beanPath, "4.通知", ActionAuditInvoiceSendMessage.class.getName(), resourceNode, 300);
		beanPathService.save(beanPath);
	}
}

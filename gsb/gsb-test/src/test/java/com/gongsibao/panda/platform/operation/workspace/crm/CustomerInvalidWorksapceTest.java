package com.gongsibao.panda.platform.operation.workspace.crm;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.NCustomerAllListPart;
import com.gongsibao.crm.web.platform.PlatformCustomerAllListPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.dic.Important;

public class CustomerInvalidWorksapceTest extends WorkspaceCreationBase{
	
	@Before
	public void setup() {
		super.setup();
		entity = NCustomer.class;// 实体
        urlList = "/operation/customer/invalid/list";// 列表的url
        listPartName = formPartName = "无效客户";
        meta = MtableManager.getMtable(entity);// 获取实体元数据
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "Operation_CRM_Customer_Invalid";
        listPartImportJs = "/gsb/supplier/crm/base/js/customer-base-list.part.js|/gsb/platform/operation/crm/js/customer-all-list.part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        
        listFilter = "id not in (SELECT customer_id from n_crm_customer_task GROUP BY customer_id) and important =" + Important.INVALID.getValue();
        
        listPartJsController = NCustomerAllListPart.class.getName();
        listPartServiceController = PlatformCustomerAllListPart.class.getName();
	}
	
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.toNew();
            datagrid.setResourceNode(node);
            datagrid.setName("客户列表");
            datagrid.setShowCheckbox(false);
            datagrid.setToolbar("panda/datagrid/row/edit");
        }

        PDatagridColumn column = null;
        addColumn(datagrid, "updatorId", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
        addColumn(datagrid, "id", "客户ID", ControlTypes.TEXT_BOX, 80);
        addColumn(datagrid, "realName", "客户名称", ControlTypes.TEXT_BOX, 120);

        //公司名称
        column = addColumn(datagrid, "isMember", "是否会员", ControlTypes.TEXT_BOX, 100);
        {
            StringBuilder builder = new StringBuilder();
            builder.append("if(value===true){return '是   ';}");
            builder.append("else{");
            builder.append("if(row.mobile){");
            builder.append("var ctrl = workspace.parts.byIndex(0).key;");
            builder.append("return '否<a title=\\'开通会员\\' class=\\'grid-btn\\' href=javascript:'+ctrl+'.openMember('+row.id+',true);>");
            builder.append("<i style=\\'font-size: 12px;color:red;\\' class=\\'fa fa-user-plus\\'></i><a>");
            builder.append("<a title=\\'静默开通\\' class=\\'grid-btn\\' href=javascript:'+ctrl+'.openMember('+row.id+',false);>");
            builder.append("<i style=\\'font-size: 12px;\\' class=\\'fa fa-user-plus\\'></i><a>';");
            builder.append("}else{return '否';}");
            builder.append("}");
            column.setFormatter(builder.toString());
            //column.setAlign(DatagridAlign.CENTER);
        }
        column = addColumn(datagrid, "mobile", "手机号", ControlTypes.TEXT_BOX, 100);
        {
            column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'手机号\\')');");
        }
        column = addColumn(datagrid, "telephone", "座机", ControlTypes.TEXT_BOX, 100);
        {
            column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'座机号\\')');");
        }
        column = addColumn(datagrid, "qq", "QQ", ControlTypes.TEXT_BOX, 100);
        {
            column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'QQ号\\')');");
        }

        column = addColumn(datagrid, "weixin", "微信", ControlTypes.TEXT_BOX, 100);
        {
            column.setFormatter(" var ctrl=workspace.parts.byIndex(0).key; return eval(ctrl+'.contactFormatter(value,row,index,\\'微信号\\')');");
        }

        //其他联系方式

        column = addColumn(datagrid, "invalidRemark", "无效原因", ControlTypes.TEXT_BOX, 200);{
        	 StringBuilder builder = new StringBuilder();
             builder.append("if(value===null){return '';}");
             builder.append("else{");
             builder.append("return '<p title='+value+'>'+value+'</p>'");
             builder.append("}");
        	column.setFormatter(builder.toString());
        }
        addColumn(datagrid, "companyName", "关联公司", ControlTypes.TEXT_BOX, 200);
        addColumn(datagrid, "lastCustomerSource.name", "客户来源", ControlTypes.TEXT_BOX, 100);

//		最近商机来源
//		最近商机跟进人
//		最近商机费用部门
        addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 100);
        column = addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 20);
        {
            column.setOrderbyMode(OrderbyMode.DESC);
        }

        return datagrid;
    }
	
	@Override
    protected PQueryProject createQueryProject(ResourceNode node) {
        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        PQueryItem item = null;
        item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
        {
            item.setTooltip("输入客户ID、客户名称、联系方式、客户来源、关联企业");
            item.setWidth(350);
        }
        addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
        //addQueryItem(queryProject, "company.companyName", "关联企业", ControlTypes.TEXT_BOX);
        return queryProject;
    }

    // 默认的表单操作
    @Override
    protected void doOperation() {

        ResourceNode node = this.getResourceNode();
        operationService.addOperation(node, OperationTypes.view);
        operationService.addOperation(node, OperationTypes.add);
        operationService.addOperation(node, OperationTypes.update);
    }
}

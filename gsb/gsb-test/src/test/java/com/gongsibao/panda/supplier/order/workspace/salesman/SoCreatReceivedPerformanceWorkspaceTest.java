package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.OrderPerformanceDetailPart;
import com.gongsibao.trade.web.SoCreatReceivePerformanceFormPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win on 2018/3/5.
 */
/*创建回款业绩*/
public class SoCreatReceivedPerformanceWorkspaceTest  extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup();
        entity = SoOrder.class;
        urlForm = "/crm/order/salesman/creceivedperformance";
        listPartName = formPartName = "创建回款业绩";
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_CReceivedPerformance";
        listToolbarPath = "/crm/roworderaddperformance/toolbar";

        List<String> ss = new ArrayList<String> ();
        ss.add("/package/easyui/datagrid-cellediting.js");
        //ss.add("/gsb/trade/js/salesman-order-add-form.part.js");
//        ss.add("/gsb/gsb.customer.controls.js");
        formJsImport = StringManager.join("|", ss);
        //formJsController = SalesmanAddOrderFormPart.class.getName();
        formServiceController = SoCreatReceivePerformanceFormPart.class.getName();//处理回款业绩
    }

    @Test
    public void run() {

        createFormWorkspace();
    }
    @Test
    public void createListToolbar() {

        ResourceNode node = this.resourceService.byCode (resourceNodeCode);
        //OperationType ot1 = operationTypeService.byCode (OperationTypes.add);
        PToolbar toolbar = new PToolbar ();
        {
            toolbar.toNew ();
            toolbar.setPath (listToolbarPath);
            toolbar.setName ("所有订单操作");
            toolbar.setResourceNode (node);
            toolbar.setToolbarType (ToolbarType.BASE);

        }


        PToolbarItem item = PToolbarHelper.getPToolbarItem (EntityState.New, "receivePerformanceDetailAdd", PToolbarHelper.iconAdd,
                "新增", null, 1, "{controller}.add();");//allocation
        toolbar.getItems ().add (item);
        {

        }
        item = PToolbarHelper.getPToolbarItem (EntityState.New, "receivePerformanceDetailDel", PToolbarHelper.iconDel,
                "删除", null, 1, "{controller}.remove();");
        toolbar.getItems ().add (item);


        toolbarService.save (toolbar);

    }


    // 默认的表单配置信息
    protected PForm createForm(ResourceNode node) {

        PForm form = super.createForm (node);
        form.setColumnCount (3);
        PFormField formField = null;

        String groupName = null;
        String groupName2="回款申请";

        formField = addFormField (form, "no", "订单编号", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }

        formField = addFormField (form, "payablePrice", "订单金额", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "paidPrice", "已付金额", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "customer.realName", "客户", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "customer.mobile", "客户电话", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "createTime", "下单时间", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "sourceType", "订单来源", groupName, ControlTypes.ENUM_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "payStatus", "付款状态", groupName, ControlTypes.ENUM_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "stageNum", "分期次数", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "channelOrderNo", "渠道订单号", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }



        formField = addFormField (form, "performancePrice", "已划分金额", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "remark", "备注", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);
            formField.setWidth (120);

        }
        /*回款申请beg*/

        formField = addFormField (form, "isCarryOver", "有无结转", groupName2, ControlTypes.SWITCH_BUTTON, false);
        {
            formField.setReadonly (true);

        }

        formField = addFormField (form, "carryOverOrderId", "结转来源订单号", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        formField = addFormField (form, "carryAmount", "结转金额", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }


//        formField = addFormFieldRefrence (form, "supplier.name", "付款账套", groupName2, SetOfBooks.class.getSimpleName (), true, false);//进行联动
//        {
//            //SetOfBooks
//            //formField.setTroikaTrigger ("controllerdepReceivable.supplierChange(newValue,oldValue);");
//        }
//
//        formField = addFormFieldRefrence (form, "department.name", "付款方式", groupName2, U8Bank.class.getSimpleName (), true, false);
//        {
//            //U8Bank
//            //formField.setTroikaTrigger ("controllerdepReceivable.departmentChange(newValue,oldValue);");
//        }
        formField = addFormField (form, "no", "付款账套", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (false);

        }
        formField = addFormField (form, "no", "付款方式", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (false);

        }

        formField = addFormField (form, "no", "付款账号名称", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (false);

        }
        formField = addFormField (form, "no", "付款账号", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (false);

        }

        formField = addFormField (form, "no", "是否一笔多单", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (false);

        }
        formField = addFormField (form, "no", "付款金额", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (false);

        }

        formField = addFormField (form, "remark", "付款凭证", groupName2, ControlTypes.OSS_UPLOAD, false);
        {
            formField.setReadonly (false);

        }
        formField = addFormField (form, "no", "付款说明", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (false);

        }


        /*回款申请end*/


        return form;
    }

    protected void addDetailGridPart(PWorkspace workspace) {

        // 关联部门回款表
        performancePart (workspace);
    }


    // 关联部门回款表
    public void performancePart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode ("Gsb_Supplier_Order_Salesman_Received");
        PDatagrid datagrid = new PDatagrid (node, "关联回款订单");
        {
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (false);
            datagrid.setReadOnly (false);
            datagrid.setShowTitle (true);
            datagrid.setToolbar (listToolbarPath);//新增和删除

            PDatagridColumn column = null;


            column = addColumn (datagrid, "supplier.name", "服务商", ControlTypes.TEXT_BOX, 150);

            column = addColumn (datagrid, "department.name", "部门", ControlTypes.NUMBER_BOX, 150);
            {

                column.setAlign (DatagridAlign.CENTER);
            }

            column = addColumn (datagrid, "salesman.name", "业务员", ControlTypes.TEXT_BOX, 150);
            column = addColumn (datagrid, "amount", "分配金额", ControlTypes.DECIMAL_FEN_BOX, 150);

        }
/*表单beg*/
        PForm form = new PForm ();
        {
            form.toNew ();
            form.setResourceNode (node);
            form.setColumnCount (1);
            form.setName ("业绩划分金额表");

            // form.setTag ();//设置提示
            form.setLabelWidth (100);
            PFormField formField = null;
            formField = addFormFieldRefrence (form, "supplier.name", "服务商", null, Supplier.class.getSimpleName (), true, false);
            {
                formField.setTroikaTrigger ("controllerdepReceivable.supplierChange(newValue,oldValue);");
            }

            formField = addFormFieldRefrence (form, "department.name", "部门", null, SupplierDepartment.class.getSimpleName (), true, false);
            {
                formField.setTroikaTrigger ("controllerdepReceivable.departmentChange(newValue,oldValue);");
            }

            formField = addFormFieldRefrence (form, "salesman.name", "业务员", null, Employee.class.getSimpleName (), true, false);
            formField = addFormField (form, "amount", "分配金额", null, ControlTypes.DECIMAL_FEN_BOX, true, false);

            {
//                //formField.setWidth (300);
//              formField.setHeight (100);

            }

        }

            /*表单end*/
        PPart part = new PPart ();
        {
            part.toNew ();
            part.setName ("业绩划分");
            part.setCode ("depReceivable");
            part.setParentCode (ReflectManager.getFieldName (meta.getCode ()));
            part.setRelationRole ("depReceivable");
            part.setResourceNode (node);
            part.setPartTypeId (PartType.DETAIL_PART.getId ());
            part.setDatagrid (datagrid);
            part.setDockStyle (DockType.DOCUMENTHOST);
            part.setToolbar (listToolbarPath);
            part.setForm (form);
            part.setWindowWidth (400);
            part.setWindowHeight (450);

//         part.setServiceController (OrderPerformanceDetailPart.class.getName ());
            part.setJsController (OrderPerformanceDetailPart.class.getName ());
        }
        workspace.getParts ().add (part);

        part = workspace.getParts ().get (0);
        {
            part.setName ("创建订单业绩");
            part.setDockStyle (DockType.TOP);
            part.setHeight (500);
            part.setHeight (450);
        }
    }

    // 默认的表单操作
    @Override
    protected void doOperation() {

        ResourceNode node = this.getResourceNode ();
        operationService.addOperation (node, OperationTypes.view);
        operationService.addOperation (node, OperationTypes.add);
    }

}

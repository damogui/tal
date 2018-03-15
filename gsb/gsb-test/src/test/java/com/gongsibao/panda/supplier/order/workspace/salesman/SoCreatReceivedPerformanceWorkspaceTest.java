package com.gongsibao.panda.supplier.order.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.OrderReceivePerformanceDetailPart;
import com.gongsibao.trade.web.SoCreatOrderPerformanceListPart;
import com.gongsibao.trade.web.SoCreatReceivePerformanceFormPart;

/**
 * Created by win on 2018/3/5.
 */
/*创建回款业绩*/
public class SoCreatReceivedPerformanceWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {
        super.setup ();
        entity = SoOrder.class;
        urlForm = "/crm/order/salesman/creceivedperformance";
        formToolbarPath = "";
        listPartName = formPartName = "创建回款业绩";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_CReceivedPerformance";
        listToolbarPath = "/crm/roworderaddreceiveformance/toolbar";

        List<String> ss = new ArrayList<String> ();
        ss.add("/package/easyui/datagrid-cellediting.js");
        ss.add ("/gsb/platform/trade/js/so-receiveperformance-add.part.js");
        ss.add ("/gsb/panda-extend/gsb.customer.controls.js");
        formJsImport = StringManager.join ("|", ss);
        listPartJsController = SoCreatOrderPerformanceListPart.class.getName ();

        formJsController = SoCreatReceivePerformanceFormPart.class.getName();
        formServiceController = SoCreatReceivePerformanceFormPart.class.getName ();//处理回款业绩
    }

    @Test
    public void run() {

        createFormWorkspace ();
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
        String groupName2 = "回款信息";

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



        formField = addFormField (form, "remark", "备注", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);
            formField.setWidth (400);

        }
        /*回款申请beg*/

        formField = addFormField (form, "isOnlinePay", "是否线上支付", groupName2, ControlTypes.SWITCH_BUTTON, true);
        {
            formField.setTroikaTrigger("controllerpays.isOnlineChange(checked);");
            formField.setReadonly (false);
            //formField.setDataOptions("有|无");


        }

        formField = addFormField (form, "onLineNotCutPay", "在线支付未创建业绩总额", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);
            //formField.setRowSpan (2);
           // formField.setColumnSpan (2);
            //formField.setFullColumn (true);

        }



        formField = addFormFieldRefrence (form, "pays.u8Bank.setOfBooks.name", "付款账套", groupName2, SetOfBooks.class.getSimpleName (), true, false);//进行联动
        {


            formField.setTroikaTrigger ("controllerpays.bankBooksChange(newValue,oldValue);");

        }

        formField = addFormFieldRefrence (form, "pays.u8Bank.name", "付款方式", groupName2,"SupplierU8Bank", true, false);
        {
            formField.setRefFilter ("set_of_books_id=1");

        }


        formField = addFormField (form, "offlinePayerName", "付款账号名称", groupName2, ControlTypes.TEXT_BOX, true,false);

        formField = addFormField (form, "offlineBankNo", "付款账号", groupName2, ControlTypes.TEXT_BOX, true,false);

        formField = addFormField (form, "payForOrderCount", "是否一笔多单", groupName2, ControlTypes.SWITCH_BUTTON, true,false);
        formField = addFormField (form, "amount", "付款金额", groupName2, ControlTypes.TEXT_BOX, true,false);

        formField = addFormField (form, "offlineRemark", "付款说明", groupName2, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (false);

        }



        formField = addFormField (form, "files", "付款凭证", groupName2, ControlTypes.OSS_UPLOAD, true,false);

        formField = addFormField (form, "imagefiles", "付款凭证图片", groupName2, ControlTypes.IMAGE, false);
        {

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


            column = addColumn (datagrid, "orderId", "订单号", ControlTypes.TEXT_BOX, 150);//depPays.supplier.name
            column = addColumn (datagrid, "orderCutAmount", "订单分配金额", ControlTypes.TEXT_BOX, 150);
            column = addColumn (datagrid, "payTypeStr", "付款类别", ControlTypes.TEXT_BOX, 150);
            column = addColumn (datagrid, "supperName", "回款业绩分配服务商", ControlTypes.TEXT_BOX, 200);
            column = addColumn (datagrid, "depName", "回款业绩分配部门", ControlTypes.TEXT_BOX, 200);
            column = addColumn (datagrid, "cutMan", "回款业绩分配业务员", ControlTypes.TEXT_BOX, 150);
            column = addColumn (datagrid, "cutAmountStr", "回款业绩分配金额", ControlTypes.TEXT_BOX, 150);


         }
            /*表单beg*/


            /*表单end*/
        PPart part = new PPart ();
        {
            part.toNew ();
            part.setName ("业绩回款");
            part.setCode ("pays");
            part.setParentCode (ReflectManager.getFieldName (meta.getCode ()));
            part.setRelationRole ("pays");//pays.depPays
            part.setResourceNode (node);
            part.setPartTypeId (PartType.DETAIL_PART.getId ());
            part.setDatagrid (datagrid);
            part.setDockStyle (DockType.DOCUMENTHOST);
            part.setToolbar (listToolbarPath);
            // part.setForm (form);
            part.setWindowWidth (400);
            part.setWindowHeight (450);

           part.setServiceController (OrderReceivePerformanceDetailPart.class.getName ());
            part.setJsController (OrderReceivePerformanceDetailPart.class.getName ());
        }
        workspace.getParts ().add (part);

        part = workspace.getParts ().get (0);
        {
            part.setName ("创建回款业绩");
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

package com.gongsibao.panda.supplier.order.workspace.salesman;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.controls.DictComboBox;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.*;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.OperationType;
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
/*创建订单业绩*/
public class SoCreatOrderPerformanceWorkspaceTest extends WorkspaceCreationBase {

    @Before
    public void setup() {
        super.setup ();
        entity = SoOrder.class;
        urlForm = "/crm/order/salesman/coperformance";
        listPartName = formPartName = "订单信息";
        meta = MtableManager.getMtable (entity);
        resourceNodeCode = "Gsb_Supplier_Order_Salesman_CoPerformance";
        listToolbarPath = "/crm/roworderaddperformance/toolbar";
        formToolbarPath = "";
        List<String> ss = new ArrayList<String> ();
        //ss.add ("/gsb/trade/js/order_performance-form.part.js");
        ss.add ("/gsb/trade/js/so-performance-add.part.js");
        ss.add ("/gsb/gsb.customer.controls.js");
        formJsImport = StringManager.join ("|", ss);
        listPartJsController = SoCreatOrderPerformanceListPart.class.getName ();

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


        PToolbarItem item = PToolbarHelper.getPToolbarItem (EntityState.New, "performanceDetailAdd", PToolbarHelper.iconAdd,
                "新增", null, 1, "{controller}.add();");//allocation
        toolbar.getItems ().add (item);{

        }
        item = PToolbarHelper.getPToolbarItem (EntityState.New, "performanceDetailDel", PToolbarHelper.iconDel,
                "删除", null, 1, "{controller}.remove();");
        toolbar.getItems ().add (item);

        item = PToolbarHelper.getPToolbarItem (EntityState.New, "performanceDetailSave", PToolbarHelper.iconAdd,
                "保存", null, 1, "{controller}.savebase();");
        {

        }
        toolbar.getItems ().add (item);

        toolbarService.save (toolbar);

    }


    // 默认的表单配置信息
    protected PForm createForm(ResourceNode node) {

        PForm form = super.createForm (node);
        form.setColumnCount (3);
        PFormField formField = null;

        String groupName = null;

        formField = addFormField (form, "no", "订单编号", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }

        formField = addFormField (form, "payablePrice", "订单金额", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }
        {
            formField.setReadonly (true);

        }

        formField = addFormField (form, "performancePrice", "已划分金额", groupName, ControlTypes.TEXT_BOX, false);
        {
            formField.setReadonly (true);

        }

        return form;
    }

    protected void addDetailGridPart(PWorkspace workspace) {

        // 业绩划分
        performancePart (workspace);
    }


    // 业绩划分
    public void performancePart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode ("Gsb_Supplier_Order_Salesman_performance");
        PDatagrid datagrid = new PDatagrid (node, "业绩划分");
        {
            datagrid.setShowCheckbox (true);
            datagrid.setSingleSelect (true);
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
            column = addColumn (datagrid, "amount", "订单业绩分配金额", ControlTypes.TEXT_BOX, 150);

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
            formField = addFormFieldRefrence (form, "supplier.name", "服务商", null, Supplier.class.getSimpleName (), false, false);
            {
                //formField.setTroikaTrigger ("controllernCustomerTask.supplierChange(newValue,oldValue);");
            }

            formField = addFormFieldRefrence (form, "department.name", "部门", null, SupplierDepartment.class.getSimpleName (), false, false);
            {
                //formField.setTroikaTrigger ("controllernCustomerTask.departmentChange(newValue,oldValue);");
            }

            formField = addFormFieldRefrence (form, "salesman.name", "业务员", null, Employee.class.getSimpleName (), false, false);
            formField = addFormField (form, "amount", "订单业绩分配金额", null, ControlTypes.TEXT_BOX, false, false);

            {
                //formField.setWidth (300);
//                formField.setHeight (100);
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

//           part.setServiceController (OrderPerformanceDetailPart.class.getName ());
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

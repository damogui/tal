package com.gongsibao.panda.crm.workspace.sys;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.controls.DictComboBox;
import com.gongsibao.crm.web.DepartmentProductDetailPart;
import com.gongsibao.crm.web.SalesmaProductDetailPart;
import com.gongsibao.crm.web.SysSalesmanListPart;
import com.gongsibao.entity.product.Product;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.SysDepartmentTreeGridPart;
import com.gongsibao.entity.supplier.SupplierDepartment;
import org.netsharp.util.ReflectManager;

//部门管理工作空间
public class SysDepartmentWorkspaceTest extends WorkspaceCreationBase {
	
	public void setup() {
		super.setup();
		urlList = "/crm/sys/department/list";
		urlForm = "/crm/sys/department/form";
		entity = SupplierDepartment.class;
		meta = MtableManager.getMtable(entity);
		listPartType = PartType.TREEGRID_PART.getId();
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_SYS_DEPARTMENT";
		formOpenMode = OpenMode.TABS;
		listPartImportJs="/gsb/crm/sys/js/sys-department-list-part.js";
		listPartJsController = SysDepartmentTreeGridPart.class.getName();
		listPartServiceController = SysDepartmentTreeGridPart.class.getName();
        formJsImport = "/gsb/crm/js/department-form.part.js|/gsb/gsb.customer.controls.js";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("部门管理");
			datagrid.setAutoQuery(false);
		}
		PDatagridColumn column = null;

		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		column = addColumn(datagrid, "parentId", "parentId", ControlTypes.TEXT_BOX, 100);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "isLeaf", "isLeaf", ControlTypes.TEXT_BOX, 100);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "部门名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	// 表单填充字段
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true);
		PFormField formField = addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, false, false);{
			
			formField.setHeight(100);
			formField.setFullColumn(true);
		}
		return form;
	}



//    @Test
//    @Override
//    public void run() {
//
//
//        this.createFormWorkspace();
//    }
    // 创建明细里面的弹窗操作
    @Override
    protected void addDetailGridPart(PWorkspace workspace) {
        // 服务范围
        addScopesDetailPart(workspace);
    }

    private void addScopesDetailPart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode("GSB_CRM_SYS_Department_Product");
        PDatagrid datagrid = new PDatagrid(node, "添加服务范围");
        {
            PDatagridColumn column = null;
            addColumn(datagrid, "productCategory1.name", "一级分类", ControlTypes.TEXT_BOX, 100, false);
            addColumn(datagrid, "productCategory2.name", "二级分类", ControlTypes.TEXT_BOX, 100, false);
            addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 200, false);
            addColumn(datagrid, "province.name", "省", ControlTypes.TEXT_BOX, 150, false);
            addColumn(datagrid, "city.name", "市", ControlTypes.TEXT_BOX, 150, false);
            addColumn(datagrid, "county.name", "区", ControlTypes.TEXT_BOX, 150, false);

        }

        PForm form = new PForm();// 添加表单
        {
            form.setResourceNode(node);
            form.toNew();
            form.setColumnCount(1);
            form.setName("添加服务范围");

            PFormField formField = null;
            formField = addFormField(form, "productCategory1.name", "一级分类", null, ControlTypes.CUSTOM, true, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                formField.setTroikaTrigger("controllerserviceProducts.productCategory1Select(record);");
                formField.setRefFilter("type=201 and pid=0");
            }

            formField = addFormField(form, "productCategory2.name", "二级分类", null, ControlTypes.CUSTOM, true, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(DictComboBox.class.getName());
                formField.setTroikaTrigger("controllerserviceProducts.productCategory2Select(record);");
                formField.setRefFilter("type=201 and pid<>0");
            }

            formField = addFormFieldRefrence(form, "product.name", "产品", null, "CRM_" + Product.class.getSimpleName(), true, false);
            {
                formField.setWidth(200);
                formField.setRefFilter("enabled=1");
                formField.setTroikaTrigger("controllerserviceProducts.productChange(newValue,oldValue);");
            }
            formField = addFormField(form, "province.name", "省份", ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(CityComboBox.class.getName());
                formField.setDataOptions("level:1,changeCtrlId:'city_name'");
            }
            formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(CityComboBox.class.getName());
                formField.setDataOptions("level:2,changeCtrlId:'county_name'");
            }
            formField = addFormField(form, "county.name", "区/县", ControlTypes.CUSTOM, false, false);
            {
                formField.setWidth(200);
                formField.setCustomControlType(CityComboBox.class.getName());
                formField.setDataOptions("level:3");
            }

        }

        PPart part = new PPart();
        {
            part.toNew();
            part.setName("服务范围");
            part.setCode("serviceProducts");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("serviceProducts");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setToolbar("panda/datagrid/detail");
            part.setJsController(DepartmentProductDetailPart.class.getName());
            part.setServiceController(DepartmentProductDetailPart.class.getName());
            part.setWindowWidth(400);
            part.setWindowHeight(450);
            part.setForm(form);
        }
        workspace.getParts().add(part);
        part = workspace.getParts().get(0);
        {
            part.setName("基本信息");
            part.setDockStyle(DockType.TOP);
        }
    }



    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}

}

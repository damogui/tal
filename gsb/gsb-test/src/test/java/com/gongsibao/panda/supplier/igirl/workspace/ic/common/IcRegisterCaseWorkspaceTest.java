package com.gongsibao.panda.supplier.igirl.workspace.ic.common;

import com.gongsibao.entity.igirl.ic.IcRegisterCase;
import com.gongsibao.igirl.ic.web.BaseInfoDetailPart;
import com.gongsibao.igirl.ic.web.DirectorDetailPart;
import com.gongsibao.igirl.ic.web.IcRegisterCasePart;
import com.gongsibao.igirl.ic.web.UnPersonDetailPart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.*;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

/**
 * @ClassName:  ProductWorkspaceTest
 * @Description:TODO 地市分类
 * @author: 曹玉玺
 * @date:   2018.4.3
 *
 */
public class IcRegisterCaseWorkspaceTest extends WorkspaceCreationBase{

	public static final String trademarkToolbarPath = "/igirl/tm/toolbar";
	@Before
	public void setup() {
		super.setup();
		urlList = "/igirl/ic/IcRegisterCase/all/list";
		urlForm = "/igirl/IcRegisterCase/form";
		entity = IcRegisterCase.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_IC_REGIST_IcRegisterCase";
		formPartName = listPartName = meta.getName();
		listToolbarPath="/igirl/IcRegisterCase/list";
		formServiceController = IcRegisterCasePart.class.getName();
		formJsController = IcRegisterCasePart.class.getName();
		formJsImport = "/gsb/igirl/js/icregistercase.form.part.js";
	}

	public static final String icFormToolbarPath = "/igirl/ic/toolbar";

	/*按钮*/
	@Test
	public void fromToolbar() {

		ResourceNode node =	 this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("工具栏");
			toolbar.setResourceNode(node);

		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("edit");
			item.setIcon("fa fa-edit");
			item.setName("编辑");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.edit();");
			toolbar.getItems().add(item);
		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("remove");
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.remove();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);

		ot1 = operationTypeService.byCode(OperationTypes.add);
		toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(icFormToolbarPath);
			toolbar.setName("上传附件工具栏");
			toolbar.setResourceNode(node);

		}
		item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("save");
			item.setIcon("fa fa-save");
			item.setName("保存");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.saveP();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}

	/*首页显示表格*/
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setAutoQuery(false);
		datagrid.setTreeField("orgName");
		datagrid.setLazy(true);
		PDatagridColumn column = null;

		column = addColumn(datagrid, "hm_number", "核名文号", ControlTypes.TEXT_BOX, 300, true);
		column = addColumn(datagrid, "hm_id", "核名身份证号", ControlTypes.TEXT_BOX, 300);{

			column.setAlign(DatagridAlign.CENTER);
		}

		return datagrid;
	}

	/*新增页显示表格*/
	/*新增页第一页效果*/
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(1);
		}
		PFormField field = null;
		addFormField(form, "hmNumber", "核名文号", null, ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "hmID", "核名身份证号", null, ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "companyName", "公司名称", null, ControlTypes.TEXT_BOX, true,false);

		addFormField(form, "customer.realName", "客户名称", null, ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "customer.mobile", "客户电话", null, ControlTypes.TEXT_BOX, true,false);
		return form;
	}

	/*新增页产生分页效果*/
	protected void addDetailGridPart(PWorkspace workspace) {
		createSecDetailPart(workspace);
		createThiDetailPart(workspace);
		createFouDetailPart(workspace);
	}
	/*新增页第二页效果*/
	private void createSecDetailPart(PWorkspace workspace) {

		/*这个service名字不用改*/
		ResourceNode node = this.resourceService.byCode("IGRIL_IC_REGIST_IcBaseInfo");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "基础信息");
		{
			addColumn(datagrid, "regCap", "注册资本", ControlTypes.TEXT_BOX, 100);
		}
		/*写成弹窗的形式，进行单独保存*/
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("基础信息");
			form.setColumnCount(2);
			String groupName = null;
			PFormField formField = null;
			addFormField(form, "regCap", "注册资本", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "tradeTerm", "营业期限", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "specIndustry", "是否属于特殊行业", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "domDistrict", "住所（经营场所）", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "domDetail", "住所详细信息", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "inpark", "是否位于中关村国家自主创新示范园区及“三城一区”内", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "proLocCity", "生产经营地", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "proLocOther", "生产经营地详细信息", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "domOwnType", "住所产权类型", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "domProRight", "住所提供方式", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "domTerm", "住所使用期限", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "specArea", "住所是否在以下地区", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "domAcreage", "营业面积", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "domUsageType", "房屋用途", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "sgzwFlag", "是否市级国有资产监督管理机构履行出资人职责的公司以及该公司设立的控股50%以上的公司", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "copyNo", "执照副本数", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "customScope", "经营范围", null, ControlTypes.TEXT_BOX, false,false);

		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("基础信息");
			part.setCode("baseInfo");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("baseInfo");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.FORM_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(icFormToolbarPath);
			part.setJsController("com.gongsibao.igirl.ic.web.BaseInfoDetailPart");
			part.setServiceController(BaseInfoDetailPart.class.getName());
			part.setWindowWidth(800);
			part.setWindowHeight(600);
			part.setForm(form);

		}
		workspace.getParts().add(part);
	}

	/*新增页第三页效果*/
	private void createThiDetailPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode("IGRIL_IC_REGIST_IcUnPerson");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "非自然人股东基本信息");
		{
			addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "invType", "单位类型", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "entLicType", "证照类型", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "nationality", "国别和地区", ControlTypes.TEXT_BOX, 100);
		}
		/*表单形式，单独保存*/
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("非自然人股东基本信息");
			form.setColumnCount(2);
			String groupName = null;
			PFormField formField = null;
			addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "invType", "单位类型", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "entLicType.name", "证照类型", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "nationality.name", "国别和地区", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "corpRpt", "法定代表人", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "prov.name", "身份证登记住址,省", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "city.name", "身份证登记住址区域", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "domOther", "身份证登记住址街道", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "money", "出资金额", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "way", "出资方式", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "time", "出资时间", null, ControlTypes.TEXT_BOX, false,false);

		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("非自然人股东基本信息");
			part.setCode("unPerson");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("unPerson");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.FORM_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(icFormToolbarPath);
			part.setJsController("com.gongsibao.igirl.ic.web.UnPersonDetailPart");
			part.setServiceController(UnPersonDetailPart.class.getName());
			part.setWindowWidth(800);
			part.setWindowHeight(600);
			part.setForm(form);

		}
		workspace.getParts().add(part);
	}

	/*新增页第四页效果*/
	private void createFouDetailPart(PWorkspace workspace) {
		ResourceNode node = this.resourceService.byCode("IGRIL_IC_REGIST_IcDirector");
		PDatagridColumn column = null;
		PDatagrid datagrid = new PDatagrid(node, "董事信息");
		{
			addColumn(datagrid, "name", "董事名字", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "nameEng", "董事英文名字", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "cerType", "董事证件类型", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "cerNo", "董事证件号码", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "country", "董事国籍", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "sex", "董事性别", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "mobTel", "董事移动电话号码", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "liteDeg", "董事文化程度", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "nation", "董事民族", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "polStand", "董事政治面貌", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "natDate", "董事出生日期", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "position", "董事职务", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "posBrForm", "董事产生方式", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "offYears", "董事任职期限", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "isManager", "董事是否兼任经理", ControlTypes.ENUM_BOX, 100);
			addColumn(datagrid, "houseAddProv.name", "董事户籍登记地址", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "houseAddCity.name", "董事户籍登记地址详细信息一", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "houseAddOther", "董事户籍登记地址详细信息二", ControlTypes.TEXT_BOX, 100);

		}
		/*表单形式，单独保存*/
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName("董事信息");
			form.setColumnCount(2);
			String groupName = null;
			PFormField formField = null;
			addFormField(form, "name", "董事名字", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "nameEng", "董事英文名字", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "cerType", "董事证件类型", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "cerNo", "董事证件号码", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "country", "董事国籍", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "sex", "董事性别", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "mobTel", "董事移动电话号码", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "liteDeg", "董事文化程度", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "nation", "董事民族", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "polStand", "董事政治面貌", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "natDate", "董事出生日期", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "position", "董事职务", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "posBrForm", "董事产生方式", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "offYears", "董事任职期限", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "isManager", "董事是否兼任经理", null, ControlTypes.ENUM_BOX, false,false);
			addFormField(form, "houseAddProv.name", "董事户籍登记地址", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "houseAddCity.name", "董事户籍登记地址详细信息一", null, ControlTypes.TEXT_BOX, false,false);
			addFormField(form, "houseAddOther", "董事户籍登记地址详细信息二", null, ControlTypes.TEXT_BOX, false,false);

		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("董事信息");
			part.setCode("director");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("director");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.FORM_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(icFormToolbarPath);
			part.setJsController("com.gongsibao.igirl.ic.web.DirectorDetailPart");
			part.setServiceController(DirectorDetailPart.class.getName());
			part.setWindowWidth(800);
			part.setWindowHeight(600);
			part.setForm(form);

		}
		workspace.getParts().add(part);
	}

	/*显示查询框*/
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "hmNumber", "核名文号", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}


}



package com.gongsibao.panda.supplier.igirl.workspace.ic.common;

import com.gongsibao.entity.igirl.ic.baseinfo.ChapterTwo;
import com.gongsibao.igirl.ic.web.ChapterTwoPart;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * @Description: java类作用描述
 * @UpdateDate: 2018/4/12 13:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChapterTwoWorkspaceTest extends WorkspaceCreationBase {
    @Before
    public void setup() {

        super.setup();
        urlList = "/igirl/ic/chaptertwo/all/list";
        urlForm = "/igirl/ic/chaptertwo/form";

        entity = ChapterTwo.class;
        meta = MtableManager.getMtable(entity);
        resourceNodeCode = "IGRIL_IC_BASE_ChapterTwo";
        formPartName = listPartName = meta.getName();
        formOpenMode = OpenMode.WINDOW;
        openWindowWidth = 800;
        openWindowHeight = 600;
        listToolbarPath="/igirl/chapterone/list";

        formServiceController = ChapterTwoPart.class.getName();
        formJsController = ChapterTwoPart.class.getName();
        formJsImport = "/gsb/igirl/js/chaptertwo.form.part.js";
        //通过重写service的save方法实现的保存操作，没有走listpart
//        listPartServiceController = ChapterTwoListPart.class.getName();
//        listPartJsController=ChapterTwoListPart.class.getName();
//        listPartImportJs="/gsb/igirl/js/chaptertwo.listpart.js";
    }

    @Override
    protected PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        {
            datagrid.setToolbar(listToolbarPath);
            datagrid.setName("刻章公司");
            datagrid.setOrderby("code");
            //datagrid.setLazy(true);
        }
        PDatagridColumn column = null;
        column=addColumn(datagrid, "code", "企业代码", ControlTypes.TEXT_BOX, 100);

        addColumn(datagrid, "name", "企业名称", ControlTypes.TEXT_BOX, 200);
        addColumn(datagrid, "arnCode",   "信用代码", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "specialCode",   "特行代码", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "address",   "经营地址", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "operationContent",   "经营范围", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "tel",   "联系电话", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "fax",   "传真", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "email",   "email", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "registeredCapital",   "注册资本", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "setUpTime",   "设立时间", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "secureMan",   "安全员", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "policeMan",   "民警", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "operationArea",   "经营面积", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "artificialPersonName",   "紧急联系人", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "artificialPersionMobile",   "紧急联系人手机", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "artificialPersionIdcard",   "紧急联系人身份证号", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "artificialPersionAddress",   "紧急联系人住址", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "artificialPersionCertType",   "紧急联系人确认状态", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "area",   "地区ID", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "sid",   "原数据ID", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "policeCode",   "原policeCode", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "user",   "原user", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "policeStation",   "原policeStation", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "factoryStatus",   "原factoryStatus", ControlTypes.TEXT_BOX, 100);
//        addColumn(datagrid, "arnPic",   "arnPic", ControlTypes.TEXT_BOX, 100);
//        addColumn(datagrid, "specialPic",   "specialPic", ControlTypes.TEXT_BOX, 100);
//        addColumn(datagrid, "addrimgPic",   "addrimgPic", ControlTypes.TEXT_BOX, 100);
//        addColumn(datagrid, "companyPic",   "companyPic", ControlTypes.TEXT_BOX, 100);
//        addColumn(datagrid, "sgmj",   "sgmj", ControlTypes.TEXT_BOX, 100);
//        addColumn(datagrid, "sjgj",   "sjgj", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "sswj",   "sswj", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "version",   "version", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "createdTime",   "createdTime", ControlTypes.TEXT_BOX, 100);
        addColumn(datagrid, "updatedTime",   "updatedTime", ControlTypes.TEXT_BOX, 100);
        return datagrid;
    }

    //
    @Override
    protected PForm createForm(ResourceNode node) {

        PForm form = new PForm(node, this.formPartName);
        {
            form.setColumnCount(1);
        }

        PFormField field = null;
        addFormField(form, "code", "企业代码", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "name", "企业名称", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "arnCode", "信用代码", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "specialCode", "特行代码", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "address", "经营地址", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "operationContent", "经营范围", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        field = addFormField(form, "tel", "联系电话", null, ControlTypes.TEXT_BOX, true,false);
        {
            field.setTroikaTrigger("controllerchapterTwo.PhoneVerify(this);");
        }
        addFormField(form, "fax", "传真", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        field = addFormField(form, "email", "email", null, ControlTypes.TEXT_BOX, true,false);
        {
            field.setTroikaTrigger("controllerchapterTwo.EmailVerify(this);");
        }
        addFormField(form, "registeredCapital", "注册资本", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "setUpTime", "设立时间", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "secureMan", "安全员", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "policeMan", "民警", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "operationArea", "经营面积", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "artificialPersonName", "紧急联系人", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        field =addFormField(form, "artificialPersionMobile", "紧急联系人手机", null, ControlTypes.TEXT_BOX, true,false);
        {
            field.setTroikaTrigger("controllerchapterTwo.PhoneVerify(this);");
        }
        addFormField(form, "artificialPersionIdcard", "紧急联系人身份证号", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "artificialPersionAddress", "紧急联系人住址", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "artificialPersionCertType", "紧急联系人确认状态", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormFieldRefrence(form, "chapterOne.code", "地区ID", null, "ChapterOne", true, false);
//        {
//            field.setTroikaTrigger("controllerchapterTwo.Test(this);");
//        }
        //addFormField(form, "area", "地区ID", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "sid", "原数据ID", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "policeCode", "原policeCode", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "user", "原user", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "policeStation", "原policeStation", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
        addFormField(form, "factoryStatus", "原factoryStatus", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
//        addFormField(form, "arnPic", "arnPic", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
//        addFormField(form, "specialPic", "specialPic", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
//        addFormField(form, "addrimgPic", "addrimgPic", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
//        addFormField(form, "companyPic", "companyPic", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
//        addFormField(form, "sgmj", "sgmj", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
//        addFormField(form, "sjgj", "sjgj", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
//        addFormField(form, "sswj", "sswj", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "version", "version", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "createdTime", "createdTime", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
        addFormField(form, "updatedTime", "updatedTime", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);

        return form;
    }

    @Override
    protected PQueryProject createQueryProject(ResourceNode node) {

        PQueryProject queryProject = super.createQueryProject(node);
        queryProject.toNew();
        addQueryItem(queryProject, "name", "企业名称", ControlTypes.TEXT_BOX);
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

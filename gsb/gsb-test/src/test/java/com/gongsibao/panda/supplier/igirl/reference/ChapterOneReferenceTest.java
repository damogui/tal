package com.gongsibao.panda.supplier.igirl.reference;

import com.gongsibao.entity.igirl.ic.baseinfo.ChapterOne;
import com.gongsibao.entity.igirl.tm.baseinfo.NclBatch;
import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * @Description: java类作用描述
 * @UpdateDate: 2018/4/13 16:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChapterOneReferenceTest extends ReferenceCreationBase {

    @Before
    public void setup() {

        resourceNodeCode = "IGRIL_IC_BASE_ChapterOne";
        datagridName = referenceName = "地区参照";
        referenceCode = ChapterOne.class.getSimpleName();
        System.out.println(referenceCode);
        intelligentMode = IntelligentMode.LIKE;
        intelligentFields = "code";

//		canNew= true;
//		popupUrl = "/panda/crm/company/form";
        width = 1000;
        height = 600;
    }

    public PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        datagrid.setOrderby("code asc");
        addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150, null, false);
        addColumn(datagrid, "name", "地区名称", ControlTypes.TEXT_BOX, 150, null, false);
        addColumn(datagrid, "firstCode", "一级代码", ControlTypes.TEXT_BOX, 150, null, false);
        addColumn(datagrid, "secCode", "二级代码", ControlTypes.TEXT_BOX, 150, null, false);
        addColumn(datagrid, "firstName", "一级名称", ControlTypes.TEXT_BOX, 150, null, false);
        addColumn(datagrid, "secName", "二级名称", ControlTypes.TEXT_BOX, 150, null, false);
        return datagrid;
    }
}

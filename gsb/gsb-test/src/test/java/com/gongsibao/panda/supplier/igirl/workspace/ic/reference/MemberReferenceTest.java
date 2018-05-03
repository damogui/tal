package com.gongsibao.panda.supplier.igirl.workspace.ic.reference;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.Member;
import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class MemberReferenceTest extends ReferenceCreationBase {
    @Before
    public void setup() {

        resourceNodeCode = "IGRIL_IC_REGIST_"+ Member.class.getSimpleName();
        datagridName = referenceName = "成员参照";
        referenceCode = "IC_" + Member.class.getSimpleName();
        intelligentMode = IntelligentMode.LIKE;
        intelligentFields = "name,mobile";
    }

    public PDatagrid createDatagrid(ResourceNode node) {
        PDatagrid datagrid = super.createDatagrid(node);
        addColumn(datagrid, "name", "姓名", ControlTypes.TEXT_BOX, 100, null, false);
        addColumn(datagrid, "mobile", "电话", ControlTypes.TEXT_BOX, 100, null, false);
        return datagrid;
    }
}

package com.gongsibao.panda.supplier.reference;

import com.gongsibao.entity.u8.U8Bank;
import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * Created by win on 2018/3/12.
 */
/*U8Bank的新扩展*/
public class SupplierU8BankReferenceTest  extends ReferenceCreationBase   {


    @Before
    public void setup() {

        resourceNodeCode =  "GSB_Basic_SupplierU8_" + U8Bank.class.getSimpleName();
        datagridName = referenceName = "预付科目参照";
        referenceCode = "Supplier"+U8Bank.class.getSimpleName();
        intelligentMode = IntelligentMode.LIKE;
        intelligentFields = "name";
    }

    public PDatagrid createDatagrid(ResourceNode node) {

        PDatagrid datagrid = super.createDatagrid(node);
        addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
        return datagrid;
    }
}

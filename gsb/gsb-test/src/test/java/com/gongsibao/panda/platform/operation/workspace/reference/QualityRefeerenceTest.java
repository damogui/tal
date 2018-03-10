package com.gongsibao.panda.platform.operation.workspace.reference;

import org.junit.Before;
import org.netsharp.core.annotations.Column;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.NCustomerTaskQuality;

public class QualityRefeerenceTest extends ReferenceCreationBase {

	@Before
	public void setup() {

		resourceNodeCode = "GSB_Operation_Customer_Quality";
		datagridName = referenceName = "任务质量参照";
		referenceCode = NCustomerTaskQuality.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
		filter = "code<>'X'";//未启动排除掉
	}

	public PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setPageSize(20);
		PDatagridColumn column = null;
		addColumn(datagrid, "intentionCategory", "分类", ControlTypes.TEXT_BOX, 80, null, false);
		column = addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 80, null, false);
		{
			//column.setOrderbyMode(OrderbyMode.ASC);
		}
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150, null, false);
		
		column = addColumn(datagrid, "nextFoolowDateRequired", "下次跟进时间必填", ControlTypes.TEXT_BOX, 150, null, false);{
			
			column.setSystem(true);
			column.setVisible(false);
		}

		column = addColumn(datagrid, "score", "分值", ControlTypes.NUMBER_BOX, 150, null, false);{

			column.setSystem(true);
			column.setVisible(false);
		}
		
		column = addColumn(datagrid, "returnedAmountRequired", "估计回款额必填", ControlTypes.TEXT_BOX, 150, null, false);{
			
			column.setSystem(true);
			column.setVisible(false);
		}
		
		column = addColumn(datagrid, "signingAmountRequired", "估计签单额必填", ControlTypes.TEXT_BOX, 150, null, false);{
			
			column.setSystem(true);
			column.setVisible(false);
		}
		
		column = addColumn(datagrid, "contentRequired", "内容必填", ControlTypes.TEXT_BOX, 150, null, false);{
			
			column.setSystem(true);
			column.setVisible(false);
		}
		
		column = addColumn(datagrid, "productRequired", "意向产品必填写", ControlTypes.TEXT_BOX, 150, null, false);{
			
			column.setSystem(true);
			column.setVisible(false);
		}
		
		column = addColumn(datagrid, "districtRequired", "意向地区必填写", ControlTypes.TEXT_BOX, 150, null, false);{
			
			column.setSystem(true);
			column.setVisible(false);
		}

		return datagrid;
	}
}

package com.gongsibao.panda.ncl;
import com.gongsibao.entity.ncl.NclBatch;
import com.gongsibao.ncl.base.INclBatchService;
import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceTest extends ResourceCreationBase {

	@Before
	public void setup() {

		parentNodeName = "NCL";
		parentNodeCode = "GSB_NCL";
		pluginName = "NCL";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("数据批次", "GSB_NCL_TM", node.getId());
		{
			this.createResourceNodeVoucher(NclBatch.class.getName(), "方案生成", "NCL_All_" + NclBatch.class.getSimpleName(), INclBatchService.class.getName(), node1.getId());
		}
	}
}







package com.gongsibao.panda.gardian;

import com.gongsibao.entity.gardian.baseinfo.Device;
import com.gongsibao.entity.gardian.baseinfo.Provides;
import com.gongsibao.entity.gardian.baseinfo.ProvidesEnv;
import com.gongsibao.gardian.base.IDeviceService;
import com.gongsibao.gardian.base.IProvidesEnvService;
import com.gongsibao.gardian.base.IProvidesService;
import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

public class ResourceTest extends ResourceCreationBase {

	@Before
	public void setup() {

		parentNodeName = "GARDIAN";
		parentNodeCode = "GSB_GARDIAN";
		pluginName = "GARDIAN";
		seq = 3;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		ResourceNode node1 = null;

		
		node1 = this.createResourceNodeCategory("基础信息", "GSB_GARDIAN_BASE", node.getId());
		{
			this.createResourceNodeVoucher(Device.class.getName(), "设备信息", "GARDIAN_BASE_" + Device.class.getSimpleName(), IDeviceService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Provides.class.getName(), "服务信息", "GARDIAN_BASE_" + Provides.class.getSimpleName(), IProvidesService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(ProvidesEnv.class.getName(), "服务环境", "GARDIAN_BASE_" + ProvidesEnv.class.getSimpleName(), IProvidesEnvService.class.getName(), node1.getId());
			//this.createResourceNodeVoucher(NCLTwo.class.getName(), "服务信息", "GARDIAN_BASE_" +NCLTwo.class.getSimpleName(), INCLTwoService.class.getName(), node1.getId());
		}
	}
}







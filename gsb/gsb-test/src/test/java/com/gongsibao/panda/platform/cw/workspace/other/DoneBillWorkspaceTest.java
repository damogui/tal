package com.gongsibao.panda.platform.cw.workspace.other;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.cw.dto.BillAuditDTO;

/**
 * 
*  我的已办 
* 项目名称：gsb-test   
* 类名称：DoneBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月27日 下午8:24:21   
* @version
 */
public class DoneBillWorkspaceTest extends TodoBillWorkspaceTest {

	@Before
    public void setup() {
        super.setup();
        urlList = "/cw/bill/done/list";
        resourceNodeCode = "GSB_CW_Manage_Done_Bills";
        //通过或者驳回 (已办理的)
        listFilter = " a.status = 2 AND a.status = 3 ";
    }
 

}

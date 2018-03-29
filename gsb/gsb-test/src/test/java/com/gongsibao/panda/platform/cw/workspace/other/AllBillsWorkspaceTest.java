package com.gongsibao.panda.platform.cw.workspace.other;

import org.junit.Before;
import org.netsharp.meta.base.WorkspaceCreationBase;

/**
 * 
* 单据查询  
* 项目名称：gsb-test   
* 类名称：AllBillsWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午1:45:10   
* @version
 */
public class AllBillsWorkspaceTest extends WorkspaceCreationBase {
	
    @Before
    public void setup() {

        super.setup();
        urlList = "/finance/bill/all/list";
        resourceNodeCode = "GSB_Finance_Manage_All_Bills";
        listFilter = "";
    }
    

}

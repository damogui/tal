package com.gongsibao.panda.platform.cw.workspace.other;

import org.junit.Before;

import com.gongsibao.cw.web.DoneBillListPart;

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
        listFilter = " a.status <> 1 ";
        listPartImportJs = "/gsb/platform/cw/js/done-bill-list-part.js";
		listPartJsController = DoneBillListPart.class.getName();
        
    }
 

}

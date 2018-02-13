package com.gongsibao.tools.ncrm;

import com.gongsibao.crm.job.NoStartNoticeJob;
import com.gongsibao.crm.job.UnFoolowNoticeJob;

public class NoticeJobTest {

	/**
	 * 测试任务调度通知-yxb
	 * @param args
	 */
	public static void main(String[] args) {
		//1.待分配通知
		/*AssignmentNoticeJob notice = new AssignmentNoticeJob();
		notice.execute("");*/
		
		//2.待跟进通知
		/*UnFoolowNoticeJob foolwNnotice = new UnFoolowNoticeJob();
		foolwNnotice.execute("");	*/	

		//3.未启动通知
		NoStartNoticeJob noStartNnotice = new NoStartNoticeJob();
		noStartNnotice.execute("");
		
	}

}

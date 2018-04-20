package com.gongsibao.igirl.ic.web;

import com.gongsibao.entity.igirl.ic.baseinfo.ChapterOne;
import com.gongsibao.entity.igirl.ic.baseinfo.ChapterTwo;
import com.gongsibao.igirl.ic.base.IChapterOneService;
import com.gongsibao.igirl.ic.base.IChapterTwoService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.commerce.ListPart;

import java.sql.Types;

/**
 * @Description: java类作用描述
 * @UpdateDate: 2018/4/17 9:45
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChapterTwoPart extends FormPart {
    IChapterTwoService iChapterTwoService = ServiceFactory.create(IChapterTwoService.class);
}

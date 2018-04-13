package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.ChapterTwo;
import com.gongsibao.igirl.ic.base.IChapterTwoService;
import org.netsharp.communication.Service;

/**
 * @Description: java类作用描述
 * @UpdateDate: 2018/4/12 14:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class ChapterTwoService extends GsbPersistableService<ChapterTwo> implements IChapterTwoService{
    public ChapterTwoService() {
        super();
        this.type= ChapterTwo.class;
    }
}

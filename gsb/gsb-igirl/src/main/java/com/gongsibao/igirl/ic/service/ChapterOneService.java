package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.ChapterOne;
import com.gongsibao.igirl.ic.base.IChapterOneService;
import org.netsharp.communication.Service;

/**
 * @Description: java类作用描述
 * @UpdateDate: 2018/4/12 14:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class ChapterOneService extends GsbPersistableService<ChapterOne> implements IChapterOneService {
    public ChapterOneService() {
        super();
        this.type = ChapterOne.class;
    }
}

package com.gongsibao.igirl.ic.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.ChapterOne;
import com.gongsibao.entity.igirl.ic.baseinfo.ChapterTwo;
import com.gongsibao.igirl.ic.base.IChapterOneService;
import com.gongsibao.igirl.ic.base.IChapterTwoService;

/**
 * @Description: java类作用描述
 * @UpdateDate: 2018/4/12 14:05
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
public class ChapterTwoService extends GsbPersistableService<ChapterTwo> implements IChapterTwoService{
   IChapterTwoService twoService = ServiceFactory.create(IChapterTwoService.class);
   IChapterOneService oneService = ServiceFactory.create(IChapterOneService.class);
    public ChapterTwoService() {
        super();
        this.type= ChapterTwo.class;
    }


    @Override
    //新增保存操作
    public ChapterTwo save(ChapterTwo entity) {
        if (entity.getEntityState() == EntityState.New) {
            Integer id = entity.getChaperOneId();
            Oql oql=new Oql();
            {
                oql.setType(ChapterOne.class);
                oql.setSelects("ChapterOne.*");
                oql.setFilter(" id=? ");
                oql.getParameters().add("id",id, Types.INTEGER);
            }
            ChapterOne one =oneService.queryFirst(oql);
            if(one!=null && one.getCode()!=null) {
                entity.setArea(one.getCode());
            }
            else
            {
                entity.setArea("");
            }
        }

        // 修改保存操作
        if (entity.getEntityState() == EntityState.Persist) {
            Integer id = entity.getChaperOneId();
            Oql oql=new Oql();
            {
                oql.setType(ChapterOne.class);
                oql.setSelects("ChapterOne.*");
                oql.setFilter(" id=? ");
                oql.getParameters().add("id",id, Types.INTEGER);
            }
            ChapterOne one =oneService.queryFirst(oql);
            if(one!=null && one.getCode()!=null) {
                entity.setArea(one.getCode());
            }
            else
            {
                entity.setArea("");
            }
        }

        // 设置tokenImageUrl
        entity = super.save(entity);
        return entity;
    }

}

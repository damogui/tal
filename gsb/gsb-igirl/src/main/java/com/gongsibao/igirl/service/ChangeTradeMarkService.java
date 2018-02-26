package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ChangeTradeMark;
import com.gongsibao.entity.igirl.dict.ChangeTradeMarkState;
import com.gongsibao.igirl.base.IChangeTradeMarkService;
import com.gongsibao.igirl.dto.ChangeTradeMark.ChangeTradeMarkDto;
import com.gongsibao.igirl.dto.ChangeTradeMark.ChangeTradeMarkToRoBotDto;
import com.gongsibao.taurus.util.StringManager;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChangeTradeMarkService extends GsbPersistableService<ChangeTradeMark> implements IChangeTradeMarkService {

    public ChangeTradeMarkService() {
        super();
        this.type = ChangeTradeMark.class;
    }

    @Override
    public ChangeTradeMarkToRoBotDto ctmToRobot() {
        ChangeTradeMarkToRoBotDto dto = new ChangeTradeMarkToRoBotDto();
        Oql oql = new Oql();
        oql.setType(ChangeTradeMark.class);
        oql.setSelects("ChangeTradeMark.*");
        oql.setFilter("changeTradeMarkState=?");
        oql.getParameters().add("changeTradeMarkState", ChangeTradeMarkState.WAIT_CONFIRM.getValue(), Types.INTEGER);
        List<ChangeTradeMark> ctms = this.queryList(oql);
        ChangeTradeMarkDto changeTradeMarkDto;
        List<ChangeTradeMarkDto> ctmDtos = new ArrayList<>();
        for (ChangeTradeMark ctm:ctms){
            changeTradeMarkDto = new ChangeTradeMarkDto();
            changeTradeMarkDto.setAppGjdq(ctm.getWriteType().getText());
            changeTradeMarkDto.setCertCode(ctm.getCertCode());
            changeTradeMarkDto.setChangeType(ctm.getChangeType().getContent());
            changeTradeMarkDto.setTxt_sqrmyzw(ctm.getTxt_sqrmyzw());
            changeTradeMarkDto.setTxt_sqrmyyw(ctm.getTxt_sqrmyyw());
            changeTradeMarkDto.setTxt_sqrdzzw(ctm.getTxt_sqrdzzw());
            changeTradeMarkDto.setTxt_sqrdzyw(ctm.getTxt_sqrdzyw());
            changeTradeMarkDto.setTxt_yzbm(ctm.getTxt_yzbm());
            changeTradeMarkDto.setTxt_lxr(ctm.getTxt_lxr());
            changeTradeMarkDto.setTxt_dh(ctm.getTxt_dh());
            changeTradeMarkDto.setTxt_dlrmc(ctm.getTxt_dlrmc());
            changeTradeMarkDto.setAgentBookPath(ctm.getAgentBookPath());
            changeTradeMarkDto.setAgentBookName(getFileName(ctm.getAgentBookPath()));
            changeTradeMarkDto.setAppTypeId(ctm.getApplierType().getText());
            changeTradeMarkDto.setScwjId(ctm.getLanguageType().getText());
            changeTradeMarkDto.setCertFilePath(ctm.getCertFilePath());
            changeTradeMarkDto.setCertFileName(getFileName(ctm.getCertFilePath()));
            changeTradeMarkDto.setCertFileENPath(ctm.getCertFileENPath());
            changeTradeMarkDto.setCertFileENName(getFileName(ctm.getCertFileENPath()));
            changeTradeMarkDto.setCertType(ctm.getCertificateType().getText());
            changeTradeMarkDto.setAppCertificateNum(ctm.getAppCertificateNum());
            changeTradeMarkDto.setAppCertFilePath(ctm.getAppCertFilePath());
            changeTradeMarkDto.setAppCertFileName(getFileName(ctm.getAppCertFilePath()));
            changeTradeMarkDto.setAppCertFileENPath(ctm.getAppCertFileENPath());
            changeTradeMarkDto.setAppCertFileENName(getFileName(ctm.getAppCertFileENPath()));
            changeTradeMarkDto.setIfShareTm(ctm.getIfShareTm());
            changeTradeMarkDto.setTxt_bgqmyzw(ctm.getTxt_bgqmyzw());
            changeTradeMarkDto.setTxt_bgqmyyw(ctm.getTxt_bgqmyyw());
            changeTradeMarkDto.setTxt_bgqdzzw(ctm.getTxt_bgqdzzw());
            changeTradeMarkDto.setTxt_bgqdzyw(ctm.getTxt_bgqdzyw());
            changeTradeMarkDto.setIfBgzmEn(ctm.getProveLanguageType().getContent());
            changeTradeMarkDto.setBgzmFilePath(ctm.getBgzmFilePath());
            changeTradeMarkDto.setBgzmFileName(getFileName(ctm.getBgzmFilePath()));
            changeTradeMarkDto.setBgzmFileENPath(ctm.getBgzmFileENPath());
            changeTradeMarkDto.setBgzmFileENName(getFileName(ctm.getBgzmFileENPath()));
            changeTradeMarkDto.setSblx(ctm.getChangeTradeMarkType().getText());
            changeTradeMarkDto.setTxt_sbsqh(ctm.getTxt_sbsqh());
            changeTradeMarkDto.setCommentPath(ctm.getCommentPath());
            changeTradeMarkDto.setCommentName(getFileName(ctm.getCommentPath()));
            ctmDtos.add(changeTradeMarkDto);
        }
        dto.setCode(200);
        dto.setCount(ctmDtos.size());
        dto.setData(ctmDtos);
        dto.setPin("123456789");
        if (dto.getCount()>0){
            dto.setMsg("获取数据成功");
        }else{
            dto.setMsg("当前未有数据");
        }
        return dto;
    }
    public String getFileName(String url){
        if (!StringManager.isNullOrEmpty(url)){
            return url.substring(url.lastIndexOf("/")+1);
        }
        return "";
    }
}
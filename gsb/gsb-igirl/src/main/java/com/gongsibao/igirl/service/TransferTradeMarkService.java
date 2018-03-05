package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.TransferTradeMark;
import com.gongsibao.entity.igirl.dict.TransferTradeMarkState;
import com.gongsibao.igirl.base.ITransferTradeMarkService;
import com.gongsibao.igirl.dto.TransferTradeMarkDto;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransferTradeMarkService extends GsbPersistableService<TransferTradeMark> implements ITransferTradeMarkService {
    public TransferTradeMarkService() {
        super();
        this.type = TransferTradeMark.class;
    }

    @Override
    public List<TransferTradeMarkDto> ctmToRobot() {
        List<TransferTradeMarkDto> dtos = new ArrayList<>();
        Oql oql = new Oql();
        oql.setType(TransferTradeMark.class);
        oql.setSelects("TransferTradeMark.*");
        oql.setFilter("transferTradeMarkState=?");
        oql.getParameters().add("transferTradeMarkState", TransferTradeMarkState.WAIT_CONFIRM.getValue(), Types.INTEGER);
        List<TransferTradeMark> ttms = this.queryList(oql);
        TransferTradeMarkDto dto;
        for (TransferTradeMark ttm:ttms){
            dto = new TransferTradeMarkDto();
            dto.setUserType(ttm.getTransferType().getText());
            dto.setCertCode(ttm.getCertCode());
            dto.setAssigneeGjdq(ttm.getAssigneeGjdq().getText());
            dto.setAppCrtyId(ttm.getAppCrtyId());
            dto.setAssigneeCnName(ttm.getAssigneeCnName());
            dto.setAssigneeEnName(ttm.getAssigneeEnName());
            dto.setAssigneeCnAddr(ttm.getAssigneeCnAddr());
            dto.setAssigneeEnAddr(ttm.getAssigneeEnAddr());
            dto.setAppGjdq(ttm.getAppGjdq().getText());
            dto.setAssignorCrtyId(ttm.getAssignorCrtyId());
            dto.setAssignorCnName(ttm.getAssignorCnName());
            dto.setAssignorEnName(ttm.getAssignorEnName());
            dto.setAssignorCnAddr(ttm.getAssignorCnAddr());
            dto.setAssignorEnAddr(ttm.getAssignorEnAddr());
            dto.setAcceptPerson(ttm.getAcceptPerson());
            dto.setAcceptAddr(ttm.getAcceptAddr());
            dto.setAcceptZip(ttm.getAcceptZip());
            dto.setAssigneeContactZip(ttm.getAssigneeContactZip());
            dto.setAssigneeContactPerson(ttm.getAssigneeContactPerson());
            dto.setAssigneeContactTel(ttm.getAssigneeContactTel());
            dto.setAgentFilenum(ttm.getAgentFileNum());
            dto.setAgentPerson(ttm.getAgentPerson());
            dto.setFileWtName(ttm.getFileWtName());
            dto.setFileWtName2(ttm.getFileWtName2());
            dto.setZdllx(ttm.getZdllx().getText());
            dto.setSdjlx(ttm.getSdjlx().getText());
            dto.setZwjlx(ttm.getZwjlx().getText());
            dto.setSwjlx(ttm.getSwjlx().getText());
            dto.setGqcxName(ttm.getGqcxName());
            dto.setZrZtCnTrName(ttm.getZrZtCnTrName());
            dto.setZrZtEnTrName(ttm.getZrZtEnTrName());
            dto.setSrZtCnTrName(ttm.getSrZtCnTrName());
            dto.setSrZtEnTrName(ttm.getSrZtEnTrName());
            if (ttm.getCertType()!=null){
                dto.setCertType(ttm.getCertType().getText());
            }else {
                dto.setCertType("");
            }
            dto.setCertNo(ttm.getCertNo());
            if (ttm.getScertType()!=null){
                dto.setScertType(ttm.getScertType().getText());
            }else{
                dto.setScertType("");
            }
            dto.setScertNo(ttm.getScertNo());
            dto.setZrSfCnTrName(ttm.getZrSfCnTrName());
            dto.setSrSfCnTrName(ttm.getSrSfCnTrName());
            dto.setZrSfEnTrName(ttm.getZrSfEnTrName());
            dto.setSrSfEnTrName(ttm.getSrSfEnTrName());
            dto.setFlwsName(ttm.getFlwsName());
            dto.setTmType(ttm.getTmType().getContent());
            dto.setIfShareTm(ttm.getIfShareTm());
            dto.setApporregNum(ttm.getApporregNum());
            dto.setFileYgName(ttm.getFileYgName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public TransferTradeMark updateTtmState(String agentFileNum, Integer state) {
        Oql oql = new Oql();
        oql.setType(TransferTradeMark.class);
        oql.setSelects("TransferTradeMark.*");
        oql.setFilter("agentFileNum=?");
        oql.getParameters().add("agentFileNum",agentFileNum,Types.VARCHAR);
        TransferTradeMark ttm = this.queryFirst(oql);
        if (ttm!=null){
            ttm.setTransferTradeMarkState(TransferTradeMarkState.getItem(state));
            ttm.toPersist();
            super.save(ttm);
        }
        return ttm;
    }

    @Override
    public TransferTradeMark save(TransferTradeMark entity) {
        TransferTradeMark entity1 = entity;
        Integer departmentId = SupplierSessionManager.getDepartmentId();
        Integer supplierId = SupplierSessionManager.getSupplierId();
        entity1.setDepartmentId(departmentId);
        entity1.setSupplierId(supplierId);
        if(entity1.getEntityState()== EntityState.New) {
            entity1.setAgentFileNum(String.valueOf(System.currentTimeMillis()));
        }
        return super.save(entity1);
    }
}

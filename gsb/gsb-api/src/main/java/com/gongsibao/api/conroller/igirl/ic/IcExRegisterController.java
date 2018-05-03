package com.gongsibao.api.conroller.igirl.ic;

import com.gongsibao.api.conroller.igirl.ic.dto.IcExRegisterCaseDto;
import com.gongsibao.api.conroller.igirl.ic.dto.IcExRegisterDto;
import com.gongsibao.api.util.ResponseResult;
import com.gongsibao.entity.igirl.ic.dict.CorpRegStatue;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.IcExLog;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import com.gongsibao.entity.igirl.ic.ex.dict.OperatorType;
import com.gongsibao.igirl.ic.base.IcExLogService;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/igirl")
public class IcExRegisterController {

    IcExRegisterService service = ServiceFactory.create(IcExRegisterService.class);
    IcExLogService logService = ServiceFactory.create(IcExLogService.class);

    @GET
    @Path("/ic/updateState/{id}/{state}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult updateState(@PathParam("id") String id,
                                      @PathParam("state") Integer state) {
        ResponseResult result = new ResponseResult();
        Integer count = service.updateState(id, state);
        if (count>0){
            result.setCode("200");
            result.setMessage("修改成功");
        }else{
            result.setCode("-1");
            result.setMessage("修改失败");
        }
        return result;
    }

    @GET
    @Path("/ic/getCaseToUpdate")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult getCaseToUpdate() {
        IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
        List<IcExRegisterCase> list = service.getIcCaseByType(ApprovalType.WAIT,null);
        OperatorType type;
        List<IcExRegisterCaseDto> lDtos = new ArrayList<>();
        List<IcExRegisterCaseDto> jDtos = new ArrayList<>();
        List<IcExRegisterCaseDto> nDtos = new ArrayList<>();
        IcExRegisterCaseDto caseDto;
        IcExRegisterDto dto;
        List<IcExRegisterDto> dtos = new ArrayList<>();
        for (IcExRegisterCase ic:list){
            caseDto = new IcExRegisterCaseDto();
            caseDto.setId(ic.getId());
            caseDto.setApprovalName(ic.getApprovalName());
            Integer ownerId = ic.getOwnerId();
            Integer operatorId = ic.getOperatorId();
            Integer collectorId = ic.getCollectorId();
            if (ownerId!=null){
                Employee employee = employeeService.byId(ownerId);
                caseDto.setOwnerTel(employee.getMobile());
            }else{
                caseDto.setOwnerTel("");
            }
            if (operatorId!=null){
                Employee employee = employeeService.byId(operatorId);
                caseDto.setOperatorTel(employee.getMobile());
            }else{
                caseDto.setOperatorTel("");
            }
            if (collectorId!=null){
                Employee employee = employeeService.byId(collectorId);
                caseDto.setCollectorTel(employee.getMobile());
            }else{
                caseDto.setCollectorTel("");
            }
            type = ic.getOperatorType();
            if (type.equals(OperatorType.LEI_JUAN)){
                lDtos.add(caseDto);
            }else if(type.equals(OperatorType.JIANG_KAI_NING)){
                jDtos.add(caseDto);
            }else{
                nDtos.add(caseDto);
            }
        }
        if (!lDtos.isEmpty()){
            type = OperatorType.LEI_JUAN;
            dto = new IcExRegisterDto();
            dto.setUsername(type.getUsername());
            dto.setPassword(type.getPassword());
            dto.setCases(lDtos);
            dtos.add(dto);
        }
        if (!jDtos.isEmpty()){
            type = OperatorType.JIANG_KAI_NING;
            dto = new IcExRegisterDto();
            dto.setUsername(type.getUsername());
            dto.setPassword(type.getPassword());
            dto.setCases(jDtos);
            dtos.add(dto);
        }
        if (!nDtos.isEmpty()){
            type = OperatorType.NIU_NAN;
            dto = new IcExRegisterDto();
            dto.setUsername(type.getUsername());
            dto.setPassword(type.getPassword());
            dto.setCases(nDtos);
            dtos.add(dto);
        }
        ResponseResult result = new ResponseResult();
        if (list.isEmpty()) {
            result.setCode("-1");
            result.setData("");
            result.setMessage("当前没有需要查询的数据");
        } else {
            result.setCode("200");
            result.setData(dtos);
            result.setMessage("获取成功");
        }
        return result;
    }

    @GET
    @Path("/ic/updateIcCase/{name}/{state}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult updateIcCase(@PathParam("name") String name,
                                       @PathParam("state") Integer state) {
        ResponseResult result = new ResponseResult();
        IcExRegisterCase icCase = service.updateIcCase(name, state);
        if (icCase != null) {
            IcExLog log = new IcExLog();
            log.setCompanyName(name);
            log.setCorpRegStatue(CorpRegStatue.getItem(state));
            log.setTitle("自动日志");
            log.setContent("更新工商状态");
            log.setCreateTime(new Date());
            log.setExcId(icCase.getId());
            log.toNew();
            logService.save(log);
            result.setCode("200");
            result.setData("");
            result.setMessage("修改成功");
        }else {
            result.setCode("-1");
            result.setData("");
            result.setMessage("修改失败");
        }
        return result;
    }

    /*获取更新日志的标题状态*/
    @GET
    @Path("/ic/getIcExLog/{state}/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult getIcExLog(@PathParam("name") String name, @PathParam("state") Integer state) {
        ResponseResult result = new ResponseResult();
        IcExLog log = new IcExLog();
        log.setCompanyName(name);
        log.setCorpRegStatue(CorpRegStatue.getItem(state));
        log.setTitle("工商日志");
        log.setContent("更新工商状态");
        log.setCreateTime(new Date());
        log.toNew();
        log = logService.save(log);
        if (log != null) {
            result.setCode("200");
            result.setData("");
            result.setMessage("修改成功");
        } else {
            result.setCode("-1");
            result.setData("");
            result.setMessage("当前公司数据不存在");
        }
        return result;
    }
}

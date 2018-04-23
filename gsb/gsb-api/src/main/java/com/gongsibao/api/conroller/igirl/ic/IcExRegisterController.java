package com.gongsibao.api.conroller.igirl.ic;

import com.gongsibao.api.util.ResponseResult;
import com.gongsibao.entity.igirl.ic.dict.CorpRegStatue;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.IcExLog;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import com.gongsibao.igirl.ic.base.IcExLogService;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import org.netsharp.communication.ServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        service.updateState(id, state);
        ResponseResult result = new ResponseResult();
        result.setCode("200");
        result.setData(null);
        return result;
    }

    @GET
    @Path("/ic/getCaseToUpdate")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult getCaseToUpdate() {
        List<IcExRegisterCase> list = service.getIcCaseByType(ApprovalType.WAIT);
        ResponseResult result = new ResponseResult();
        if (list.isEmpty()) {
            result.setCode("-1");
            result.setData("");
            result.setMessage("当前没有需要查询的数据");
        } else {
            result.setCode("200");
            result.setData(list);
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

package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.trade.*;
import com.gongsibao.product.base.IWorkflowService;
import com.gongsibao.trade.base.*;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.product.WorkflowFile;
import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.dic.OrderProdTraceType;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatus;
import com.gongsibao.entity.trade.dic.TraceFileStatus;
import com.gongsibao.product.base.IWorkflowFileService;
import com.gongsibao.product.base.IWorkflowNodeService;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.trade.web.dto.AddTraceFileDTO;
import com.gongsibao.trade.web.dto.TraceFileDTO;

public class OrderProdDetailController {

    IOrderService orderService = ServiceFactory.create(IOrderService.class);
    IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);
    IOrderProdTraceService traceService = ServiceFactory.create(IOrderProdTraceService.class);
    IOrderProdUserMapService prodUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);
    IWorkflowNodeService workflowNodeService = ServiceFactory.create(IWorkflowNodeService.class);
    IWorkflowService workflowService = ServiceFactory.create(IWorkflowService.class);
    IWorkflowFileService workflowFileService = ServiceFactory.create(IWorkflowFileService.class);
    IOrderProdOrganizationMapService orderProdOrganizationMapService = ServiceFactory.create(IOrderProdOrganizationMapService.class);

    /**
     * @throws
     * @Title: getOrderProdById
     * @Description: TODO(根据Id查询订单明细 - - 要优化 ， 查询字段太多)
     * @param: @param id
     * @param: @return
     * @return: OrderProd
     */
    public OrderProd getOrderProdById(Integer id) {

        return orderProdService.byId(id);
    }

    /**
     * @throws
     * @Title: getOrderById
     * @Description: TODO(根据Id查询订单 - - 要优化 ， 查询字段太多)
     * @param: @param id
     * @param: @return
     * @return: SoOrder
     */
    public SoOrder getOrderById(Integer id) {

        return orderService.byId(id);
    }

    /**
     * @throws
     * @Title: editApplyNo
     * @Description: TODO(编辑申请号)
     * @param: @param orderProdId
     * @param: @param applyNo
     * @param: @return
     * @return: Boolean
     */
    public Boolean editApplyNo(Integer orderProdId, String applyNo) {

        return orderProdService.editApplyNo(orderProdId, applyNo);
    }

    /**
     * @throws
     * @Title: editHandleName
     * @Description: TODO(编辑办理名称)
     * @param: @param orderProdId
     * @param: @param handleName
     * @param: @return
     * @return: Boolean
     */
    public Boolean editHandleName(Integer orderProdId, String handleName) {

        return orderProdService.editHandleName(orderProdId, handleName);
    }

    /**
     * @throws
     * @Title: getLoginUserId
     * @Description: TODO(获取当前登录人Id)
     * @param: @return
     * @return: Integer
     */
    public Integer getLoginUserId() {

        return SessionManager.getUserId();
    }

    /**
     * @throws
     * @Title: queryProdTraceList
     * @Description: TODO(根据订单明细Id查询订单明细跟进)
     * @param: @param orderProdId
     * @param: @return
     * @return: List<OrderProdTrace>
     */
    public EasyuiDatagridResult queryProdTraceList(Integer orderProdId, Integer pageIndex, Integer pageSize) {

        Paging paging = new Paging();
        {
            paging.setPageNo(pageIndex);
            paging.setPageSize(pageSize);
        }

        Oql oql = new Oql();
        {
            oql.setType(OrderProdTrace.class);
            oql.setSelects("OrderProdTrace.*,operator.{id,name},orderProdStatus.{id,name}");
            oql.setFilter("orderProdId=?");
            oql.setOrderby("createTime DESC");
            oql.setPaging(paging);
            oql.getParameters().add("orderProdId", orderProdId, Types.INTEGER);
        }
        List<OrderProdTrace> list = traceService.queryList(oql);
        EasyuiDatagridResult result = new EasyuiDatagridResult();
        {
            result.setRows(list);
            if (oql.getPaging() != null) {

                result.setTotal(oql.getPaging().getTotalCount());
            }
        }
        return result;
    }

    /**
     * @throws
     * @Title: updateTraceTipColor
     * @Description: TODO(更新订单明细跟进文字颜色)
     * @param: @param traceId
     * @param: @param tipColor
     * @param: @return
     * @return: Boolean
     */
    public Boolean updateTraceTipColor(Integer traceId, String tipColor) {

        return traceService.updateTraceTipColor(traceId, tipColor);
    }

    /**
     * @throws
     * @Title: queryWorkflowNodeList
     * @Description: TODO(查询订单明细工作流节点)
     * @param: @param orderProdId
     * @param: @return
     * @return: List<WorkflowNode>
     */
    public List<WorkflowNode> queryWorkflowNodeList(Integer id) {
        List<WorkflowNode> resList = workflowNodeService.getListByOrderProdId(id);
        return resList;
    }

    /**
     * @throws
     * @Title: getLastUpdateProcessTrace
     * @Description: TODO(获取最后一条更改状态的跟进记录)
     * @param: @param orderProdId
     * @param: @param orderProdStatusId
     * @param: @return
     * @return: OrderProdTrace
     */
    public OrderProdTrace getLastUpdateProcessTrace(Integer orderProdId, Integer orderProdStatusId) {

        return traceService.getLastUpdateProcessTrace(orderProdId, orderProdStatusId);
    }

    /**
     * @throws
     * @Title: updateProcessStatus
     * @Description: TODO(更新订单 、 订单明细进度状态, 并创建跟进记录)
     * @param: @param orderProdId
     * @param: @param processStatusId
     * @param: @param processStatusText
     * @param: @return
     * @return: Boolean
     */
    public Boolean updateProcessStatus(OrderProdTrace trace) {

        return traceService.updateProcessStatus(trace);
    }

    /**
     * @throws
     * @Title: remark
     * @Description: TODO(备注)
     * @param: @param trace
     * @param: @return
     * @return: Boolean
     */
    public Boolean remark(OrderProdTrace trace) {

        traceService.create(trace);
        return true;
    }

    /**
     * @throws
     * @Title: markComplaint
     * @Description: TODO(标记投诉)
     * @param: @param trace
     * @param: @param isFocus
     * @param: @return
     * @return: Boolean
     */
    public Boolean markComplaint(OrderProdTrace trace, Boolean isFocus) {

        return traceService.markComplaint(trace, isFocus);
    }

    /**
     * @throws
     * @Title: markAbnormal
     * @Description: TODO(标记异常)
     * @param: @param trace
     * @param: @return
     * @return: Boolean
     */
    public Boolean markAbnormal(OrderProdTrace trace) {

        return traceService.markAbnormal(trace);
    }

    /**
     * @throws
     * @Title: remindPrincipal
     * @Description: TODO(提醒客户)
     * @param: @param trace
     * @param: @return
     * @return: Boolean
     */
    public Boolean remindCustomer(OrderProdTrace trace) {

        return traceService.remindCustomer(trace);
    }

    /**
     * @throws
     * @Title: sendExpress
     * @Description: TODO(发快递)
     * @param: @param trace
     * @param: @return
     * @return: Boolean
     */
    public Boolean sendExpress(OrderProdTrace trace) {

        return traceService.sendExpress(trace);
    }

    /**
     * @throws
     * @Title: queryProdPrincipalList
     * @Description: TODO(查询负责人)
     * @param: @param orderProdId
     * @param: @return
     * @return: List<OrderProdUserMap>
     */
    public List<OrderProdUserMap> queryProdPrincipalList(Integer orderProdId) {

        return prodUserMapService.queryProdPrincipalList(orderProdId);
    }

    /**
     * @throws
     * @Title: remindPrincipal
     * @Description: TODO(提醒负责人)
     * @param: @param soOrderProdId
     * @param: @param orderProdStatusId
     * @param: @param principalName 负责人姓名
     * @param: @param principalMobile 负责人手机号：冗余的，查询时直接使用
     * @param: @param orderNo 订单号
     * @param: @param info 提醒内容
     * @param: @param isSendSms
     * @param: @return
     * @return: Boolean
     */
    public Boolean remindPrincipal(Integer soOrderProdId, Integer orderProdStatusId, String principalName, String principalMobile, String orderNo, String info, Boolean isSendSms) {

        return traceService.remindPrincipal(soOrderProdId, orderProdStatusId, principalName, principalMobile, orderNo, info, isSendSms);
    }

    /**
     * @throws
     * @Title: finishPrincipal
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param orderProdStatusId
     * @param: @return
     * @return: Boolean
     */
    public Boolean finishPrincipal(Integer orderProdStatusId) {

        return prodUserMapService.updateStatus(orderProdStatusId, OrderProdUserMapStatus.Cjfz, OrderProdUserMapStatus.Zzfz);
    }

    /**
     * @throws
     * @Title: querySalesmans
     * @Description: TODO(查询业务员 ： 要改成使用DTO)
     * @param: @param keyWord 关键字（服务商名称、部门名称、业务员姓名）
     * @param: @return
     * @return: List<Salesman>
     */
    public EasyuiDatagridResult querySalesmans(String keyWord, Integer pageIndex, Integer pageSize) {

        Paging paging = new Paging();
        {
            paging.setPageNo(pageIndex);
            paging.setPageSize(pageSize);
        }

        List<String> ss = new ArrayList<String>();
        ss.add("salesman.name like '%" + keyWord + "%'");
        ss.add("supplier.name like '%" + keyWord + "%'");
        ss.add("department.name like '%" + keyWord + "%'");
        String filter = StringManager.join(" or ", ss);
        Oql oql = new Oql();
        {
            oql.setType(Salesman.class);
            oql.setSelects("salesman.{id,employeeId,name,receiving,disabled},supplier.{id,name},department.{id,name}");
            oql.setFilter(filter);
            oql.setPaging(paging);
        }
        ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
        List<Salesman> list = salesmanService.queryList(oql);
        EasyuiDatagridResult result = new EasyuiDatagridResult();
        {
            result.setRows(list);
            if (oql.getPaging() != null) {

                result.setTotal(oql.getPaging().getTotalCount());
            }
        }
        return result;
    }


    //根据订单编号查询明细订单操作组
    public EasyuiDatagridResult queryInteractionGroup(String keyWord, Integer pageIndex, Integer pageSize) {
        Paging paging = new Paging();
        {
            paging.setPageNo(pageIndex);
            paging.setPageSize(pageSize);
        }
        int startIndex = (paging.getPageNo() - 1) * paging.getPageSize();
        int count = orderProdOrganizationMapService.getCountByOrderNo(keyWord);
        List<OrderProdOrganizationMap> list = orderProdOrganizationMapService.getListByOrderNo(keyWord, startIndex, paging.getPageSize());
        EasyuiDatagridResult result = new EasyuiDatagridResult();
        {
            result.setRows(list);
            result.setTotal(count);
        }
        return result;
    }


    /**
     * @throws
     * @Title: queryCompanys
     * @Description: TODO(查询公司信息)
     * @param: @param keyWord
     * @param: @param pageIndex
     * @param: @param pageSize
     * @param: @return
     * @return: EasyuiDatagridResult
     */
    public EasyuiDatagridResult queryCompanys(String keyWord, Integer pageIndex, Integer pageSize) {

        Paging paging = new Paging();
        {
            paging.setPageNo(pageIndex);
            paging.setPageSize(pageSize);
        }

        List<String> ss = new ArrayList<String>();
        ss.add("name like '%" + keyWord + "%'");
        ss.add("fullName like '%" + keyWord + "%'");
        ss.add("companyName like '%" + keyWord + "%'");
        String filter = StringManager.join(" or ", ss);
        Oql oql = new Oql();
        {
            oql.setType(CompanyIntention.class);
            oql.setSelects("id,name,fullName,companyName");
            oql.setFilter(filter);
            oql.setPaging(paging);
        }
        ICompanyIntentionService companyService = ServiceFactory.create(ICompanyIntentionService.class);
        List<CompanyIntention> list = companyService.queryList(oql);
        EasyuiDatagridResult result = new EasyuiDatagridResult();
        {
            result.setRows(list);
            if (oql.getPaging() != null) {

                result.setTotal(oql.getPaging().getTotalCount());
            }
        }
        return result;
    }

    /**
     * @throws
     * @Title: addPrincipal
     * @Description: TODO(添加负责人)
     * @param: @param principalIds
     * @param: @param principalNames
     * @param: @return
     * @return: Boolean
     */
    public Boolean addPrincipal(Integer orderProdId, String principalIds, String principalNames) {

        return prodUserMapService.addPrincipal(orderProdId, principalIds, principalNames);
    }

    //批量添加负责人
    public Boolean addBatchPrincipal(List<Integer> orderProdIdList, List<Integer> principalIds, String principalNames) {
        return prodUserMapService.addBatchPrincipal(orderProdIdList, principalIds, principalNames);
    }

    /**
     * @throws
     * @Title: getCustomerByOrderId
     * @Description: TODO(根据订单Id获取NCustomer)
     * @param: @param orderId
     * @param: @return
     * @return: NCustomer
     */
    public NCustomer getCustomerByOrderId(Integer orderId) {

        return orderService.getCustomerByOrderId(orderId);
    }

    /**
     * @throws
     * @Title: cancelRelevanceCompany
     * @Description: TODO(取消订单明细关联企业信息)
     * @param: @param orderProdId
     * @param: @return
     * @return: Boolean
     */
    public Boolean cancelRelevanceCompany(Integer orderProdId) {

        return orderProdService.cancelRelevanceCompany(orderProdId);
    }

    /**
     * @throws
     * @Title: cancelRelevanceCompany
     * @Description: TODO(添加订单明细关联企业信息)
     * @param: @param orderProdId
     * @param: @return
     * @return: Boolean
     */
    public Boolean addRelevanceCompany(Integer orderProdId, Integer companyId) {

        return orderProdService.addRelevanceCompany(orderProdId, companyId);
    }

    IOrderProdTraceFileService traceFileService = ServiceFactory.create(IOrderProdTraceFileService.class);

    /**
     * @throws
     * @Title: queryOrderProdTraceFile
     * @Description: TODO(根据订单明细Id查询跟进文件)
     * @param: @param orderProdId
     * @param: @return
     * @return: List<OrderProdTraceFile>
     */
    public List<OrderProdTraceFile> queryPreviewOrderProdTraceFiles(Integer orderProdId) {

        List<OrderProdTraceFile> fileList = traceFileService.queryOrderProdTraceFiles(orderProdId);
        return fileList;
    }

    /**
     * @throws
     * @Title: queryWorkflowFileList
     * @Description: TODO(查询文件节点)
     * @param: @param orderProdId
     * @param: @return
     * @return: List<OrderProdTraceFile>
     */
    public List<WorkflowFile> queryWorkflowFileList(Integer id) {
        List<WorkflowFile> fileList = workflowFileService.getListByOrderProdId(id);
        return fileList;
    }

    /**
     * @throws
     * @Title: queryOrderProdTraceFiles
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param orderProdId
     * @param: @return
     * @return: TraceFileDTO
     */
    public TraceFileDTO queryOrderProdTraceFiles(Integer orderProdId) {

        TraceFileDTO dto = new TraceFileDTO();
        List<OrderProdTraceFile> alreadyFileList = traceFileService.queryList(orderProdId);
        OrderProd orderProd = orderProdService.byId(orderProdId);

        // 必须上传的全部文件
        IWorkflowFileService workflowFileService = ServiceFactory.create(IWorkflowFileService.class);
        List<WorkflowFile> totalMustFileList = workflowFileService.queryWorkflowMustFileList(orderProd.getProductId(), orderProd.getCityId());

        int totalMustCount = totalMustFileList.size();
        int alreadyUploadCount = 0;

        List<WorkflowFile> alreadyMustFileList = new ArrayList<WorkflowFile>();
        for (OrderProdTraceFile traceFile : alreadyFileList) {

            WorkflowFile workflowFile = traceFile.getWorkflowFile();
            if (workflowFile != null && workflowFile.getMust() && traceFile.getStatus() == TraceFileStatus.NEWEST) {

                // 计算已经上传的必须文件数量
                alreadyUploadCount++;
                alreadyMustFileList.add(workflowFile);
            }
        }

        // 删除已经上传的
        totalMustFileList.removeAll(alreadyMustFileList);

        List<String> ss = new ArrayList<String>();
        for (WorkflowFile file : totalMustFileList) {

            ss.add(file.getName());
        }

        String notUploadFileNames = StringManager.join("、", ss);
        int notUploadCount = totalMustCount - alreadyUploadCount;

        dto.setFileList(alreadyFileList);
        dto.setAlreadyUploadCount(alreadyUploadCount);
        dto.setNotUploadCount(notUploadCount);
        dto.setNotUploadFileNames(notUploadFileNames);
        return dto;
    }

    /**
     * @throws
     * @Title: topTraceFile
     * @Description: TODO(跟进文件置顶)
     * @param: @param orderProdId
     * @param: @param traceFileId
     * @param: @return
     * @return: Boolean
     */
    public Boolean topTraceFile(Integer orderProdId, Integer traceFileId) {

        return traceFileService.topTraceFile(orderProdId, traceFileId);
    }

    /**
     * @throws
     * @Title: addTraceFile
     * @Description: TODO(创建文件)
     * @param: @param dto
     * @param: @return
     * @return: Boolean
     */
    public Boolean addTraceFile(AddTraceFileDTO dto) {

        OrderProdTrace trace = new OrderProdTrace();
        {
            trace.toNew();
            trace.setOrderProdId(dto.getOrderProdId());
            trace.setOrderProdStatusId(dto.getProcessStatusId());
            trace.setTypeId(OrderProdTraceType.Sccl);
            trace.setInfo("文件上传: " + dto.getWorkflowFileName());
            trace.setOperatorId(SessionManager.getUserId());
        }
        List<OrderProdTraceFile> traceFileList = new ArrayList<>();
        List<File> files = dto.getFiles();
        for (File file : files) {

            file.toNew();
            OrderProdTraceFile traceFile = new OrderProdTraceFile();
            traceFile.toNew();
            traceFile.setFile(file);
            traceFile.setWorkflowFileId(dto.getWorkflowFileId());
            traceFile.setWorkflowFileName(dto.getWorkflowFileName());
            traceFile.setRemark(dto.getInfo());
            traceFileList.add(traceFile);
        }
        return traceFileService.addTraceFile(trace, traceFileList);
    }
}

package com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.Enity;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.entity.crm.dic.QualityCategory;
import com.gongsibao.entity.crm.dic.TaskQualityProgress;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.annotations.*;
import org.netsharp.entity.IEntity;
import org.netsharp.entity.Persistable;

import java.util.Date;

/**
 * Created by win on 2018/2/11.
 */
@Table(name = "n_crm_task_foolow", orderBy = " create_time DESC",header = "任务跟进")
public class ImNCustomerTaskFoolow  extends Persistable implements IEntity {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 4684375504055933956L;
    @Id

    @Column(name = "id", header = "主键")
    private Integer id;

    @Column(name = "creator_id", header = "创建人ID")
    private Integer creatorId;

    @Column(name = "creator", header = "创建人名称")
    private String creator;

    @Column(name = "create_time", header = "创建时间")
    private Date createTime;

    @Column(name = "updator_id", header = "更新人ID")
    private Integer updatorId;

    @Column(name = "updator", header = "更新人名称")
    private String updator;

    @Column(name = "update_time", header = "更新时间")
    private Date updateTime;

    //private Date ts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

//	public Date getTs() {
//		return ts;
//	}
//
//	public void setTs(Date ts) {
//		this.ts = ts;
//	}

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Integer updatorId) {
        this.updatorId = updatorId;
    }

    public boolean hasId() {
        Mtable meta = MtableManager.getMtable(this.getClass());
        return !meta.getId().isEmpty(this.id);
    }
    @JsonIgnore
    @Reference(foreignKey = "customerId", header = "客户Id")
    private NCustomer customer;

    @Column(name = "customer_id", header = "客户")
    private Integer customerId;

    @JsonIgnore
    @Reference(foreignKey = "taskId", header = "任务Id")
    private NCustomerTask task;

    @Column(name = "task_id", header = "任务")
    private Integer taskId;

    @Column(name = "quality_category", header = "质量分类")
    private QualityCategory qualityCategory;

    @Column(name = "quality_id", header = "质量Id")
    private Integer qualityId;

    @Reference(foreignKey = "qualityId", header = "质量")
    private NCustomerTaskQuality quality;

    @Column(name = "quality_progress", header = "质量进度")
    private TaskQualityProgress qualityProgress = TaskQualityProgress.INVARIABILITY;

    @Column(name = "next_foolow_time", header = "下次跟进时间")
    private Date nextFoolowTime;

    @Column(name = "content", size = 1000, header = "跟进内容")
    private String content;

    @Column(name = "signing_amount", header = "估计签单金额")
    private Integer signingAmount;

    @Column(name = "returned_amount", header = "估计回款金额")
    private Integer returnedAmount;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId;

    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;

    @Column(name = "department_id", header = "服务商部门Id")
    private Integer departmentId;

    @Reference(foreignKey = "departmentId", header = "服务商部门")
    private SupplierDepartment department;

    public TaskQualityProgress getQualityProgress() {
        return qualityProgress;
    }

    public void setQualityProgress(TaskQualityProgress qualityProgress) {
        this.qualityProgress = qualityProgress;
    }

    public Integer getQualityId() {
        return qualityId;
    }

    public void setQualityId(Integer qualityId) {
        this.qualityId = qualityId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public SupplierDepartment getDepartment() {
        return department;
    }

    public void setDepartment(SupplierDepartment department) {
        this.department = department;
    }

    public NCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(NCustomer customer) {
        this.customer = customer;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public NCustomerTask getTask() {
        return task;
    }

    public void setTask(NCustomerTask task) {
        this.task = task;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public QualityCategory getQualityCategory() {
        return qualityCategory;
    }

    public void setQualityCategory(QualityCategory qualityCategory) {
        this.qualityCategory = qualityCategory;
    }

    public NCustomerTaskQuality getQuality() {
        return quality;
    }

    public void setQuality(NCustomerTaskQuality quality) {
        this.quality = quality;
    }

    public Date getNextFoolowTime() {
        return nextFoolowTime;
    }

    public void setNextFoolowTime(Date nextFoolowTime) {
        this.nextFoolowTime = nextFoolowTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSigningAmount() {
        return signingAmount;
    }

    public void setSigningAmount(Integer signingAmount) {
        this.signingAmount = signingAmount;
    }

    public Integer getReturnedAmount() {
        return returnedAmount;
    }

    public void setReturnedAmount(Integer returnedAmount) {
        this.returnedAmount = returnedAmount;
    }


}

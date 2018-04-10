package com.gongsibao.panda.platform.operation.workspace.supplier.data.ImportData.Enity;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.crm.*;
import com.gongsibao.entity.crm.dic.*;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.*;
import org.netsharp.core.annotations.*;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.core.id.IId;
import org.netsharp.core.property.IProperty;
import org.netsharp.entity.IEntity;
import org.netsharp.entity.IPersistable;
import org.netsharp.entity.Persistable;
import org.netsharp.organization.entity.Employee;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by win on 2018/2/10.
 */
@Table(name = "crm_customer", header = "客户信息")
public class ImNCustomer extends Persistable implements IEntity {
    /**
     * @Fields serialVersionUID : n_crm_customer  已经换为crm_customer
     */
    private static final long serialVersionUID = -1451778506769623188L;
    @Exclusive
    private EntityState entityState;

    @Exclusive
    @JsonIgnore
    protected HashMap<String, Object> innerValues = new HashMap<String, Object>();

    @Exclusive
    @JsonIgnore
    private Object tag;

    public EntityState getEntityState() {
        return entityState;
    }

    public void setEntityState(EntityState entityState) {
        if(entityState==EntityState.New){
            this.toNew();
        }
        else if(entityState==EntityState.Persist){
            this.toPersist();
        }
        else if(entityState==EntityState.Deleted){
            this.toDeleted();
        }
        else{
            this.entityState=entityState;
        }
    }

    public void clone(IPersistable row) {

        Mtable meta = MtableManager.getMtable(this.getClass());

        ArrayList<org.netsharp.core.Column> columns = meta.getColumns();
        for (org.netsharp.core.Column column : columns) {
            IProperty p = column.getProperty();
            Object value = p.field(row);
            p.field(this, value);
        }

        this.entityState = row.getEntityState();
    }

    /* 读取属性,支持多级属性，如：order.get("customer.category.name"); */
    public Object get(String property) {

        Mtable meta = MtableManager.getMtable(this.getClass());

        Object obj = this;
        if (property.contains(".")) {

            String[] ps = property.split("\\.");

            for (int i = 0; i < ps.length - 1; i++) {
                TableRelation ref = meta.getReference(ps[i]);
                if (ref == null) {
                    ref = meta.getCompositeOne(ps[i]);
                    if (ref == null) {
                        throw new NetsharpException(meta.getEntityId() + "." + ps[i] + "引用不存在！");
                    }
                }

                obj = ref.getPropertyValue(obj);

                if (obj == null) {
                    return null;
                }

                meta = (Mtable) ref.getToTable();
            }

            property = ps[ps.length - 1];
        }

        org.netsharp.core.Column column = meta.getPropertyOrColumn(property);
        if (column == null) {
            return this.getInnerValues().get(property);
        } else {
            IProperty p = column.getProperty();
            Object value = p.field(obj);

            return value;
        }
    }

    /* 设置属性,支持多级属性，如：order.set("customer.category.name","客户"); */
    public void set(String property, Object value) {

        if (StringManager.isNullOrEmpty(property)) {
            throw new NetsharpException("属性名不能为空");
        }
        if (property.contains(".")) {
            throw new NetsharpException("暂不支持多级属性");
        }

        Mtable meta = MtableManager.getMtable(this.getClass());

        org.netsharp.core.Column column = meta.getPropertyOrColumn(property);
        if (column == null) {
            this.getInnerValues().put(property, value);
        } else {
            column.getProperty().field(this, value);
        }
    }

    public void toNew() {

        this.entityState = EntityState.New;

        Mtable meta = MtableManager.getMtable(this.getClass());
        org.netsharp.core.Column keyColumn = meta.getKeyColumn();
        if(keyColumn!=null && !keyColumn.isAuto()){

            IId iid = meta.getId();
            Object id = meta.getId(this);
            if(iid.isEmpty(id)){
                meta.setId(this, iid.newId());
            }
        }
    }

    public void toPersist() {
        this.entityState = EntityState.Persist;
    }

    public void toDeleted() {
        this.entityState = EntityState.Deleted;
    }

    public void toTransient() {
        this.entityState = EntityState.Transient;
    }

    @JsonIgnore
    public HashMap<String, Object> getInnerValues() {
        return innerValues;
    }

    @JsonIgnore
    public Object getTag() {
        return tag;
    }

    @JsonIgnore
    public void setTag(Object tag) {
        this.tag = tag;
    }
    @Id

    @Column(name = "pkid", header = "主键")
    private Integer id;

    @Column(name="add_user_id",header="添加人")
    private Integer creatorId = 0;

    @Column(name="add_time",header="创建时间")
    private Date createTime;

    @Column(name="upd_user_id",header="修改人")
    private Integer updatorId = 0;

    @Column(name="upd_time",header="修改时间")
    private Date updateTime;

    @Column(name = "creator", header = "创建人名称")
    private String creator;

    @Column(name = "updator", header = "更新人名称")
    private String updator;

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
    @Column(name = "account_id", header = "帐号Id")
    private Integer accountId;

    @Reference(foreignKey = "accountId")
    private Account account;

    @Column(name = "real_name", header = "姓名")
    private String realName;

    @Column(name = "sex", header = "性别，0保密、1男、2女")
    private Sex sex = Sex.SECRECY;

    @Column(name = "mobile", header = "手机号码")
    private String mobile;

    @Column(name = "is_member", header = "是否是会员：0-否；1-是")
    private Boolean isMember = false;

    @Column(name = "telephone", header = "座机")
    private String telephone;

    @Column(name = "email", header = "邮箱")
    private String email;

    @Column(name = "qq", header = "QQ")
    private String qq;

    @Column(name = "weixin", header = "微信")
    private String weixin;

    @Column(name = "birdthday", header = "客户生日")
    private Date birdthday;

    @Column(name = "addr", header = "客户地址")
    private String addr;

    @Column(name = "f_province_id")
    private Integer provinceId;

    @Reference(foreignKey = "provinceId", header = "省份")
    private Dict province;

    @Column(name = "f_city_id")
    private Integer cityId;

    @Reference(foreignKey = "cityId", header = "城市")
    private Dict city;

    @Column(name = "f_county_id")
    private Integer countyId;

    @Reference(foreignKey = "countyId", header = "区/县")
    private Dict county;

    @Column(name = "unvalid_remark", header = "沟通无效原因")
    private String unvalidRemark;

    @Column(name = "maybe_remark", header = "潜在原因")
    private String maybeRemark;

    @Column(name = "customer_source_other", header = "客户来源选择其他时填写的详情")
    private String customerSourceOther;

    @Column(name = "introducer_user_id", header = "介绍人id(内部人员)")
    private Integer introducerUserId;

    @Column(name = "consult_way", header = "421 CRM咨询途径: 4211 400电话、 4212 在线客服、 4213企业QQ、 4214 PC官网、 4215 H5官网、 4216 手机APP")
    private ConsultWay consultWay;

    @Column(name = "consult_way_other", header = "咨询途径选择其他时填写的详情")
    private String consultWayOther;

    @Column(name = "important", header = "402 重要程度: 4021普通、 4022中级、 4023高级、 4024VIP")
    private Important important = Important.COMMON;

    @Column(name = "is_invalid", header = "是否无效")
    private Integer invalid = 0;

    @Column(name = "introducer_id", header = "介绍人id")
    private Integer introducerId;

    @Column(name = "remark", header = "备注信息")
    private String remark;

    @Column(name = "allocation_type", header = "分配方式")
    private AllocationType allocationType = AllocationType.NATURAL;

    @Column(name = "supplier_id", header = "分配服务商Id")
    private Integer supplierId;

    @Reference(foreignKey = "supplierId", header = "分配服务商")
    private Supplier supplier;

    @Column(name = "department_id", header = "分配服务商部门Id")
    private Integer departmentId;

    @Reference(foreignKey = "departmentId", header = "分配服务商部门")
    private SupplierDepartment department;


    @Column(name = "swt_customer_id", header = "商务通客Id")
    private String swtCustomerId;

    @Column(name = "swt_service_id", header = "商务通客服Id")
    private String swtServiceId;

    @Column(name = "intention_category", header = "质量分类")
    private QualityCategory intentionCategory;

    @Column(name = "quality_id", header = "质量Id")
    private Integer qualityId;

    @Reference(foreignKey = "qualityId", header = "质量")
    private NCustomerTaskQuality quality;

    @Column(name = "last_follow_time", header = "最近跟进时间")
    private Date lastFollowTime;

    @Column(name = "last_foolow_user_id", header = "最后跟进人Id")
    private Integer lastFoolowUserId;

    @Reference(foreignKey = "lastFoolowUserId", header = "最后跟进人")
    private Employee lastFoolowUser;

    @Column(name = "last_content", size = 1000, header = "最后跟进内容")
    private String lastContent;

    @Column(name = "next_foolow_time", header = "下次跟进时间")
    private Date nextFoolowTime;

    @Reference(foreignKey = "customerSourceId", header = "客户来源")
    private Dict customerSource;

    @Column(name = "customer_source_id", header = "客户来源")
    private Integer customerSourceId;

    //    @Column(name = "crm_source_type", header = "是不是招商渠道来源")
//    private Integer crmSourceType=0;//1是招商渠道  FollowStatus  区分 4017  渠道合作
//
    @Column(name = "task_count", header = "商机数量：创建商机，删除商机时更新此值")
    private Integer taskCount = 0;
    @Column(name = "company_id", header = "关联公司Id")//新增的id是后来
    private Integer companyId=0;

    @Subs(foreignKey = "customerId", header = "客户任务", subType = NCustomerTask.class)
    private List<NCustomerTask> tasks;

    @Subs(foreignKey = "customerId", header = "意向产品", subType = ImNCustomerProduct.class,primaryKey = "pkid")
    private List<ImNCustomerProduct> products;

    @Subs(foreignKey = "customerId", header = "关联企业", subType = ImNCustomerCompany.class,primaryKey ="pkid")
    private List<ImNCustomerCompany> companys;

    @Subs(foreignKey = "customerId", header = "跟进日志", subType = ImNCustomerTaskFoolow.class)
    private List<ImNCustomerTaskFoolow> follows;

    @Subs(foreignKey = "customerId", header = "通知日志", subType = NCustomerTaskNotify.class)
    private List<NCustomerTaskNotify> notifys;

    @Subs(foreignKey = "customerId", header = "流转日志", subType = NCustomerOperationLog.class)
    private List<NCustomerOperationLog> changes;

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getQualityId() {
        return qualityId;
    }

    public void setQualityId(Integer qualityId) {
        this.qualityId = qualityId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Date getBirdthday() {
        return birdthday;
    }

    public void setBirdthday(Date birdthday) {
        this.birdthday = birdthday;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Dict getProvince() {
        return province;
    }

    public void setProvince(Dict province) {
        this.province = province;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Dict getCity() {
        return city;
    }

    public void setCity(Dict city) {
        this.city = city;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public Dict getCounty() {
        return county;
    }

    public void setCounty(Dict county) {
        this.county = county;
    }

    public String getUnvalidRemark() {
        return unvalidRemark;
    }

    public void setUnvalidRemark(String unvalidRemark) {
        this.unvalidRemark = unvalidRemark;
    }

    public String getMaybeRemark() {
        return maybeRemark;
    }

    public void setMaybeRemark(String maybeRemark) {
        this.maybeRemark = maybeRemark;
    }

    public String getCustomerSourceOther() {
        return customerSourceOther;
    }

    public void setCustomerSourceOther(String customerSourceOther) {
        this.customerSourceOther = customerSourceOther;
    }

    public Integer getIntroducerUserId() {
        return introducerUserId;
    }

    public void setIntroducerUserId(Integer introducerUserId) {
        this.introducerUserId = introducerUserId;
    }

    public ConsultWay getConsultWay() {
        return consultWay;
    }

    public void setConsultWay(ConsultWay consultWay) {
        this.consultWay = consultWay;
    }

    public String getConsultWayOther() {
        return consultWayOther;
    }

    public void setConsultWayOther(String consultWayOther) {
        this.consultWayOther = consultWayOther;
    }

    public Important getImportant() {
        return important;
    }

    public void setImportant(Important important) {
        this.important = important;
    }

    public Integer getInvalid() {
        return invalid;
    }

    public void setInvalid(Integer invalid) {
        this.invalid = invalid;
    }

    public Integer getIntroducerId() {
        return introducerId;
    }

    public void setIntroducerId(Integer introducerId) {
        this.introducerId = introducerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AllocationType getAllocationType() {
        return allocationType;
    }

    public void setAllocationType(AllocationType allocationType) {
        this.allocationType = allocationType;
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

    public String getSwtCustomerId() {
        return swtCustomerId;
    }

    public void setSwtCustomerId(String swtCustomerId) {
        this.swtCustomerId = swtCustomerId;
    }

    public String getSwtServiceId() {
        return swtServiceId;
    }

    public void setSwtServiceId(String swtServiceId) {
        this.swtServiceId = swtServiceId;
    }

    public Date getLastFollowTime() {
        return lastFollowTime;
    }

    public void setLastFollowTime(Date lastFollowTime) {
        this.lastFollowTime = lastFollowTime;
    }

    public Dict getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(Dict customerSource) {
        this.customerSource = customerSource;
    }

    public Integer getCustomerSourceId() {
        return customerSourceId;
    }

    public void setCustomerSourceId(Integer customerSourceId) {
        this.customerSourceId = customerSourceId;
    }

    public List<NCustomerTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<NCustomerTask> tasks) {
        this.tasks = tasks;
    }

    public List<ImNCustomerProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ImNCustomerProduct> products) {
        this.products = products;
    }

    public List<ImNCustomerCompany> getCompanys() {
        return companys;
    }

    public void setCompanys(List<ImNCustomerCompany> companys) {
        this.companys = companys;
    }

    public List<ImNCustomerTaskFoolow> getFollows() {
        return follows;
    }

    public void setFollows(List<ImNCustomerTaskFoolow> follows) {
        this.follows = follows;
    }

    public List<NCustomerTaskNotify> getNotifys() {
        return notifys;
    }

    public void setNotifys(List<NCustomerTaskNotify> notifys) {
        this.notifys = notifys;
    }

    public List<NCustomerOperationLog> getChanges() {
        return changes;
    }

    public void setChanges(List<NCustomerOperationLog> changes) {
        this.changes = changes;
    }

    public QualityCategory getIntentionCategory() {
        return intentionCategory;
    }

    public void setIntentionCategory(QualityCategory intentionCategory) {
        this.intentionCategory = intentionCategory;
    }

    public NCustomerTaskQuality getQuality() {
        return quality;
    }

    public void setQuality(NCustomerTaskQuality quality) {
        this.quality = quality;
    }

    public Integer getLastFoolowUserId() {
        return lastFoolowUserId;
    }

    public void setLastFoolowUserId(Integer lastFoolowUserId) {
        this.lastFoolowUserId = lastFoolowUserId;
    }

    public Employee getLastFoolowUser() {
        return lastFoolowUser;
    }

    public void setLastFoolowUser(Employee lastFoolowUser) {
        this.lastFoolowUser = lastFoolowUser;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public Date getNextFoolowTime() {
        return nextFoolowTime;
    }

    public void setNextFoolowTime(Date nextFoolowTime) {
        this.nextFoolowTime = nextFoolowTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(Boolean isMember) {
        this.isMember = isMember;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}

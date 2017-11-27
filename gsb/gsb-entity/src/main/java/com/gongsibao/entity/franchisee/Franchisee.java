package com.gongsibao.entity.franchisee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;
import org.netsharp.pcc.entity.ProvinceCityCounty;

import com.gongsibao.entity.franchisee.dic.CooperativeMode;
import com.gongsibao.entity.franchisee.dic.ExpectedSign;
import com.gongsibao.entity.franchisee.dic.IntentionDegree;
import com.gongsibao.entity.franchisee.dic.TrackProgress;

@Table(name="bd_franchisee",header="加盟商")
public class Franchisee extends BizEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2344696619079772669L;

	@Column(name="province_id")
	private Integer provinceId;
	
	@Reference(foreignKey="provinceId",header="省份")
	private ProvinceCityCounty province;
	
	@Column(name="city_id")
	private Integer cityId;
	
	@Reference(foreignKey="cityId",header="城市")
	private ProvinceCityCounty city;
	
	@Column(name="county_id")
	private Integer countyId;
	
	@Reference(foreignKey="countyId",header="区/县")
	private ProvinceCityCounty county;
	
	@Column(name="employee_count",header="员工人数")
	private Integer employeeCount;
	
	@Column(name="annualIncome",header="年收入(万)")
	private BigDecimal annualIncome;
	
    @Column(name="register_address",size=400,header="注册地址")
    private String registerAddress;
    
    @Column(name="work_address",size=400,header="办公地址")
    private String workdAddress;
    
    
    @Column(name="legal_person",header="法人")
    private String legalPerson;
    
    @Column(name="linkman_name",header="姓名")
    private String linkmanName;
    
    @Column(name="mobile",header="手机号")
    private String mobile;
    
    @Column(name="post",header="职务")
    private String post;
    
    @Column(name="weixin",header="微信")
    private String weixin;
    
    
    //跟进信息
    @Reference(foreignKey="departmentId")
    private Organization department;
    
    @Column(name="department_id",header="部门")
    private Integer departmentId;
    
    @Column(name="owner_id",header="业务员Id")
    private Integer ownerId;
    
    @Reference(foreignKey="ownerId")
    private Employee owner;
    
    @Column(name="last_tracker_id",header="最后跟进人Id")
    private Integer lastTrackerId;
    
    @Reference(foreignKey="lastTrackerId")
    private Employee lastTracker;
    
    @Column(name="next_track_date",header="下次跟进日期")
    private Date nextTrackDate;
    
    @Column(name="last_track_time",header="最后跟进看覅看")
    private Date lastTrackTime;
    
    @Column(name="intention_degree",header="意向度")
    private IntentionDegree intentionDegree = IntentionDegree.DEGREE_3;
    
    @Column(name="cooperative_mode",header="合作模式")
    private CooperativeMode cooperativeMode;
    
    @Column(name="track_progress",header="进度")
    private TrackProgress trackProgress = TrackProgress.TRACK_PROGRESS_1;
    
    @Column(name="last_track_content",size=1000,header="最后跟进内容")
    private String lastTrackContent;
    
    @Column(name="expected_sign",header="预计签单时间")
    private ExpectedSign expectedSign;
    
	@Subs(foreignKey="franchiseeId",header="联系人信息",subType=FranchiseeLinkman.class)
	private List<FranchiseeLinkman> linkmans;
	
	@Subs(foreignKey="franchiseeId",header="经营范围",subType=FranchiseeBusinessScope.class)
	private List<FranchiseeBusinessScope> scopes;
	
	@Subs(foreignKey="franchiseeId",header="跟进信息",subType=FranchiseeTrack.class)
	private List<FranchiseeTrack> tracks;
	

	public Date getLastTrackTime() {
		return lastTrackTime;
	}

	public void setLastTrackTime(Date lastTrackTime) {
		this.lastTrackTime = lastTrackTime;
	}

	public ExpectedSign getExpectedSign() {
		return expectedSign;
	}

	public void setExpectedSign(ExpectedSign expectedSign) {
		this.expectedSign = expectedSign;
	}

	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public ProvinceCityCounty getProvince() {
		return province;
	}

	public void setProvince(ProvinceCityCounty province) {
		this.province = province;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public ProvinceCityCounty getCity() {
		return city;
	}

	public void setCity(ProvinceCityCounty city) {
		this.city = city;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public ProvinceCityCounty getCounty() {
		return county;
	}

	public void setCounty(ProvinceCityCounty county) {
		this.county = county;
	}

	public Integer getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(Integer employeeCount) {
		this.employeeCount = employeeCount;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getWorkdAddress() {
		return workdAddress;
	}

	public void setWorkdAddress(String workdAddress) {
		this.workdAddress = workdAddress;
	}

	public List<FranchiseeLinkman> getLinkmans() {
		return linkmans;
	}

	public void setLinkmans(List<FranchiseeLinkman> linkmans) {
		this.linkmans = linkmans;
	}

	public List<FranchiseeBusinessScope> getScopes() {
		return scopes;
	}

	public void setScopes(List<FranchiseeBusinessScope> scopes) {
		this.scopes = scopes;
	}

	public List<FranchiseeTrack> getTracks() {
		return tracks;
	}

	public void setTracks(List<FranchiseeTrack> tracks) {
		this.tracks = tracks;
	}


	public Organization getDepartment() {
		return department;
	}

	public void setDepartment(Organization department) {
		this.department = department;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public IntentionDegree getIntentionDegree() {
		return intentionDegree;
	}

	public void setIntentionDegree(IntentionDegree intentionDegree) {
		this.intentionDegree = intentionDegree;
	}


	public CooperativeMode getCooperativeMode() {
		return cooperativeMode;
	}

	public void setCooperativeMode(CooperativeMode cooperativeMode) {
		this.cooperativeMode = cooperativeMode;
	}

	public TrackProgress getTrackProgress() {
		return trackProgress;
	}

	public void setTrackProgress(TrackProgress trackProgress) {
		this.trackProgress = trackProgress;
	}

	public Date getNextTrackDate() {
		return nextTrackDate;
	}

	public void setNextTrackDate(Date nextTrackDate) {
		this.nextTrackDate = nextTrackDate;
	}

	public String getLinkmanName() {
		return linkmanName;
	}

	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLastTrackContent() {
		return lastTrackContent;
	}

	public void setLastTrackContent(String lastTrackContent) {
		this.lastTrackContent = lastTrackContent;
	}

	public Integer getLastTrackerId() {
		return lastTrackerId;
	}

	public void setLastTrackerId(Integer lastTrackerId) {
		this.lastTrackerId = lastTrackerId;
	}

	public Employee getLastTracker() {
		return lastTracker;
	}

	public void setLastTracker(Employee lastTracker) {
		this.lastTracker = lastTracker;
	}
}

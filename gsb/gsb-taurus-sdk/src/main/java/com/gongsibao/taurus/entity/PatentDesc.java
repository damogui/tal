package com.gongsibao.taurus.entity;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 * @ClassName: PatentDesc
 * @Description:TODO 专利详情
 * @author: 韩伟
 * @date: 2017年10月19日 上午11:14:44
 * 
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved.
 */
public class PatentDesc implements IEntity {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 7109512016706081248L;

	/**
	 * @Fields pid : TODO(专利信息 id )
	 */
	private String pid;

	private String id;

	/**
	 * @Fields sysid : TODO(sysid（原先为表的主键，现在作为保留字段存在.）)
	 */
	private String sysid;

	/**
	 * @Fields appNumber : TODO(申请号)
	 */
	
	@JsonProperty(value="appnumber")
	private String appNumber;

	/**
	 * @Fields pubNumber : TODO(公开（公告）号)
	 */
    @JsonProperty(value = "pubnumber")
	private String pubNumber;

	/**
	 * @Fields appDate : TODO(申请日)
	 */
	@JsonProperty(value="appdate")
	private String appDate;

	/**
	 * @Fields pubDate : TODO(公开（公告）日)
	 */
	private String pubDate;

	/**
	 * @Fields title : TODO(名称)
	 */
	private String title;

	/**
	 * @Fields mainIpc : TODO(主分类号)
	 */
	private String mainIpc;

	/**
	 * @Fields ipc : TODO(分类号)
	 */
	private String ipc;

	/**
	 * @Fields applicantName : TODO(申请（专利权）人)
	 */
	@JsonProperty(value = "applicantname")
	private String applicantName;

	/**
	 * @Fields inventroName : TODO(发明（设计）人)
	 */
	private String inventroName;

	/**
	 * @Fields appResource : TODO(申请来源)
	 */
	private String appResource;

	/**
	 * @Fields categoryType : TODO(中国专利范畴分类号)
	 */
	private String categoryType;

	/**
	 * @Fields priority : TODO(优先权)
	 */
	private String priority;

	/**
	 * @Fields family : TODO(同族)
	 */
	private String family;

	/**
	 * @Fields familyNo : TODO(同族号)
	 */
	private String familyNo;

	/**
	 * @Fields agencyName : TODO(专利代理机构)
	 */
	private String agencyName;

	/**
	 * @Fields agentName : TODO(代理人)
	 */
	private String agentName;

	/**
	 * @Fields addrProvince : TODO(省)
	 */
	private String addrProvince;

	/**
	 * @Fields addrCity : TODO(市)
	 */
	private String addrCity;

	/**
	 * @Fields addrCounty : TODO(县)
	 */
	private String addrCounty;

	/**
	 * @Fields address : TODO(地址)
	 */
	private String address;

	/**
	 * @Fields patType : TODO(专利类型:1、发明专利;2、实用新型;3、外观专利;8、PCT 发明;9、PCT 实用新型)
	 */
	private String patType;

	/**
	 * @Fields iapp : TODO(国际申请)
	 */
	private String iapp;

	/**
	 * @Fields ipub : TODO(国际公布)
	 */
	private String ipub;

	/**
	 * @Fields den : TODO(进入国家日期)
	 */
	private String den;

	/**
	 * @Fields abs : TODO(摘要)
	 */
	private String abs;

	/**
	 * @Fields legalStatus : TODO(法律状态)
	 */
	private String legalStatus;

	/**
	 * @Fields lprs : TODO(最新法律状态)
	 */
	private String lprs;

	/**
	 * @Fields draws : TODO(用一句话描述这个变量表示什么)
	 */
	private String draws;

	/**
	 * @Fields dbName : TODO(专利所属库名)
	 */
	private String dbName;

	/**
	 * @Fields tifDistributePath : TODO()
	 */
	private String tifDistributePath;

	/**
	 * @Fields pages : TODO(页数)
	 */
	private String pages;

	/**
	 * @Fields proCode : TODO(国省代码)
	 */
	private String proCode;

	/**
	 * @Fields appCoun : TODO(申请国代码)
	 */
	private String appCoun;

	/**
	 * @Fields gazettePath : TODO(公报发布路径)
	 */
	private String gazettePath;

	/**
	 * @Fields gazettePage : TODO(公报所在页（起始页）)
	 */
	private String gazettePage;

	/**
	 * @Fields gazetteCount : TODO(公报翻页信息 ：0
	 *         代表只有一页，其他数字代表可再翻的页数（即总页数=gazetteCount+1）)
	 */
	private String gazetteCount;

	/**
	 * @Fields statusCode : TODO(专利状态码 ：10（有效），20（失效），21（专利权届满的专利），30（在审）)
	 */
	private String statusCode;

	/**
	 * @Fields cl : TODO(主权项)
	 */
	private String cl;

	/**
	 * @Fields IsActive : TODO()
	 */
	private String IsActive;

	/**
	 * @Fields claimsPath : TODO(权利要求书)
	 */
	private String claimsPath;

	/**
	 * @Fields instrPath : TODO(说明书)
	 */
	private String instrPath;

	/**
	 * @Fields divideInitAppNo : TODO(分案原申请号)
	 */
	private String divideInitAppNo;

	/**
	 * @Fields issueDate : TODO(颁证日)
	 */
	private String issueDate;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public String getAppNumber() {
		return appNumber;
	}

	public void setAppNumber(String appNumber) {
		this.appNumber = appNumber;
	}

	public String getPubNumber() {
		return pubNumber;
	}

	public void setPubNumber(String pubNumber) {
		this.pubNumber = pubNumber;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMainIpc() {
		return mainIpc;
	}

	public void setMainIpc(String mainIpc) {
		this.mainIpc = mainIpc;
	}

	public String getIpc() {
		return ipc;
	}

	public void setIpc(String ipc) {
		this.ipc = ipc;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getInventroName() {
		return inventroName;
	}

	public void setInventroName(String inventroName) {
		this.inventroName = inventroName;
	}

	public String getAppResource() {
		return appResource;
	}

	public void setAppResource(String appResource) {
		this.appResource = appResource;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getFamilyNo() {
		return familyNo;
	}

	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getAddrCounty() {
		return addrCounty;
	}

	public void setAddrCounty(String addrCounty) {
		this.addrCounty = addrCounty;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPatType() {
		return patType;
	}

	public void setPatType(String patType) {
		this.patType = patType;
	}

	public String getIapp() {
		return iapp;
	}

	public void setIapp(String iapp) {
		this.iapp = iapp;
	}

	public String getIpub() {
		return ipub;
	}

	public void setIpub(String ipub) {
		this.ipub = ipub;
	}

	public String getDen() {
		return den;
	}

	public void setDen(String den) {
		this.den = den;
	}

	public String getAbs() {
		return abs;
	}

	public void setAbs(String abs) {
		this.abs = abs;
	}

	public String getLegalStatus() {
		return legalStatus;
	}

	public void setLegalStatus(String legalStatus) {
		this.legalStatus = legalStatus;
	}

	public String getLprs() {
		return lprs;
	}

	public void setLprs(String lprs) {
		this.lprs = lprs;
	}

	public String getDraws() {
		return draws;
	}

	public void setDraws(String draws) {
		this.draws = draws;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getTifDistributePath() {
		return tifDistributePath;
	}

	public void setTifDistributePath(String tifDistributePath) {
		this.tifDistributePath = tifDistributePath;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getAppCoun() {
		return appCoun;
	}

	public void setAppCoun(String appCoun) {
		this.appCoun = appCoun;
	}

	public String getGazettePath() {
		return gazettePath;
	}

	public void setGazettePath(String gazettePath) {
		this.gazettePath = gazettePath;
	}

	public String getGazettePage() {
		return gazettePage;
	}

	public void setGazettePage(String gazettePage) {
		this.gazettePage = gazettePage;
	}

	public String getGazetteCount() {
		return gazetteCount;
	}

	public void setGazetteCount(String gazetteCount) {
		this.gazetteCount = gazetteCount;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getCl() {
		return cl;
	}

	public void setCl(String cl) {
		this.cl = cl;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String isActive) {
		IsActive = isActive;
	}

	public String getClaimsPath() {
		return claimsPath;
	}

	public void setClaimsPath(String claimsPath) {
		this.claimsPath = claimsPath;
	}

	public String getInstrPath() {
		return instrPath;
	}

	public void setInstrPath(String instrPath) {
		this.instrPath = instrPath;
	}

	public String getDivideInitAppNo() {
		return divideInitAppNo;
	}

	public void setDivideInitAppNo(String divideInitAppNo) {
		this.divideInitAppNo = divideInitAppNo;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
}

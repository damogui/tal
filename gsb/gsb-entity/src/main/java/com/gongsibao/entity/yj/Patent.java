package com.gongsibao.entity.yj;

import com.gongsibao.entity.BaseEntity;
import java.sql.Timestamp;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

@Table(name="yj_patent")
public class Patent extends BaseEntity {
	
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5396904991653098977L;
	
	@Column(name="company_id",header="CompanyId")
    private Integer companyId;
    @Column(name="yj_company_id",header="YjCompanyId")
    private Integer yjCompanyId;
    @Column(name="company_name",header="CompanyName")
    private String companyName;
    @Column(header="jyid")
    private String jyid;
    @Column(name="application_number",header="ApplicationNumber")
    private String applicationNumber;
    @Column(name="application_date",header="ApplicationDate")
    private Timestamp applicationDate;
    @Column(name="publication_number",header="PublicationNumber")
    private String publicationNumber;
    @Column(name="publication_date",header="PublicationDate")
    private Timestamp publicationDate;
    @Column(name="legal_status_desc",header="LegalStatusDesc")
    private String legalStatusDesc;
    @Column(header="title")
    private String title;
    @Column(name="kind_code_desc",header="KindCodeDesc")
    private String kindCodeDesc;
    @Column(name="document_types",header="DocumentTypes")
    private String documentTypes;
    @Column(name="main_ipc",header="MainIpc")
    private String mainIpc;
    @Column(header="agent")
    private String agent;
    @Column(name="legal_status_date",header="LegalStatusDate")
    private Timestamp legalStatusDate;
    @Column(name="primary_examiner",header="PrimaryExaminer")
    private String primaryExaminer;
    @Column(name="assiant_examiner",header="AssiantExaminer")
    private String assiantExaminer;
    @Column(header="cites")
    private String cites;
    @Column(name="other_references",header="OtherReferences")
    private String otherReferences;
    @Column(name="patent_image",header="PatentImage")
    private String patentImage;
    @Column(name="abstract_info",header="AbstractInfo")
    private String abstractInfo;
    @Column(name="priority_number",header="PriorityNumber")
    private String priorityNumber;
    @Column(name="priority_date",header="PriorityDate")
    private Timestamp priorityDate;
    @Column(header="assignee")
    private String assignee;
    @Column(header="claims")
    private String claims;
    @Column(name="grant_date",header="GrantDate")
    private Timestamp grantDate;
    @Column(name="first_inventor",header="FirstInventor")
    private String firstInventor;
    @Column(name="first_applicant",header="FirstApplicant")
    private String firstApplicant;
    @Column(header="pdfs")
    private String pdfs;

    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getYjCompanyId() {
        return yjCompanyId;
    }
    public void setYjCompanyId(Integer yjCompanyId) {
        this.yjCompanyId = yjCompanyId;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getJyid() {
        return jyid;
    }
    public void setJyid(String jyid) {
        this.jyid = jyid;
    }
    public String getApplicationNumber() {
        return applicationNumber;
    }
    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }
    public Timestamp getApplicationDate() {
        return applicationDate;
    }
    public void setApplicationDate(Timestamp applicationDate) {
        this.applicationDate = applicationDate;
    }
    public String getPublicationNumber() {
        return publicationNumber;
    }
    public void setPublicationNumber(String publicationNumber) {
        this.publicationNumber = publicationNumber;
    }
    public Timestamp getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Timestamp publicationDate) {
        this.publicationDate = publicationDate;
    }
    public String getLegalStatusDesc() {
        return legalStatusDesc;
    }
    public void setLegalStatusDesc(String legalStatusDesc) {
        this.legalStatusDesc = legalStatusDesc;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getKindCodeDesc() {
        return kindCodeDesc;
    }
    public void setKindCodeDesc(String kindCodeDesc) {
        this.kindCodeDesc = kindCodeDesc;
    }
    public String getDocumentTypes() {
        return documentTypes;
    }
    public void setDocumentTypes(String documentTypes) {
        this.documentTypes = documentTypes;
    }
    public String getMainIpc() {
        return mainIpc;
    }
    public void setMainIpc(String mainIpc) {
        this.mainIpc = mainIpc;
    }
    public String getAgent() {
        return agent;
    }
    public void setAgent(String agent) {
        this.agent = agent;
    }
    public Timestamp getLegalStatusDate() {
        return legalStatusDate;
    }
    public void setLegalStatusDate(Timestamp legalStatusDate) {
        this.legalStatusDate = legalStatusDate;
    }
    public String getPrimaryExaminer() {
        return primaryExaminer;
    }
    public void setPrimaryExaminer(String primaryExaminer) {
        this.primaryExaminer = primaryExaminer;
    }
    public String getAssiantExaminer() {
        return assiantExaminer;
    }
    public void setAssiantExaminer(String assiantExaminer) {
        this.assiantExaminer = assiantExaminer;
    }
    public String getCites() {
        return cites;
    }
    public void setCites(String cites) {
        this.cites = cites;
    }
    public String getOtherReferences() {
        return otherReferences;
    }
    public void setOtherReferences(String otherReferences) {
        this.otherReferences = otherReferences;
    }
    public String getPatentImage() {
        return patentImage;
    }
    public void setPatentImage(String patentImage) {
        this.patentImage = patentImage;
    }
    public String getAbstractInfo() {
        return abstractInfo;
    }
    public void setAbstractInfo(String abstractInfo) {
        this.abstractInfo = abstractInfo;
    }
    public String getPriorityNumber() {
        return priorityNumber;
    }
    public void setPriorityNumber(String priorityNumber) {
        this.priorityNumber = priorityNumber;
    }
    public Timestamp getPriorityDate() {
        return priorityDate;
    }
    public void setPriorityDate(Timestamp priorityDate) {
        this.priorityDate = priorityDate;
    }
    public String getAssignee() {
        return assignee;
    }
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    public String getClaims() {
        return claims;
    }
    public void setClaims(String claims) {
        this.claims = claims;
    }
    public Timestamp getGrantDate() {
        return grantDate;
    }
    public void setGrantDate(Timestamp grantDate) {
        this.grantDate = grantDate;
    }
    public String getFirstInventor() {
        return firstInventor;
    }
    public void setFirstInventor(String firstInventor) {
        this.firstInventor = firstInventor;
    }
    public String getFirstApplicant() {
        return firstApplicant;
    }
    public void setFirstApplicant(String firstApplicant) {
        this.firstApplicant = firstApplicant;
    }
    public String getPdfs() {
        return pdfs;
    }
    public void setPdfs(String pdfs) {
        this.pdfs = pdfs;
    }
}
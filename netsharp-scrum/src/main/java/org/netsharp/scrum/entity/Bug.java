package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.scrum.dic.Importance;
import org.netsharp.scrum.dic.StoryStatus;
import org.netsharp.scrum.dic.Urgency;

@BizCode(bizType="BUG")
@Table(name="scrum_bug",header="研发缺陷")
public class Bug extends BizEntity {
	
	
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -784762849274727069L;
	@Column(name="testor_id")
	private Integer testorId;
	@Reference(foreignKey="testorId")
	private Employee testor;//测试人员
	@Column(name="developer_id")
	private Integer developerId;
	@Reference(foreignKey="developerId")
	private Employee developer;//测试人员
	
	@Column(name="putor_id")
	private Integer putorId;
	
	@Reference(foreignKey="putorId")
	private Employee putor;//提出人
	
	@Column(name="project_id")
	private Integer projectId;
	
	@Reference(foreignKey="projectId")
	private Project project;//提出人
	
	
	private StoryStatus status = StoryStatus.hibernate;//支持状态
	
	@Column(size=2000)
	private String content;//缺陷描述
	
	@Column(size=1000)
	private String service;//开发说明
	
	private Importance importance = Importance.general;//重要性
	
	private Urgency urgency = Urgency.general;//紧急性
	
	@Column(name="estimate_hours")
	private Double estimateHours;//估时，以小时为单位
	
	@Column(name="actual_hours")
	private Double actualHours;//实际耗时，以小时为单位
	
	@Column(name="file_path",size=200)
	private String filePath;
	
	public StoryStatus getStatus() {
		return status;
	}
	public void setStatus(StoryStatus status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPutorId() {
		return putorId;
	}
	public void setPutorId(Integer putorId) {
		this.putorId = putorId;
	}
	public Employee getPutor() {
		return putor;
	}
	public void setPutor(Employee putor) {
		this.putor = putor;
		
		if(this.putor==null){
			this.putorId=null;
		}
		else{
			this.putorId=this.putor.getId();
		}
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public Importance getImportance() {
		return importance;
	}
	public void setImportance(Importance importance) {
		this.importance = importance;
	}
	public Urgency getUrgency() {
		return urgency;
	}
	public void setUrgency(Urgency urgency) {
		this.urgency = urgency;
	}
	public Double getEstimateHours() {
		return estimateHours;
	}
	public void setEstimateHours(Double estimateHours) {
		this.estimateHours = estimateHours;
	}
	public Double getActualHours() {
		return actualHours;
	}
	public void setActualHours(Double actualHours) {
		this.actualHours = actualHours;
	}
	public Integer getTestorId() {
		return testorId;
	}
	public void setTestorId(Integer testorId) {
		this.testorId = testorId;
	}
	public Employee getTestor() {
		return testor;
	}
	public void setTestor(Employee testor) {
		this.testor = testor;
		
		if(this.testor==null){
			this.testorId=null;
		}
		else{
			this.testorId=this.testor.getId();
		}
	}
	public Integer getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(Integer developerId) {
		this.developerId = developerId;
	}
	public Employee getDeveloper() {
		return developer;
	}
	public void setDeveloper(Employee developer) {
		
		this.developer = developer;
		
		if(this.developer==null){
			this.developerId=null;
		}
		else{
			this.developerId=this.developer.getId();
		}
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
}

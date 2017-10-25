package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="scrum_story_trace",header="任务跟进")
public class StoryTrace extends Entity {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -3593232205242204113L;
	@Column( name = "story_id")
	private Integer storyId;
	@Reference(foreignKey="storyId")
    private Story story;
	
	@Column(size=1000)
	private String content;

	public Integer getStoryId() {
		return storyId;
	}

	public void setStoryId(Integer storyId) {
		this.storyId = storyId;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story project) {
		this.story = project;
		if(this.story==null){
			this.storyId=null;
		}
		else{
			this.storyId=this.story.getId();
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}

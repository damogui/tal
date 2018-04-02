package com.gongsibao.entity.acount;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_account_recommend",header="为您推荐")
public class AccountRecommend extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 2216763942421232094L;
	
	@Column(name = "title", header = "推荐标题")
	private String title;
	
	@Column(name = "img", header = "图片url")
    private String img;
    
	@Column(name = "link", header = "图片点击链接")
    private String link;
    
	@Column(name = "sort", header = "排序")
    private Integer sort;
	
	@Column(name = "hot", header = "热门 0否 1是")
    private Boolean hot = false;
	
	@Column(name = "sort",size=500, header = "描述信息")
    private String desc;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }
	public Boolean getHot() {
		return hot;
	}
	public void setHot(Boolean hot) {
		this.hot = hot;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
    
}
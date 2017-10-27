package org.netsharp.scrum.entity;

import org.netsharp.core.annotations.BizCode;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;

@BizCode(bizType="PC")
@Table(name="scrum_project_cat",header="项目分类")
public class ProjectCategory  extends CatEntity {
	private static final long serialVersionUID = 1L;
}

	
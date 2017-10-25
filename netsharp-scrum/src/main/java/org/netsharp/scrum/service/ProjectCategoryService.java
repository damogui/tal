package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IProjectCategoryService;
import org.netsharp.scrum.entity.ProjectCategory;
import org.netsharp.service.PersistableService;

@Service
public class ProjectCategoryService extends PersistableService<ProjectCategory> implements IProjectCategoryService {
	public ProjectCategoryService(){
		super();
		this.type=ProjectCategory.class;
	}
}
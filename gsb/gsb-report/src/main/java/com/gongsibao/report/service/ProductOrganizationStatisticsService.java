package com.gongsibao.report.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.report.ProductOrganizationStatistics;
import com.gongsibao.report.base.IProductOrganizationStatisticsService;

@Service
public class ProductOrganizationStatisticsService extends PersistableService<ProductOrganizationStatistics> implements IProductOrganizationStatisticsService {

	public ProductOrganizationStatisticsService() {
		super();
		this.type = ProductOrganizationStatistics.class;
	}
}
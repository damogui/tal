package com.gongsibao.report.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.report.ProductDistrictStatistics;
import com.gongsibao.report.base.IProductDistrictStatisticsService;

@Service
public class ProductDistrictStatisticsService extends PersistableService<ProductDistrictStatistics> implements IProductDistrictStatisticsService {

	public ProductDistrictStatisticsService() {
		super();
		this.type = ProductDistrictStatistics.class;
	}
}
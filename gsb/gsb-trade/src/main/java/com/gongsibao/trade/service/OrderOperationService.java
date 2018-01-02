package com.gongsibao.trade.service;

import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.dto.SoOrderDTO;
import com.gongsibao.trade.base.IOrderOperationService;

@Service
public class OrderOperationService extends SoOrderDTOService implements IOrderOperationService {

	public OrderOperationService(){
        super();
        this.type=SoOrderDTO.class;
    }

	
}

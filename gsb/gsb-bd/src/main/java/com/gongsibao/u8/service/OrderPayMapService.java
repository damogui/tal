package com.gongsibao.u8.service;

import com.gongsibao.entity.er.Order;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.u8.base.IOrderPayMapService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderPayMapService extends PersistableService<OrderPayMap> implements IOrderPayMapService {


    public OrderPayMapService() {
        super ();
        this.type = OrderPayMap.class;
    }
    @Override
    public List<OrderPayMap> queryByOrderIdPayId(Integer orderId, Integer payId) {
        Oql oql = new Oql ();
        {
            oql.setType (this.type);
            oql.setSelects ("*");
            oql.setFilter ("orderId=" + orderId + "");
            oql.setFilter ("payId=" + payId + "");
        }
        return this.pm.queryList (oql);
    }
}

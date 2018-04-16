package com.gongsibao.trade.base;

import java.util.Map;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.Invoice;
import org.netsharp.core.annotations.Transaction;

public interface IInvoiceService extends IPersistableService<Invoice> {

    /**
     * ���뷢Ʊ
     *
     * @param @return ����
     * @return Boolean    ��������
     * @throws
     * @Title: applyInvoice
     * @Description: TODO
     */
    @Transaction
    public Boolean applyInvoice(Invoice invoice, Map<String, Object> paraMap);

    void updateStatus(Integer id, AuditStatusType auditStatusType);

    Invoice getById(Integer id);
}
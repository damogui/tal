package com.gongsibao.crm.service;

import com.gongsibao.entity.crm.dic.CapitalType;
import com.gongsibao.entity.crm.dic.CompanyOrgType;
import com.gongsibao.entity.crm.dic.CompanyType;
import com.gongsibao.entity.crm.dic.RegisterCapitalType;
import org.netsharp.communication.Service;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICompanyIntentionService;
import com.gongsibao.entity.crm.CompanyIntention;
import org.netsharp.util.StringManager;

import java.sql.Types;

@Service
public class CompanyIntentionService extends PersistableService<CompanyIntention> implements ICompanyIntentionService {

    public CompanyIntentionService() {
        super();
        this.type = CompanyIntention.class;
    }

    @Override
    public CompanyIntention getByCompanyName(String companyName) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("company_name = ? ");
            oql.getParameters().add("@companyName", companyName, Types.VARCHAR);
        }
        CompanyIntention companyIntention = this.queryFirst(oql);
        return companyIntention;
    }

    @Override
    public CompanyIntention save(CompanyIntention entity) {

        CompanyIntention company = getByCompanyName(entity.getCompanyName());
        if (company != null) {
            throw new BusinessException("该公司名称已经存在，禁止保存");
        }

        setDefaultValue(entity);
        return super.save(entity);
    }

    //设置默认值
    private void setDefaultValue(CompanyIntention entity) {
        //公司组织形式(441)
        if (entity.getOrgType() == null) {
            entity.setOrgType(CompanyOrgType.TYPE_44101);
        }
        //公司分类 0地域+公司名+行业特征+公司类型, 1公司名+（地域）+行业特征+公司类型 2公司名+行业特征+（地域）+公司类型")
        if (entity.getCompanyType() == null) {
            entity.setCompanyType(CompanyType.CompanyType_0);
        }
        if (entity.getOrderProdId() == null) {
            entity.setOrderProdId(0);
        }
        if (StringManager.isNullOrEmpty(entity.getOrderContactName())) {
            entity.setOrderContactName("");
        }
        if (StringManager.isNullOrEmpty(entity.getOrderContactMobile())) {
            entity.setOrderContactMobile("");
        }
        if (StringManager.isNullOrEmpty(entity.getOrderContactEmail())) {
            entity.setOrderContactEmail("");
        }
        if (entity.getSetupStatus() == null) {
            entity.setSetupStatus(false);
        }
        if (entity.getCityId() == null) {
            entity.setCityId(0);
        }
        if (entity.getIsSelfAddress() == null) {
            entity.setIsSelfAddress(0);
        }
        if (entity.getCapitalType() == null) {
            entity.setCapitalType(CapitalType.CapitalType_1);
        }
        if (entity.getRegisterCapital() == null) {
            entity.setRegisterCapital(0);
        }
        if (entity.getRegisterCapitalType() == null) {
            entity.setRegisterCapitalType(RegisterCapitalType.CompanyType_0);
        }
        if (entity.getIsSelfCapital() == null) {
            entity.setIsSelfCapital(false);
        }
        if (entity.getIsExpress() == null) {
            entity.setIsExpress(false);
        }
        if (entity.getIsNameVerify() == null) {
            entity.setIsNameVerify(false);
        }
        if (entity.getNameVerifyFileId() == null) {
            entity.setNameVerifyFileId(0);
        }
        if (StringManager.isNullOrEmpty(entity.getVerifyNo())) {
            entity.setVerifyNo("");
        }
        if (entity.getIsDelete() == null) {
            entity.setIsDelete(false);
        }
        if (entity.getOrganizationNo() == null) {
            entity.setOrganizationNo("");
        }
        if (entity.getOrderContractQq() == null) {
            entity.setOrderContractQq("");
        }
        if (entity.getOrderContractWechat() == null) {
            entity.setOrderContractWechat("");
        }
        if (entity.getResidenceType() == null) {
            entity.setResidenceType(1);
        }
        if (entity.getHouseSpace() == null) {
            entity.setHouseSpace(0);
        }
        if (entity.getLogoUrl() == null) {
            entity.setLogoUrl("");
        }
        if (entity.getNationTax() == null) {
            entity.setNationTax("");
        }
        if (entity.getLocalTax() == null) {
            entity.setLocalTax("");
        }
        if (entity.getDescription() == null) {
            entity.setDescription("");
        }
    }

}
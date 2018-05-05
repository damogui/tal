package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.CompanyName;
import com.gongsibao.entity.igirl.ic.ex.dict.NameStructureType;
import com.gongsibao.igirl.ic.base.IcCompanyNameService;
import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;

import java.sql.Types;
import java.util.List;

@Service
public class CompanyNameService extends GsbPersistableService<CompanyName> implements IcCompanyNameService {
    public CompanyNameService() {
        super();
        this.type = CompanyName.class;
    }

    @Override
    public String getName(CompanyName companyName) {
        NameStructureType type = companyName.getType();
        StringBuffer sb = new StringBuffer();
        if (type.equals(NameStructureType.ONE)){
            sb.append(companyName.getRegion()).
                    append(companyName.getEntTra()).
                    append(companyName.getIndustryType().getText()).
                    append(companyName.getOrganizationalType().getText());
        }else if(type.equals(NameStructureType.TWO)){
            sb.append(companyName.getEntTra()).append("(").
                    append(companyName.getRegion()).append(")").
                    append(companyName.getIndustryType().getText()).
                    append(companyName.getOrganizationalType().getText());
        }else{
            sb.append(companyName.getEntTra()).
                    append(companyName.getIndustryType().getText()).append("(").
                    append(companyName.getRegion()).append(")").
                    append(companyName.getOrganizationalType().getText());
        }
        return sb.toString();
    }

    @Override
    public CompanyName byEntTra(String entTra) {
        Oql oql = new Oql();
        oql.setType(CompanyName.class);
        oql.setSelects("*");
        oql.setFilter("entTra=?");
        oql.getParameters().add("entTra",entTra, Types.VARCHAR);
        return this.pm.queryFirst(oql);
    }

    @Override
    public CompanyName updateState(Integer id) {
        CompanyName companyName = this.byId(id);
        if (companyName!=null){
            Integer exbid = companyName.getExcelBaseInfoId();
            Oql oql = new Oql();
            oql.setType(CompanyName.class);
            oql.setSelects("*");
            oql.setFilter("excelBaseInfoId=? and state=1");
            oql.getParameters().add("excelBaseInfoId",exbid,Types.INTEGER);
            List<CompanyName> companyNames = this.pm.queryList(oql);
            for (CompanyName cn:companyNames){
                cn.setState(false);
                cn.setEntityState(EntityState.Persist);
            }
            companyName.setState(true);
            companyName.setEntityState(EntityState.Persist);
            companyNames.add(companyName);
            super.saves(companyNames);
        }
        return companyName;
    }
}

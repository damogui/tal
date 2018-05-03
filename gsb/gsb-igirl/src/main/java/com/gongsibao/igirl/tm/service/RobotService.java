package com.gongsibao.igirl.tm.service;

import com.gongsibao.entity.igirl.tm.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.tm.dict.ConfigType;
import com.gongsibao.igirl.tm.base.IGirlConfigService;
import com.gongsibao.igirl.tm.base.IGirlRobotService;
import com.gongsibao.igirl.tm.utils.RobotUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import java.sql.Types;

@Service
public class RobotService implements IGirlRobotService{
    IGirlConfigService configService = ServiceFactory.create(IGirlConfigService.class);
    @Override
    public String postToRobot(String msg) {
        String content;
        Oql oql1 = new Oql();
        oql1.setType(IGirlConfig.class);
        oql1.setSelects("IGirlConfig.*");
        oql1.setFilter("configType = ?");
        oql1.getParameters().add("configType", ConfigType.IGIRL_TOKEN.getValue(), Types.INTEGER);
        IGirlConfig token = configService.queryFirst(oql1);
        if (token!=null){
            RobotUtils.postToRobot(msg,token.getConfigValue());
            content= "发送成功";
        }else{
            content = "token参数未设置";
        }
        return content;
    }

    @Override
    public String postToRobotErrorMsg(String msg) {
        String content;
        Oql oql1 = new Oql();
        oql1.setType(IGirlConfig.class);
        oql1.setSelects("IGirlConfig.*");
        oql1.setFilter("configType = ?");
        oql1.getParameters().add("configType", ConfigType.IGIRL_TOKEN_ERR.getValue(), Types.INTEGER);
        IGirlConfig token = configService.queryFirst(oql1);
        if (token!=null){
            RobotUtils.postToRobotErrorMsg(msg,token.getConfigValue());
            content = "发送成功";
        }else{
            content = "tokenErr参数未设置";
        }
        return content;
    }
}

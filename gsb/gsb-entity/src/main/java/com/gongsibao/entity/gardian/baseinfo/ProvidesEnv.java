package com.gongsibao.entity.gardian.baseinfo;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.*;
import org.netsharp.entity.Entity;


@Table(name="gd_provides_env",header="服务环境")
public class ProvidesEnv extends Entity {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4813059865807109558L;

	@Column(name = "name", header = "名称")
    private String name;

    @Column(name = "private_ip", header = "内网IP")
    private String privateip;

    @Column(name = "private_port", header = "内网端口")
    private int privateport;

    @Column(name = "public_ip", header = "外网ip")
    private String publicip;

    @Column(name = "public_port", header = "外网端口")
    private int publicport;


    @Column(name = "provides_id", header = "服务id")
    private Integer providesId;

    @JsonIgnore
    @Reference(foreignKey="providesId",header="服务id")
    private Provides provides;

    public ProvidesEnv() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrivateip() {
        return privateip;
    }

    public void setPrivateip(String privateip) {
        this.privateip = privateip;
    }



    public String getPublicip() {
        return publicip;
    }

    public void setPublicip(String publicip) {
        this.publicip = publicip;
    }

    public int getPrivateport() {
        return privateport;
    }

    public void setPrivateport(int privateport) {
        this.privateport = privateport;
    }

    public int getPublicport() {
        return publicport;
    }

    public void setPublicport(int publicport) {
        this.publicport = publicport;
    }




    public Integer getProvidesId() {
        return providesId;
    }

    public void setProvidesId(Integer providesId) {
        this.providesId = providesId;
    }

    public Provides getProvides() {
        return provides;
    }

    public void setProvides(Provides provides) {
        this.provides = provides;
    }


//    @Reference(foreignKey="tradeMarkCaseId",header="商标方案")
//    private TradeMarkCase tradeMarkCase;

}


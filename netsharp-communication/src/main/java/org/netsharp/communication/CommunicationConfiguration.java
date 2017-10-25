package org.netsharp.communication;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.util.PropertyConfigurer;
import org.netsharp.util.StringManager;

/*通讯配置*/
public class CommunicationConfiguration {

    private TransportProtocol transportProtocol;
    private String address;
    private Integer port=8888;
    
    private static CommunicationConfiguration instance=null;

    public static CommunicationConfiguration getConfiguration(){
    	
    	if(instance==null)
    	{
    		
    		PropertyConfigurer conf=PropertyConfigurer.newInstance("/conf/communication.properties");
    		
    		instance = new CommunicationConfiguration();
    		{
    			instance.setPort(Integer.valueOf(conf.get("communication.port")));
    			instance.setAddress(conf.get("communication.address"));
    			instance.setTransportProtocol(TransportProtocol.valueOf(conf.get("communication.transportProtocol")));
    		}
    	}
    	
    	return CommunicationConfiguration.instance;
    }
    
    @Override
    public String toString(){
    	List<String> ss = new ArrayList<String>();
    	ss.add("address:"+this.address);
    	ss.add("transportProtocol:"+this.transportProtocol);
    	ss.add("port:"+this.port);
    	
    	return StringManager.join(StringManager.NewLine, ss);
    }

    public TransportProtocol getTransportProtocol() {
        return transportProtocol;
    }

    public void setTransportProtocol(TransportProtocol transportProtocol) {
        this.transportProtocol = transportProtocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}

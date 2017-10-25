package org.netsharp.communication;

/**
 * 通讯服务调用协议类型
 */
public enum TransportProtocol {

    local("本地调用"),rmi("rmi调用"), http("http调用");

    private String text;

    TransportProtocol(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
}

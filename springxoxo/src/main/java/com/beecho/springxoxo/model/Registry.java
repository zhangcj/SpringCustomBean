package com.beecho.springxoxo.model;

/**
 * @author 春哥大魔王
 */

public class Registry {
    private String id;
    private String address;
    private String username;
    private String password;
    private int timeout;
    private int session; // 注册中心会话超时时间（毫秒），用于检测提供者非正常断线后的脏数据，比如用户心跳检测的实现，此时间就是心跳间隔，不同于注册中心实现
    private Boolean check; // 注册中心不存在时，是否报错
    private String protocol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}

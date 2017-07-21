package com.beecho.springxoxo.model;

/**
 * @author 春哥大魔王
 */

public class Reference {
    private String id;
    private String service;
    private String group;

    private Boolean check;
    private int retries;
    private String cluster;
    private String loadbalance;
    private int version;
    private int timeout; // 服务方法调用超时时间（毫秒）
    private int connections; // 每个服务提供者最大连接数
    private String url; // 用于测试环境，绕过注册中心，直连服务提供者
    private String owner; // 调用者负责人
    private int actives; // 每个服务消费者每服务每方法最大并发调用数


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getLoadbalance() {
        return loadbalance;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getConnections() {
        return connections;
    }

    public void setConnections(int connections) {
        this.connections = connections;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getActives() {
        return actives;
    }

    public void setActives(int actives) {
        this.actives = actives;
    }
}

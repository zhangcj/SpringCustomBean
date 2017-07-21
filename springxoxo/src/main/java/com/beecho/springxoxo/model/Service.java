package com.beecho.springxoxo.model;

/**
 * @author 春哥大魔王
 */

public class Service {
    private String id;
    private String service;
    private String ref;
    private String protocol;
    private String group;

    private String cluster;
    private int retries;
    private String loadbalance;
    /*
    当一个接口实现，出现不兼容升级时，可以通过版本号过渡，版本号不同的服务互相间不引用

        在低压力时间段，先升级一半提供者为新版本
        在将所有消费者升级为新版本
        然后将剩下的一半提供者升级为新版本
     */
    private String version;
    private int delay; // 延迟暴露秒数，用于初始化一些资源

    private int timeout; // 远程服务调用超时时间（毫秒）
    private int connections; // 对每个提供者提供的最大连接数
    private String token; // 令牌验证
    private Boolean deprecated; // 服务是否过时，如果为true，消费方引用时，将打印过时error警告日志
    private Boolean accesslog; // 是否需要输出访问日志
    private String owner; // 服务负责人
    private String document; // 服务接口说明文档，wiki地址
    private int weight; // 权重
    private int executes; // 服务提供者每个服务方法最大可并行执行的请求数
    private int actives; // 每服务消费者每服务方法最大并发调用数
    private String filter; // 服务提供方远程调用过程拦截器名称
    private String listener; // 服务提供方导出服务监听器名称

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

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public String getLoadbalance() {
        return loadbalance;
    }

    public void setLoadbalance(String loadbalance) {
        this.loadbalance = loadbalance;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        this.deprecated = deprecated;
    }

    public Boolean getAccesslog() {
        return accesslog;
    }

    public void setAccesslog(Boolean accesslog) {
        this.accesslog = accesslog;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getExecutes() {
        return executes;
    }

    public void setExecutes(int executes) {
        this.executes = executes;
    }

    public int getActives() {
        return actives;
    }

    public void setActives(int actives) {
        this.actives = actives;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getListener() {
        return listener;
    }

    public void setListener(String listener) {
        this.listener = listener;
    }
}

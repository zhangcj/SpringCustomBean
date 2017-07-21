package com.beecho.springxoxo.model;

/**
 * @author 春哥大魔王
 */

public class Protocol {
    private String id;
    private String name;
    private int port;
    private String host;
    private String threadpool;
    private int threads; // 服务线程池大小
    private int iothreads; // io线程池大小
    private int accepts; // 服务提供方最大可接受连接数
    private int payload; // 请求及响应数据包大小限制，单位：字节
    private String codec; // 编解码方式
    private String serialization; // 序列化方式
    private Boolean accesslog; // 是否输出访问日志
    private String dispatcher; // 置顶线程模型
    private int queues; // 线程池队列大小，当线程池满时，排队等待执行的队列大小，建议不要设置，当线程程池时应立即失败，重试其它服务提供机器，而不是排队，除非有特殊需求。
    private int buffer; // 网络读写缓冲区大小

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getThreadpool() {
        return threadpool;
    }

    public void setThreadpool(String threadpool) {
        this.threadpool = threadpool;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getIothreads() {
        return iothreads;
    }

    public void setIothreads(int iothreads) {
        this.iothreads = iothreads;
    }

    public int getAccepts() {
        return accepts;
    }

    public void setAccepts(int accepts) {
        this.accepts = accepts;
    }

    public int getPayload() {
        return payload;
    }

    public void setPayload(int payload) {
        this.payload = payload;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    public Boolean getAccesslog() {
        return accesslog;
    }

    public void setAccesslog(Boolean accesslog) {
        this.accesslog = accesslog;
    }

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    public int getQueues() {
        return queues;
    }

    public void setQueues(int queues) {
        this.queues = queues;
    }

    public int getBuffer() {
        return buffer;
    }

    public void setBuffer(int buffer) {
        this.buffer = buffer;
    }
}

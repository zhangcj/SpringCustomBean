启动时检查：

    默认提供启动时检查，消费者启动时，默认需要检查服务提供者是否可用。
    不可用抛出异常，阻止Spring初始化完成，以便上线时，能及时发现问题。


集群容错模式：

Failover Cluster(默认选项):
    失败自动切换，当出现失败，重试其他服务器。（缺省）
    通常用于读操作，但重试会带来更长的延迟。
    可以通过retries = 2来设置重试次数（不包含第一次）。

Failfast Cluster:
    快速失败，只发起一次调用，失败立即报错。
    通常用于非幂等性的写操作，比如新增记录。

Failsafe Cluster:
    失败安全，出现异常时，直接忽略。
    通常用于写入审计日志等操作。

Failback Cluster:
    失败自动恢复，后台记录失败请求，定时重发。
    通常用户消息通知操作

Forking Cluster:
    并行调用多个服务器，只要一个成功即返回。
    通常用于实时性要求较高的读操作，但需要浪费更多服务资源。
    可通过forks = 2来设置最大并行数。

Broadcast Cluster:
    广播调用所有提供者，逐个调用，任意一台报错则报错。
    通常用于通知所有提供者更新缓存或日志等本地资源信息。


消费端负载均衡：

Random LoadBalance:
    随机。
    在一个截面上碰撞的概率高，但调用量越大分布越均匀，而且按概率使用权重后也比较均匀，有利于动态调整提供者权重。

RoundRobin LoadBalance:
    轮询。
    存在慢的提供者累积请求问题，比如：第二台机器很慢，但没挂，当请求调到第二台卡在那里，久而久之，所有的请求都卡在第二台上。

LeatActive LoadBanlance:
    最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差。
    使慢的提供者收到更少的请求，因为越慢的提供者调用的前后计数差越大。


上下文信息：

上下文中存放的是当前调用过程所需的环境信息。

RpcContext是一个ThreadLocal的临时状态记录器，当接收到RPC请求，或发起RPC请求时，RpcContext的状态都会变化。
比如：A调B，B调C，则B机器上，在B调C之前，RpcContext记录的是A调B的信息，在B调C之后，RpcContext记录的是B调C的信息。

服务消费方：
xxxService.xxx(); // 远程调用
boolean isConsumerSide = RpcContext.getContext().isConsumerSide(); // 本端是否为消费端，这里会返回true
String serverIP = RpcContext.getContext().getRemoteHost(); // 获取最后一次调用的提供方IP地址
String application = RpcContext.getContext().getUrl().getParameter("application"); // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
// ...
yyyService.yyy(); // 注意：每发起RPC调用，上下文状态会变化
// ...

服务提供方：
public class XxxServiceImpl implements XxxService {

    public void xxx() { // 服务方法实现
        boolean isProviderSide = RpcContext.getContext().isProviderSide(); // 本端是否为提供端，这里会返回true
        String clientIP = RpcContext.getContext().getRemoteHost(); // 获取调用方IP地址
        String application = RpcContext.getContext().getUrl().getParameter("application"); // 获取当前服务配置信息，所有配置信息都将转换为URL的参数
        // ...
        yyyService.yyy(); // 注意：每发起RPC调用，上下文状态会变化
        boolean isProviderSide = RpcContext.getContext().isProviderSide(); // 此时本端变成消费端，这里会返回false
        // ...
    }

}


服务延迟暴露：

如果你的服务需要Warmup时间，比如初始化缓存，等待相关资源就位等，可以使用delay进行延迟暴露。
延迟到Spring初始化完成后，在暴露服务：（基于Spring的ContextRefreshedEvent事件触发暴露）。

消费方直连提供者：

在开发及测试环境下，经常需要绕过注册中心，只注册指定服务提供者，这时可能需要点对点的直连。
<xxoo:reference id="xxxService" interface="com.alibaba.xxx.XxxService" url="dubbo://localhost:20890" />


配置说明：

    只有 group, interface, version 三者匹配决定同一个服务，其他配置均为调优和治理参数。

    追加配置项有三类：
        服务发现：表示该配置项用于服务的注册与发现，目的是让消费方找到提供方。
        服务治理：表示该配置项用于治理服务间的关系，或为开发测试提供便利的条件。
        性能调优：表示该配置项用于调优性能，不同的选型对性能产生不同的影响。




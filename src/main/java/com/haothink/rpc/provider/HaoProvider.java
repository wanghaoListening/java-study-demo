package com.haothink.rpc.provider;

import java.io.Serializable;

/**
 * @author wanghao
 * @date 2019年06月11日 11:11
 * description: 服务暴露
 * 服务暴露就是将服务发布到(zookeeper)上，提供给其他的消费者访问，当消费者拿到这个信息之后，
 * 就可以连接服务端，并发起请求。 暴露就是需要将该服务的基本信息发布出去，这里列举的主要信息
 * 有 服务机器host 服务监听端口 服务接口 服务序列化方式 服务权重 服务版本
 *
 */
public class HaoProvider implements Serializable, Cloneable {


    private static final long serialVersionUID = -6022960317995998676L;

    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 服务所在host 域名orip
     */
    private String host;

    /**
     * 服务暴露的机器端口
     */
    private Integer port;

    /**
     * 服务的版本号
     */
    private String version;

    /**
     * 服务的权重
     */
    private Integer weight;

    /**
     * 服务的序列化方式
     */
    private String serialization;


    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }
}

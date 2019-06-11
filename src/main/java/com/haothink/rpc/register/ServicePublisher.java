package com.haothink.rpc.register;

import com.haothink.rpc.provider.HaoProvider;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author wanghao
 * @date 2019年06月11日 11:20
 * description: 将服务以节点的形式添加到 zk 上
 */
public class ServicePublisher implements Publisher{

    private static final Logger logger = LoggerFactory.getLogger(ServicePublisher.class);

    private static final String ROOT_PATH = "/hao";

    private static final String ROOT_PROVIDER = "provider";

    private static final String split = "&";

    private ZkClient zkClient;

    public ServicePublisher() {
        this.zkClient = new ZkClient("127.0.0.1",2181);
    }

    @Override
    public void registerService(List<HaoProvider> providerList) {

        assert zkClient != null;
        providerList.parallelStream().forEach(provider -> {
            String host = provider.getHost();
            Integer port = provider.getPort();
            String serviceName = provider.getServiceName();
            String version = provider.getVersion();
            Integer weight = provider.getWeight();
            String serverPath = ROOT_PATH + "/" + serviceName + ROOT_PROVIDER;
            if (!zkClient.exists(serverPath)) {
                zkClient.createPersistent(serverPath, true);
            }
            String finalInfo = host + split + port + split + serviceName + split + version + split + weight + split + provider.getSerialization();
            String path = serverPath + "/" + finalInfo;
            if (!zkClient.exists(path)) {
                logger.info("注册服务:{}到ZooKeeper", serverPath);
                zkClient.createEphemeral(path);
            } else {
                logger.warn("服务:{}已被注册", serverPath);
            }
        });
    }
}

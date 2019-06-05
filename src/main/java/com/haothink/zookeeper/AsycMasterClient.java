package com.haothink.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Random;

/**
 * @author wanghao
 * @date 2019年05月17日 19:46
 * description:
 */
public class AsycMasterClient {


    String serverId = Integer.toHexString(new Random().nextInt());
    static boolean isLeader;

    ZooKeeper zk;
    String hostPort;

    AsyncCallback.StringCallback masterCreateCallBack = new AsyncCallback.StringCallback() {
        @Override
        public void processResult(int rc, String s, Object o, String s1) {

            switch(KeeperException.Code.get(rc)){

                case CONNECTIONLOSS:
                    return;
                case OK:
                    isLeader = true;
                    break;
                default:
                    isLeader = false;

            }
            System.out.println("I am"+isLeader+"the leader");
        }
    };

    AsyncCallback.DataCallback masterCheckCallBack = new AsyncCallback.DataCallback() {
        @Override
        public void processResult(int rc, String s, Object o, byte[] bytes, Stat stat) {

            switch (KeeperException.Code.get(rc)){
                case CONNECTIONLOSS:
                    checkMaster();
                    return;
                case NONODE:
                    runForMaster();
                    return;
                default:

            }
        }
    };

    void runForMaster(){

        zk.create("/master",serverId.getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,masterCreateCallBack,null);
    }

    void checkMaster(){
        zk.getData("/master",false,masterCheckCallBack,null);
    }


}

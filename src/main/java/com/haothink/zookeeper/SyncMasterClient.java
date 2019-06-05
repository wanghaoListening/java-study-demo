package com.haothink.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

/**
 * @author wanghao
 * @date 2019年05月16日 15:13
 * description:
 */
public class SyncMasterClient implements Watcher {

    String serverId = Integer.toHexString(new Random().nextInt());
    static boolean isLeader = false;
    ZooKeeper zk;
    String hostPort;

    public SyncMasterClient(String hostPort) {
        this.hostPort = hostPort;
    }

    void startZookeeper() throws IOException {

        zk = new ZooKeeper(hostPort,15000,this);
    }

    void stopZookeeper() throws InterruptedException {
        zk.close();
    }

    boolean checkMaster() {
        while (true){
            try {
                Stat stat = new Stat();
                byte[] data = zk.getData("/master",false,stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (InterruptedException e) {
                return false;
            } catch (KeeperException e) {
                //try again
            }
        }
    }

    void runForMaster(){
        while (true){
            try {
                zk.create("/master",serverId.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (InterruptedException e) {
                isLeader = false;
                break;
            } catch (KeeperException e) {
                // try again
            }
            if(checkMaster()){break;}

        }
    }
    @Override
    public void process(WatchedEvent watchedEvent) {

        System.out.println(watchedEvent);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        final String hostPort = "127.0.0.1:2181";
        SyncMasterClient masterClient = new SyncMasterClient(hostPort);
        masterClient.startZookeeper();
        masterClient.runForMaster();
        if(isLeader){
            System.out.println("I am a leader");
            Thread.sleep(80000);
        }else{
            System.out.println("someone else is leader");
        }

        masterClient.stopZookeeper();
    }
}

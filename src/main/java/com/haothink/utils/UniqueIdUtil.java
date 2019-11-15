package com.haothink.utils;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

/**
 * Created by wanghao on 2019-11-12
 * mail:wanghaotime@qq.com
 **/
public class UniqueIdUtil {


    public static void main(String[] args) throws UnknownHostException {
        System.out.println(getUniqueTime());
    }

    public static String getUUID(){

        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String getUniqueTime() throws UnknownHostException {

        long currentTimeMillis = System.currentTimeMillis();
        Random RANDOM = new Random();
        int randomNum = RANDOM.nextInt(10000);

        String ip = getHostIp();

        if(Objects.nonNull(ip)){
            ip = ip.replaceAll("\\.","");
        }
        return currentTimeMillis+"-"+ip+"-"+randomNum;
    }

    public static String getHostIp(){
        try{
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()){
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress ip = addresses.nextElement();
                    if (ip instanceof Inet4Address
                            && !ip.isLoopbackAddress() //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                            && !ip.getHostAddress().contains(":")){
                        return ip.getHostAddress();
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}

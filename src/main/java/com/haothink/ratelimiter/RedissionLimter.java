package com.haothink.ratelimiter;

import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;


/**
 * @author wanghao
 * @date 2019年08月09日 17:15
 * description: redission 限流
 */
public class RedissionLimter {

    private static RedissonClient redisson;


    static {
        //namespace star
        Config config = new Config();
        config.useSentinelServers()
                .setPassword("jswd50x,1x1zshn")
                .setMasterName("zhicheng-star-master")
                //可以用"rediss://"来启用SSL连接
                .addSentinelAddress("10.143.130.245:26379", "10.143.130.246:26379","10.143.130.246:26379");

        redisson = Redisson.create(config);
    }


    public static void main(String[] args) {

        RRateLimiter rateLimiter = redisson.getRateLimiter("rateLimiter");
        // 初始化
        // 最大流速 = 每1秒钟产生10个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 10, 1, RateIntervalUnit.SECONDS);

        if(rateLimiter.tryAcquire()){

        }
    }
}

package com.springbootredis.lock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class LockService {
    private static final Logger logger = LogManager.getLogger(LockService.class);
    @Autowired
    @Qualifier("redisson")
    private RedissonClient redisson;

    @Value("${redisson.leaseTime}")
    private int redissonLockLease;

    @Value("${redisson.waitTime}")
    private int redissonWaitTime;

    public RLock getLock(String key){
        RLock lock = redisson.getFairLock(key);
        return lock;
    }


    public boolean tryLock(RLock lock){
        try {
            return lock.tryLock(redissonWaitTime, redissonLockLease, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            logger.error("[Lock Acquisition] " + e.getMessage());
            return false;
        }
    }

    public void releaseLock(RLock lock){
        try {
            if(lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        } catch (Exception e) {
            logger.error("[Lock Release] " + e.getMessage());
        }
    }
}

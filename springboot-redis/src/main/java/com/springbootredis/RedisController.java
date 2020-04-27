package com.springbootredis;

import org.redisson.api.RLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootredis.dto.Customer;
import com.springbootredis.lock.LockService;
import com.springbootredis.repository.CustomerRedisRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RedisController {
    private final CustomerRedisRepository customerRedisRepository;
    private final LockService lockService;

    @GetMapping(value = "/save-redis")
    public Customer saveToRedis() throws Exception {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setFirstName("oah");
        customer.setLastName("pv");
        customer.setTimeToLive(100000);

        String lockKey = "LOCK_KEY" + String.valueOf(customer.getId()) + customer.getFirstName();
        RLock lock = lockService.getLock(lockKey);

        Customer customerInLock= null;
        if (lockService.tryLock(lock)) {
            try {
                // if current thread is holding lock
                // do get customer from redis
                customerInLock = customerRedisRepository.findById(123L).orElseThrow(() -> new Exception());
            } finally {
                lockService.releaseLock(lock); // if occur exception => release lock anyway
            }
        } else {
            throw new Exception("Acquire lock");
        }
        return customerRedisRepository.save(customerInLock);
    }

    @GetMapping(value = "/get-redis")
    public Customer getRedis() throws Exception {

        return customerRedisRepository.findById(123L).orElseThrow(() -> new Exception());
    }
}

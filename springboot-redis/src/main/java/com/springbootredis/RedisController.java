package com.springbootredis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springbootredis.dto.Customer;
import com.springbootredis.repository.CustomerRedisRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RedisController {
    private final CustomerRedisRepository customerRedisRepository;

    @GetMapping(value = "/save-redis")
    public Customer saveToRedis() {
        Customer customer = new Customer();
        customer.setId(123L);
        customer.setFirstName("oah");
        customer.setLastName("pv");
        customer.setTimeToLive(100000);
        return customerRedisRepository.save(customer);
    }

    @GetMapping(value = "/get-redis")
    public Customer getRedis() throws Exception {
        return customerRedisRepository.findById(123L).orElseThrow(() -> new Exception());
    }
}

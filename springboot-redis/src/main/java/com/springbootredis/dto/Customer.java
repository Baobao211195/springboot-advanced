package com.springbootredis.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "customer")
public class Customer implements Serializable {
    @Id
    @Indexed
    private Long id;
    private String firstName;
    private String lastName;
//    private Address address;
    @TimeToLive
    private long timeToLive;
}
@Data
@Builder
class Address {
    private String street;
    private String city;
    private String state;
    private String zipcode;
}
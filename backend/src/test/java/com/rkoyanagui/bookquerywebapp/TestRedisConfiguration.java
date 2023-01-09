package com.rkoyanagui.bookquerywebapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.testcontainers.containers.GenericContainer;

@TestConfiguration
public class TestRedisConfiguration
{
    private final int port;

    public TestRedisConfiguration(@Value("${spring.redis.port}") int port)
    {
        this.port = port;
    }

    @Bean
    public GenericContainer<?> redisContainer()
    {
        var fullImageName = "redis/redis-stack:6.2.6-v0";
        var container = new GenericContainer<>(fullImageName).withExposedPorts(port);
        container.start();
        return container;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(GenericContainer<?> redisContainer)
    {
        var host = redisContainer.getHost();
        var mappedPort = redisContainer.getMappedPort(port);
        var config = new RedisStandaloneConfiguration(host, mappedPort);
        return new JedisConnectionFactory(config);
    }
}

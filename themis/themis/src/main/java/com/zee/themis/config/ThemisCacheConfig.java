package com.zee.themis.config;

import com.zee.themis.dto.RuleNameSpaceCache;
import com.zee.themis.entity.RuleNamespace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

/**
 * @author Kuldeep Gupta
 * @Date 24/06/22
 */

@Configuration
public class ThemisCacheConfig {

    @Value("${spring.redis.cluster.nodes}")
    private List<String> clusterNodes;

    @Value("${spring.redis.primaryEndpoint}")
    private String primaryEndpoint;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.readerEndpoint}")
    private String readerEndpoint;
    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.commandTimeout}")
    private Duration commandTimeout;

    @Bean(name= "redisTemplate")
    public RedisTemplate <RuleNamespace, RuleNameSpaceCache> redisTemplate(){
        RedisTemplate<RuleNamespace, RuleNameSpaceCache> redisTemplate= new RedisTemplate<RuleNamespace, RuleNameSpaceCache>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean(name = "inHousePaymentRedisTemplate")
    public RedisTemplate <String, String> inHousePaymentRedisTemplate(){
        RedisTemplate<String, String> redisTemplate= new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        //client config
        LettuceClientConfiguration lettuceClientConfiguration= LettuceClientConfiguration
                .builder()
                .commandTimeout(commandTimeout)
                .useSsl()
                .build();
        //server config
//        RedisClusterConfiguration redisClusterConfiguration= new RedisClusterConfiguration(clusterNodes);
//        redisClusterConfiguration.setPassword(password);
//        return new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
        RedisStaticMasterReplicaConfiguration redisStaticMasterReplicaConfiguration= new RedisStaticMasterReplicaConfiguration(primaryEndpoint, port);
        redisStaticMasterReplicaConfiguration.addNode(readerEndpoint, port);
        redisStaticMasterReplicaConfiguration.setPassword(password);
        return new LettuceConnectionFactory(redisStaticMasterReplicaConfiguration, lettuceClientConfiguration);
    }
}

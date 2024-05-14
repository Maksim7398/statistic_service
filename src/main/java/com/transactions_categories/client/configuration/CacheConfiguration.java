package com.transactions_categories.client.configuration;

import com.transactions_categories.client.configuration.properties.AppCacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@EnableConfigurationProperties(AppCacheProperties.class)
public class CacheConfiguration {

    @Bean
    @ConditionalOnExpression("'${app.cache.cacheType}'.equals('redis')")
    public CacheManager redisCacheManager(AppCacheProperties appCacheProperties, LettuceConnectionFactory lettuceConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
        appCacheProperties.getCacheNames().forEach(cacheName -> {
            redisCacheConfigurationMap.put(cacheName, redisCacheConfiguration.entryTtl(
                    appCacheProperties.getCashes().get(cacheName).getExpiry()
            ));
        });

        return RedisCacheManager.builder(lettuceConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .withInitialCacheConfigurations(redisCacheConfigurationMap)
                .build();
    }
}

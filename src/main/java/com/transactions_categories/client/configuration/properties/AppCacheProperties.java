package com.transactions_categories.client.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "app.cache")
public class AppCacheProperties {

    private List<String> cacheNames;

    private Map<String,CacheProperties> cashes;

    @Data
    public static class CacheProperties{
        private Duration expiry = Duration.ZERO;
    }

    public interface CacheNames{

        String OKVED_CATEGORY = "okved-category";

    }

}

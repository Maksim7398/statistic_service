package com.transactions_categories.client;

import com.transactions_categories.client.model.RequestQuery;
import com.transactions_categories.client.model.Suggestions;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Component
@RequiredArgsConstructor
public class OKVEDClient {

    private final WebClient webClient;

    public Set<Suggestions> getTypeForCode(RequestQuery query) {
        return webClient
                .post()
                .header("Authorization", "Token 0ad41cccd3c7936e8dc7e074a4049a3661a68a3c")
                .body(BodyInserters.fromValue(query))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, List<Suggestions>>>() {
                })
                .map(m -> new HashSet<>(m.get("suggestions")))
                .block();
    }

}

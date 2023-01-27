package com.xgileit.mp.messageprocessor.redirectService.webClient.subscriptionService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

@Component
public class SubscriptionClientService {

    WebClient client = WebClient.create("http://localhost:8081/subMapping");

    public Boolean getSubResponse(String subReferenceId) {
        Mono<Boolean> employeeMono = client.get()
                .uri((UriBuilder uri) -> uri
                        .path("/getSubStatus")
                        .queryParam("subReferenceId", subReferenceId)
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Boolean.class);

        Boolean response = employeeMono.block();
        return response;
    }
}

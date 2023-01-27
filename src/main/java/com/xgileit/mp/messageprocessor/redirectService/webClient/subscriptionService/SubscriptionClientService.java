package com.xgileit.mp.messageprocessor.redirectService.webClient.subscriptionService;

import com.xgileit.mp.messageprocessor.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class SubscriptionClientService {

    WebClient client = WebClient.create("http://localhost:8081/subMapping");

    public Map<String,Object> getSubResponse(String subReferenceId) {
        Mono<Map> employeeMono = client.get()
                .uri((UriBuilder uri) -> uri
                        .path("/getSubDetails")
                        .queryParam("subReferenceId", subReferenceId)
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Map.class);

        Map<String,Object> response = employeeMono.block();
        return response;
    }
}

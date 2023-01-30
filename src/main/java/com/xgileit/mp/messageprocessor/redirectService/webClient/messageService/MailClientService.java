package com.xgileit.mp.messageprocessor.redirectService.webClient.messageService;

import com.xgileit.mp.messageprocessor.dto.RequestDto;
import com.xgileit.mp.messageprocessor.dto.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MailClientService {

    WebClient client = WebClient.create("http://localhost:8080");

    public ResponseDto sendEmail(RequestDto requestData) {
        Mono<ResponseDto> employeeMono = client.post()
                .uri("/sendAttachEmail")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(requestData), RequestDto.class)
                .retrieve()
                .bodyToMono(ResponseDto.class);

        ResponseDto response = employeeMono.block();
        return response;
    }
}

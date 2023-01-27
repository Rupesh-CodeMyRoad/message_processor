package com.xgileit.mp.messageprocessor.service;

import com.xgileit.mp.messageprocessor.dto.RequestDto;
import com.xgileit.mp.messageprocessor.dto.ResponseDto;
import com.xgileit.mp.messageprocessor.model.RequestResponse;
import com.xgileit.mp.messageprocessor.redirectService.webClient.messageService.MailClientService;
import com.xgileit.mp.messageprocessor.redirectService.webClient.subscriptionService.SubscriptionClientService;
import com.xgileit.mp.messageprocessor.repository.RequestResponseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class MessageParserServiceImpl implements MessageParserService {

    private final MailClientService mailClientService;
    private final RequestResponseRepo requestResponseRepo;
    private final SubscriptionClientService subscriptionClientService;

    @Override
    public Object parseMessage(RequestDto request) {
        ResponseDto result = null;
        RequestResponse requestData;
        if (request.getSubReferenceId() == null) {
            throw new RuntimeException("Please Subscribe first to use services");
        }
        Boolean checkSubscriptionValidity = subscriptionClientService.getSubResponse(request.getSubReferenceId());
        if (checkSubscriptionValidity == false) {
            throw new RuntimeException("Please Subscribe first to use services");
        }
//        Map<String, Object> messageProperties = extractMessageProperties(request.getSubReferenceId());
//        if (messageProperties.get("subType").equals("SMS")){
        if (true) {
             requestData = RequestResponse.builder()
                    .request(request.toString())
                    .status(false)
                    .subReferenceId(request.getSubReferenceId())
                    .build();
            requestResponseRepo.save(requestData);
            try {
                result = mailClientService.getResponse(request);
            }catch (Exception e){
                //handle exception Here
                requestData.setResponse(e.getMessage());
                requestResponseRepo.save(requestData);
                throw new RuntimeException("Unable to Send Message");
            }
            if (result.getStatusCode() == 200){
                requestData.setResponse(result.toString());
                requestData.setStatus(true);
                requestResponseRepo.save(requestData);
            }else {
                //Handle error response here
            }
        } else {
            //another condition for sms or we-chat
        }
        return result;

    }

    private static Map<String, Object> extractMessageProperties(String subReferenceId) {
        Map<String, Object> data = new HashMap<>();
        String consumer = null;
        String subType = null;
        Matcher matcher;
        Pattern pattern = Pattern.compile("(?<=\\-)(.*?)(?=\\-)");
        matcher = pattern.matcher(subReferenceId);
        if (matcher.find()) {
            consumer = matcher.group(1);
            subType = matcher.group(2);
        }
        System.out.println(consumer);
        System.out.println(subType);

        data.put("consumer", consumer);
        data.put("subType", subType);
        return data;
    }
}

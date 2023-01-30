package com.xgileit.mp.messageprocessor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
    public Object parseMessage(RequestDto request) throws JsonProcessingException {
        ResponseDto result = null;
        Map<String,Object> subResponse;
        Map<String,Object> subData;
        Map<String,Object> subTypeData;
        Boolean subStatus;
        String subType;
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();


        RequestResponse requestData;
        if (request.getSubReferenceId() == null) {
            throw new RuntimeException("Please Subscribe first to use services");
        }
        try {
            subResponse = subscriptionClientService.getSubResponse(request.getSubReferenceId());
            subData = (Map<String, Object>) subResponse.get("subData");
            subTypeData = (Map<String, Object>) subData.get("subType");
            subStatus = (Boolean) subData.get("status");
            subType = (String) subTypeData.get("subName");
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        } if (subResponse == null) {
            throw new RuntimeException("Sorry no any subscription history found");
        }
        if (subStatus == true && subType.equals("SMS")) {
             requestData = RequestResponse.builder()
                    .request(ow.writeValueAsString(request))
                    .status(false)
                    .subReferenceId(request.getSubReferenceId())
                    .build();
            requestResponseRepo.save(requestData);
            try {
                result = mailClientService.sendEmail(request);
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

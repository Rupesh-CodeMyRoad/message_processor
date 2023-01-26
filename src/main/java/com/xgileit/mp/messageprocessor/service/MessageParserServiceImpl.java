package com.xgileit.mp.messageprocessor.service;

import com.xgileit.mp.messageprocessor.dto.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageParserServiceImpl implements MessageParserService {

//    @Autowired
//    MailServiceController mailServiceController;

    @Override
    public Object parseMessage(RequestDto request) {
        if (request.getSubReferenceId() == null) {
            throw new RuntimeException("Please Subscribe first to use services");
        }
        Map<String, Object> messageProperties = extractMessageProperties(request.getSubReferenceId());
//        if (messageProperties.get("subType").equals("SMS")){
        if (true) {
//            List<String> result = mailServiceController.getString("Rupesh");
            System.out.println("a");
        } else {
            return null;
        }
        return null;

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

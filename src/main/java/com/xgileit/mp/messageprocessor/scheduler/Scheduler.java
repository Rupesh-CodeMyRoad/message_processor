package com.xgileit.mp.messageprocessor.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgileit.mp.messageprocessor.controller.MessageParserController;
import com.xgileit.mp.messageprocessor.dto.RequestDto;
import com.xgileit.mp.messageprocessor.model.RequestResponse;
import com.xgileit.mp.messageprocessor.repository.RequestResponseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Scheduler {

    @Autowired
    RequestResponseRepo requestResponseRepo;

    @Autowired
    MessageParserController messageParserController;

    @Scheduled(cron = "* * * * * *")
    public void cronJobSch() throws JsonProcessingException {
        List<RequestResponse> requestList = requestResponseRepo.findAll().stream().filter(requestResponse -> Boolean.FALSE == requestResponse.getStatus()).collect(Collectors.toList());
        for (RequestResponse request : requestList) {

            RequestDto reDto = new ObjectMapper().readValue(request.getRequest(), RequestDto.class);
            reDto.setRequestResponseId(request.getRequestResponseId());
            messageParserController.messageHandler(reDto);
            System.out.println("request :" + reDto);
        }
    }
}

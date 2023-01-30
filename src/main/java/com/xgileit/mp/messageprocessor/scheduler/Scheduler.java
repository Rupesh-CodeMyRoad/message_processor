package com.xgileit.mp.messageprocessor.scheduler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xgileit.mp.messageprocessor.controller.MessageParserController;
import com.xgileit.mp.messageprocessor.dto.RequestDto;
import com.xgileit.mp.messageprocessor.model.RequestResponse;
import com.xgileit.mp.messageprocessor.repository.RequestResponseRepo;

@Component
public class Scheduler {
    
	@Autowired RequestResponseRepo requestResponseRepo;
	
	@Autowired MessageParserController  messageParserController;
	
	@Scheduled(cron = "0 0 12 * * ?")
    public void cronJobSch() throws JsonMappingException, JsonProcessingException {
    List<RequestResponse> requestList = requestResponseRepo.findAll().stream().filter(requestResponse ->Boolean.FALSE==requestResponse.getStatus()).collect(Collectors.toList());
    for (RequestResponse request : requestList) {
	
    RequestDto reDto	= new ObjectMapper().readValue(request.getRequest(), RequestDto.class);  
    messageParserController.saveSubType(reDto);
     System.out.println("request :"+reDto);
	}
	}
}

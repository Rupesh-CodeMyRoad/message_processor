package com.xgileit.mp.messageprocessor.scheduler;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xgileit.mp.messageprocessor.model.RequestResponse;
import com.xgileit.mp.messageprocessor.repository.RequestResponseRepo;

@Component
public class Scheduler {
    
	@Autowired RequestResponseRepo requestResponseRepo;
	@Scheduled(cron = "* 23 10 * * *")
    public void cronJobSch() {
      
        System.out.println("hello");
      Map <String,String> requestMap=  requestResponseRepo.findAll().stream().filter(requestResponse ->Boolean.FALSE==requestResponse.getStatus()).collect(Collectors.toMap(RequestResponse::getSubReferenceId, RequestResponse::getRequest));
      for (Map.Entry<String, String> entry : requestMap.entrySet())
		{
    	    System.out.println("refID="+entry.getKey()+"  request ="+entry.getValue());
    	  
		}
    }
}

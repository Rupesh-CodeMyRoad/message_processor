package com.xgileit.mp.messageprocessor.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Scheduler {
    @Scheduled(cron = "59 23 * * *")
    public void cronJobSch() {
        System.out.println("hello");
    }
}

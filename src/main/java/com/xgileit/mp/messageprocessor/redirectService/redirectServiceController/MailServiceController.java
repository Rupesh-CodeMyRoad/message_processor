//package com.xgileit.mp.messageprocessor.redirectService.redirectServiceController;
//
//import com.xgileit.mp.messageprocessor.redirectService.MailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class MailServiceController {
//    private final MailService mailFeignService;
//
//    @PostMapping("/post")
//    public List<String> getString(String data) {
//        List<String> result = mailFeignService.callSmsService(data);
//        System.out.println(result);
//        return result;
//    }
//}

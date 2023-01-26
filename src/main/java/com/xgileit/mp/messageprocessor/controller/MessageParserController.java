package com.xgileit.mp.messageprocessor.controller;

import com.xgileit.mp.messageprocessor.dto.RequestDto;
import com.xgileit.mp.messageprocessor.service.MessageParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/msgParser")
public class MessageParserController {

    private final MessageParserService service;

    /**
     * Used to transfer message from queue to respective services
     *
     * @return success or failure response
     */
    @PostMapping(value = "/parse")
    public ResponseEntity<?> saveSubType(@RequestBody RequestDto request) {
        return ResponseEntity.ok(service.parseMessage(request));
    }
}

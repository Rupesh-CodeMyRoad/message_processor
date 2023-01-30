package com.xgileit.mp.messageprocessor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xgileit.mp.messageprocessor.dto.RequestDto;
import com.xgileit.mp.messageprocessor.service.MessageParserService;

import lombok.RequiredArgsConstructor;

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
    public ResponseEntity<?> saveSubType(@ModelAttribute RequestDto request) throws JsonProcessingException {
        return ResponseEntity.ok(service.parseMessage(request));
    }
}

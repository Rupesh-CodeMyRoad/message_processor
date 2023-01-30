package com.xgileit.mp.messageprocessor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.xgileit.mp.messageprocessor.dto.RequestDto;

public interface MessageParserService {
    Object parseMessage(RequestDto message) throws JsonProcessingException;
}

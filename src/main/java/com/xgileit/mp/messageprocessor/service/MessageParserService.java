package com.xgileit.mp.messageprocessor.service;

import com.xgileit.mp.messageprocessor.dto.RequestDto;

public interface MessageParserService {
    Object parseMessage(RequestDto message);
}

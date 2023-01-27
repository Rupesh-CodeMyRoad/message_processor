package com.xgileit.mp.messageprocessor.dto;

import lombok.Data;

@Data
public class ResponseDto {
    String messageId;
    String awsRequestId;
    int statusCode;
}

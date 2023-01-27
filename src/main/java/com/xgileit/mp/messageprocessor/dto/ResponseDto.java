package com.xgileit.mp.messageprocessor.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseDto {
    String messageId;
    String awsRequestId;
    int statusCode;


//    Long subMappingId;
//    String subReferenceId;
//    String consumerName;
//    Boolean status;
//    Date dateLogged;
//    Long subType;
}

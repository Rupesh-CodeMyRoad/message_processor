package com.xgileit.mp.messageprocessor.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Data
public class RequestDto {
    Long requestResponseId;
    String fromEmail;
    String toEmail;
    String subject;
    String body;
    String subReferenceId;

    List<String> toEmailList;
    String templateName;
    Set<MultipartFile> attachment;
}

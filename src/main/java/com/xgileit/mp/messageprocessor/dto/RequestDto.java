package com.xgileit.mp.messageprocessor.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Data
public class RequestDto {
    String from;
    String to;
    String subject;
    String body;
    String subReferenceId;

    List<String> toList;
    String templateName;
    Set<MultipartFile> attachment;
}

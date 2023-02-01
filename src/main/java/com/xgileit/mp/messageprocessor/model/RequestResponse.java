package com.xgileit.mp.messageprocessor.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity responsible for storing Request Response data
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "request_response")
public class RequestResponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_response_id", nullable = false)
    private Long requestResponseId;

    @Column(name = "sub_reference_id", nullable = false)
    private String subReferenceId;

    @Column(name = "request", nullable = false)
    private String request;

    @Column(name = "response")
    private String response;

    @Column(name = "status")
    private Boolean status;

}

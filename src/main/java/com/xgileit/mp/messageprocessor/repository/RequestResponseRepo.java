package com.xgileit.mp.messageprocessor.repository;

import com.xgileit.mp.messageprocessor.model.RequestResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestResponseRepo extends JpaRepository<RequestResponse, Long> {
}

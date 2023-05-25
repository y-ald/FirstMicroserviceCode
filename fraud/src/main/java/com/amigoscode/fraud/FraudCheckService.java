package com.amigoscode.fraud;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;
    
    public boolean isFraudulentCustomer(Integer customerId) {
        fraudCheckHistoryRepository.save(
            FraudCheckHistory.builder()
                    .customerId(customerId)
                    .isFraudster(false)
                    .createAt(LocalDateTime.now())
                    .build()   
        );
        return false;
    }
}

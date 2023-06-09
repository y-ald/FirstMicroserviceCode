package com.amigoscode.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepositry customerRepositry;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepositry.saveAndFlush(customer);
        // todo: check if fraudster
        FraudCheckResponse fraudCheckResponse =  restTemplate.getForObject(
            "http://FRAUD/api/v1/fraud-check/{customerId}",
            FraudCheckResponse.class,
            customer.getId()
        );

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        // todo: send notification 
    }

}

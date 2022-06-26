package com.leonardobatistacarais.estore.usersservice.query;

import com.leonardobatistacarias.estore.core.model.PaymentDetails;
import com.leonardobatistacarias.estore.core.model.User;
import com.leonardobatistacarias.estore.core.query.FetchUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {

    @QueryHandler
    public User findUserPaymentDetails(FetchUserPaymentDetailsQuery query) {

        PaymentDetails paymentDetails = PaymentDetails.builder()
                .cardNumber("1234456789")
                .cvv("123")
                .name("Leonardo Batista")
                .validUntilMonth(12)
                .validUntilYear(2030)
                .build();

        User user = User.builder()
                .firstName("Leonardo")
                .lastName("Batista")
                .userId(query.getUserId())
                .paymentDetails(paymentDetails)
                .build();

        return user;
    }
}

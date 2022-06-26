package com.leonardobatistacarias.estore.orderservice.core.events;

import com.leonardobatistacarias.estore.orderservice.core.data.model.OrderStatus;
import lombok.Value;

@Value
public class OrderApprovedEvent {

    private final String orderId;
    private OrderStatus orderStatus = OrderStatus.APPROVED;
}

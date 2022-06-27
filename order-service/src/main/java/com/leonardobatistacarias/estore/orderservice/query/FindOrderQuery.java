package com.leonardobatistacarias.estore.orderservice.query;

import lombok.Value;

@Value
public class FindOrderQuery {
    private final String orderId;
}

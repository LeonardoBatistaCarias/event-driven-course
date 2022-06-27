package com.leonardobatistacarias.estore.orderservice.query;

import com.leonardobatistacarias.estore.orderservice.core.data.OrderEntity;
import com.leonardobatistacarias.estore.orderservice.core.data.OrdersRepository;
import com.leonardobatistacarias.estore.orderservice.core.data.model.OrderSummary;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class OrderQueriesHandler {

    OrdersRepository ordersRepository;

    public OrderQueriesHandler(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @QueryHandler
    public OrderSummary findOrder(FindOrderQuery findOrderQuery) {
        OrderEntity orderEntity = ordersRepository.findByOrderId(findOrderQuery.getOrderId());
        return new OrderSummary(orderEntity.getOrderId(),
                orderEntity.getOrderStatus(), "");
    }

}

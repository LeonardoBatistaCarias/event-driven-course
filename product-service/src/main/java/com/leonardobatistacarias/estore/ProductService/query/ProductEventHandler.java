package com.leonardobatistacarias.estore.ProductService.query;

import com.leonardobatistacarias.estore.ProductService.event.ProductCreatedEvent;
import com.leonardobatistacarias.estore.ProductService.event.core.data.ProductEntity;
import com.leonardobatistacarias.estore.ProductService.event.core.data.repository.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    private final ProductRepository productRepository;

    public ProductEventHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        productRepository.save(productEntity);
    }
}

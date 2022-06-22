package com.leonardobatistacarias.estore.ProductService.command;

import com.leonardobatistacarias.estore.ProductService.event.ProductCreatedEvent;
import com.leonardobatistacarias.estore.ProductService.event.core.data.ProductLookupEntity;
import com.leonardobatistacarias.estore.ProductService.event.core.data.repository.ProductLookupRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {

    private final ProductLookupRepository productLookupRepository;

    public ProductLookupEventsHandler(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductLookupEntity productLookupEntity = new ProductLookupEntity(event.getProductId(), event.getTitle());
        productLookupRepository.save(productLookupEntity);
    }

}

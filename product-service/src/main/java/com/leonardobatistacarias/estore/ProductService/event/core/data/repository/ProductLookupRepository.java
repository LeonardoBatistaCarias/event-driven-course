package com.leonardobatistacarias.estore.ProductService.event.core.data.repository;

import com.leonardobatistacarias.estore.ProductService.event.core.data.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {

    ProductLookupEntity findByProductIdOrTitle(String productId, String title);

}

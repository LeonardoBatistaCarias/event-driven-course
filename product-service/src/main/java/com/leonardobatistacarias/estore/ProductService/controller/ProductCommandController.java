package com.leonardobatistacarias.estore.ProductService.controller;

import com.leonardobatistacarias.estore.ProductService.command.CreateProductCommand;
import com.leonardobatistacarias.estore.ProductService.model.CreateProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductCommandController {

    private final Environment env;
    private final CommandGateway commandGateway;

    public ProductCommandController(Environment env, CommandGateway commandGateway) {
        this.env = env;
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createProduct(@Valid @RequestBody CreateProductRestModel createProductRestModel) {
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .price(createProductRestModel.getPrice())
                .quantity(createProductRestModel.getQuantity())
                .title(createProductRestModel.getTitle())
                .productId(UUID.randomUUID().toString()).build();

        String returnValue;
        try{
            returnValue = commandGateway.sendAndWait(createProductCommand);
        }catch(Exception ex) {
            returnValue = ex.getLocalizedMessage();
        }


        return returnValue;
    }

//    @GetMapping
//    public String getProduct() {
//        return "HTTP GET Handled " + env.getProperty("local.server.port");
//    }
//
//    @PutMapping
//    public String updateProduct() {
//        return "HTTP PUT Handled";
//    }
//
//    @DeleteMapping
//    public String deleteProduct() {
//        return "HTTP DELETE Handled";
//    }

}
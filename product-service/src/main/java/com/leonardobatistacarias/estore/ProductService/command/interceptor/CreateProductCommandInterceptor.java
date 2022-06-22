package com.leonardobatistacarias.estore.ProductService.command.interceptor;

import com.leonardobatistacarias.estore.ProductService.command.CreateProductCommand;
import com.leonardobatistacarias.estore.ProductService.event.core.data.ProductLookupEntity;
import com.leonardobatistacarias.estore.ProductService.event.core.data.repository.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateProductCommandInterceptor.class);

    private ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            LOGGER.info("Intercepted command: " + command.getPayloadType());

            if(CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();

                ProductLookupEntity productLookupEntity = productLookupRepository
                        .findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());

                if(productLookupEntity != null) {
                    throw new IllegalStateException(String.format("Product with product id %s or title %s already exist",
                            createProductCommand.getProductId(), createProductCommand.getTitle()));
                }
            }

            return command;
        };
    }
}

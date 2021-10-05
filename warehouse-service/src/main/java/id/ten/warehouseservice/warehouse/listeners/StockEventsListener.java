package id.ten.warehouseservice.warehouse.listeners;

import id.ten.warehouseservice.warehouse.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockEventsListener {

    public void dispatch(@Payload Object event) {
        var order = (Order) event;
        log.info("Received stock reservation event for order {}", order.getId());
        Flux.fromIterable(order.getOrderLines())
                .flatMap()
    }

}

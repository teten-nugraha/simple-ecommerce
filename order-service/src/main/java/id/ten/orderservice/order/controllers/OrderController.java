package id.ten.orderservice.order.controllers;

import id.ten.orderservice.order.OrderService;
import id.ten.orderservice.order.controllers.models.CreateOrderRequest;
import id.ten.orderservice.order.controllers.models.CreateOrderResponse;
import id.ten.orderservice.order.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ResponseStatus(CREATED)
    @PostMapping
    public Mono<CreateOrderResponse> createNewOrder(@Validated @RequestBody CreateOrderRequest request) {
        log.info("Initiate order request from customer {}", request.getCustomerId());

        return orderService.create(request.toOrder())
                .doOnNext(orderService::reserveStock)
                .map(Order::getId)
                .map(CreateOrderResponse::new);
    }

}

package id.ten.orderservice.order.controllers.models;

import id.ten.orderservice.order.domain.Address;
import id.ten.orderservice.order.domain.Order;
import id.ten.orderservice.order.domain.OrderLine;
import id.ten.orderservice.order.domain.PaymentDetails;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

import static id.ten.orderservice.order.domain.OrderStatus.INITIATED_RESERVING_STOCK;

@Value
@Builder
@RequiredArgsConstructor
public class CreateOrderRequest {

    @NotEmpty
    private final String customerId;

    @NotEmpty
    @Valid
    private final List<OrderLine> orderLines;

    @NotNull
    @Valid
    private final Address shippingAddress;

    @NotNull
    @Valid
    private final Address billingAddress;

    @NotNull
    @Valid
    private final PaymentDetails paymentDetails;

    public Order toOrder() {
        return Order.builder()
                .billingAddress(billingAddress)
                .shippingAddress(shippingAddress)
                .orderLines(orderLines)
                .customerId(customerId)
                .dateCreated(Instant.now())
                .status(INITIATED_RESERVING_STOCK)
                .paymentDetails(paymentDetails)
                .build();
    }
}

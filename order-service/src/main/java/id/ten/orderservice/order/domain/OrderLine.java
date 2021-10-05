package id.ten.orderservice.order.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Value
@RequiredArgsConstructor
public class OrderLine {
    @NotEmpty
    private final String itemId;
    @Min(1)
    private final Integer quantity;
}

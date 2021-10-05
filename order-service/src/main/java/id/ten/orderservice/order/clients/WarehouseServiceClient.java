package id.ten.orderservice.order.clients;

import id.ten.orderservice.order.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static id.ten.orderservice.common.configs.KafkaConfig.WAREHOUSE_STOCK_RESERVE_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
public class WarehouseServiceClient {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendStockReservationEvent(Order order) {
        log.info("reserving stock for order {} by customer {}", order.getId(), order.getCustomerId());
        var key  = String.format("%s-stock-reservation", order.getId());
        kafkaTemplate.send(WAREHOUSE_STOCK_RESERVE_TOPIC, key, order);
    }
}

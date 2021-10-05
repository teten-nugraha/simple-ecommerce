package id.ten.orderservice.order;

import id.ten.orderservice.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Mono<Order> create(Order order) {
        return orderRepository.save(order);
    }

}

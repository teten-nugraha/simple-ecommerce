package id.ten.orderservice.order;


import id.ten.orderservice.order.domain.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

interface OrderRepository extends ReactiveMongoRepository<Order, String> {
}

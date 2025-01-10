package br.com.bersch.service;

import br.com.bersch.dto.OrderRequest;
import br.com.bersch.model.Order;
import br.com.bersch.model.Product;
import br.com.bersch.repository.OrderRepository;
import br.com.bersch.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Order createOrder(List<OrderRequest> orderRequests) {
        Order order = new Order();
        for (OrderRequest orderRequest : orderRequests) {
            Optional<Product> productOpt = productRepository.findById(orderRequest.getProductId());

            if (productOpt.isPresent()) {
                Product product = productOpt.get();
                product.setAmount(orderRequest.getAmount());
                order.getProducts().add(product);
            } else {
                throw new RuntimeException("Product not found with ID: " + orderRequest.getProductId());
            }
        }
        order.calculatePrice();
        return orderRepository.save(order);
    }

    public Optional<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId);
    }

    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}

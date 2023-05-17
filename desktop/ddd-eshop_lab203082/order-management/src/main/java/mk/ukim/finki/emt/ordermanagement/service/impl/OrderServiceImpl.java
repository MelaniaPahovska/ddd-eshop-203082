package mk.ukim.finki.emt.ordermanagement.service.impl;

import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderIdNotFoundExc;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderItemIdNotFoundExc;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderState;
import mk.ukim.finki.emt.ordermanagement.domain.repository.OrderRepository;
import mk.ukim.finki.emt.ordermanagement.service.OrderService;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderItemForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    //private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;


    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "order must not be null.");
        var violations = validator.validate(orderForm);

        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid order data");
        }

        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        return newOrder.getId();
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotFoundExc {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotFoundExc::new);
        order.addItem(orderItemForm.getProduct(), orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        //domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getProduct().getId().getId(), orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotFoundExc, OrderItemIdNotFoundExc {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotFoundExc::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }

    @Override
    public void cancelOrder(OrderId id) {
        var order = findById(id).orElseThrow(OrderIdNotFoundExc::new);

        order.setOrderState(OrderState.CANCELLED);

        orderRepository.saveAndFlush(order);

    }


    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency());
        orderForm.getItems().forEach(item -> order.addItem(item.getProduct(), item.getQuantity()));
        return order;
    }
}


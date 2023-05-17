package mk.ukim.finki.emt.ordermanagement.xport.rest;


import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.ordermanagement.domain.exceptions.OrderIdNotFoundExc;
import mk.ukim.finki.emt.ordermanagement.domain.model.Order;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderId;
import mk.ukim.finki.emt.ordermanagement.domain.model.OrderItemId;
import mk.ukim.finki.emt.ordermanagement.service.OrderService;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderForm;
import mk.ukim.finki.emt.ordermanagement.service.forms.OrderItemForm;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderRest {
    private final OrderService orderService;

    @GetMapping
    public List<Order> listAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable OrderId id) {
        return orderService.findById(id).orElseThrow(OrderIdNotFoundExc::new);
    }

    @PostMapping("/placeOrder")
    public void placeOrder(@RequestBody OrderForm orderForm, HttpServletResponse response) throws IOException {
        orderService.placeOrder(orderForm);
        response.sendRedirect("/api/orders");
    }

    @PostMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable OrderId id, HttpServletResponse response) throws IOException {
        orderService.cancelOrder(id);
        response.sendRedirect("/api/orders");
    }

    @PostMapping("/{id}/add")
    public void addItem(@PathVariable OrderId id, @RequestBody OrderItemForm orderItemForm, HttpServletResponse response) throws IOException {

        orderService.addItem(id, orderItemForm);
        response.sendRedirect("/api/orders");

    }


    @PostMapping("/{id}/remove")
    public void removeItem(@PathVariable OrderId id, @RequestBody OrderItemId item, HttpServletResponse response) throws IOException {
        orderService.deleteItem(id, item);
        response.sendRedirect("/api/orders");
    }
}
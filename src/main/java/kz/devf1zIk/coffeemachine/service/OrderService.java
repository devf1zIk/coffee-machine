package kz.devf1zIk.coffeemachine.service;

import kz.devf1zIk.coffeemachine.dto.OrderDto;
import kz.devf1zIk.coffeemachine.exception.ValidateException;
import kz.devf1zIk.coffeemachine.model.Order;
import kz.devf1zIk.coffeemachine.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OperationalService operationalService;

    public Long findPopularRecipe() {
        return orderRepository.findMostPopularRecipe();
    }

    public void addOrder(OrderDto orderDto) {
        if (!operationalService.validate()) {
            throw new ValidateException("Not working hours");
        }

        var order = new Order();
        order.setRecipeId(orderDto.getRecipeId());
        order.setDate(LocalDateTime.now());
        orderRepository.save(order);
    }
}

package kz.devf1zIk.coffeemachine.controller;

import kz.devf1zIk.coffeemachine.dto.OrderDto;
import kz.devf1zIk.coffeemachine.dto.RecipeDto;
import kz.devf1zIk.coffeemachine.model.Recipe;
import kz.devf1zIk.coffeemachine.service.OrderService;
import kz.devf1zIk.coffeemachine.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CoffeeMachineController {

    private OrderService orderService;
    private RecipeService recipeService;

    @GetMapping("/statistics/popular")
    public ResponseEntity<Long> popularRecipe() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findPopularRecipe());
    }

    @PostMapping("/recipe")
    public ResponseEntity<Recipe> addRecipe(@RequestBody RecipeDto recipeDto) {
        var saved = recipeService.addRecipe(recipeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/order")
    public ResponseEntity<Object> addOrder(@RequestBody OrderDto orderDto) {
        orderService.addOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package kz.devf1zIk.coffeemachine.repository;

import kz.devf1zIk.coffeemachine.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
        SELECT o.recipeId
        FROM Order o
        GROUP BY o.recipeId
        ORDER BY COUNT(o.recipeId) DESC
        LIMIT 1
    """)
    Long findMostPopularRecipe();
}

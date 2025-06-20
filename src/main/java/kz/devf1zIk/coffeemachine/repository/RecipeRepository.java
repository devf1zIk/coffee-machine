package kz.devf1zIk.coffeemachine.repository;

import kz.devf1zIk.coffeemachine.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

}

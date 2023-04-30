package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.pojo.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}

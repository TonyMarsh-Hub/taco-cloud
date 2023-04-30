package tacos.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.pojo.Ingredient;
import tacos.pojo.Ingredient.Type;
import tacos.pojo.Taco;
import tacos.data.IngredientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute  // 修饰方法时，会在调用目标方法前被 Spring MVC 调用，然后将返回值放入模型中
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("taco", new Taco());
        return "design";
    }

    /**
     * 过滤出指定类型的配料
     *
     * @param ingredients 所有配料
     * @param type        指定类型
     * @return 指定类型的配料
     */
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


    @PostMapping
    public String processTaco(@Valid @ModelAttribute("taco") Taco taco, Errors errors) {
        if (errors.hasErrors()) return "design";
        // Save the taco...
        // We'll do this in chapter 3
        log.info("Processing taco: " + taco);

        return "redirect:/orders/current";
    }
}

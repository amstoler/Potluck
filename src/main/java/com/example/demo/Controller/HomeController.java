package com.example.demo.Controller;

import com.example.demo.Model.FoodItem;
import com.example.demo.Repositories.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping ("/user")
public class HomeController {

    @Autowired
    FoodItemRepo foodItemRepo;

    @GetMapping("/home")
    public  String showHome() {return "home";}

    @GetMapping("/addItem")
    public String showItemForm(Model model) {
        model.addAttribute("foodItem", new FoodItem());

        return "itemForm";

    }

    @PostMapping("/processItem")
    public String displayItems(@Valid @ModelAttribute("foodItem") FoodItem foodItem, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "itemForm";
        }

        foodItemRepo.save(foodItem);

        //Allows EVERYTHING stored in foodItemRepo to display to Itemform.
        model.addAttribute("foodItem", foodItemRepo.findAll());

        return "displayItems";

    }


}

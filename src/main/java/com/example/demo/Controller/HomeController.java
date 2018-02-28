package com.example.demo.Controller;

import com.example.demo.Model.AppRole;
import com.example.demo.Model.AppUser;
import com.example.demo.Model.FoodItem;
import com.example.demo.Repositories.FoodItemRepo;
import com.example.demo.Repositories.RoleRepo;
import com.example.demo.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping ("/user")
public class HomeController {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/home")
    public  String showHome() {return "home";}

    @PostMapping("/index")
    public  String index() {return "home";}
    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("newuser",new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("newuser") AppUser appUser, BindingResult result,HttpServletRequest request)
    {
        if(result.hasErrors())
        {
            return "register";
        }

        appUser.addRole(roleRepo.findAppRoleByRoleName("USER"));

        userRepo.save(appUser);
        return "redirect:/user/home";
    }

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

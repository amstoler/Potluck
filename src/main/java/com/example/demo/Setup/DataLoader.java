package com.example.demo.Setup;

import com.example.demo.Model.AppRole;
import com.example.demo.Model.AppUser;
import com.example.demo.Model.FoodItem;
import com.example.demo.Repositories.FoodItemRepo;
import com.example.demo.Repositories.RoleRepo;
import com.example.demo.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    FoodItemRepo foodItemRepo;

    @Override
    public void run (String...strings) throws Exception {

        AppRole myrole= new AppRole("USER");
        roleRepo.save(myrole);

        myrole= new AppRole("ADMIN");
        roleRepo.save(myrole);

        AppUser user = new AppUser("ariel@email.com","password","Ariel","Stoler", "applicant");
        user.addRole(roleRepo.findAppRoleByRoleName("USER"));
        userRepo.save(user);

        user = new AppUser("admin@email.com", "password", "First Name", "Last Name", "admin");
        user.addRole(roleRepo.findAppRoleByRoleName("ADMIN"));
        FoodItem foodItem = new FoodItem("Pasta", "10", "food");
        foodItemRepo.save(foodItem);
        user.addFoodItem(foodItem);
        userRepo.save(user);




/*
        FoodItem foodItem2 = new FoodItem("Icecream", "20", "dessert" );
        foodItemRepo.save(foodItem);

        FoodItem foodItem3 = new FoodItem("Sprite", "30", "drink");
        foodItemRepo.save(foodItem);

        FoodItem foodItem4 = new FoodItem("Salad", "15", "food");
        foodItemRepo.save(foodItem);*/

    }

}

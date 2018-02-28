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

        AppUser user = new AppUser("ariel@email.com","password","Ariel","Stoler", "user");
        user.addRole(roleRepo.findAppRoleByRoleName("USER"));
        FoodItem foodItem = new FoodItem("Juice", "10", "drink");
        foodItemRepo.save(foodItem);
        user.addFoodItem(foodItem);
        userRepo.save(user);

        user = new AppUser("Bob@email.com", "password", "Bob", "Bobberson", "admin");
        user.addRole(roleRepo.findAppRoleByRoleName("ADMIN"));
        foodItem = new FoodItem("Pasta", "10", "food");
        foodItemRepo.save(foodItem);
        user.addFoodItem(foodItem);
        userRepo.save(user);

        user = new AppUser("Tim@email.com", "password", "Tim", "Timmerson", "admin");
        user.addRole(roleRepo.findAppRoleByRoleName("ADMIN"));
        foodItem = new FoodItem("Icecream", "20", "dessert" );
        foodItemRepo.save(foodItem);
        user.addFoodItem(foodItem);
        userRepo.save(user);

        user = new AppUser("Jim@email.com", "password", "Jim", "Jimmerson", "user");
        user.addRole(roleRepo.findAppRoleByRoleName("USER"));
        foodItem = new FoodItem("Salad", "15", "food");
        foodItemRepo.save(foodItem);
        user.addFoodItem(foodItem);
        userRepo.save(user);

        user = new AppUser("user@email.com", "password", "First Name", "Last Name", "admin");
        user.addRole(roleRepo.findAppRoleByRoleName("ADMIN"));
        foodItem = new FoodItem("Sprite", "30", "drink");
        foodItemRepo.save(foodItem);
        user.addFoodItem(foodItem);
        userRepo.save(user);

    }

}

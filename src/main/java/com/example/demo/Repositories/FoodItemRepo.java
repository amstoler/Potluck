package com.example.demo.Repositories;

import com.example.demo.Model.AppRole;
import com.example.demo.Model.FoodItem;
import org.springframework.data.repository.CrudRepository;

public interface FoodItemRepo extends CrudRepository<FoodItem, Long> {
    FoodItem findFoodItemBy(String foodName);
}

package com.example.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "foodName")
    private String foodName;
    @Column(name = "servingSize" )
    private String servingSize;
    @Column(name = "foodType")
    private String foodType;

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }

    @ManyToMany(mappedBy = "foodItems", fetch = FetchType.LAZY)
    private Set<AppUser> users;

    public FoodItem() {this.users = new HashSet<>();}

    public FoodItem(String foodName, String servingSize, String foodType) {
        this.foodName = foodName;
        this.servingSize = servingSize;
        this.foodType = foodType;
        this.users = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getServingSize() {
        return servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }
}

package org.example.backend.repository;

import org.example.backend.entity.Food;
import org.example.backend.projection.food.FoodProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepo extends JpaRepository<Food, Long> {

    String get_by_category = "select\n" +
            "f.id,\n" +
            "f.name,\n" +
            "f.description,\n" +
            "f.price,\n" +
            "f.image_url,\n" +
            "coalesce(avg(r.quantity), 0) as rating \n" +
            "from food f join category c on f.category_id = c.id\n" +
            "left join rating r on f.id = r.food_id\n" +
            "where c.id = ? group by f.id ";

    @Query(value = get_by_category, nativeQuery = true)
    List<FoodProjection> getFoodProjectionByCategory(Long categoryId);

    String get_all = "select\n" +
            "f.id,\n" +
            "f.name,\n" +
            "f.description,\n" +
            "f.price,\n" +
            "f.image_url,\n" +
            "coalesce(avg(r.quantity), 0) as rating\n" +
            "from food f join category c on f.category_id = c.id\n" +
            "left join rating r on f.id = r.food_id group by f.id ";

    @Query(value = get_all, nativeQuery = true)
    List<FoodProjection> getAllFoodProjection();
}

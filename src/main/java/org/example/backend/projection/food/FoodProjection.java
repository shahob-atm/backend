package org.example.backend.projection.food;

public interface FoodProjection {
    Long getId();
    String getName();
    String getDescription();
    Double getPrice();
    String getImageUrl();
    Integer getRating();
}

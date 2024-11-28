package org.example.backend.projection.order;

public interface OrderProjection {
    Long getId();
    String getOrderDate();
    String getStatus();
    String getUsername();
    Integer getCount();
    Double getPrice();
}

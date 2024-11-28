package org.example.backend.repository;

import org.example.backend.entity.Order;
import org.example.backend.projection.order.OrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    String get_all = "select\n" +
            "o.id,\n" +
            "to_char(o.order_date, 'DD.MM.YYYY HH24:MI') as order_date,\n" +
            "o.status,\n" +
            "u.username,\n" +
            "coalesce(count(orf.id), 0) as count,\n" +
            "sum(orf.price * orf.count) as price\n" +
            "from orders o join order_food orf on o.id = orf.order_id\n" +
            "join users u on o.user_id = u.id group by o.id, u.username";

    @Query(value = get_all, nativeQuery = true)
    List<OrderProjection> getOrderProjections();
}

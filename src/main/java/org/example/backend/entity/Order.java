package org.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CurrentTimestamp
    @Column(columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    private User user;
}

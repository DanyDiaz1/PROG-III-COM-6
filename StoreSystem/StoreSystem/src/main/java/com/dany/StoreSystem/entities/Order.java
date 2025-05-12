package com.dany.StoreSystem.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString (exclude = "local")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String description;
    private double price;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "local_id",
            referencedColumnName = "localId"
    )
    private Local local;


}

package com.dany.StoreSystem.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "manager")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long localId;
    private String name;
    private String floor;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "manager_id", unique=true, nullable = false)
    private Manager manager;

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "local_customer_map",
            joinColumns = @JoinColumn(
                    name = "local_id",
                    referencedColumnName = "localId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "customer_id",
                    referencedColumnName = "customerId"
            )
    )
    private List<Customer> customerList;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "local_id",
            referencedColumnName = "localId"
    )
    private List<Order> orderList;

}

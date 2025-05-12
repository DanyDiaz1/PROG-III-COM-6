package com.dany.StoreSystem.entities;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Builder
public class Address {
    private String city;
    private String mainStreet;
    private String secondaryStreet;
}

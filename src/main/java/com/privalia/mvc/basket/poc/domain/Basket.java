package com.privalia.mvc.basket.poc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Data
@Table(name = "basket")
@Entity
@NoArgsConstructor
public class Basket {

    public Basket (String ownerName, BasketItem item) {
        this.ownerName = ownerName;
        this.items = Arrays.asList(item);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "owner_name")
    private String ownerName;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "update_time")
    private Instant updateTime;

    @OneToMany(fetch = FetchType.EAGER,
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "basket_id")
    private List<BasketItem> items;

}

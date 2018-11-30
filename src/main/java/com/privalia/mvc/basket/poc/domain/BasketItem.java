package com.privalia.mvc.basket.poc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@Table(name = "basket_item")
@Entity
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "amount")
    private Integer amount;

    @UpdateTimestamp
    @Column(name = "update_time")
    @JsonIgnore
    private Instant updateTime;
}

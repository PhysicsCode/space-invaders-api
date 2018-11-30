package com.privalia.mvc.basket.poc.jdbc.repository;


import com.privalia.mvc.basket.poc.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {


    Optional<Basket> getBasketByOwnerName(String ownerName);

    @Modifying
    @Query(nativeQuery = true,
            value = "Delete FROM basket_item " +
                    "Where basket_id = (" +
                        "Select basket.id " +
                        "From basket " +
                        "Where basket.owner_name = ?1) " +
                    "And basket_item.item_id = ?2 ")
    Integer deleteBasketItemByIdAndOwnerName(String ownerName, Long itemId);
}

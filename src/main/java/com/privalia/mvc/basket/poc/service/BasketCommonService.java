package com.privalia.mvc.basket.poc.service;

import com.privalia.mvc.basket.poc.domain.Basket;
import com.privalia.mvc.basket.poc.domain.BasketItem;
import com.privalia.mvc.basket.poc.exception.EmptyBasketException;
import com.privalia.mvc.basket.poc.jdbc.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketCommonService {

    private final BasketRepository basketRepository;

    public Basket getBasketForUser(Principal userPrincipal) {

        return basketRepository.getBasketByOwnerName(userPrincipal.getName())
                .orElseThrow(() -> new EmptyBasketException("Basket is empty"));
    }

    public Basket postItemToBasket(Principal userPrincipal, BasketItem item) {

        return basketRepository.save(basketRepository.getBasketByOwnerName(userPrincipal.getName())
                .map(basket -> rebuildBasket(basket, item))
                .orElse(new Basket(userPrincipal.getName(), item))
        );
    }

    public void deleteBasket(Principal userPrincipal) {

        basketRepository.getBasketByOwnerName(userPrincipal.getName()).ifPresent(basketRepository::delete);
    }

    public Basket deleteBasketItem(Principal userPrincipal, Long itemId) {

        Basket basket = getBasketForUser(userPrincipal);

        basket.getItems().removeIf(basketItem -> basketItem.getItemId().equals(itemId));

        if (basket.getItems().isEmpty()) {
            basketRepository.delete(basket);
            throw new EmptyBasketException("Basket is empty");
        }

        return basketRepository.save(basket);
    }

    private Basket rebuildBasket(Basket basket, BasketItem basketItem) {

        basket.getItems().removeIf(item -> basketItem.getItemId().equals(item.getItemId()));

        basket.getItems().add(basketItem);

        return basket;
    }
}

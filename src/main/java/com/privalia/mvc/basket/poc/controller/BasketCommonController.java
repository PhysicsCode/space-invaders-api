package com.privalia.mvc.basket.poc.controller;

import com.privalia.mvc.basket.poc.domain.BasketItem;
import com.privalia.mvc.basket.poc.exception.EmptyBasketException;
import com.privalia.mvc.basket.poc.service.BasketCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
@PreAuthorize("hasRole('USER')")
public class BasketCommonController {

    private final BasketCommonService basketCommonService;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBasketForUser(Principal userPrincipal) {

        return ResponseEntity.ok().body(basketCommonService.getBasketForUser(userPrincipal));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity postItemsToNewBasket(Principal userPrincipal,
                                                     @RequestBody BasketItem item) {

        return ResponseEntity.ok().body(basketCommonService.postItemToBasket(userPrincipal, item));
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteBasket(Principal userPrincipal) {

        basketCommonService.deleteBasket(userPrincipal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{itemId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteItemInBasket(Principal userPrincipal,
                                                   @PathVariable(value = "itemId") Long itemId) {

        return ResponseEntity.ok().body(basketCommonService.deleteBasketItem(userPrincipal, itemId));
    }


    @ExceptionHandler(EmptyBasketException.class)
    public ResponseEntity handleException(EmptyBasketException e) {

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

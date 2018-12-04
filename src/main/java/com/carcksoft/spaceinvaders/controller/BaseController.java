package com.carcksoft.spaceinvaders.controller;

import com.carcksoft.spaceinvaders.dto.PlayerInfoOutput;
import com.carcksoft.spaceinvaders.dto.SpaceInvadersInputDTO;
import com.carcksoft.spaceinvaders.service.SpaceIntelligenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class BaseController {

    private final SpaceIntelligenceService spaceIntelligenceService;

    @PostMapping(path = "/name",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity retrievePlayerInfo() {

        return ResponseEntity.ok().body(PlayerInfoOutput.defaultPojo());
    }

    @PostMapping(path = "/move",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity calculatePlayerAction(@RequestBody SpaceInvadersInputDTO input) {

        return ResponseEntity.ok().body(spaceIntelligenceService.calculateNextStep(input));
    }
}

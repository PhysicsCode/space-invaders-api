package com.carcksoft.spaceinvaders.controller;

import com.carcksoft.spaceinvaders.dto.OutputInstructionDTO;
import com.carcksoft.spaceinvaders.service.HackBean;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hack")
@RequiredArgsConstructor
public class HackBeanController {

    private final HackBean hackBean;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/activator")
    public String isActive() {

        return String.valueOf(hackBean.isActive());
    }

    @PostMapping("/activator")
    public void setActive(@RequestBody String active) {

        hackBean.setActive(Boolean.valueOf(active));
        simpMessagingTemplate.convertAndSend("/topic/activator", active);
    }

    @GetMapping("/instruction")
    public String getInstruction() {

        return hackBean.getInstruction().getMove();
    }

    @PostMapping("/instruction")
    public String setInstruction(@RequestBody String instruction) {

        hackBean.setInstruction(OutputInstructionDTO.get(instruction));
        simpMessagingTemplate.convertAndSend("/topic/instruction", getInstruction());
        return getInstruction();
    }

    @GetMapping("/map")
    public String getMap() {

        return hackBean.getAsciiBoard();
    }

    @PostMapping("/map")
    public void setMap(@RequestBody String map) {

        hackBean.setAsciiBoard(map);
        simpMessagingTemplate.convertAndSend("/topic/map", map);
    }

    @GetMapping("/fire")
    public String getFire() {

        return hackBean.getCanFire();
    }
}

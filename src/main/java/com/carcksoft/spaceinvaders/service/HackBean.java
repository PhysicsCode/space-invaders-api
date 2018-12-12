package com.carcksoft.spaceinvaders.service;

import com.carcksoft.spaceinvaders.dto.OutputInstructionDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class HackBean {

    private boolean active = false;
    private OutputInstructionDTO instruction = OutputInstructionDTO.UP;
    private String asciiBoard = "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■<br>" +
                                "■□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□·X······□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□········□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□········□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□···P··O·□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■■■■■■■■■□■·······□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□■·····O·□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■□□□□□□□□□■·······□□□□□□□□□□□□□□□□□□□□□□□□□□□■<br>" +
                                "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■<br>";
    private String canFire = "false";

}

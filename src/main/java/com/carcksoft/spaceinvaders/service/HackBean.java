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

    public OutputInstructionDTO getInstruction() {

        OutputInstructionDTO returnable = this.instruction;

        if (returnable.equals(OutputInstructionDTO.F_UP) ||
                returnable.equals(OutputInstructionDTO.F_DOWN) ||
                returnable.equals(OutputInstructionDTO.F_LEFT) ||
                returnable.equals(OutputInstructionDTO.F_RIGHT)) {

            resetInstruction();
        }
        return returnable;
    }

    private void resetInstruction() {
        this.instruction = OutputInstructionDTO.NONE;

    }
}

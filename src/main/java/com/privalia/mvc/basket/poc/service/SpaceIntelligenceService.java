package com.privalia.mvc.basket.poc.service;

import com.privalia.mvc.basket.poc.dto.OutputInstructionDTO;
import com.privalia.mvc.basket.poc.dto.SpaceInvadersInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceIntelligenceService {

    public OutputInstructionDTO calculateNextStep(SpaceInvadersInputDTO input) {

        return OutputInstructionDTO.DOWN;
    }
}

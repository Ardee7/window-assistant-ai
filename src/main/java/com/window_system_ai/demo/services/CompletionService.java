package com.window_system_ai.demo.services;

import com.window_system_ai.demo.dtos.CompletionRequestDTO;
import com.window_system_ai.demo.dtos.CompletionResponseDTO;

public interface CompletionService {

    public CompletionResponseDTO promptQuery(CompletionRequestDTO requestDTO);
}

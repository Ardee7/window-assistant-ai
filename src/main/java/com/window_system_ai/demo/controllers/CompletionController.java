package com.window_system_ai.demo.controllers;

import com.window_system_ai.demo.dtos.CompletionRequestDTO;
import com.window_system_ai.demo.dtos.GenericResponseDTO;
import com.window_system_ai.demo.services.CompletionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Slf4j
public class CompletionController {

    private final CompletionService completionService;

    public CompletionController(CompletionService completionService) {
        this.completionService = completionService;
    }

    @PostMapping("/prompt")
    private ResponseEntity<GenericResponseDTO> promptQuery(@RequestBody CompletionRequestDTO request){
        return new ResponseEntity<>(
                GenericResponseDTO.build(this.completionService.promptQuery(request)),
                HttpStatus.OK);
    }
}

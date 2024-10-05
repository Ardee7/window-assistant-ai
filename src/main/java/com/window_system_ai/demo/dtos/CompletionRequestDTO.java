package com.window_system_ai.demo.dtos;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletionRequestDTO {

    @NotNull
    @Schema(implementation = MessageDTO.class)
    private List<MessageDTO> messages;

    @NotNull
    @Schema(implementation = MessageDTO.class, example = "gpt-3.5-turbo-0125")
    private String model;

}

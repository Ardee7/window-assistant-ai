package com.window_system_ai.demo.dtos;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {

    @NotNull
    @Schema(implementation = String.class, example = "system")
    private String role;

    @NotNull
    @Schema(implementation = String.class, example = "Hello!")
    private String content;

}

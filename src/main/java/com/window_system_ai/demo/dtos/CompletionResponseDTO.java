package com.window_system_ai.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletionResponseDTO {

    private String id;
    private String object;
    private long created;
    private String model;
    private String systemFingerprint;
    private List<ChoicesDTO> choices;
    private UsageDTO usage;

}

package com.window_system_ai.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChoicesDTO {
    private int index;
    private MessageDTO message;
    private String logprobs;
    private String finishReason;
}

package com.window_system_ai.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsageDTO {
    private int promptTokens;
    private int completionTokens;
    private int totalTokens;
    private CompletionTokensDetailsDTO completionTokensDetails;
}

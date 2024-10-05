package com.window_system_ai.demo.services.impl;

import com.window_system_ai.demo.configurations.ChatGPTConfig;
import com.window_system_ai.demo.dtos.CompletionRequestDTO;
import com.window_system_ai.demo.dtos.CompletionResponseDTO;
import com.window_system_ai.demo.dtos.MessageDTO;
import com.window_system_ai.demo.exceptions.CompletionException;
import com.window_system_ai.demo.factories.ChatGPTRequestFactory;
import com.window_system_ai.demo.services.CompletionService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CompletionServiceImpl implements CompletionService {

    private final ChatGPTRequestFactory chatGPTRequestFactory;

    private final ChatGPTConfig chatGPTConfig;

    public CompletionServiceImpl(ChatGPTRequestFactory chatGPTRequestFactory, ChatGPTConfig chatGPTConfig) {
        this.chatGPTRequestFactory = chatGPTRequestFactory;
        this.chatGPTConfig = chatGPTConfig;
    }

    @Override
    public CompletionResponseDTO promptQuery(CompletionRequestDTO requestDTO) {

        requestDTO.getMessages().add(new MessageDTO("system", "You are a helpful assistant."));
        return this.chatGPTRequestFactory.postRequest(chatGPTConfig.getCompletionPath(), requestDTO).retrieve()
                .bodyToMono(new ParameterizedTypeReference<CompletionResponseDTO>() {
                })
                .doOnError(res -> Mono.error(new CompletionException(String.format("Something went wrong when trying this endpoint %s", chatGPTConfig.getCompletionPath()))))
                .block();
    }
}

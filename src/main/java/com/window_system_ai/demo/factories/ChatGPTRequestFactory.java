package com.window_system_ai.demo.factories;

import com.window_system_ai.demo.configurations.ChatGPTConfig;
import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ChatGPTRequestFactory {

    private WebClient webClient;

    private ChatGPTConfig chatGPTConfig;

    @Autowired
    public ChatGPTRequestFactory(ChatGPTConfig chatGPTConfig) throws SSLException {
        this.chatGPTConfig = chatGPTConfig;

        SslContext sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        HttpClient httpClient = HttpClient.create().secure(options -> options.sslContext(sslContext))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000)
                .compress(true)
                .doOnConnected(ctx ->
                        ctx.addHandlerLast(
                                new ReadTimeoutHandler(20000, TimeUnit.MILLISECONDS)
                        ));
        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        this.webClient = WebClient.builder()
                .clientConnector(connector)
                .baseUrl(this.chatGPTConfig.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONNECTION, "Keep-Alive")
                .defaultHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", this.chatGPTConfig.getApiKey()))
                .defaultHeader("Keep-Alive", "timeout=20000")
                .filter(this.logRequest())
                .filter(this.logResponse())
                .build();
    }

    public final WebClient.RequestHeadersSpec<?> getRequest(
            final String path) {
        log.info("GET HTTP Method to be called.");
        return webClient.get().uri(path);
    }

    public final WebClient.RequestHeadersSpec<?> postRequest(
            final String path, final Object requestBody) {

        log.info("API URL to be called: {}", path);
        return webClient.post().uri(path)
                .body(BodyInserters.fromValue(requestBody))
                .accept(MediaType.APPLICATION_JSON);
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {} \nHeaders: {} \nCookies: {} \nBody: {}", clientRequest.method(), clientRequest.url(), clientRequest.headers(), clientRequest.cookies(), clientRequest.body().toString());
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("Response Status: {} {}", clientResponse.statusCode(), clientResponse.bodyToMono(Object.class));
            return Mono.just(clientResponse);
        });
    }

}

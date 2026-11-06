package com.tecnologico.toolcalling.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("Eres un asistente académico claro y amable. " +
                        "Cuando una pregunta requiera una operación matemática o conocer la fecha y hora, " +
                        "utiliza las herramientas disponibles en lugar de inventar el resultado. " +
                        "Explica brevemente qué herramienta utilizaste.")
                .build();
    }
}

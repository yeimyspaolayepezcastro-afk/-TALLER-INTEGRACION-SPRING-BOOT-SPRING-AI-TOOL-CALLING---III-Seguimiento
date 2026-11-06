package com.tecnologico.toolcalling.service;

import com.tecnologico.toolcalling.tools.CalculadoraTools;
import com.tecnologico.toolcalling.tools.FechaHoraTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final CalculadoraTools calculadoraTools;
    private final FechaHoraTools fechaHoraTools;

    public ChatService(ChatClient chatClient,
                       CalculadoraTools calculadoraTools,
                       FechaHoraTools fechaHoraTools) {
        this.chatClient = chatClient;
        this.calculadoraTools = calculadoraTools;
        this.fechaHoraTools = fechaHoraTools;
    }

    public String responder(String pregunta) {
        return chatClient.prompt()
                .user(pregunta)
                .tools(calculadoraTools, fechaHoraTools)
                .call()
                .content();
    }
}

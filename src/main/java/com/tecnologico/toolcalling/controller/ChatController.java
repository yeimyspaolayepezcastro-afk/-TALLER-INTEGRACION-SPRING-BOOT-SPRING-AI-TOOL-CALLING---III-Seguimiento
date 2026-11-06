package com.tecnologico.toolcalling.controller;

import com.tecnologico.toolcalling.dto.ChatRequest;
import com.tecnologico.toolcalling.dto.ChatResponse;
import com.tecnologico.toolcalling.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<ChatResponse> conversar(@Valid @RequestBody ChatRequest request) {
        String respuesta = chatService.responder(request.pregunta());
        return ResponseEntity.ok(new ChatResponse(respuesta));
    }
}

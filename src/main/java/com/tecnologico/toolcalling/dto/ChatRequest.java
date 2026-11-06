package com.tecnologico.toolcalling.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(@NotBlank(message = "Escribe una pregunta.") String pregunta) {
}

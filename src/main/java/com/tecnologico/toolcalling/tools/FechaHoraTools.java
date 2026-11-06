package com.tecnologico.toolcalling.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class FechaHoraTools {

    @Tool(description = "Consulta la fecha y hora actual del servidor. Úsala cuando el usuario pregunte qué fecha u hora es hoy.")
    public String consultarFechaHoraActual() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}

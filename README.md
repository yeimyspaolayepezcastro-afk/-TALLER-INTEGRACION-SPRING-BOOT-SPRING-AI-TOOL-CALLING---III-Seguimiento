# Taller: Spring Boot + Spring AI + Tool Calling

Proyecto base en Java 21 con Spring Boot, Spring AI, Groq y una interfaz web sencilla.

## ¿Qué incluye?

- API REST `POST /api/chat`.
- `ChatClient` configurado como bean.
- Tool Calling declarativo con `@Tool`.
- `CalculadoraTools`: suma, multiplicación y división.
- `FechaHoraTools`: consulta la fecha y hora del servidor.
- Frontend HTML, CSS y JavaScript servido desde Spring Boot.
- Configuración de Groq mediante variable de entorno para no guardar la API Key en el código.

## Requisitos

- JDK 21.
- Maven 3.9 o superior (o usar el Maven Wrapper si se agrega al proyecto).
- Una API Key de Groq.
- Acceso a Internet para descargar dependencias y consultar el modelo.

## Configuración de la API Key

No escribas la clave directamente en `application.properties` ni la subas a Git.

En Windows PowerShell, abre la terminal en la carpeta del proyecto y ejecuta:

```powershell
$env:GROQ_API_KEY="TU_API_KEY_DE_GROQ"
```

En CMD:

```cmd
set GROQ_API_KEY=TU_API_KEY_DE_GROQ
```

En Linux/macOS:

```bash
export GROQ_API_KEY="TU_API_KEY_DE_GROQ"
```

La configuración actual usa el endpoint compatible con OpenAI de Groq y el modelo `openai/gpt-oss-20b`. Si tu cuenta de Groq no tiene habilitado ese modelo, cambia `spring.ai.openai.chat.options.model` por un modelo disponible en tu consola de Groq que admita tool calling.

## Ejecutar

Desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

Abre en el navegador:

```text
http://localhost:8080
```

## Probar el endpoint REST

```bash
curl -X POST http://localhost:8080/api/chat \
  -H "Content-Type: application/json" \
  -d '{"pregunta":"¿Cuánto es 125 dividido entre 5?"}'
```

En PowerShell también puedes probarlo desde la interfaz web o usar `Invoke-RestMethod`.

## Preguntas de prueba para Tool Calling

1. `Suma 45 y 78.`
2. `Multiplica 12 por 9.`
3. `Divide 144 entre 12.`
4. `¿Qué fecha y hora es en este momento?`
5. `¿Cuánto es 25 por 4 y luego súmale 10?`

El modelo decide cuándo necesita una herramienta. Spring AI ejecuta el método anotado con `@Tool`, devuelve el resultado al modelo y permite que este redacte la respuesta final.

## Estructura

```text
spring-ai-toolcalling/
├── pom.xml
├── README.md
└── src/
    └── main/
        ├── java/com/tecnologico/toolcalling/
        │   ├── ToolCallingApplication.java
        │   ├── config/AiConfig.java
        │   ├── controller/ChatController.java
        │   ├── dto/ChatRequest.java
        │   ├── dto/ChatResponse.java
        │   ├── service/ChatService.java
        │   └── tools/
        │       ├── CalculadoraTools.java
        │       └── FechaHoraTools.java
        └── resources/
            ├── application.properties
            └── static/
                ├── index.html
                ├── styles.css
                └── app.js
```

## Nota de seguridad

Las herramientas de este taller son de demostración y no realizan operaciones destructivas. Antes de conectar herramientas a bases de datos, pagos, archivos o servicios externos, valida permisos y entradas en el backend. No confíes en que el modelo aplique controles de seguridad.

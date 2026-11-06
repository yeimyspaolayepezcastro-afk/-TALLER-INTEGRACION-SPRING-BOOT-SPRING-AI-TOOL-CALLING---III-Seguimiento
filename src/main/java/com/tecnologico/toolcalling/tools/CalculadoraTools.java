package com.tecnologico.toolcalling.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraTools {

    @Tool(description = "Suma dos números y devuelve el resultado exacto.")
    public String sumar(double numero1, double numero2) {
        return BigDecimal.valueOf(numero1).add(BigDecimal.valueOf(numero2)).stripTrailingZeros().toPlainString();
    }

    @Tool(description = "Multiplica dos números y devuelve el resultado.")
    public String multiplicar(double numero1, double numero2) {
        return BigDecimal.valueOf(numero1).multiply(BigDecimal.valueOf(numero2)).stripTrailingZeros().toPlainString();
    }

    @Tool(description = "Divide el primer número entre el segundo. No permite dividir entre cero.")
    public String dividir(double dividendo, double divisor) {
        if (divisor == 0) {
            return "Error: no es posible dividir entre cero.";
        }
        return BigDecimal.valueOf(dividendo)
                .divide(BigDecimal.valueOf(divisor), 6, RoundingMode.HALF_UP)
                .stripTrailingZeros().toPlainString();
    }
}

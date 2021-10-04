package br.com.contadigital.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class DebitoCreditoRequest {

    @NotNull
    private Long conta;

    @PositiveOrZero
    private BigDecimal valorCredito;

    public DebitoCreditoRequest(Long conta, BigDecimal valorCredito) {
        this.conta = conta;
        this.valorCredito = valorCredito;
    }

    public Long getConta() {
        return conta;
    }

    public BigDecimal getValorCredito() {
        return valorCredito;
    }
}

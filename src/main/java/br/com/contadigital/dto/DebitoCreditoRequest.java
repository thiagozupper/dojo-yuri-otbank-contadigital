package br.com.contadigital.dto;

import br.com.contadigital.models.ContaDigital;
import br.com.contadigital.util.validator.ExistsId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class DebitoCreditoRequest {

    @NotNull
    @ExistsId(domainClass = ContaDigital.class, fieldName = "conta")
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

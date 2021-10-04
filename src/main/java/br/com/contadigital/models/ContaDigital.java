package br.com.contadigital.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
public class ContaDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long conta;

    @PositiveOrZero
    private BigDecimal saldo;

    @NotNull
    private Long idCliente;

    @Deprecated
    public ContaDigital() {
    }

    public ContaDigital(Long conta, Long idCliente) {
        this.conta = conta;
        this.saldo = BigDecimal.ZERO;
        this.idCliente = idCliente;
    }



}

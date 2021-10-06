package br.com.contadigital.models;

import org.springframework.http.ResponseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.math.MathContext;

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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void creditarvalor(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    public boolean debitarvalor(BigDecimal valor) {

        if (valor.compareTo(this.saldo) <= 0) {
            this.saldo = this.saldo.subtract(valor);
            return true;
        }
        return false;
    }


}

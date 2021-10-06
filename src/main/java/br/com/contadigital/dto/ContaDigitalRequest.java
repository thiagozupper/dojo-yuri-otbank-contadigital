package br.com.contadigital.dto;

import br.com.contadigital.models.ContaDigital;
import br.com.contadigital.util.validator.ExistsId;

import javax.validation.constraints.NotNull;

public class ContaDigitalRequest {


    @NotNull
    @ExistsId(domainClass = ContaDigital.class, fieldName = "conta")
    private Long conta;

    @NotNull
    private Long idCliente;

    public ContaDigitalRequest(Long conta, Long idCliente) {
        this.conta = conta;
        this.idCliente = idCliente;
    }

    public ContaDigital toModel() {
        return new ContaDigital(conta, idCliente);
    }

    public Long getConta() {
        return conta;
    }

    public Long getIdCliente() {
        return idCliente;
    }
}

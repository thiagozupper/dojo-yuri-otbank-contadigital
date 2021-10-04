package br.com.contadigital.dto;

import br.com.contadigital.models.ContaDigital;

import javax.validation.constraints.NotNull;

public class ContaDigitalRequest {

    @NotNull
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
}

package br.com.contadigital.controllers;

import br.com.contadigital.dto.ContaDigitalRequest;
import br.com.contadigital.models.ContaDigital;
import br.com.contadigital.repositories.ContaDigitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/contas")
public class ContaDigitalController {

    private ContaDigitalRepository contaDigitalRepository;

    public ContaDigitalController(ContaDigitalRepository contaDigitalRepository) {
        this.contaDigitalRepository = contaDigitalRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarConta(@RequestBody @Valid ContaDigitalRequest request){
        ContaDigital contaDigital = request.toModel();
        contaDigitalRepository.save(contaDigital);
        return ResponseEntity.ok().build();
    }


}

package br.com.contadigital.controllers;

import br.com.contadigital.dto.ContaDigitalRequest;
import br.com.contadigital.dto.DebitoCreditoRequest;
import br.com.contadigital.models.ContaDigital;
import br.com.contadigital.repositories.ContaDigitalRepository;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/contas")
public class ContaDigitalController {

    private ContaDigitalRepository contaDigitalRepository;

    public ContaDigitalController(ContaDigitalRepository contaDigitalRepository) {
        this.contaDigitalRepository = contaDigitalRepository;
    }

    @PostMapping("/novo")
    public ResponseEntity<?> cadastrarConta(@RequestBody @Valid ContaDigitalRequest request) {
        ContaDigital contaDigital = request.toModel();
        contaDigitalRepository.save(contaDigital);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping("/credito")
    public ResponseEntity<?> creditarValor(@RequestBody @Valid DebitoCreditoRequest request) {
        Optional<ContaDigital> supostaContaDigital = contaDigitalRepository.findByConta(request.getConta());

        ContaDigital contaDigital = supostaContaDigital.get();
        contaDigital.creditarvalor(request.getValorCredito());
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PutMapping("/debito")
    public ResponseEntity<?> debitarValor(@RequestBody @Valid DebitoCreditoRequest request) {
        Optional<ContaDigital> supostaContaDigital = contaDigitalRepository.findByConta(request.getConta());


        ContaDigital contaDigital = supostaContaDigital.get();

        boolean debitaValor = contaDigital.debitarvalor(request.getValorCredito());
        if (debitaValor) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }


}

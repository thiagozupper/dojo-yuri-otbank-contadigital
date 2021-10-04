package br.com.contadigital.repositories;

import br.com.contadigital.models.ContaDigital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaDigitalRepository extends JpaRepository<ContaDigital, Long> {


    Optional<ContaDigital> findByConta(Long conta);
}

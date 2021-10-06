package br.com.contadigital.util.validator;

import br.com.contadigital.models.ContaDigital;
import br.com.contadigital.repositories.ContaDigitalRepository;
import br.com.contadigital.util.handler.ErrorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {


    private String domainAttrribute;
    private Class<?> klass;

    @Autowired
    private ContaDigitalRepository contaDigitalRepository;

    @Override //Função que inicializa o Validator
    public void initialize(ExistsId params) {

        domainAttrribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override //Método que verifica se é válido
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<ContaDigital> possivelConta = contaDigitalRepository.findByConta(value);
        return possivelConta.isPresent();
    }
}
package br.com.contadigital.util.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {


    private String domainAttrribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override //Função que inicializa o Validator
    public void initialize(ExistsId params) {

        domainAttrribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override //Método que verifica se é válido
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = manager.createQuery("select 1 from " + klass.getName() + "where " + domainAttrribute + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <= 1, "Foi encontrado mais de um " + klass + "com o atributo igual!");
        return list.isEmpty();
    }
}
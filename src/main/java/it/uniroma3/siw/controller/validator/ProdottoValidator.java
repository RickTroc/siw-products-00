package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.repository.ProdottoRepository;

@Component
public class ProdottoValidator implements Validator{
    
    @Autowired
    private ProdottoRepository prodottoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Prodotto.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Prodotto prodotto = (Prodotto)o;

        if(prodotto.getNome()!= null && prodotto.getDescrizione()!= null &&  
        prodotto.getPrezzo()!=null && this.prodottoRepository.existsByNomeAndDescrizione(prodotto.getNome(), prodotto.getDescrizione()))
        errors.reject("prodotto.duplicate");
        
    }


}
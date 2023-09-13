package it.uniroma3.siw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.repository.FornitoreRepository;
import it.uniroma3.siw.repository.ProdottoRepository;
import jakarta.validation.Valid;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Transactional
    public void createNewProdotto(Prodotto prodotto){
        this.prodottoRepository.save(prodotto);
    }

    @Transactional
    public Prodotto findProdottobyId(Long id){
        return this.prodottoRepository.findById(id).get();
    }

    @Transactional
    public void saveProdotto(@Valid Prodotto prodotto) throws IOException{
        this.prodottoRepository.save(prodotto);
    }

    
    @Transactional
    public void deleteProdotto(Long id){
        Prodotto prodotto = this.findProdottobyId(id);
        List<Fornitore> fornitori = prodotto.getFornitori();

        for(Fornitore f : fornitori)
            f.getProdotti().remove(prodotto);

        this.prodottoRepository.delete(prodotto);
    }

    @Transactional
    public Prodotto updateProdotto(Prodotto oldP, Prodotto newP){
        oldP.setNome(newP.getNome());
        oldP.setDescrizione(newP.getDescrizione());
        oldP.setPrezzo(newP.getPrezzo());
        this.prodottoRepository.save(oldP);
        return oldP;

    }

    



    @Transactional
    public List<Fornitore> fornitoriToAdd(Long prodId){
        List<Fornitore> fornToAdd = new ArrayList<>();

        for(Fornitore f : this.fornitoreRepository.findFornitoreNotInProdotto(prodId)){
            fornToAdd.add(f);
        }

        return fornToAdd;
    }
    
}

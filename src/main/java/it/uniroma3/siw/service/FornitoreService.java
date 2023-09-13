package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.repository.FornitoreRepository;
import it.uniroma3.siw.repository.ProdottoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import it.uniroma3.siw.model.Prodotto;

@Service
public class FornitoreService {
    
    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Transactional
    public void createNewFornitore(Fornitore fornitore){
        this.fornitoreRepository.save(fornitore);
    }

    @Transactional
    public Fornitore findFornitoreById(Long id){
        return this.fornitoreRepository.findById(id).get();
    }

    @Transactional
    public List<Prodotto> prodottiToAdd(Long fornId){
        List<Prodotto> prodToAdd = new ArrayList<>();

        for(Prodotto p : this.prodottoRepository.findProdottoNotInFornitore(fornId)){
            prodToAdd.add(p);
        }

        return prodToAdd;
    }

    @Transactional
    public void saveFornitore(@Valid Fornitore fornitore) throws IOException{
        this.fornitoreRepository.save(fornitore);
    }

    
    @Transactional
	public void deleteFornitore(Long id) {
		Fornitore fornitore = this.findFornitoreById(id);
		List<Prodotto> prodotti = fornitore.getProdotti();
		for(Prodotto p : prodotti) {
			p.getFornitori().remove(fornitore);
		}
		
		this.fornitoreRepository.delete(fornitore);
	}

    @Transactional
    public void updateFornitore(Long id, Fornitore updatedFornitore) {
        // Verifica se il fornitore esiste nel database
        Fornitore existingFornitore = fornitoreRepository.findById(id).get();

        // Aggiorna i dati del fornitore con quelli provenienti dalla form
        existingFornitore.setNome(updatedFornitore.getNome());
        existingFornitore.setIndirizzo(updatedFornitore.getIndirizzo());

        // Salva il fornitore aggiornato nel database
        fornitoreRepository.save(existingFornitore);
    }
    

}

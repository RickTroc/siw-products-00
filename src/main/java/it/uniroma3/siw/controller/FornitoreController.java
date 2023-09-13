package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.uniroma3.siw.controller.validator.FornitoreValidator;
import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.repository.FornitoreRepository;
import it.uniroma3.siw.repository.ProdottoRepository;
import it.uniroma3.siw.service.FornitoreService;
import it.uniroma3.siw.service.ProdottoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FornitoreController {
    
    @Autowired
    private FornitoreService fornitoreService;

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private FornitoreValidator fornitoreValidator;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @GetMapping(value = "/fornitore/{id}")
    public String getFornitore(@PathVariable("id") Long id, Model model){
        model.addAttribute("fornitore", this.fornitoreService.findFornitoreById(id));
        return "fornitore.html";
    }

    @GetMapping(value = "/fornitori")
    public String getFornitori(Model model){
        model.addAttribute("fornitori", this.fornitoreRepository.findAll());
        return "fornitori.html";
    }

    @GetMapping(value = "/admin/formNewFornitore")
    public String formNewFornitore(Model model){
        model.addAttribute("fornitore", new Fornitore());
        return "admin/formNewFornitore";
    }


    @PostMapping("/admin/fornitore")
    public String newFornitore( @Valid @ModelAttribute("fornitore") Fornitore fornitore, 
                                Model model,BindingResult bindingResult) throws IOException{

        this.fornitoreValidator.validate(fornitore, bindingResult);
        if(!bindingResult.hasErrors()){
            this.fornitoreService.saveFornitore(fornitore);
            model.addAttribute("fornitore", fornitore);
            return "fornitore.html";
        }
        else{
            model.addAttribute("messaggioErrore", "Questo fornitore esiste gia.");
            return "admin/formNewFornitore.html";    
        }
        

    }

    @Transactional
    @GetMapping(value = "/admin/updateFornitore/{fornId}")
    public String updateFornitore(@PathVariable("fornId") Long fornId, Model model){
        Fornitore fornitore = this.fornitoreService.findFornitoreById(fornId);
        model.addAttribute("fornitore", fornitore);
        return "formUpdateFornitore.html";
    }


    public String updateFornitore(@RequestParam("fornitore") Long fonritoreId, @RequestParam("nome") String nome,
                                  @RequestParam("indirizzo") String indirizzo, @RequestParam("email")String email, Model model){
        Fornitore forn = this.fornitoreService.findFornitoreById(fonritoreId);
        forn.setNome(nome);
        forn.setIndirizzo(indirizzo);
        forn.setEmail(email);
        this.fornitoreRepository.save(forn);

        model.addAttribute("forn", forn);
        return "fornitore.html";
    }


    @GetMapping(value = "/admin/manageFornitore")
    public String manageFornitore(Model model){
        model.addAttribute("fornitori", this.fornitoreRepository.findAll());
        return "admin/manageFornitore";
    }

    @GetMapping(value = "/admin/formUpdateFornitore/{id}")
    public String formUpdateFornitore(@PathVariable("id") Long id, Model model){
        model.addAttribute("fornitore", this.fornitoreService.findFornitoreById(id));
        return "admin/formUpdateFornitore.html";
    }


    //Aggiunge un prodotto al fornitore e mostra la lista dei prodotti disponibili da aggiungere
    @GetMapping(value = "/admin/addProdottoToFornitore/{prodId}/{fornId}")
    public String addProdottoToFornitore(@PathVariable("fornId") Long fornId, @PathVariable("prodId") Long prodId, Model model){
        Fornitore fornitore = this.fornitoreService.findFornitoreById(fornId);
        Prodotto prodotto = this.prodottoService.findProdottobyId(prodId);
        List<Prodotto> prodotti = fornitore.getProdotti();

        prodotti.add(prodotto);
        this.fornitoreRepository.save(fornitore);

        List<Prodotto> prodToAdd = this.fornitoreService.prodottiToAdd(fornId);

        model.addAttribute("fornitore", fornitore);
        model.addAttribute("prodToAdd", prodToAdd);

        return "admin/prodToAdd.html";
    }


    @GetMapping("/admin/removeProdottoFromFornitore/{prodId}/{fornId}")
    public String removeProdottoFromFornitore(@PathVariable("prodId") Long prodId, @PathVariable("fornId") Long fornId, Model model){
        Prodotto prodotto = this.prodottoService.findProdottobyId(prodId);
        Fornitore fornitore = this.fornitoreService.findFornitoreById(fornId);
        List<Prodotto> prodotti = fornitore.getProdotti();
        prodotti.remove(prodotto);
        this.fornitoreRepository.save(fornitore);

        List<Prodotto> prodToAdd = this.fornitoreService.prodottiToAdd(fornId);

        model.addAttribute("fornitore", fornitore);
        model.addAttribute("prodToAdd", prodToAdd);
        
        return "admin/prodToAdd.html";
    }


    @GetMapping("/admin/updateProdotti/{id}")
	public String updateProdotti(@PathVariable("id") Long id, Model model) {

		List<Prodotto> prodToAdd = this.fornitoreService.prodottiToAdd(id);
		model.addAttribute("prodToAdd", prodToAdd);
		model.addAttribute("fornitore", this.fornitoreService.findFornitoreById(id));

		return "admin/prodToAdd.html";
	}


    @GetMapping(value="/admin/deleteFornitore")
    public String deleteFornitore(Model model) {
        model.addAttribute("fornitori", this.fornitoreRepository.findAll());
        return "admin/deleteFornitore.html";
    }
    

    @GetMapping(value = "/admin/deleteFornitore/{id}")
    public String deleteFornitore(@PathVariable("id") Long id, Model model){
        this.fornitoreService.deleteFornitore(id);
        model.addAttribute("fornitori", this.fornitoreRepository.findAll());
        return "/admin/deleteFornitore.html";
    }
}

package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.controller.validator.ProdottoValidator;
import it.uniroma3.siw.model.Fornitore;
import it.uniroma3.siw.model.Prodotto;
import it.uniroma3.siw.repository.ProdottoRepository;
import it.uniroma3.siw.service.FornitoreService;
import it.uniroma3.siw.service.ProdottoService;
import jakarta.validation.Valid;

@Controller
public class ProdottoController {
    
  @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    private FornitoreService fornitoreService;

    @Autowired
    private ProdottoValidator prodottoValidator;



     @GetMapping(value = "/prodotto/{id}")
    public String getProdotto(@PathVariable("id") Long id, Model model){
        model.addAttribute("prodotto", this.prodottoService.findProdottobyId(id));
        return "prodotto.html";
    }

    @GetMapping(value = "/prodotti")
    public String getProdotti(Model model){
        model.addAttribute("prodotti", this.prodottoRepository.findAll());
        return "prodotti.html";
    }

    @GetMapping(value ="/admin/formNewProdotto")
    public String formNewFornitore(Model model){
        model.addAttribute("prodotto", new Prodotto());
        return "admin/formNewProdotto";
    }

    @PostMapping("/admin/prodotto")
    public String newProdotto(  @Valid @ModelAttribute("prodotto") Prodotto prodotto,
                                Model model, BindingResult bindingResult) throws IOException{

        this.prodottoValidator.validate(prodotto, bindingResult);
        if(!bindingResult.hasErrors()){
            this.prodottoService.saveProdotto(prodotto);
            model.addAttribute("prodotto", prodotto);
            return "prodotto.html";
        }
        else{
            model.addAttribute("messaggioErrore", "Questo prodotto esiste gia");
            return "admin/formNewProdotto.html";
        }
    }

    @GetMapping(value = "/admin/manageProdotto")
    public String manageProdotto(Model model){
        model.addAttribute("prodotti", this.prodottoRepository.findAll());
        return "admin/manageProdotto";
    }


    @GetMapping(value = "/admin/formUpdateProdotto/{id}")
    public String formUpdateProdotto(@PathVariable("id") Long id, Model model){
        Prodotto prodotto = this.prodottoService.findProdottobyId(id);
        this.prodottoService.updateProdotto(prodotto, new Prodotto());

        model. addAttribute("prodotto", this.prodottoService.findProdottobyId(id));
        return "admin/formUpdateProdotto.html";
    }





    @GetMapping(value = "/admin/addFornitoreToProdotto/{prodId}/{fornId}")
    public String addFornitoreToProdotto(@PathVariable("fornId") Long fornId, @PathVariable("prodId") Long prodId, Model model){
        Prodotto prodotto = this.prodottoService.findProdottobyId(prodId);
        Fornitore fornitore = this.fornitoreService.findFornitoreById(fornId);
        List<Fornitore> fornitori = prodotto.getFornitori();

        fornitori.add(fornitore);
        this.prodottoRepository.save(prodotto);

        List<Fornitore> fornToAdd = this.prodottoService.fornitoriToAdd(prodId);

        model.addAttribute("prodotto", prodotto);
        model.addAttribute("fornToAdd", fornToAdd);

        return "admin/fornToAdd.html";
    }


    @GetMapping(value = "/admin/removeFornitoreFromProdotto/{prodId}/{fornId}")
    public String removeFornitoreFromProdotto(@PathVariable("fornId") Long fornId, @PathVariable("prodId") Long prodId, Model model){
        Prodotto prodotto = this.prodottoService.findProdottobyId(prodId);
        Fornitore fornitore = this.fornitoreService.findFornitoreById(fornId);
        List<Fornitore> fornitori = prodotto.getFornitori();
        fornitori.remove(fornitore);
        this.prodottoRepository.save(prodotto);

        List<Fornitore> fornToAdd = this.prodottoService.fornitoriToAdd(prodId);

        model.addAttribute("prodotto", prodotto);
        model.addAttribute("fornToAdd", fornToAdd);

        return "admin/fornToAdd.html";
    }
    
    @GetMapping(value = "/admin/updateFornitori/{id}")
    public String updateFornitori(@PathVariable("id") Long id, Model model){

        List<Fornitore> fornToAdd = this.prodottoService.fornitoriToAdd(id);
        model.addAttribute("fornToAdd", fornToAdd);
        model.addAttribute("prodotto", this.prodottoService.findProdottobyId(id));

        return "/admin/fornToAdd.html";
    }

    @GetMapping(value = "/admin/deleteProdotto")
    public String deleteProdotto(Model model){
        model.addAttribute("prodotti", this.prodottoRepository.findAll());
        return "/admin/deleteProdotto.html";
    }

    @GetMapping(value = "/admin/deleteProdotto/{id}")
    public String deleteProdotto(@PathVariable("id") Long id,Model model){
        this.prodottoService.deleteProdotto(id);
        model.addAttribute("prodotti", this.prodottoRepository.findAll());

        return "/admin/deleteProdotto.html";

    }



    @Transactional
    @GetMapping(value = "/admin/updateProdotto/{prodId}")
    public String updateProdotto(@PathVariable("prodId") Long id, Model model){

        Prodotto prodotto = this.prodottoService.findProdottobyId(id);
        model.addAttribute("prodotto", prodotto);
        return "formUpdateProdotto.html";
    }


    @Transactional
    @PostMapping(value = "admin/updateProdotto")
    public String updateProdotto(@RequestParam("prodotto") Long prodottoId, @RequestParam("nome")String nome, 
                                @RequestParam("prezzo")String prezzo, @RequestParam("descrizione")String descrizione, Model model){

        Prodotto prod = this.prodottoService.findProdottobyId(prodottoId);
        prod.setDescrizione(descrizione);
        prod.setNome(nome);
        prod.setPrezzo(prezzo);
        this.prodottoRepository.save(prod);
        
      
     
        model.addAttribute("prodotto", prod);
        return "prodotto.html";
    }
}

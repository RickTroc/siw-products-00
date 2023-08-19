package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.repository.ProdottoRepository;

@Controller
public class ProdottoController {
    
    @Autowired
    private ProdottoRepository prodottoRepository;


    @GetMapping(value = "/")
    public String index(Model model)
    {
        return "index.html";
    }
}

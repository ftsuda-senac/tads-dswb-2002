/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring.produto;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author fedts
 */
@Controller
@RequestMapping("/produto/nome")
public class ProdutoNomeController {
    
    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ModelAndView buscarPorNome(@RequestParam("nome") String nome) {
        
        List<Produto> resultados = repository.buscarPorNomeSql(nome);
        ModelAndView mv = new ModelAndView("produto/produto-nome");
        mv.addObject("resultados", resultados);
        return mv;
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplorest.controller;

import br.senac.tads.dsw.exemplorest.dominio.InteresseRepository;
import br.senac.tads.dsw.exemplorest.dominio.Pessoa;
import br.senac.tads.dsw.exemplorest.dominio.PessoaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author fedts
 */
@RestController
@RequestMapping("/hateoas/pessoas")
public class PessoaHateoasController {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private InteresseRepository interesseRepository;
    
    @GetMapping
    public PagedModel<EntityModel<Pessoa>> listar(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "qtd", defaultValue = "10") int qtd
    ) {
        Page<Pessoa> pagePessoas = pessoaRepository.findAll(PageRequest.of(pagina, qtd));
        
        List<EntityModel<Pessoa>> modelos = new ArrayList<>();
        for (Pessoa pessoa : pagePessoas.getContent()) {
            EntityModel<Pessoa> emp = EntityModel.of(pessoa);
            emp.add(linkTo(methodOn(PessoaHateoasController.class).findById(pessoa.getId())).withSelfRel());
            modelos.add(emp);
        }
        PagedModel<EntityModel<Pessoa>> pagedModel = PagedModel.of(modelos, 
                new PagedModel.PageMetadata(pagePessoas.getNumberOfElements(), 
                        pagePessoas.getNumber(),
                        pagePessoas.getTotalElements()));
        return pagedModel;
    }
    
    @GetMapping("/{id}")
    public EntityModel<Pessoa> findById(@PathVariable("id") Integer id) {
        Pessoa pessoa = pessoaRepository.findById(id).get();
        
        EntityModel<Pessoa> emp = EntityModel.of(pessoa);
        emp.add(linkTo(methodOn(PessoaHateoasController.class).findById(pessoa.getId())).withSelfRel());
        return emp;
    }
    
}

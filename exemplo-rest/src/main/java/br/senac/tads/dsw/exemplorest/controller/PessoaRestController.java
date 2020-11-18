/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplorest.controller;

import br.senac.tads.dsw.exemplorest.dominio.Interesse;
import br.senac.tads.dsw.exemplorest.dominio.InteresseRepository;
import br.senac.tads.dsw.exemplorest.dominio.Pessoa;
import br.senac.tads.dsw.exemplorest.dominio.PessoaRepository;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author fedts
 */
@RestController
@RequestMapping("/rest/pessoas")
public class PessoaRestController {
    
    private PessoaRepository pessoaRepository;
    
    private InteresseRepository interesseRepository;
    
    public PessoaRestController(PessoaRepository pessoaRepository, InteresseRepository interesseRepository) {
        this.pessoaRepository = pessoaRepository;
        this.interesseRepository = interesseRepository;
    }
    
    @GetMapping
    //@CrossOrigin(origins = "http://localhost:8080")
    @CrossOrigin(origins = "*")
    public Page<Pessoa> listar(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "qtd", defaultValue = "10") int qtd
    ) {
        Page<Pessoa> pessoas = pessoaRepository.findAll(PageRequest.of(pagina, qtd));
        return pessoas;
    }

    @GetMapping("/{id}")
    public Pessoa findById(@PathVariable("id") Integer id) {
        Optional<Pessoa> optPessoa = pessoaRepository.findById(id);
        if (optPessoa.isPresent()) {
            return optPessoa.get();
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Pessoa pessoa) {
        
        // Verifica se estã salvando Pessoa nova ou atualizando existente
        boolean criaNovo = false;
        if (pessoa.getId() == null) {
            criaNovo = true;
        }

        Set<Interesse> interesses = new LinkedHashSet<>();
        for (Integer id : pessoa.getInteressesId()) {
            interesseRepository.findById(id).ifPresent(interesse -> interesses.add(interesse));
        }
        pessoa.setInteresses(interesses);
        pessoa = pessoaRepository.save(pessoa);
        
        if (criaNovo) {
            // Preparar a URI que identifica a pessoa salva
            // A informação é retornada no cabeçalho "Location"
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(pessoa.getId()).toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        pessoaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

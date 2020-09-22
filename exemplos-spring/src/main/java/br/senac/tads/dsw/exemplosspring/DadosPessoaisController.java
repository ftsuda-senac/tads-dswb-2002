/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dados-pessoais")
public class DadosPessoaisController {
 
    @GetMapping("/{apelido}")
    public ModelAndView mostrar(@PathVariable("apelido") String apelido) {
        String nome = "Seu Madruga";
        String email = "madruga@teste.com.br";
        String telefone = "(11) 99999-7272";
        LocalDate dataNascimento = LocalDate.of(1971, 6, 20);
        
        ModelAndView mv = new ModelAndView("dados-pessoais");
        mv.addObject("nome", nome);
        mv.addObject("telefone", telefone);
        mv.addObject("email", email);
        mv.addObject("dataNascimento", dataNascimento);
        return mv;
    }

}

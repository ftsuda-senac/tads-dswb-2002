/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring.sessao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExemplosGetController {

    @GetMapping("/ex1")
    public String exemplo1() {
        return "links/get/exemplo1";
    }

    @GetMapping("/ex2")
    public String exemplo2(Model model) {
        model.addAttribute("mensagem", "Mensagem gerada no Controller");
        model.addAttribute("numero", 1122);
        model.addAttribute("dataHoraAcesso", LocalDateTime.now());
        return "links/get/exemplo2";
    }

    @GetMapping("/ex2b")
    public ModelAndView exemplo2b() {
        ModelAndView mv = new ModelAndView("links/get/exemplo2");
        mv.addObject("mensagem", "Mensagem gerada no Controller usando ModelAndView");
        mv.addObject("numero", 9876);
        mv.addObject("dataHoraAcesso", LocalDateTime.now());
        return mv;
    }

    @GetMapping("/ex3")
    public ModelAndView exemplo3() {
        List<Dados> lista = new ArrayList<>();
        lista.add(new Dados("Walter White", 145, LocalDate.of(1984, 4, 20), false));
        lista.add(new Dados("Jesse Pinkman", 566, LocalDate.of(2000, 10, 3), false));
        lista.add(new Dados("Seu Madruga", 72, LocalDate.of(1999, 1, 15), true));
        lista.add(new Dados("Luke Skywalker", 621, LocalDate.of(2003, 6, 10), false));
        
        ModelAndView mv = new ModelAndView("links/get/exemplo3");
        mv.addObject("lista", lista);
        return mv;
    }
    
    @GetMapping("/ex4")
    public ModelAndView exemplo4(
            @RequestParam("nome") String nome,
            @RequestParam(value = "numero", defaultValue="0") int numero,
            @RequestParam(value = "data", required = false) String dataStr) {
        
        Dados dado = new Dados();
        dado.setNome(nome);
        dado.setNumero(numero);
        LocalDate data;
        if (dataStr != null) {
            data = LocalDate.parse(dataStr);
        } else {
            data = LocalDate.now();
        }
        dado.setData(data);
        
        ModelAndView mv = new ModelAndView("links/get/exemplo4");
        mv.addObject("item", dado);
        return mv;
    }

    @GetMapping("/ex5/{apelido}")
    public ModelAndView exemplo5(
            @PathVariable("apelido") String apelido,
            @RequestParam(value = "numero", defaultValue="0") int numero,
            @RequestParam(value = "data", required = false) String dataStr) {
        String nome;
        if ("fulano".equals(apelido)) {
            nome = "Fulano da Silva";
        } else if ("ciclano".equals(apelido)) {
            nome = "Ciclano de Souza";
        } else if ("beltrana".equals(apelido)) {
            nome = "Beltrana dos Santos";
        } else {
            nome = "NOME INVÁLIDO";
        }

        Dados dado = new Dados();
        dado.setNome(nome);
        dado.setNumero(numero);
        LocalDate data;
        if (dataStr != null) {
            data = LocalDate.parse(dataStr);
        } else {
            data = LocalDate.now();
        }
        dado.setData(data);
        
        ModelAndView mv = new ModelAndView("links/get/exemplo5");
        mv.addObject("item", dado);
        mv.addObject("apelido", apelido);
        return mv;
    }

}

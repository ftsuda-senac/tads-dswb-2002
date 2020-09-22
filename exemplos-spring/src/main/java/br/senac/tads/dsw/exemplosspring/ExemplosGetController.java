/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        return "exemplo1";
    }
   
    @GetMapping("/ex2")
    public String exemplo2(Model model) {
        model.addAttribute("mensagem", "Mensagem gerada no Controller");
        model.addAttribute("numero", 1234);
        model.addAttribute("dataAcesso", LocalDateTime.now());
        return "exemplo2";
    }

    @GetMapping("/ex2mv")
    public ModelAndView exemplo2mv() {
        ModelAndView mv = new ModelAndView("exemplo2");
        mv.addObject("mensagem", "Mensagem gerada no Controller usando ModelAndView");
        mv.addObject("numero", 5678);
        mv.addObject("dataAcesso", LocalDateTime.now());
        return mv;
    }

    @GetMapping("/ex3")
    public ModelAndView exemplo3() {
        List<Dados> lista = new ArrayList<>();
        lista.add(new Dados("Darth Vader", 123, LocalDateTime.now(), false));
        lista.add(new Dados("Luke Skywalker", 621, LocalDateTime.now(), false));
        lista.add(new Dados("Leia Organa", 498, LocalDateTime.now(), false));
        lista.add(new Dados("Han Solo", 608, LocalDateTime.now(), true));
        
        ModelAndView mv = new ModelAndView("exemplo3");
        mv.addObject("lista", lista);
        return mv;
    }

    @GetMapping("/ex4")
    public ModelAndView exemplo4(
            @RequestParam("nome") String nome,
            @RequestParam(value = "numero", defaultValue = "0") int numero,
            @RequestParam(value = "data", required = false) String data) {
        Dados dados = new Dados();
        dados.setNome(nome);
        dados.setNumero(numero);
        LocalDate localDate;
        if (data != null) {
            localDate = LocalDate.parse(data);
        } else {
            localDate = LocalDate.now();
        }
        dados.setDataHora(LocalDateTime.of(localDate, LocalTime.now()));
        
        ModelAndView mv = new ModelAndView("exemplo4");
        mv.addObject("item", dados);
        return mv;
    }
    
    @GetMapping("/ex5/{apelido}")
    public ModelAndView exemplo5(
            @PathVariable("apelido") String apelido,
            @RequestParam(value = "numero", defaultValue = "0") int numero,
            @RequestParam(value = "data", required = false) String data) {
        Dados dados = new Dados();
        if ("fulano".equals(apelido)) {
            dados.setNome("Fulano da Silva");
        } else if ("ciclano".equals(apelido)) {
            dados.setNome("Ciclano de Souza");
        } else if ("beltrana".equals(apelido)) {
            dados.setNome("Beltrana das Cruzes");
        }
        dados.setNumero(numero);
        LocalDate localDate;
        if (data != null) {
            localDate = LocalDate.parse(data);
        } else {
            localDate = LocalDate.now();
        }
        dados.setDataHora(LocalDateTime.of(localDate, LocalTime.now()));
        
        ModelAndView mv = new ModelAndView("exemplo5");
        mv.addObject("item", dados);
        mv.addObject("apelido", apelido);
        return mv;
    }
   
    @GetMapping("/ex6")
    public ModelAndView exemplo6(
            @RequestHeader("user-agent") String userAgent) {
        
        boolean clientIsMobile = userAgent.toLowerCase().contains("mobile");
        
        ModelAndView mv = new ModelAndView("exemplo6");
        mv.addObject("ua", userAgent);
        mv.addObject("mobile", clientIsMobile);
        return mv;
    }

    @GetMapping("/ex7")
    public ModelAndView exemplo7(
        @RequestHeader Map<String, String> cabecalhosHttp) {
        
        ModelAndView mv = new ModelAndView("exemplo7");
        mv.addObject("cabecalhos", cabecalhosHttp);
        return mv;
    }
   
    @GetMapping("/ex8")
    public String exemplo8() {
        return "exemplo8";
    }
}

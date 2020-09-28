/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/formulario")
public class FormularioController {

    @GetMapping
    public ModelAndView mostrarForm() {
        return new ModelAndView("formulario");
    }

    @PostMapping
    public ModelAndView receberDados(@ModelAttribute Formulario formulario) {
        ModelAndView mv = new ModelAndView("resultado-post");
        mv.addObject("dados", formulario);
        return mv;
    }
    
}

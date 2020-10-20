/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/i18n")
public class IntlController {

    @GetMapping("/sem")
    public ModelAndView semI18n() {
        return new ModelAndView("/intl/sem-i18n");
    }

    @GetMapping("/com")
    public ModelAndView comI18n(@RequestHeader("Accept-Language") String acceptLanguage) {
        return new ModelAndView("/intl/com-i18n").addObject("acceptLanguage", acceptLanguage);
    }
   
    @GetMapping("/com-interceptor")
    public ModelAndView comI18nInterceptor() {
        return new ModelAndView("/intl/com-i18n-interceptor");
    }

    @GetMapping("/form")
    public ModelAndView mostrarForm() {
        return new ModelAndView("/intl/form").addObject("info", new Info());
    }

    @PostMapping("/form")
    public ModelAndView receberDados(
            @ModelAttribute("info") @Valid Info info, 
            BindingResult bindingResult, 
            RedirectAttributes redirAttr) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/intl/form");
        }
        
        String msg = "Info válido";
        redirAttr.addFlashAttribute("msg", msg);
        return new ModelAndView("redirect:/i18n/form");
    }
}

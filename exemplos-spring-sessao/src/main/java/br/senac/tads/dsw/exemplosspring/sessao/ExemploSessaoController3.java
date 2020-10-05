/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring.sessao;

import br.senac.tads.dsw.exemplosspring.sessao.item.Item;
import br.senac.tads.dsw.exemplosspring.sessao.item.ItemService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fedts
 */
@Controller
@RequestMapping("/exemplo-sessao3")
public class ExemploSessaoController3 {
    
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ModelAndView mostrarTela() {
        return new ModelAndView("exemplo-sessao3").addObject("itens", itemService.findAll());
    }

    @PostMapping
    public ModelAndView adicionarItem(
            @ModelAttribute("itemId") Integer itemId,
            HttpServletRequest request,
            RedirectAttributes redirAttr) {
        
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("itensSelecionados3") == null) {
            sessao.setAttribute("itensSelecionados3", new ArrayList<>());
        }
        List<ItemSelecionado> itensSelecionados = (ArrayList<ItemSelecionado>) sessao.getAttribute("itensSelecionados3");
        
        Item item = itemService.findById(itemId);
        itensSelecionados.add(new ItemSelecionado(item));
        redirAttr.addFlashAttribute("msg", "Item ID " + item.getId() + " adicionado com sucesso");
        return new ModelAndView("redirect:/exemplo-sessao3");
    }

    @GetMapping("/limpar")
    public ModelAndView limparSessao(HttpServletRequest request,
            RedirectAttributes redirAttr) {
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("itensSelecionados3") != null) {
            List<ItemSelecionado> itensSelecionados = (ArrayList<ItemSelecionado>) sessao.getAttribute("itensSelecionados3");
            itensSelecionados.clear();
        }
        redirAttr.addFlashAttribute("msg", "Itens removidos");
        return new ModelAndView("redirect:/exemplo-sessao3");
    }

    public List<ItemSelecionado> getItensSelecionados() {
        return new ArrayList<>();
    }

    @ModelAttribute("titulo")
    public String getTitulo() {
        return "Exemplo Sessao 3 - Uso do HttpSession obtido do HttpServletRequest";
    }

}

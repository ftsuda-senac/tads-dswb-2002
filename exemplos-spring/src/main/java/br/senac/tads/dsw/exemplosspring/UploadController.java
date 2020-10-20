/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author fedts
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @GetMapping
    public ModelAndView mostrarForm() {
        return new ModelAndView("upload-form");
    }

    @PostMapping("/salvar")
    public ModelAndView receberArquivos(@RequestParam("arquivo") MultipartFile arquivo,
            RedirectAttributes redirAttr) {
        
        // Verifica se arquivo é vazio -> ERRO
        if (arquivo.isEmpty()) {
            redirAttr.addFlashAttribute("msg", "Arquivo inválido");
            return new ModelAndView("redirect:/upload");
        }
        
        try {
            byte[] bytesArquivo = arquivo.getBytes();
            Path destino = Paths.get("C:/senac/tads/uploads/" + arquivo.getOriginalFilename());
            Files.write(destino, bytesArquivo);
            
            redirAttr.addFlashAttribute("msg", "Arquivo " + arquivo.getOriginalFilename() + " enviado com sucesso.");
            redirAttr.addFlashAttribute("urlAcessoUpload", "/upload-acesso/" + arquivo.getOriginalFilename());
            return new ModelAndView("redirect:/upload");
            
        } catch (IOException e) {
            redirAttr.addFlashAttribute("msg", "Erro durante upload " + e.getMessage());
            return new ModelAndView("redirect:/upload");
        }
        
    }
    
}

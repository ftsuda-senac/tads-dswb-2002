/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.tutorial;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DadosPessoaisServlet2", urlPatterns = {"/dados-pessoais2"})
public class DadosPessoaisServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String ua = request.getHeader("user-agent");
        boolean isMobile = ua.toLowerCase().contains("mobile");

        // Recuperar informações passadas via queryString
        String idStr = request.getParameter("id");
        DadosPessoais dados = new DadosPessoais();
        if ("1".equals(idStr)) {
            dados.setNome("Seu Madruga");
            dados.setEmail("madruga@teste.com.br");
            dados.setTelefone("(11) 99999-1234");
            dados.setDataNascimento(LocalDate.of(1971, 6, 20));
            dados.setImgPath("/img/madruga.jpg");
            // request.setAttribute("dados", dados);
        } else if ("2".equals(idStr)) {
            dados.setNome("Palhaço Bozo");
            dados.setEmail("bozo@teste.com.br");
            dados.setTelefone("(11) 99999-9876");
            dados.setDataNascimento(LocalDate.of(1991, 10, 1));
            dados.setImgPath("/img/madruga.jpg");
            // request.setAttribute("dados", dados);
        } else {
            response.sendError(404);
            return;
        }
        request.setAttribute("mobile", isMobile);
        request.setAttribute("info", "Mensagem gerada no Servlet às " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        
        // CONVERTE OBJ JAVA PARA JSON (VER NO POM.XML A DEPENDENCIA PARA LOCALDATE)
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(dados);
        System.out.println("JSON gerado: " + json);
        
        try (PrintWriter out = response.getWriter()) {
            out.print(json);
        }
        
        //RequestDispatcher dispatcher = request.getRequestDispatcher("dadosPessoais.jsp");
        //dispatcher.forward(request, response);
    }

}

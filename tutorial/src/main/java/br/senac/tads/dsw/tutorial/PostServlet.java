/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.tutorial;

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

@WebServlet(name = "PostServlet", urlPatterns = {"/exemplo-post"})
public class PostServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar tela do form.
        RequestDispatcher dispatcher = request.getRequestDispatcher("formulario.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String dataNascimentoStr = request.getParameter("dataNascimento");
        
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr);
        
        DadosPessoais dados = new DadosPessoais();
        dados.setNome(nome);
        dados.setEmail(email);
        dados.setDataNascimento(dataNascimento);
        dados.setTelefone(telefone);
        dados.setImgPath("/img/madruga.jpg");
        
        request.setAttribute("dados", dados);
        request.setAttribute("info", "Dados preenchido pelo usuário às " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        request.setAttribute("mobile", false);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("dadosPessoais.jsp");
        dispatcher.forward(request, response);
    }
}

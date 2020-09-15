/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplos.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Exemplo1Servlet", urlPatterns = {"/exemplo1"})
public class Exemplo1Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String protocol = request.getProtocol();
        
        response.setContentType("text/html;charset=UTF-8");
        response.addHeader("xpto", "bla bla bla");
        
        // Tratando e manipulando cookies
        boolean encontrado = false;
        if (request.getCookies() != null && request.getCookies().length > 0) {
            for (Cookie cookie : request.getCookies()) {
                if ("cookieabc".equals(cookie.getName())) {
                    String valorAntigo = cookie.getValue();
                    int valorNovo = Integer.parseInt(valorAntigo) + 1;
                    response.addCookie(new Cookie("cookieabc", String.valueOf(valorNovo)));
                    encontrado = true;
                }
            }
        }
        if (!encontrado) {
            response.addCookie(new Cookie("cookieabc", "0"));
        }

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Exemplo1Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Alguns dados enviados na requisição</h1>");
            out.println("<ul>");
            out.println("<li>Protocolo: " + request.getProtocol() + "</li>");
            out.println("<li>Host: " + request.getHeader("Host") + "</li>");
            out.println("<li>User-agent: " + request.getHeader("User-agent") + "</li>");
            out.println("<li>Query string: " + request.getQueryString() + "</li>");
            out.println("<li>Context path: " + request.getContextPath() + "</li>");
            out.println("<li>Parâmetro \"dado1\": " + request.getParameter("dado1") + "</li>");
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}

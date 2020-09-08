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

@WebServlet(name = "DadosPessoaisServlet", urlPatterns = {"/dados-pessoais"})
public class DadosPessoaisServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String ua = request.getHeader("user-agent");
        boolean isMobile = ua.toLowerCase().contains("mobile");

        // Recuperar informações passadas via queryString
        String idStr = request.getParameter("id");
        if ("1".equals(idStr)) {
            DadosPessoais dados1 = new DadosPessoais();
            dados1.setNome("Seu Madruga");
            dados1.setEmail("madruga@teste.com.br");
            dados1.setTelefone("(11) 99999-1234");
            dados1.setDataNascimento(LocalDate.of(1971, 6, 20));
            dados1.setImgPath("/img/madruga.jpg");
            request.setAttribute("dados", dados1);
        } else if ("2".equals(idStr)) {
            DadosPessoais dados2 = new DadosPessoais();
            dados2.setNome("Palhaço Bozo");
            dados2.setEmail("bozo@teste.com.br");
            dados2.setTelefone("(11) 99999-9876");
            dados2.setDataNascimento(LocalDate.of(1991, 10, 1));
            dados2.setImgPath("/img/madruga.jpg");
            request.setAttribute("dados", dados2);
        } else {
            response.sendError(404);
            return;
        }
        request.setAttribute("mobile", isMobile);
        request.setAttribute("info", "Mensagem gerada no Servlet às " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        RequestDispatcher dispatcher = request.getRequestDispatcher("dadosPessoais.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}

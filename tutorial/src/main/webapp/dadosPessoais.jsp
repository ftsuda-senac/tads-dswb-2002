<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${dados.getNome()}" /> - Página pessoal</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel="stylesheet" href="${pageContext.request.getContextPath()}/css/estilos.css">
</head>
<body>
    <article>
        <h1><c:out value="${dados.getNome()}" /></h1>
        <header>
            <div class="avatar">
                <img src="${pageContext.request.getContextPath()}${dados.getImgPath()}" alt="Foto de perfil">
            </div>
            <div class="contato">
                <p><c:out value="${dados.getTelefone()}" /></p>
                <p><a href="mailto:${dados.getEmail()}"><c:out value="${dados.getEmail()}" /></a></p>
                <p>Data de nascimento: ${dados.getDataNascimento()}</p>
            </div>
        </header>
    </article>
    <p><c:out value="${info}" /></p>
    <c:if test="${mobile == true}">
        <p>DISPOSITO MÓVEL</p>
    </c:if>
    <p><c:out value="${pageContext.request.getHeader('user-agent')}" /></p>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Preencha as informações</h1>
        <form action="${pageContext.request.getContextPath()}/exemplo-post" method="post">
            <div>
                <label>Nome</label>
                <input type="text" name="nome">
            </div>
            <div>
                <label>E-mail</label>
                <input type="email" name="email">
            </div>
            <div>
                <label>Telefone</label>
                <input type="text" name="telefone">
            </div>
            <div>
                <label>Data de nascimento</label>
                <input type="date" name="dataNascimento">
            </div>
            <div>
                <label>Senha</label>
                <input type="password" name="senha">
            </div>
            <button type="submit">Enviar</button>
        </form>
    </body>
</html>

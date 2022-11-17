<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
    <title>LAB 10</title>
</head>
<body>

<div class='container'>
    <div class="row mb-4">
        <div class="col"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Iniciar Sesión</h1>
            <% if(session.getAttribute("infono")!= null) {%>
            <div class="alert alert-danger" role="alert">
                <%=session.getAttribute("infono")%>
            </div>
            <%session.removeAttribute("infono");%>
            <%}%>
            <form method="post" action="<%=request.getContextPath()%>/ServletInicio?action=login">
                <div class="form-group">
                    <label>Usuario</label>
                    <input type="text" class="form-control" name="usuario">
                </div>
                <div class="form-group">
                    <label>Contraseña</label>
                    <input class="form-control" name="password" type="text"/>
                </div>
                <button type="submit" class="btn btn-primary">Ingresar</button>
            </form>
        </div>
        <div class="col"></div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
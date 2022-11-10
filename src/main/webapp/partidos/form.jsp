<%@ page import="com.example.lab9_base.Bean.Seleccion" %>
<%@ page import="com.example.lab9_base.Bean.Arbitro" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Seleccion> listaseleccion = (ArrayList<Seleccion>) request.getAttribute("listaseleccion");
%>
<%
    ArrayList<Arbitro> listaarbitro = (ArrayList<Arbitro>) request.getAttribute("listaarbitro");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
        <title>LAB 9</title>
    </head>
    <body>
    <nav class="navbar navbar-expand-md navbar-light bg-light">
        <a class="navbar-brand" href="#">Clasificatorias Sudamericanas Qatar 2022</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
            <ul class="navbar-nav">
                <li class="nav-item" >
                    <a class="nav-link" href="<%=request.getContextPath()%>/PartidoServlet">Partidos</a>
                </li>
                <li class="nav-item" >
                    <a class="nav-link" href="<%=request.getContextPath()%>/ArbitroServlet">Arbitros</a>
                </li>
            </ul>
        </div>
    </nav>
        <div class='container'>
            <div class="row mb-4">
                <div class="col"></div>
                <div class="col-md-6">
                    <h1 class='mb-3'>Crear un Partido de Clasificatorias</h1>
                    <% if(session.getAttribute("infono")!= null) {%>
                    <div class="alert alert-danger" role="alert">
                        <%=session.getAttribute("infono")%>
                    </div>
                    <%session.removeAttribute("infono");%>
                    <%}%>
                    <form method="post" action="<%=request.getContextPath()%>/PartidoServlet?action=guardar">
                        <div class="form-group">
                            <label>Jornada</label>
                            <input type="number" class="form-control" name="jornada">
                        </div>
                        <% if(session.getAttribute("infojornada")!= null) {%>
                        <div class="alert alert-danger" role="alert">
                            <%=session.getAttribute("infojornada")%>
                        </div>
                        <%session.removeAttribute("infojornada");%>
                        <%}%>
                        <div class="form-group">
                            <label>Fecha</label>
                            <input class="form-control datetimepicker" id="fecha" name="fecha"
                                   type="date"/>
                        </div>
                        <% if(session.getAttribute("infofecha")!= null) {%>
                        <div class="alert alert-danger" role="alert">
                            <%=session.getAttribute("infofecha")%>
                        </div>
                        <%session.removeAttribute("infofecha");%>
                        <%}%>
                        <div class="form-group">
                            <label>Selección local</label>
                            <select name="local" class="form-control">
                                <%--                       COLOCAR LISTA DE SELECCIONES DE LA BASE DE DATOS--%>
                                    <option selected></option>
                                    <% for(Seleccion sele : listaseleccion) {%>
                                    <option value="<%=sele.getIdSeleccion()%>"><%=sele.getNombre()%></option>
                                    <% }%>
                            </select>
                        </div>
                        <% if(session.getAttribute("infolocal")!= null) {%>
                        <div class="alert alert-danger" role="alert">
                            <%=session.getAttribute("infolocal")%>
                        </div>
                        <%session.removeAttribute("infolocal");%>
                        <%}%>
                        <div class="form-group">
                            <label>Selección Visitante</label>
                            <select name="visitante" class="form-control">
                                <%--                        COLOCAR LISTA DE SELECCIONES DE LA BASE DE DATOS--%>
                                    <option selected></option>
                                    <% for(Seleccion sele1 : listaseleccion) {%>
                                    <option value="<%=sele1.getIdSeleccion()%>"><%=sele1.getNombre()%></option>
                                    <% }%>
                            </select>
                        </div>
                        <% if(session.getAttribute("infovisitante")!= null) {%>
                        <div class="alert alert-danger" role="alert">
                            <%=session.getAttribute("infovisitante")%>
                        </div>
                        <%session.removeAttribute("infovisitante");%>
                        <%}%>
                        <div class="form-group">
                            <label>Árbitro</label>
                            <select name="arbitro" class="form-control">
                                <%--                        COLOCAR LISTA DE ÁRBITRO DE LA BASE DE DATOS--%>
                                    <option selected></option>
                                    <% for(Arbitro arbitro : listaarbitro) {%>
                                    <option value="<%=arbitro.getIdArbitro()%>"><%=arbitro.getNombre()%></option>
                                    <% }%>
                            </select>
                        </div>
                        <% if(session.getAttribute("infoarr")!= null) {%>
                        <div class="alert alert-danger" role="alert">
                            <%=session.getAttribute("infoarr")%>
                        </div>
                        <%session.removeAttribute("infoarr");%>
                        <%}%>
                        <button type="submit" class="btn btn-primary">Guardar</button>
                        <a href="<%=request.getContextPath()%>/PartidoServlet" class="btn btn-danger">Cancelar</a>
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

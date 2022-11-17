<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17/11/2022
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css' />

    <title>Title</title>
</head>
<body>
<table class="table">
    <tr>
        <th>#</th>
        <th>Jornada</th>
        <th>Fecha</th>
        <th>Selección Local</th>
        <th>Selección Visitante</th>
        <th>Estadio a jugar</th>
        <th>Árbitro</th>
    </tr>
    <% for(Partido match : listapartido) {%>
    <% Estadio estadio = match.getSeleccionLocal().getEstadio();%>
    <% Arbitro ar = match.getArbitro();%>
    <tr>
        <td><%= match.getIdPartido()%></td>
        <td><%= match.getNumeroJornada()%></td>
        <td><%= match.getFecha()%></td>
        <td><%= match.getSeleccionLocal().getNombre()%></td>
        <td><%= match.getSeleccionVisitante().getNombre()%></td>
        <td><%= estadio.getNombre()%></td>
        <td><%= ar.getNombre()%></td>
    </tr>
    <% }%>
</table>
</body>
</html>

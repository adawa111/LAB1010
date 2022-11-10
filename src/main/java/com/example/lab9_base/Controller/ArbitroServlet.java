package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Dao.DaoArbitros;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ArbitroServlet", urlPatterns = {"/ArbitroServlet"})
public class ArbitroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        DaoArbitros arbitrosDao = new DaoArbitros();
        Arbitro arbitro = null;

        switch (action) {

            case "buscar":
                /*
                Inserte su código aquí
                */
                break;

            case "guardar":
                /*
                Inserte su código aquí
                */
                arbitro = new Arbitro();

                String nombre = request.getParameter("nombre");
                String pais = request.getParameter("pais");
                arbitro.setNombre(nombre);
                arbitro.setPais(pais);

                arbitrosDao.crearArbitro(arbitro);
                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/ArbitroServlet");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        ArrayList<String> paises = new ArrayList<>();
        paises.add("Peru");
        paises.add("Chile");
        paises.add("Argentina");
        paises.add("Paraguay");
        paises.add("Uruguay");
        paises.add("Colombia");
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("nombre");
        opciones.add("pais");
        DaoArbitros arbitrosDao = new DaoArbitros();

        switch (action) {
            case "lista":
                /*
                Inserte su código aquí
                 */
                ArrayList<Arbitro> listaArbitros = null;
                listaArbitros = arbitrosDao.listarArbitros();

                request.setAttribute("listaArbitros",listaArbitros);
                request.setAttribute("opciones",opciones);
                view = request.getRequestDispatcher("/arbitros/list.jsp");
                view.forward(request, response);
                break;
            case "crear":
                /*
                Inserte su código aquí
                */
                request.setAttribute("paises",paises);
                view = request.getRequestDispatcher("/arbitros/form.jsp");
                view.forward(request, response);
                break;

            case "borrar":
                /*
                Inserte su código aquí
                */

                String id1 = request.getParameter("id");
                int id = Integer.parseInt(id1);
                arbitrosDao.borrarArbitro(id);
                response.sendRedirect(request.getContextPath()+ "/ArbitroServlet");
                break;
        }
    }
}

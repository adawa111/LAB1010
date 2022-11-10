package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Dao.DaoArbitros;
import com.example.lab9_base.Dao.DaoBase;
import com.example.lab9_base.Dao.DaoPartidos;
import com.example.lab9_base.Dao.DaoSelecciones;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PartidoServlet", urlPatterns = {"/PartidoServlet", ""})
public class PartidoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "guardar" : request.getParameter("action");
        RequestDispatcher view;
        DaoPartidos dao = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoarbitro = new DaoArbitros();

        switch (action) {
            case ("guardar"):
                ArrayList<Seleccion> listaseleccion = null;
                listaseleccion = daoSelecciones.listarSelecciones();
                ArrayList<Arbitro> listaarbitro = null;
                listaarbitro = daoarbitro.listarArbitros();
                request.setAttribute("listaarbitro",listaarbitro);
                request.setAttribute("listaseleccion",listaseleccion);

                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoPartidos dao = new DaoPartidos();
        switch (action) {
            case "lista":
                ArrayList<Partido> listapartido = null;
                listapartido = dao.listaDePartidos();
                System.out.println(listapartido.get(1).getSeleccionLocal().getNombre());
                request.setAttribute("listapartido",listapartido);
                view = request.getRequestDispatcher("index.jsp");
                view.forward(request, response);
                break;
            case "crear":
                view = request.getRequestDispatcher("partidos/form.jsp");
                view.forward(request, response);
                break;

        }

    }
}

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
                String jornada = request.getParameter("jornada");
                String fecha = request.getParameter("fecha");
                String local = request.getParameter("i");
                String visitante = request.getParameter("j");
                String arbitro = request.getParameter("k");
                Partido par = null;
                try{
                    int jor = Integer.parseInt(jornada);
                    int loc = Integer.parseInt(local);
                    int vis = Integer.parseInt(visitante);
                    int arr = Integer.parseInt(arbitro);
                    Seleccion lo = dao.obtenerSeleccion(loc);
                    Seleccion vi = dao.obtenerSeleccion(vis);
                    Arbitro ab = dao.obtenerArbitro(arr);
                    par.setSeleccionLocal(lo);
                    par.setSeleccionVisitante(vi);
                    par.setArbitro(ab);
                    par.setFecha(fecha);
                    par.setNumeroJornada(jor);
                    dao.crearPartido(par);
                }catch (NumberFormatException e){
                    System.out.println("bad");
                }


                break;

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoPartidos dao = new DaoPartidos();
        DaoSelecciones daoSelecciones = new DaoSelecciones();
        DaoArbitros daoarbitro = new DaoArbitros();
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

                ArrayList<Seleccion> listaseleccion = null;
                listaseleccion = daoSelecciones.listarSelecciones();
                System.out.println(listaseleccion.get(2).getNombre());
                ArrayList<Arbitro> listaarbitro = null;
                listaarbitro = daoarbitro.listarArbitros();
                System.out.println(listaarbitro.get(1).getNombre());
                request.setAttribute("listaarbitro",listaarbitro);
                request.setAttribute("listaseleccion",listaseleccion);
                view = request.getRequestDispatcher("partidos/form.jsp");
                view.forward(request, response);
                break;

        }

    }
}

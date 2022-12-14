package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Dao.DaoSelecciones;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
                String local = request.getParameter("local");
                String visitante = request.getParameter("visitante");
                String arbitro = request.getParameter("arbitro");
                Partido par = new Partido();
                int a = 0;
                int b = 0;
                int c = 0;
                int d = 0;
                int e = 0;
                int jor = 0, loc = 0, vis = 0, arr = 0;
                if (!(jornada.isEmpty())){
                    try {
                        jor = Integer.parseInt(jornada);
                        a =1;
                    }catch (NumberFormatException ee){
                        System.out.println("bad");
                        request.getSession().setAttribute("infojornada","Campo llenado incorrectamente");
                    }
                } else {
                    request.getSession().setAttribute("infojornada","Campo Obligatorio");
                }
                if (!(fecha.isEmpty())){
                    b = 1;
                }else {
                    request.getSession().setAttribute("infofecha","Seleccione una fecha v??lida");
                }
                if (!(local.isEmpty())){
                    try {
                        loc = Integer.parseInt(local);
                        c = 1;
                    }catch (NumberFormatException ee){
                        System.out.println("bad");
                        request.getSession().setAttribute("infolocal","Campo llenado incorrectamente");
                    }
                } else {
                    request.getSession().setAttribute("infolocal","Seleccione un pa??s local");
                }
                if ( !(visitante.isEmpty())){
                    try {
                        vis = Integer.parseInt(visitante);
                        d = 1;
                    }catch (NumberFormatException ee){
                        System.out.println("bad");
                        request.getSession().setAttribute("infovisitante","Campo llenado incorrectamente");
                    }
                } else {
                    request.getSession().setAttribute("infovisitante","Seleccione un pa??s visitante");
                }
                if (!(arbitro.isEmpty())){
                    try {
                        arr = Integer.parseInt(arbitro);
                        e = 1;
                    }catch (NumberFormatException ee){
                        System.out.println("bad");
                        request.getSession().setAttribute("infoarr","Campo llenado incorrectamente");
                    }
                } else {
                    request.getSession().setAttribute("infoarr","Seleccione a un ??rbitro");
                }
                if (a==1&b==1&c==1&d==1&e==1){
                    if (daoSelecciones.existe(loc)==1 & daoSelecciones.existe(vis)==1){
                        Seleccion lo = dao.obtenerSeleccion(loc);
                        Seleccion vi = dao.obtenerSeleccion(vis);
                        Arbitro ab = dao.obtenerArbitro(arr);
                        par.setSeleccionLocal(lo);
                        par.setSeleccionVisitante(vi);
                        par.setArbitro(ab);
                        par.setFecha(fecha);
                        par.setNumeroJornada(jor);
                        if (lo.getIdSeleccion() != vi.getIdSeleccion()){
                            if (dao.partidosIguales(par)==0){
                                dao.crearPartido(par);
                                request.getSession().setAttribute("infosi","El partido se a??adio con ??xtio");
                                response.sendRedirect(request.getContextPath() + "/PartidoServlet?");
                            }else {
                                request.getSession().setAttribute("infono","Partido ya registrado");
                                response.sendRedirect(request.getContextPath() + "/PartidoServlet?action=crear");
                            }
                        }else {
                            request.getSession().setAttribute("infono","Las selecciones no pueden ser las mismas");
                            response.sendRedirect(request.getContextPath() + "/PartidoServlet?action=crear");
                        }
                    }else {
                        request.getSession().setAttribute("infono","Las selecciones ingresadas no est??n registradas ");
                        response.sendRedirect(request.getContextPath() + "/PartidoServlet?action=crear");
                    }
                }else {
                    System.out.println("bad");
                    request.getSession().setAttribute("infono","Campos llenados incorrectamente");
                    response.sendRedirect(request.getContextPath() + "/PartidoServlet?action=crear");
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

    public static class DaoBase {

        public Connection getConnection() throws SQLException {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch(ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            String user= "root";
            String pass= "123456";
            String url = "jdbc:mysql://localhost:3306/bi_corp_business?serverTimezone=America/Lima";

            return DriverManager.getConnection(url,user,pass);
        }
    }
}

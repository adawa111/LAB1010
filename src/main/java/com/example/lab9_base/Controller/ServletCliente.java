package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Clientes;
import com.example.lab9_base.Bean.Contratos;
import com.example.lab9_base.Bean.Credentials;
import com.example.lab9_base.Dao.DaoCliente;
import com.example.lab9_base.Dao.DaoContrato;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletCliente", value = "/ServletCliente")
public class ServletCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        String id = request.getParameter("id");
        DaoCliente daoCliente = new DaoCliente();
        DaoContrato contratoDao = new DaoContrato();
        switch (action) {
            case "puntaje": //expected loss

                request.setAttribute("puntaje", daoCliente.mostrarMaxExpectedLoss(id));
                view = request.getRequestDispatcher("clientepuntaje.jsp");
                view.forward(request, response);

                break;
            case "buscarCliente":

                Clientes cliente = daoCliente.buscarCliente(id);
                request.setAttribute("client",cliente);
                view = request.getRequestDispatcher("clientedatos.jsp");
                view.forward(request, response);
                break;
            case "listarContratos":
                ArrayList<Contratos> listaContratos = contratoDao.listarContratos();
                request.setAttribute("listaContratos",listaContratos);
                view = request.getRequestDispatcher("clientecontratos.jsp");
                view.forward(request, response);
                break;

            case "mostrarContratos":

                request.setAttribute("listaContratosxEstado",contratoDao.mostrarContratosxEstado());
                view = request.getRequestDispatcher("clienteestados.jsp");
                view.forward(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/ServletCliente");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

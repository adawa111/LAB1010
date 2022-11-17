package com.example.lab9_base.Controller;

import com.example.lab9_base.Bean.Clientes;
import com.example.lab9_base.Bean.Credentials;
import com.example.lab9_base.Dao.DaoCliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletAdmin", value = "/ServletAdmin")
public class ServletAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        RequestDispatcher view;
        DaoCliente cl = new DaoCliente();
        switch (action) {
            case "crear":
                String dni = request.getParameter("documento");
                String pass = request.getParameter("password");
                cl.crearCredentialCliente(dni,pass);
                response.sendRedirect(request.getContextPath());
                break;

        }


    }
}

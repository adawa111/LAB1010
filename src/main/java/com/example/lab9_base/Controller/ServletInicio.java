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
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletInicio", value = "/ServletInicio")
public class ServletInicio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");
        RequestDispatcher view;
        DaoCliente cli = new DaoCliente();
        switch (action) {
            case "login":
                /*
                Inserte su código aquí
                 */
                String user = request.getParameter("usuario");
                String pass = request.getParameter("password");
                Credentials cre = cli.buscarUsuario(user,pass);

                if(cre != null){
                    HttpSession session = request.getSession();
                    session.setAttribute("sesion",cre);
                    int a = cre.getTipoUsuario();
                    if (a==1){
                        ArrayList<Clientes> listaclientes = null;
                        listaclientes = cli.listarClientes();
                        request.setAttribute("lista",listaclientes);
                        view = request.getRequestDispatcher("/admin.jsp");
                        view.forward(request,response);
                    } else if (a==2) {
                        view = request.getRequestDispatcher("/cliente.jsp");
                        view.forward(request,response);
                    }
                }else {
                    request.getSession().setAttribute("infono","Datos erroneos");
                    response.sendRedirect(request.getContextPath()+"/ServletInicio?");
                }
                break;


        }
    }
}

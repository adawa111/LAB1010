package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;
import com.example.lab9_base.Dao.DaoPartidos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoSelecciones extends DaoBase{
    public int existe(int id){
        int existe = 0;
        String sql1 = "select * from seleccion where idSeleccion ="+id;
        try(Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs1 = stm.executeQuery(sql1)) {
            rs1.next();
            if (rs1!=null){
                existe = 1;
            }else {
                existe = 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return existe;
    }
    public ArrayList<Seleccion> listarSelecciones() {


        DaoPartidos daopar = new DaoPartidos();
        ArrayList<Seleccion> selecciones = new ArrayList<>();
        String sql = "select * from seleccion";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){
            while (rs.next()) {
                Seleccion sele = new Seleccion();
                sele.setIdSeleccion(rs.getInt("idSeleccion"));
                sele.setNombre(rs.getString("nombre"));;
                sele.setTecnico(rs.getString("tecnico"));
                sele.setEstadio(daopar.obtenerEstadio(rs.getString("estadio_idEstadio")));
                selecciones.add(sele);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selecciones;
    }


}

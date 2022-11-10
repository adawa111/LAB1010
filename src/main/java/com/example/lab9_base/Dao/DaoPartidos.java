package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;
import com.example.lab9_base.Bean.Estadio;
import com.example.lab9_base.Bean.Partido;
import com.example.lab9_base.Bean.Seleccion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoPartidos extends DaoBase{
    public Arbitro obtenerArbitro(int id){
        Arbitro refere = new Arbitro();
        String sql1 = "Select * from arbitro where idArbitro ="+id;
        try(Connection con1 = this.getConnection();
            Statement stm1 = con1.createStatement();
            ResultSet rs1 = stm1.executeQuery(sql1)) {
            rs1.next();
            refere.setIdArbitro(id);
            refere.setNombre(rs1.getString("nombre"));
            refere.setPais(rs1.getString("pais"));
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return refere;
    }
    public Estadio obtenerEstadio(String id){
        Estadio esta = new Estadio();
        String sql1 = "Select * from estadio where idEstadio ="+id;
        try(Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs1 = stm.executeQuery(sql1)) {
            rs1.next();
            int idd = Integer.parseInt(id);
            esta.setIdEstadio(idd);
            esta.setClub(rs1.getString("club"));
            esta.setProvincia(rs1.getString("provincia"));
            esta.setNombre(rs1.getString("nombre"));

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return esta;
    }

    public Seleccion obtenerSeleccion(int id){
        Seleccion sele = new Seleccion();
        String sql1 = "select * from seleccion where idSeleccion ="+id;
        try(Connection con = this.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs1 = stm.executeQuery(sql1)) {
            rs1.next();
            sele.setIdSeleccion(id);
            sele.setNombre(rs1.getString("nombre"));;
            sele.setTecnico(rs1.getString("tecnico"));
            sele.setEstadio(obtenerEstadio(rs1.getString("estadio_idEstadio")));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sele;
    }
    public ArrayList<Partido> listaDePartidos() {

        ArrayList<Partido> partidos = new ArrayList<>();
        String sql = "select * from partido";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){
            while (rs.next()) {
                Partido match = new Partido();
                match.setIdPartido(rs.getInt("idPartido"));
                match.setSeleccionLocal(obtenerSeleccion(rs.getInt("seleccionLocal")));
                Seleccion local = obtenerSeleccion(rs.getInt("seleccionVisitante"));
                Seleccion vistante = obtenerSeleccion(rs.getInt("seleccionLocal"));
                match.setSeleccionLocal(local);
                match.getSeleccionLocal().getNombre();
                match.setSeleccionVisitante(vistante);
                match.getSeleccionVisitante().getNombre();
                Arbitro ar = obtenerArbitro(rs.getInt("arbitro"));
                match.setArbitro(ar);
                System.out.println(ar.getNombre());
                match.setFecha(rs.getString("fecha"));
                match.setNumeroJornada(rs.getInt("numeroJornada"));
                partidos.add(match);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return partidos;
    }

    public void crearPartido(Partido partido) {

        /*
        Inserte su código aquí
        */
    }
}

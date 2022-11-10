package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Arbitro;

import java.sql.*;
import java.util.ArrayList;

public class DaoArbitros extends DaoBase {
    public ArrayList<Arbitro> listarArbitros() {
        ArrayList<Arbitro> arbitros = new ArrayList<>();
        /*
        Inserte su código aquí

        */

        try (Connection conn = this.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM lab9.arbitro")) {
            while (rs.next()) {
                Arbitro arbi = new Arbitro();
                arbi.setIdArbitro(rs.getInt(1));
                arbi.setNombre(rs.getString(2));
                arbi.setPais(rs.getString(3));
                arbitros.add(arbi);
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arbitros;
    }

    public void crearArbitro(Arbitro arbitro) {
        /*
        Inserte su código aquí
        */

        String sql = "INSERT INTO arbitro (nombre, pais) " +
                "VALUES (?, ?)";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            //falta latitud y longitud
            pstmt.setString(1, arbitro.getNombre());
            pstmt.setString(2,arbitro.getPais());

            pstmt.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Arbitro> busquedaPais(String pais) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "select * from arbitro where pais like ?";
        try (Connection conn = this.getConnection();
             PreparedStatement stm = conn.prepareStatement(sql);) {
            stm.setString(1,"%"+pais+"%");
            try (ResultSet rs = stm.executeQuery();){
                while (rs.next()) {
                    Arbitro arbi = new Arbitro();
                    arbi.setIdArbitro(rs.getInt(1));
                    arbi.setNombre(rs.getString(2));
                    arbi.setPais(rs.getString(3));
                    arbitros.add(arbi);
                }
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arbitros;
    }

    public ArrayList<Arbitro> busquedaNombre(String nombre) {

        ArrayList<Arbitro> arbitros = new ArrayList<>();
        String sql = "select * from arbitro where nombre like ?";
        try (Connection conn1 = this.getConnection();
             PreparedStatement stm = conn1.prepareStatement(sql);) {
            stm.setString(1,"%"+nombre+"%");
            try (ResultSet rs = stm.executeQuery();){
                while (rs.next()) {
                    Arbitro arbi = new Arbitro();
                    arbi.setIdArbitro(rs.getInt(1));
                    arbi.setNombre(rs.getString(2));
                    arbi.setPais(rs.getString(3));
                    arbitros.add(arbi);
                }
            }

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return arbitros;
    }

    public Arbitro buscarArbitro(int id) {
        Arbitro arbitro = new Arbitro();
        /*
        Inserte su código aquí
        */
        return arbitro;
    }

    public void borrarArbitro(int id) {
        /*
        Inserte su código aquí
        */
        String sql = "DELETE FROM arbitro WHERE idArbitro = ?";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.example.lab9_base.Dao;
import com.example.lab9_base.Bean.Clientes;
import com.example.lab9_base.Bean.Credentials;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DaoCliente extends DaoBase{

    public Clientes buscarCliente(String id){
        Clientes client = new Clientes();
        String sql1 = "Select * from jm_client_bii where g4093_nro_id ="+id;
        try(Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs1 = stm.executeQuery(sql1)) {
            rs1.next();
            int idd = Integer.parseInt(id);
            client.setNombreCliente(rs1.getString("g4093_name"));
            client.setEdad(rs1.getString("g4093_age"));
            client.setTipoCliente(rs1.getString("g4093_type"));
            client.setTipoDocumento(rs1.getString("g4093_documentType"));
            client.setNombreDocumento(rs1.getString("g4093_nro_id"));
            if (Objects.equals(rs1.getString("g4093_type"), "N")){
                client.setNaju("Natural");
            } else if (Objects.equals(rs1.getString("g4093_type"), "J")) {
                client.setNaju("Juridica");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    public ArrayList<Clientes> listarClientes (){
        ArrayList<Clientes> listaClientes = new ArrayList<>();
        String sql = "select g4093_nro_id, g4093_name, g4093_age, g4093_type, g4093_documentType " +
                "from jm_client_bii" +
                "where g4093_nro_id Not in (Select nro_documento from credentials)";
        try (Connection conn = this.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()){
                Clientes cli = new Clientes();
                cli.setNombreCliente(rs.getString("g4093_name"));
                cli.setEdad(rs.getString("g4093_age"));
                cli.setTipoCliente(rs.getString("g4093_type"));
                cli.setTipoDocumento(rs.getString("g4093_documentType"));
                cli.setNombreDocumento(rs.getString("g4093_nro_id"));
                listaClientes.add(cli);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaClientes;
    }

    public float mostrarMaxExpectedLoss (String id){
        float expectedLoss = 0;
        String sql = "select * from jm_values " +
                "where substring(jm_cotr_bis_g6789,len(jm_cotr_bis_g6789)-1,"+
                id.length()+") = "+id;
        try(Connection con = this.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs1 = stm.executeQuery(sql)) {
            rs1.next();
            float valorpd = (rs1.getFloat("pd_value"));
            float valorlgd = (rs1.getFloat("lgd_value"));
            float tasa = (rs1.getFloat("recovery_rate"));
            expectedLoss = valorpd*valorlgd*(1-tasa);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return expectedLoss;
    }

    public void crearCredentialCliente(String user, String password){

        //Credentials credencial = new Credentials();
        String sql = "INSERT INTO credentials (nro_documento, password, hashedPassword, tipoUsuario) VALUES (?,?,sha2(?,256),?)";

        try (Connection connection =this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,user);
            pstmt.setString(2,password);
            pstmt.setString(3,password);
            pstmt.setInt(4,2);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    public Credentials buscarUsuario(String numeroDocumento, String password){

        Credentials credencial = new Credentials();
        String sql = "Select * from credentials where nro_documento=? and hashedPassword=sha(?,256)";
        try(Connection con = this.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);) {

            pstmt.setString(1, numeroDocumento) ;
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                        credencial.setNumeroDocumento(rs.getString(1));
                        credencial.setTipoUsuario(rs.getInt("tipoUsuario"));
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return credencial;
    }


}

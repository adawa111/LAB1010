package com.example.lab9_base.Dao;

import com.example.lab9_base.Bean.Contratos;
import com.example.lab9_base.Dtos.CantidadContratosDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DaoContrato extends DaoBase{

    public ArrayList<Contratos> listarContratos() {

        ArrayList<Contratos> listaContratos = new ArrayList<>();
        String sql = "SELECT g6789_contract, g6789_currency,g6789_months, \n" +
                "CASE\n" +
                "    WHEN G6789_status = '0' THEN \"normal\"\n" +
                "    WHEN G6789_status = '1' THEN \"cura\"\n" +
                "    when G6789_status = '2' THEN \"mora\"\n" +
                "    ELSE \"\"\n" +
                "END as estado\n" +
                "FROM bi_corp_business.jm_cotr_bis;";
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){

            while (rs.next()) {
                Contratos contrato = new Contratos();
                contrato.setNroDeContrato(rs.getString(1));
                contrato.setDivisa(rs.getString(2));
                contrato.setMesesEnEseEstado(rs.getInt(3));
                contrato.setEstado(rs.getInt(4));

                listaContratos.add(contrato);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContratos;

    }


    public ArrayList<CantidadContratosDto> mostrarContratosxEstado() {

        ArrayList<CantidadContratosDto> listaContraEstados = new ArrayList<>();
        String sql="SELECT client_nro_id,  \n" +
                "case when G6789_status='0' then 'Normal' when G6789_status='1' then 'cura' when G6789_status='2' then 'mora' else 'NA' end as 'EstadoContrato', count(G6789_status) as 'cantidad contratos' \n" +
                "FROM bi_corp_business.jm_cotr_bis contrato \n" +
                "inner join jm_values valores on contrato.g6789_contract = valores.jm_cotr_bis_g6789_cod_nup group by G6789_status;";

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){

            while (rs.next()) {
                Contratos contrato = new Contratos();
                CantidadContratosDto ccd = new CantidadContratosDto();
                contrato.setIdCliente(rs.getString(1));
                ccd.setEstado(rs.getString(2));
                ccd.setCantidadContrato(rs.getInt(3));

                listaContraEstados.add(ccd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContraEstados;



    }

}

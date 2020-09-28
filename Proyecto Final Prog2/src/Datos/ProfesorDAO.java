/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.ProfesorModelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Martin
 */
public class ProfesorDAO extends SQLQuery {

    private ArrayList<ProfesorModelo> profesores;
    private ProfesorModelo profesor;

    public ArrayList<ProfesorModelo> getProfesores() {
        profesores = new ArrayList<>();

        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("select * from profesor");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                profesor = new Modelo.ProfesorModelo();
                profesor.setDni(String.valueOf(hojadeResultados.getLong(1)));
                profesor.setNombre(hojadeResultados.getString(2));
                profesor.setApellido(hojadeResultados.getString(3));
                profesor.setFechaNacimiento(hojadeResultados.getString(4));
                profesor.setDomicilio(hojadeResultados.getString(5));
                profesor.setTelefono(hojadeResultados.getString(6));
                profesores.add(profesor);
            }
            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return profesores;

    }

    public void crearProfesor(ProfesorModelo profesor) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            datos = consulta.executeQuery();
            String sql = "INSERT INTO profesor (prof_dni, prof_nombre, prof_apellido, prof_fec_nac, prof_domicilio, prof_telefono) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(sql);
            preparedStmt.setLong(1, profesor.getDni());
            preparedStmt.setString(2, profesor.getNombre());
            preparedStmt.setString(3, profesor.getApellido());
            preparedStmt.setString(4, profesor.getFechaNacimiento());
            preparedStmt.setString(5, profesor.getDomicilio());
            preparedStmt.setString(6, profesor.getTelefono());
            preparedStmt.execute();
            desconectar();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {

            if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "DNI inválido: DNI ya existe");
            } else if (ex.getSQLState().equals("22001")) {
                JOptionPane.showMessageDialog(null, "Fecha inválida. Use formato AAAA-MM-DD");
            }
        }
    }

    public void modificarProfesor(ProfesorModelo profesor) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE profesor SET prof_nombre=?, prof_apellido=?, prof_fec_nac=? ,prof_domicilio=?, prof_telefono=? WHERE prof_dni=?");
            preparedStmt.setString(1, profesor.getNombre());
            preparedStmt.setString(2, profesor.getApellido());
            preparedStmt.setString(3, profesor.getFechaNacimiento());
            preparedStmt.setString(4, profesor.getDomicilio());
            preparedStmt.setString(5, profesor.getTelefono());
            preparedStmt.setLong(6, profesor.getDni());
            preparedStmt.executeUpdate();
            desconectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

            //System.out.println(ex.getMessage());
            if (ex.getSQLState().equals("22001")) {
                JOptionPane.showMessageDialog(null, "Fecha inválida. Use formato AAAA-MM-DD");
            }
        }
    }

    public void eliminarProfesor(ProfesorModelo profesor) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("DELETE FROM profesor WHERE prof_dni = ?");
            consulta.setLong(1, profesor.getDni());
            consulta.executeUpdate();

            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.CursadoModelo;
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
public class CursadoDAO extends SQLQuery {

    private ArrayList<CursadoModelo> cursados;
    private CursadoModelo cursado;

    public ArrayList<CursadoModelo> getCursados() {
        cursados = new ArrayList<>();

        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("select * from cursado");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                cursado = new CursadoModelo();
                cursado.setDniAlumno(String.valueOf(hojadeResultados.getLong(1)));
                cursado.setCodMat(String.valueOf(hojadeResultados.getLong(2)));
                cursado.setNota(String.valueOf(hojadeResultados.getLong(3)));
                cursados.add(cursado);
            }
            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex);

        }

        return cursados;

    }

    public void crearCursado(CursadoModelo cursado) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            datos = consulta.executeQuery();
            String sql = "INSERT INTO cursado (cur_alu_dni, cur_mat_cod, cur_nota) VALUES (?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(sql);
            preparedStmt.setLong(1, cursado.getDniAlumno());
            preparedStmt.setLong(2, cursado.getCodMat());
            preparedStmt.setLong(3, cursado.getNota());
            preparedStmt.execute();
            desconectar();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());            
            if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Combinación de DNI y código de materia inválida: Ya existe");
            } else if (ex.getMessage().contains("foreign key")) {
                if (ex.getMessage().contains("cur_mat_cod")) {
                    JOptionPane.showMessageDialog(null, "Código de materia inválido: No existe");
                } else if (ex.getMessage().contains("cur_alu_dni")) {
                    JOptionPane.showMessageDialog(null, "DNI de alumno inválido: No existe");
                }
            }
        }
    }

    public void modificarCursado(CursadoModelo cursado) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE cursado SET cur_nota=? WHERE cur_alu_dni=? AND cur_mat_cod=?");
            preparedStmt.setLong(1, cursado.getNota());
            preparedStmt.setLong(2, cursado.getDniAlumno());
            preparedStmt.setLong(3, cursado.getCodMat());
            preparedStmt.executeUpdate();
            desconectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

        }
    }

    public void eliminarCursado(CursadoModelo cursado) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("DELETE FROM cursado WHERE cur_alu_dni = ? AND cur_mat_cod=?");
            consulta.setLong(1, cursado.getDniAlumno());
            consulta.setLong(2, cursado.getCodMat());
            consulta.executeUpdate();

            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

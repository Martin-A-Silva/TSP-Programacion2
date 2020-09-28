/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.MateriaModelo;
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
public class MateriaDAO extends SQLQuery {

    private ArrayList<MateriaModelo> materias;
    private MateriaModelo materia;

    public ArrayList<MateriaModelo> getMaterias() {
        materias = new ArrayList<>();

        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("select * from materia");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                materia = new MateriaModelo();
                materia.setCod(String.valueOf(hojadeResultados.getLong(1)));
                materia.setNombre(hojadeResultados.getString(2));
                materia.setDniProf(String.valueOf(hojadeResultados.getLong(3)));
                materias.add(materia);
            }
            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex);

        }

        return materias;

    }

    public void crearMateria(MateriaModelo materia) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            datos = consulta.executeQuery();
            String sql = "INSERT INTO materia (mat_cod, mat_nombre, mat_prof_dni) VALUES (?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(sql);
            preparedStmt.setLong(1, materia.getCod());
            preparedStmt.setString(2, materia.getNombre());
            preparedStmt.setLong(3, materia.getDniProf());
            preparedStmt.execute();
            desconectar();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            //System.out.println(ex.getSQLState());
            if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Código de materia inválido: Ya existe");
            } else if (ex.getMessage().contains("foreign key")) {
                JOptionPane.showMessageDialog(null, "DNI de profesor inválido: No existe");
            }
        }
    }

    public void modificarMateria(MateriaModelo materia) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE materia SET mat_nombre=?, mat_prof_dni=? WHERE mat_cod=?");
            preparedStmt.setString(1, materia.getNombre());
            preparedStmt.setLong(2, materia.getDniProf());
            preparedStmt.setLong(3, materia.getCod());
            preparedStmt.executeUpdate();
            desconectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

            if (ex.getMessage().contains("foreign key")) {
                JOptionPane.showMessageDialog(null, "DNI de profesor inválido: No existe");
            }
        }
    }

    public void eliminarMateria(MateriaModelo materia) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("DELETE FROM materia WHERE mat_cod = ?");
            consulta.setLong(1, materia.getCod());
            consulta.executeUpdate();

            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

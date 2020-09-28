/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.AlumnoModelo;
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
public class AlumnoDAO extends SQLQuery {

    private ArrayList<AlumnoModelo> alumnos;
    private AlumnoModelo alumno;

    public ArrayList<AlumnoModelo> getAlumnos() {
        alumnos = new ArrayList<>();

        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("select * from alumno");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                alumno = new Modelo.AlumnoModelo();
                alumno.setDni(String.valueOf(hojadeResultados.getLong(1)));
                alumno.setNombre(hojadeResultados.getString(2));
                alumno.setApellido(hojadeResultados.getString(3));
                alumno.setFechaNacimiento(hojadeResultados.getString(4));
                alumno.setDomicilio(hojadeResultados.getString(5));
                alumno.setTelefono(hojadeResultados.getString(6));
                alumno.setInscCod(String.valueOf(hojadeResultados.getLong(7)));
                alumnos.add(alumno);
            }
            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return alumnos;

    }

    public void crearAlumno(AlumnoModelo alumno) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            datos = consulta.executeQuery();
            String sql = "INSERT INTO alumno (alu_dni, alu_nombre, alu_apellido, alu_fec_nac, alu_domicilio, alu_telefono, alu_insc_cod) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(sql);
            preparedStmt.setLong(1, alumno.getDni());
            preparedStmt.setString(2, alumno.getNombre());
            preparedStmt.setString(3, alumno.getApellido());
            preparedStmt.setString(4, alumno.getFechaNacimiento());
            preparedStmt.setString(5, alumno.getDomicilio());
            preparedStmt.setString(6, alumno.getTelefono());
            preparedStmt.setLong(7, alumno.getInscCod());
            preparedStmt.execute();
            desconectar();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {

            if (ex.getMessage().contains("foreign key")) {

                JOptionPane.showMessageDialog(null, "Código de inscripción inválido: No existe");
            } else if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "DNI inválido: DNI ya existe");
            } else if (ex.getSQLState().equals("22001")) {
                JOptionPane.showMessageDialog(null, "Fecha inválida. Use formato AAAA-MM-DD");
            }
        }
    }

    public void modificarAlumno(AlumnoModelo alumno) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE Alumno SET alu_nombre=?, alu_apellido=?, alu_fec_nac=? ,alu_domicilio=?, alu_telefono=?, alu_insc_cod=? WHERE alu_dni=?");
            preparedStmt.setString(1, alumno.getNombre());
            preparedStmt.setString(2, alumno.getApellido());
            preparedStmt.setString(3, alumno.getFechaNacimiento());
            preparedStmt.setString(4, alumno.getDomicilio());
            preparedStmt.setString(5, alumno.getTelefono());
            preparedStmt.setLong(6, alumno.getInscCod());
            preparedStmt.setLong(7, alumno.getDni());
            preparedStmt.executeUpdate();
            desconectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

            //System.out.println(ex.getMessage());
            if (ex.getMessage().contains("foreign key")) {
                JOptionPane.showMessageDialog(null, "Código de inscripción inválido: No existe");
            } else if (ex.getSQLState().equals("22001")) {
                JOptionPane.showMessageDialog(null, "Fecha inválida. Use formato AAAA-MM-DD");
            }

        }
    }

    public void eliminarAlumno(AlumnoModelo alumno) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("DELETE FROM Alumno WHERE alu_dni = ?");
            consulta.setLong(1, alumno.getDni());
            consulta.executeUpdate();

            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

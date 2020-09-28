/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.InscripcionModelo;
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
public class InscripcionDAO extends SQLQuery {

    private ArrayList<InscripcionModelo> inscripciones;
    private InscripcionModelo inscripcion;

    public ArrayList<InscripcionModelo> getInscripciones() {
        inscripciones = new ArrayList<>();

        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("select * from inscripcion");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                inscripcion = new InscripcionModelo();
                inscripcion.setCod(String.valueOf(hojadeResultados.getLong(1)));
                inscripcion.setNombre(hojadeResultados.getString(2));
                inscripcion.setFecha(hojadeResultados.getString(3));
                inscripcion.setCarCod(String.valueOf(hojadeResultados.getLong(4)));
                inscripciones.add(inscripcion);
            }
            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex);

        }

        return inscripciones;

    }

    public void crearInscripcion(InscripcionModelo inscripcion) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            datos = consulta.executeQuery();
            String sql = "INSERT INTO inscripcion (insc_cod, insc_nombre, insc_fecha, insc_car_cod) VALUES (?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(sql);
            preparedStmt.setLong(1, inscripcion.getCod());
            preparedStmt.setString(2, inscripcion.getNombre());
            preparedStmt.setString(3, inscripcion.getFecha());
            preparedStmt.setLong(4, inscripcion.getCarCod());
            preparedStmt.execute();
            desconectar();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            if (ex.getMessage().contains("foreign key")) {
                JOptionPane.showMessageDialog(null, "Código de carrera inválido: No existe");
            } else if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Código de inscripción inválido: Código ya existe");
            } else if (ex.getSQLState().equals("22001")) {
                JOptionPane.showMessageDialog(null, "Fecha inválida. Use formato AAAA-MM-DD");
            }
        }
    }

    public void modificarInscripcion(InscripcionModelo inscripcion) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE Inscripcion SET insc_nombre=?, insc_fecha=?, insc_car_cod=? WHERE insc_cod=?");
            preparedStmt.setString(1, inscripcion.getNombre());
            preparedStmt.setString(2, inscripcion.getFecha());
            preparedStmt.setLong(3, inscripcion.getCarCod());
            preparedStmt.setLong(4, inscripcion.getCod());
            preparedStmt.executeUpdate();
            desconectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            //System.out.println(ex.getMessage());            
            if (ex.getMessage().contains("foreign key")) {
                JOptionPane.showMessageDialog(null, "Código de carrera inválido: No existe");
            } else if (ex.getSQLState().equals("22001")) {
                JOptionPane.showMessageDialog(null, "Fecha inválida. Use formato AAAA-MM-DD");
            }
        }
    }

    public void eliminarInscripcion(InscripcionModelo inscripcion) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("DELETE FROM Inscripcion WHERE insc_cod = ?");
            consulta.setLong(1, inscripcion.getCod());
            consulta.executeUpdate();

            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.CarreraModelo;
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
public class CarreraDAO extends SQLQuery {

    private ArrayList<CarreraModelo> carreras;
    private CarreraModelo carrera;

    public ArrayList<CarreraModelo> getCarreras() {
        carreras = new ArrayList<>();

        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("select * from carrera");
            ResultSet hojadeResultados = consulta.executeQuery();

            while (hojadeResultados.next()) {
                carrera = new CarreraModelo();
                carrera.setCod(String.valueOf(hojadeResultados.getLong(1)));
                carrera.setNombre(hojadeResultados.getString(2));
                carrera.setDuracion(String.valueOf(hojadeResultados.getLong(3)));
                carreras.add(carrera);
            }
            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println(ex);

        }

        return carreras;

    }

    public void crearCarrera(CarreraModelo carrera) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            datos = consulta.executeQuery();
            String sql = "INSERT INTO carrera (car_cod, car_nombre, car_duracion) VALUES (?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(sql);
            preparedStmt.setLong(1, carrera.getCod());
            preparedStmt.setString(2, carrera.getNombre());
            preparedStmt.setLong(3, carrera.getDuracion());
            preparedStmt.execute();
            desconectar();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            //System.out.println(ex.getSQLState());
            if (ex.getMessage().contains("Duplicate entry")) {
                JOptionPane.showMessageDialog(null, "Código de carrera inválido: Ya existe");
            }

        }
    }

    public void modificarCarrera(CarreraModelo carrera) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement("UPDATE carrera SET car_nombre=?, car_duracion=? WHERE car_cod=?");
            preparedStmt.setString(1, carrera.getNombre());
            preparedStmt.setLong(2, carrera.getDuracion());
            preparedStmt.setLong(3, carrera.getCod());
            preparedStmt.executeUpdate();
            desconectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

        }
    }

    public void eliminarCarrera(CarreraModelo carrera) {
        try {
            conectar("localhost", "sga_2020", "root", "mysql");
            consulta = conn.prepareStatement("DELETE FROM carrera WHERE car_cod = ?");
            consulta.setLong(1, carrera.getCod());
            consulta.executeUpdate();

            desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

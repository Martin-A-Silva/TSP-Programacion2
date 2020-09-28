/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.CarreraDAO;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class CarreraModelo {

    private long cod;
    private String nombre;
    private long duracion;

    private CarreraDAO carreraDAO = new CarreraDAO();

    public long getCod() {
        return cod;
    }

    public void setCod(String cod) throws Exception {

        try {
            this.cod = Long.parseLong(cod);
        } catch (Exception ex) {
            throw new Exception("Debe ingresar un código de inscripción");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) throws Exception {
        try {
            this.duracion = Long.parseLong(duracion);
        } catch (NumberFormatException ex) {
            throw new Exception("Debe ingresar con números la duración en años");
        }

    }

    public CarreraDAO getCarreraDAO() {
        return carreraDAO;
    }

    public void setCarreraDAO(CarreraDAO carreraDAO) {
        this.carreraDAO = carreraDAO;
    }

    public ArrayList<Modelo.CarreraModelo> getCarreras() {
        return carreraDAO.getCarreras();
    }

    public void crearCarrera() throws Exception {
        if (nombre.equals("")) {
            throw new Exception("Debe ingresar un nombre");
        } else {
            carreraDAO.crearCarrera(this);
        }

    }

    public void modificarCarrera() throws Exception {
        if (nombre.equals("")) {
            throw new Exception("Debe ingresar un nombre");
        } else {
            carreraDAO.modificarCarrera(this);
        }

    }

    public void eliminarCarrera() {
        carreraDAO.eliminarCarrera(this);
    }
}

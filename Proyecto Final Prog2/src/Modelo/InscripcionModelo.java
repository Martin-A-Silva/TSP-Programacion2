/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.InscripcionDAO;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class InscripcionModelo {

    private long cod;
    private String nombre;
    private String fecha;
    private long carCod;
    private InscripcionDAO inscripcionDAO = new InscripcionDAO();

    public long getCod() {
        return cod;
    }

    public void setCod(String cod) throws Exception {
        try {
            this.cod = Long.parseLong(cod);
        } catch (NumberFormatException ex) {
            throw new Exception("Debe ingresar un código de inscripción válido");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getCarCod() {
        return carCod;
    }

    public void setCarCod(String carCod) throws Exception {
        try {
            this.carCod = Long.parseLong(carCod);
        } catch (NumberFormatException ex) {
            throw new Exception("Debe ingresar un código de carrera válido");
        }
    }

    public ArrayList<Modelo.InscripcionModelo> getInscripciones() {
        return inscripcionDAO.getInscripciones();
    }

    public void crearInscripcion() throws Exception {
        if (nombre.equals("")) {
            throw new Exception("Debe ingresar un nombre");
        } else {
            inscripcionDAO.crearInscripcion(this);
        }

    }

    public void modificarInscripcion() throws Exception {
        if (nombre.equals("")) {
            throw new Exception("Debe ingresar un nombre");
        } else {
            inscripcionDAO.modificarInscripcion(this);
        }

    }

    public void eliminarInscripcion() {
        inscripcionDAO.eliminarInscripcion(this);
    }
}

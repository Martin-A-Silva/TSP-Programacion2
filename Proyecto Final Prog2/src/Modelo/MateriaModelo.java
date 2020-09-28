/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.MateriaDAO;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class MateriaModelo {

    private long cod;
    private String nombre;
    private long dniProf;

    private MateriaDAO materiaDAO = new MateriaDAO();

    public long getCod() {
        return cod;
    }

    public void setCod(String cod) throws Exception {

        try {
            this.cod = Long.parseLong(cod);
        } catch (Exception ex) {
            throw new Exception("Debe ingresar un código de materia");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDniProf() {
        return dniProf;
    }

    public void setDniProf(String dniProf) throws Exception {
        try {
            this.dniProf = Long.parseLong(dniProf);
        } catch (NumberFormatException ex) {
            throw new Exception("Debe ingresar el DNI del profesor");
        }

    }

    public MateriaDAO getMateriaDAO() {
        return materiaDAO;
    }

    public void setMateriaDAO(MateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }

    public ArrayList<Modelo.MateriaModelo> getMaterias() {
        return materiaDAO.getMaterias();
    }

    public void crearMateria() throws Exception {
        if (nombre.equals("")) {
            throw new Exception("Debe ingresar un nombre");
        } else {
            materiaDAO.crearMateria(this);
        }

    }

    public void modificarMateria() throws Exception {
        if (nombre.equals("")) {
            throw new Exception("Debe ingresar un nombre");
        } else {
            materiaDAO.modificarMateria(this);
        }

    }

    public void eliminarMateria() {
        materiaDAO.eliminarMateria(this);
    }
}

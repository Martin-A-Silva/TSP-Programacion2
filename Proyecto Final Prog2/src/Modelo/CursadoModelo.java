/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.CursadoDAO;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class CursadoModelo {

    private long dniAlumno;
    private long codMat;
    private long nota;

    private CursadoDAO cursadoDAO = new CursadoDAO();

    public long getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(String dniAlumno) throws Exception {

        try {
            this.dniAlumno = Long.parseLong(dniAlumno);
        } catch (Exception ex) {
            throw new Exception("Debe ingresar un DNI de alumno");
        }
    }

    public long getCodMat() {
        return codMat;
    }

    public void setCodMat(String codMat) throws Exception {

        try {
            this.codMat = Long.parseLong(codMat);
        } catch (Exception ex) {
            throw new Exception("Debe ingresar un código de materia");
        }
    }

    public long getNota() {
        return nota;
    }

    public void setNota(String nota) {
        if (!nota.equals("")) {           //la nota la considero opcional
            this.nota = Long.parseLong(nota);
        }
    }

    public ArrayList<Modelo.CursadoModelo> getCursados() {
        return cursadoDAO.getCursados();
    }

    public void crearCursado() {
        cursadoDAO.crearCursado(this);
    }

    public void modificarCursado() throws Exception {
        cursadoDAO.modificarCursado(this);
    }

    public void eliminarCursado() {
        cursadoDAO.eliminarCursado(this);
    }
}

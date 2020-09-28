/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.AlumnoDAO;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class AlumnoModelo {

    private long dni;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String domicilio;
    private String telefono;
    private long inscCod;
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    public ArrayList<Modelo.AlumnoModelo> getAlumnos() {
        return alumnoDAO.getAlumnos();
    }

    public long getDni() {
        return dni;
    }

    public void setDni(String dni) throws Exception {
        try {
            this.dni = Long.parseLong(dni);
        } catch (NumberFormatException ex) {
            throw new Exception("Debe ingresar un DNI válido");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public long getInscCod() {
        return inscCod;
    }

    public void setInscCod(String inscCod) throws Exception {

        try {
            this.inscCod = Long.parseLong(inscCod);
        } catch (NumberFormatException ex) {
            throw new Exception("Debe ingresar un código de inscripción válido");
        }
    }

    public void crearAlumno() throws Exception {
        if (nombre.equals("")) {

            throw new Exception("Debe ingresar un nombre");
        }
        if (apellido.equals("")) {

            throw new Exception("Debe ingresar un apellido");
        }
        alumnoDAO.crearAlumno(this);
    }

    public void modificarAlumno() throws Exception {
        if (nombre.equals("")) {

            throw new Exception("Debe ingresar un nombre");
        }
        if (apellido.equals("")) {

            throw new Exception("Debe ingresar un apellido");
        }
        alumnoDAO.modificarAlumno(this);
    }

    public void eliminarAlumno() {
        alumnoDAO.eliminarAlumno(this);
    }
}

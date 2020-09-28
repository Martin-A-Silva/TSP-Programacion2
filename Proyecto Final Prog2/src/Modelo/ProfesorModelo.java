/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.ProfesorDAO;
import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public class ProfesorModelo {

    private long dni;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String domicilio;
    private String telefono;
    private ProfesorDAO profesorDAO = new ProfesorDAO();

    public ArrayList<Modelo.ProfesorModelo> getProfesores() {
        return profesorDAO.getProfesores();
    }

    public long getDni() {
        return dni;
    }

    public void setDni(String dni) throws Exception {
        if (dni.equals("")) { //Esta verificacion la hago acá porque parseLong no admite cadenas vacias
            throw new Exception("Debe ingresar un DNI");
        }
        this.dni = Long.parseLong(dni);

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

    public void crearProfesor() throws Exception {
        if (nombre.equals("")) {
            //JOptionPane.showMessageDialog(null, "Debe insertar un nombre");
            throw new Exception("Debe ingresar un nombre");
        }
        if (apellido.equals("")) {
            //JOptionPane.showMessageDialog(null, "Debe insertar un apellido");
            throw new Exception("Debe ingresar un apellido");
        }
        profesorDAO.crearProfesor(this);
    }

    public void modificarProfesor() throws Exception {
        if (nombre.equals("")) {
            //JOptionPane.showMessageDialog(null, "Debe insertar un nombre");
            throw new Exception("Debe ingresar un nombre");
        }
        if (apellido.equals("")) {
            //JOptionPane.showMessageDialog(null, "Debe insertar un apellido");
            throw new Exception("Debe ingresar un apellido");
        }
        profesorDAO.modificarProfesor(this);
    }

    public void eliminarProfesor() {
        profesorDAO.eliminarProfesor(this);
    }
}

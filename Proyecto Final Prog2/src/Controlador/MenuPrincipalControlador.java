/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Martin
 */
public class MenuPrincipalControlador implements ActionListener {

    MenuPrincipalVista vista;

    public MenuPrincipalControlador(MenuPrincipalVista menu) {
        this.vista = menu;

        vista.setVisible(true);
        vista.setLocationRelativeTo(null);

        vista.jBtnAlu.addActionListener(this);
        vista.jBtnCar.addActionListener(this);
        vista.jBtnCurs.addActionListener(this);
        vista.jBtnInsc.addActionListener(this);
        vista.jBtnMat.addActionListener(this);
        vista.jBtnProf.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.jBtnAlu)) {
            vista.setVisible(false);
            new AlumnoControlador(new AlumnoVista(), new AlumnoModelo(), vista);
        } else if (e.getSource().equals(vista.jBtnCar)) {
            vista.setVisible(false);
            new CarreraControlador(new CarreraVista(), new CarreraModelo(), vista);
        } else if (e.getSource().equals(vista.jBtnCurs)) {
            vista.setVisible(false);
            new CursadoControlador(new CursadoVista(), new CursadoModelo(), vista);
        } else if (e.getSource().equals(vista.jBtnInsc)) {
            vista.setVisible(false);
            new InscripcionControlador(new InscripcionVista(), new InscripcionModelo(), vista);
        } else if (e.getSource().equals(vista.jBtnMat)) {
            vista.setVisible(false);
            new MateriaControlador(new MateriaVista(), new MateriaModelo(), vista);
        } else if (e.getSource().equals(vista.jBtnProf)) {
            vista.setVisible(false);
            new ProfesorControlador(new ProfesorVista(), new ProfesorModelo(), vista);
        }
    }

}

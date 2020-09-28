/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CursadoModelo;
import Vista.CursadoVista;
import Vista.MenuPrincipalVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Martin
 */
public class CursadoControlador implements ActionListener, MouseListener {

    CursadoVista vista;
    CursadoModelo modelo;
    private MenuPrincipalVista vistaPpal;

    public CursadoControlador(CursadoVista vista, CursadoModelo modelo, MenuPrincipalVista vistaPpal) {
        this.vista = vista;
        this.modelo = modelo;
        this.vistaPpal = vistaPpal;

        this.vista.setVisible(true);
        this.vista.setLocationRelativeTo(null);
        refrescarTabla(this.vista.jTable1);

        this.vista.jBtnNuevo.addActionListener(this);
        this.vista.jBtnModificar.addActionListener(this);
        this.vista.jBtnEliminar.addActionListener(this);
        this.vista.jBtnVolver.addActionListener(this);
        this.vista.jTable1.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.jBtnNuevo)) {
            if (vista.jTxtFieldDniAlumno.isEditable()) {
                try {

                    modelo.setDniAlumno(vista.jTxtFieldDniAlumno.getText());
                    modelo.setCodMat(vista.jTxtFieldCodMat.getText());
                    modelo.setNota(vista.jTxtFieldNota.getText());
                    modelo.crearCursado();
                    refrescarTabla(vista.jTable1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                vista.jTxtFieldDniAlumno.setEditable(true);
                vista.jTxtFieldDniAlumno.setText("");
                vista.jTxtFieldCodMat.setEditable(true);
                vista.jTxtFieldCodMat.setText("");
                vista.jTxtFieldNota.setText("");
            }

        } else if (e.getSource().equals(vista.jBtnModificar)) {
            if (vista.jTxtFieldDniAlumno.isEditable()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista");
            } else {
                try {

                    modelo.setDniAlumno(vista.jTxtFieldDniAlumno.getText());
                    modelo.setCodMat(vista.jTxtFieldCodMat.getText());
                    modelo.setNota(vista.jTxtFieldNota.getText());
                    modelo.modificarCursado();
                    refrescarTabla(vista.jTable1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else if (e.getSource().equals(vista.jBtnEliminar)) {
            if (vista.jTxtFieldDniAlumno.isEditable()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista");
            } else {
                try {

                    modelo.setDniAlumno(vista.jTxtFieldDniAlumno.getText());
                    modelo.eliminarCursado();
                    vista.jTxtFieldDniAlumno.setEditable(true);
                    vista.jTxtFieldDniAlumno.setText("");
                    vista.jTxtFieldCodMat.setEditable(true);
                    vista.jTxtFieldCodMat.setText("");
                    vista.jTxtFieldNota.setText("");
                    refrescarTabla(vista.jTable1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else if (e.getSource().equals(vista.jBtnVolver)) {
            vista.dispose();
            vistaPpal.setVisible(true);
        }
    }

    public final void refrescarTabla(JTable tabla) {

        ArrayList<CursadoModelo> inscripciones;
        inscripciones = modelo.getCursados();
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"DNI de Alumno", "Código de materia", "Nota"}, inscripciones.size());
        tabla.setModel(modeloTabla);
        if (inscripciones.size() > 0) {
            for (int i = 0; i < inscripciones.size(); i++) {
                tabla.setValueAt(inscripciones.get(i).getDniAlumno(), i, 0);
                tabla.setValueAt(inscripciones.get(i).getCodMat(), i, 1);
                tabla.setValueAt(inscripciones.get(i).getNota(), i, 2);
            }
        }
        inscripciones.clear();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = vista.jTable1.rowAtPoint(e.getPoint());
            if (fila > -1) {
                vista.jTxtFieldDniAlumno.setText(String.valueOf(vista.jTable1.getValueAt(fila, 0)));
                vista.jTxtFieldDniAlumno.setEditable(false);
                vista.jTxtFieldCodMat.setText(String.valueOf(vista.jTable1.getValueAt(fila, 1)));
                vista.jTxtFieldCodMat.setEditable(false);
                vista.jTxtFieldNota.setText(String.valueOf(vista.jTable1.getValueAt(fila, 2)));

            }
        }
    }

    //<editor-fold defaultstate="collapsed" desc="comment">
    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
//</editor-fold>
}

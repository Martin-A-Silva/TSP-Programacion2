/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.MateriaModelo;
import Vista.MateriaVista;
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
public class MateriaControlador implements ActionListener, MouseListener {

    MateriaVista vista;
    MateriaModelo modelo;
    private MenuPrincipalVista vistaPpal;

    public MateriaControlador(MateriaVista vista, MateriaModelo modelo, MenuPrincipalVista vistaPpal) {
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
            if (vista.jTxtFieldCodMat.isEditable()) {
                try {

                    modelo.setCod(vista.jTxtFieldCodMat.getText());
                    modelo.setNombre(vista.jTxtFieldNombre.getText());
                    modelo.setDniProf(vista.jTxtFieldDniProf.getText());
                    modelo.crearMateria();
                    refrescarTabla(vista.jTable1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                vista.jTxtFieldCodMat.setEditable(true);
                vista.jTxtFieldCodMat.setText("");
                vista.jTxtFieldNombre.setText("");
                vista.jTxtFieldDniProf.setText("");
            }

        } else if (e.getSource().equals(vista.jBtnModificar)) {
            if (vista.jTxtFieldCodMat.isEditable()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista");
            } else {
                try {

                    modelo.setCod(vista.jTxtFieldCodMat.getText());
                    modelo.setNombre(vista.jTxtFieldNombre.getText());
                    modelo.setDniProf(vista.jTxtFieldDniProf.getText());
                    modelo.modificarMateria();
                    refrescarTabla(vista.jTable1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else if (e.getSource().equals(vista.jBtnEliminar)) {
            if (vista.jTxtFieldCodMat.isEditable()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista");
            } else {
                try {

                    modelo.setCod(vista.jTxtFieldCodMat.getText());
                    modelo.eliminarMateria();
                    vista.jTxtFieldCodMat.setEditable(true);
                    vista.jTxtFieldCodMat.setText("");
                    vista.jTxtFieldNombre.setText("");
                    vista.jTxtFieldDniProf.setText("");
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

        ArrayList<MateriaModelo> inscripciones;
        inscripciones = modelo.getMaterias();
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Código de Materia", "Nombre", "DNI del profesor"}, inscripciones.size());
        tabla.setModel(modeloTabla);
        if (inscripciones.size() > 0) {
            for (int i = 0; i < inscripciones.size(); i++) {
                tabla.setValueAt(inscripciones.get(i).getCod(), i, 0);
                tabla.setValueAt(inscripciones.get(i).getNombre(), i, 1);
                tabla.setValueAt(inscripciones.get(i).getDniProf(), i, 2);
            }
        }
        inscripciones.clear();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = vista.jTable1.rowAtPoint(e.getPoint());
            if (fila > -1) {
                vista.jTxtFieldCodMat.setText(String.valueOf(vista.jTable1.getValueAt(fila, 0)));
                vista.jTxtFieldCodMat.setEditable(false);
                vista.jTxtFieldNombre.setText(String.valueOf(vista.jTable1.getValueAt(fila, 1)));
                vista.jTxtFieldDniProf.setText(String.valueOf(vista.jTable1.getValueAt(fila, 2)));

            }
        }
    }

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
}

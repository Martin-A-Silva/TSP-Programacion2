/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.CarreraModelo;
import Vista.CarreraVista;
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
public class CarreraControlador implements ActionListener, MouseListener {

    CarreraVista vista;
    CarreraModelo modelo;
    private MenuPrincipalVista vistaPpal;

    public CarreraControlador(CarreraVista vista, CarreraModelo modelo, MenuPrincipalVista vistaPpal) {
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
            if (vista.jTxtFieldCodCar.isEditable()) {
                try {

                    modelo.setCod(vista.jTxtFieldCodCar.getText());
                    modelo.setNombre(vista.jTxtFieldNombre.getText());
                    modelo.setDuracion(vista.jTxtFieldDuracion.getText());
                    modelo.crearCarrera();
                    refrescarTabla(vista.jTable1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                vista.jTxtFieldCodCar.setEditable(true);
                vista.jTxtFieldCodCar.setText("");
                vista.jTxtFieldNombre.setText("");
                vista.jTxtFieldDuracion.setText("");
            }

        } else if (e.getSource().equals(vista.jBtnModificar)) {
            if (vista.jTxtFieldCodCar.isEditable()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista");
            } else {
                try {

                    modelo.setCod(vista.jTxtFieldCodCar.getText());
                    modelo.setNombre(vista.jTxtFieldNombre.getText());
                    modelo.setDuracion(vista.jTxtFieldDuracion.getText());
                    modelo.modificarCarrera();
                    refrescarTabla(vista.jTable1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else if (e.getSource().equals(vista.jBtnEliminar)) {
            if (vista.jTxtFieldCodCar.isEditable()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista");
            } else {
                try {

                    modelo.setCod(vista.jTxtFieldCodCar.getText());
                    modelo.eliminarCarrera();
                    vista.jTxtFieldCodCar.setEditable(true);
                    vista.jTxtFieldCodCar.setText("");
                    vista.jTxtFieldNombre.setText("");
                    vista.jTxtFieldDuracion.setText("");
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

        ArrayList<CarreraModelo> inscripciones;
        inscripciones = modelo.getCarreras();
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Código de Carrera", "Nombre", "Duración"}, inscripciones.size());
        tabla.setModel(modeloTabla);
        if (inscripciones.size() > 0) {
            for (int i = 0; i < inscripciones.size(); i++) {
                tabla.setValueAt(inscripciones.get(i).getCod(), i, 0);
                tabla.setValueAt(inscripciones.get(i).getNombre(), i, 1);
                tabla.setValueAt(inscripciones.get(i).getDuracion(), i, 2);
            }
        }
        inscripciones.clear();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = vista.jTable1.rowAtPoint(e.getPoint());
            if (fila > -1) {
                vista.jTxtFieldCodCar.setText(String.valueOf(vista.jTable1.getValueAt(fila, 0)));
                vista.jTxtFieldCodCar.setEditable(false);
                vista.jTxtFieldNombre.setText(String.valueOf(vista.jTable1.getValueAt(fila, 1)));
                vista.jTxtFieldDuracion.setText(String.valueOf(vista.jTable1.getValueAt(fila, 2)));

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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ProfesorModelo;
import Vista.MenuPrincipalVista;
import Vista.ProfesorVista;
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
public class ProfesorControlador implements ActionListener, MouseListener {

    ProfesorVista vista;
    ProfesorModelo modelo;
    private MenuPrincipalVista vistaPpal;

    public ProfesorControlador(ProfesorVista vista, ProfesorModelo modelo, MenuPrincipalVista vistaPpal) {
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
            if (vista.jTxtFieldDNI.isEditable()) {
                try {

                    modelo.setDni(vista.jTxtFieldDNI.getText());
                    modelo.setNombre(vista.jTxtFieldNombre.getText());
                    modelo.setApellido(vista.jTxtFieldApellido.getText());
                    modelo.setFechaNacimiento(vista.jTxtFieldFecNac.getText());
                    modelo.setDomicilio(vista.jTxtFieldDom.getText());
                    modelo.setTelefono(vista.jTxtFieldTel.getText());
                    modelo.crearProfesor();
                    refrescarTabla(vista.jTable1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                vista.jTxtFieldDNI.setEditable(true);
                vista.jTxtFieldDNI.setText("");
                vista.jTxtFieldNombre.setText("");
                vista.jTxtFieldApellido.setText("");
                vista.jTxtFieldFecNac.setText("");
                vista.jTxtFieldDom.setText("");
                vista.jTxtFieldTel.setText("");
            }

        } else if (e.getSource().equals(vista.jBtnModificar)) {
            if (vista.jTxtFieldDNI.isEditable()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista");
            } else {
                try {

                    modelo.setDni(vista.jTxtFieldDNI.getText());
                    modelo.setNombre(vista.jTxtFieldNombre.getText());
                    modelo.setApellido(vista.jTxtFieldApellido.getText());
                    modelo.setFechaNacimiento(vista.jTxtFieldFecNac.getText());
                    modelo.setDomicilio(vista.jTxtFieldDom.getText());
                    modelo.setTelefono(vista.jTxtFieldTel.getText());
                    modelo.modificarProfesor();
                    refrescarTabla(vista.jTable1);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else if (e.getSource().equals(vista.jBtnEliminar)) {
            if (vista.jTxtFieldDNI.isEditable()) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un elemento de la lista");
            } else {
                try {

                    modelo.setDni(vista.jTxtFieldDNI.getText());
                    modelo.eliminarProfesor();
                    vista.jTxtFieldDNI.setEditable(true);
                    vista.jTxtFieldDNI.setText("");
                    vista.jTxtFieldNombre.setText("");
                    vista.jTxtFieldApellido.setText("");
                    vista.jTxtFieldFecNac.setText("");
                    vista.jTxtFieldDom.setText("");
                    vista.jTxtFieldTel.setText("");
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

        ArrayList<ProfesorModelo> profesores;
        profesores = modelo.getProfesores();
        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Apellido", "Fecha de Nacimiento", "Domicilio", "Telefono"}, profesores.size());
        tabla.setModel(modeloTabla);
        if (profesores.size() > 0) {
            for (int i = 0; i < profesores.size(); i++) {
                tabla.setValueAt(profesores.get(i).getDni(), i, 0);
                tabla.setValueAt(profesores.get(i).getNombre(), i, 1);
                tabla.setValueAt(profesores.get(i).getApellido(), i, 2);
                tabla.setValueAt(profesores.get(i).getFechaNacimiento(), i, 3);
                tabla.setValueAt(profesores.get(i).getDomicilio(), i, 4);
                tabla.setValueAt(profesores.get(i).getTelefono(), i, 5);
            }
        }
        profesores.clear();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            int fila = vista.jTable1.rowAtPoint(e.getPoint());
            if (fila > -1) {
                vista.jTxtFieldDNI.setText(String.valueOf(vista.jTable1.getValueAt(fila, 0)));
                vista.jTxtFieldDNI.setEditable(false);
                vista.jTxtFieldNombre.setText(String.valueOf(vista.jTable1.getValueAt(fila, 1)));
                vista.jTxtFieldApellido.setText(String.valueOf(vista.jTable1.getValueAt(fila, 2)));
                vista.jTxtFieldFecNac.setText(String.valueOf(vista.jTable1.getValueAt(fila, 3)));
                vista.jTxtFieldDom.setText(String.valueOf(vista.jTable1.getValueAt(fila, 4)));
                vista.jTxtFieldTel.setText(String.valueOf(vista.jTable1.getValueAt(fila, 5)));
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

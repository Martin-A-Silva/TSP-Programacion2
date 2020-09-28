/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Modelo;
import Vista.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 *
 * @author Martin
 */
public class Controlador implements ActionListener, KeyListener {

    Vista vista;
    Modelo modelo;

    private boolean resultado;
    private char operacion;

    public Controlador(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.resultado = true;

        vista.setVisible(true);
        vista.setTitle("Calculadora");
        vista.setLocationRelativeTo(null);

        this.vista.btn0.addActionListener(this);
        this.vista.btn1.addActionListener(this);
        this.vista.btn2.addActionListener(this);
        this.vista.btn3.addActionListener(this);
        this.vista.btn4.addActionListener(this);
        this.vista.btn5.addActionListener(this);
        this.vista.btn6.addActionListener(this);
        this.vista.btn7.addActionListener(this);
        this.vista.btn8.addActionListener(this);
        this.vista.btn9.addActionListener(this);
        this.vista.btnC.addActionListener(this);
        this.vista.btnCE.addActionListener(this);
        this.vista.btnCerrar.addActionListener(this);
        this.vista.btnDivision.addActionListener(this);
        this.vista.btnDot.addActionListener(this);
        this.vista.btnEquals.addActionListener(this);
        this.vista.btnMinus.addActionListener(this);
        this.vista.btnPlus.addActionListener(this);
        this.vista.btnSigno.addActionListener(this);
        this.vista.btnTimes.addActionListener(this);
        this.vista.jTextField1.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent evt) {
        
        char enter = evt.getKeyChar();
                    
        if (enter == '.') {
            if (this.vista.jTextField1.getText().contains(".")) {    //Para que no haya doble punto
                evt.consume();
            }

        } else if (enter == '+') {
            actionPerformed(new ActionEvent(vista.btnPlus, 1001, "+"));
            evt.consume();
        } else if (enter == '-') {
            actionPerformed(new ActionEvent(vista.btnMinus, 1001, "-"));
            evt.consume();
        } else if (enter == '*') {
            actionPerformed(new ActionEvent(vista.btnTimes, 1001, "*"));
            evt.consume();
        } else if (enter == '/') {
            actionPerformed(new ActionEvent(vista.btnDivision, 1001, "/"));
            evt.consume();
        } else if (enter == '\n') {
            actionPerformed(new ActionEvent(vista.btnEquals, 1001, "="));
        } else if (!(Character.isDigit(enter))) {   //Para que sólo puedan ingresarse numeros y el punto
            evt.consume();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.btn0)) {
            if (resultado) {
                this.vista.jTextField1.setText("0");
                resultado = false;
            }
            if (!this.vista.jTextField1.getText().equals("0")) { //Para no llenar de ceros
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "0");
            }
        } else if (e.getSource().equals(vista.btn1)) {
            //Si es un cero, reemplazarlo por 1, al igual que si se acaba de hacer una cuenta
            if (this.vista.jTextField1.getText().equals("0") || resultado) {
                this.vista.jTextField1.setText("1");
                resultado = false;
            } else {
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "1");
            }
        } else if (e.getSource().equals(vista.btn2)) {
            if (this.vista.jTextField1.getText().equals("0") || resultado) {
                this.vista.jTextField1.setText("2");
                resultado = false;
            } else {
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "2");
            }
        } else if (e.getSource().equals(vista.btn3)) {
            if (this.vista.jTextField1.getText().equals("0") || resultado) {
                this.vista.jTextField1.setText("3");
                resultado = false;
            } else {
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "3");
            }
        } else if (e.getSource().equals(vista.btn4)) {
            if (this.vista.jTextField1.getText().equals("0") || resultado) {
                this.vista.jTextField1.setText("4");
                resultado = false;
            } else {
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "4");
            }
        } else if (e.getSource().equals(vista.btn5)) {
            if (this.vista.jTextField1.getText().equals("0") || resultado) {
                this.vista.jTextField1.setText("5");
                resultado = false;
            } else {
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "5");
            }
        } else if (e.getSource().equals(vista.btn6)) {
            if (this.vista.jTextField1.getText().equals("0") || resultado) {
                this.vista.jTextField1.setText("6");
                resultado = false;
            } else {
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "6");
            }
        } else if (e.getSource().equals(vista.btn7)) {
            if (this.vista.jTextField1.getText().equals("0") || resultado) {
                this.vista.jTextField1.setText("7");
                resultado = false;
            } else {
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "7");
            }
        } else if (e.getSource().equals(vista.btn8)) {
            if (this.vista.jTextField1.getText().equals("0") || resultado) {
                this.vista.jTextField1.setText("8");
                resultado = false;
            } else {
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "8");
            }
        } else if (e.getSource().equals(vista.btn9)) {
            if (this.vista.jTextField1.getText().equals("0") || resultado) {
                this.vista.jTextField1.setText("9");
                resultado = false;
            } else {
                this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "9");
            }
        } else if (e.getSource().equals(vista.btnC)) {
            this.vista.jTextField1.setText("0");
            this.vista.jTextField2.setText("");
        } else if (e.getSource().equals(vista.btnCE)) {
            this.vista.jTextField1.setText("0");
        } else if (e.getSource().equals(vista.btnCerrar)) {
            System.exit(0);
        } else if (e.getSource().equals(vista.btnDot)) {
            if (!this.vista.jTextField1.getText().contains(".") && !this.vista.jTextField1.getText().contains(",")) {
                if (this.vista.jTextField1.getText().equals("")) {
                    this.vista.jTextField1.setText(this.vista.jTextField1.getText() + "0.");
                } else {
                    this.vista.jTextField1.setText(this.vista.jTextField1.getText() + ".");
                }
            }
        } else if (e.getSource().equals(vista.btnSigno)) {
            //Si cambiamos el signo del resultado es porque vamos a seguir usandolo, y no hay que borrarlo
            resultado = false;
            if (!this.vista.jTextField1.getText().equals("0")) {
                if (this.vista.jTextField1.getText().contains("-")) {
                    this.vista.jTextField1.setText(this.vista.jTextField1.getText().substring(1));
                } else {
                    this.vista.jTextField1.setText("-" + this.vista.jTextField1.getText());
                }
            }
        } else if (e.getSource().equals(vista.btnPlus)) {
            operacion = '+';
            this.vista.jTextField2.setText(this.vista.jTextField1.getText());
            this.vista.jTextField1.setText("0");
        } else if (e.getSource().equals(vista.btnMinus)) {
            operacion = '-';
            this.vista.jTextField2.setText(this.vista.jTextField1.getText());
            this.vista.jTextField1.setText("0");
        } else if (e.getSource().equals(vista.btnTimes)) {
            operacion = '*';
            this.vista.jTextField2.setText(this.vista.jTextField1.getText());
            this.vista.jTextField1.setText("0");
        } else if (e.getSource().equals(vista.btnDivision)) {
            operacion = '/';
            this.vista.jTextField2.setText(this.vista.jTextField1.getText());
            this.vista.jTextField1.setText("0");
        } else if (e.getSource().equals(vista.btnEquals)) {
            if (!this.vista.jTextField2.getText().equals("")) {
                try {

                    switch (operacion) {
                        case '+':
                            this.vista.jTextField1.setText(String.valueOf(this.modelo.suma(Double.parseDouble(this.vista.jTextField2.getText().replace(',', '.')), Double.parseDouble(this.vista.jTextField1.getText().replace(',', '.')))));
                            break;
                        case '-':
                            this.vista.jTextField1.setText(String.valueOf(this.modelo.resta(Double.parseDouble(this.vista.jTextField2.getText().replace(',', '.')), Double.parseDouble(this.vista.jTextField1.getText().replace(',', '.')))));
                            break;
                        case '*':
                            this.vista.jTextField1.setText(String.valueOf(this.modelo.multiplicacion(Double.parseDouble(this.vista.jTextField2.getText().replace(',', '.')), Double.parseDouble(this.vista.jTextField1.getText().replace(',', '.')))));
                            break;
                        case '/':
                            this.vista.jTextField1.setText(String.valueOf(this.modelo.division(Double.parseDouble(this.vista.jTextField2.getText().replace(',', '.')), Double.parseDouble(this.vista.jTextField1.getText().replace(',', '.')))));
                            break;
                    }
                    this.vista.jTextField2.setText("");
                    resultado = true;
                } catch (NumberFormatException ex) {
                    this.vista.jTextField1.setText("ERROR: Formato inválido");
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        
    }

    @Override
    public void keyPressed(KeyEvent k) {

    }

}

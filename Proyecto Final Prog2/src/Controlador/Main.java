/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.MenuPrincipalVista;

/**
 *
 * @author Martin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MenuPrincipalVista ppalVista = new MenuPrincipalVista();
        MenuPrincipalControlador ppalCont = new MenuPrincipalControlador(ppalVista);
    }
    
}

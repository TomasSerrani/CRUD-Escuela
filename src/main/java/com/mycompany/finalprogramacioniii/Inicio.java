/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finalprogramacioniii;
import javax.swing.SwingUtilities;

/**
 *
 * @author DBTecnología
 */
public class Inicio {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interfaz_InicioSesionUsuario::crearYMostrarGUI);
        Interfaz_InicioSesionUsuario IISU = new Interfaz_InicioSesionUsuario();
        IISU.setVisible(true);
    }
}

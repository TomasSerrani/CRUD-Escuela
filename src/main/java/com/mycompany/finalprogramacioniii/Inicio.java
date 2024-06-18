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
        SwingUtilities.invokeLater(Interfaz::crearYMostrarGUI);
        Interfaz IISU = new Interfaz();
        IISU.setVisible(true);
    }
}

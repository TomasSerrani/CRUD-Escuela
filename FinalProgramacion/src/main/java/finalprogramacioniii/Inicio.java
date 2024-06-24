/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package finalprogramacioniii;
import javax.swing.SwingUtilities;

/**
 *
 * @author DBTecnología
 */
public class Inicio {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interfaz::new);
        Interfaz IISU = new Interfaz();
        IISU.setVisible(true);
    }
}

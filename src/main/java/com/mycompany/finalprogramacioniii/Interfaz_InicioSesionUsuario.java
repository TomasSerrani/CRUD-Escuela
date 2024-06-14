
package com.mycompany.finalprogramacioniii;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Interfaz_InicioSesionUsuario extends JFrame {
    
    public static void crearYMostrarGUI(){
    JFrame frame = new JFrame ("Autentificacion de usuario");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    
    JButton botonRegistrar = new JButton ("Registrarse");
    JButton botonIniciar = new JButton ("Iniciar sesion");
    
    botonRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDialogoDeRegistro(frame);
            }
    });
    botonIniciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mostrarDialogoDeInicio(frame);
            }
    });
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(2, 1, 10, 10));
    panel.add(botonRegistrar);
    panel.add(botonIniciar);
    
    frame.add(panel, BorderLayout.CENTER);
    frame.setVisible(true);
}
    private static void mostrarDialogoDeRegistro(JFrame parentFrame){
    JDialog dialogoDeRegistro = new JDialog(parentFrame, "Registro", true);
    dialogoDeRegistro.setSize(300, 200);
    dialogoDeRegistro.setLayout(new GridLayout(3, 2, 10, 10));
    
    JLabel nombreLabel = new JLabel("Nombre de Usuario: ");
    JTextField nombreField = new JTextField();
    JLabel contraLabel = new JLabel("Contrasenia: ");
    JTextField contraField = new JTextField();
    JLabel correoLabel = new JLabel("Correo electronico: ");
    JTextField correoField = new JTextField();
    JButton botonRegistrar = new JButton("Registrarse");
    
    botonRegistrar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            String nombreUsuario = nombreField.getText();
            String contraseña = contraField.getText();
            String correo = correoField.getText();
            JOptionPane.showMessageDialog(dialogoDeRegistro, "Se logro registrar correctamente!!");
            dialogoDeRegistro.dispose();
        }
    });
    dialogoDeRegistro.add(nombreLabel);
    dialogoDeRegistro.add(nombreField);
    dialogoDeRegistro.add(contraLabel);
    dialogoDeRegistro.add(contraField);
    dialogoDeRegistro.add(correoLabel);
    dialogoDeRegistro.add(new JLabel());
    dialogoDeRegistro.add(contraField);
    
    dialogoDeRegistro.setVisible(true);
}
    
    private static void mostrarDialogoDeInicio(JFrame parentFrame){
    JDialog dialogoDeInicio = new JDialog(parentFrame, "Iniciar Sesion", true);
    dialogoDeInicio.setSize(300, 200);
    dialogoDeInicio.setLayout(new GridLayout(3, 2, 10, 10));
        
    JLabel nombreLabel = new JLabel ("Nombre de usuario: ");
    JTextField nombreField = new JTextField();
    JLabel contraLabel = new JLabel ("Contrasela:");
    JTextField contraField = new JTextField();
    JButton botonIniciar = new JButton ("Iniciar sesion");
    
    botonIniciar.addActionListener(new ActionListener(){
       @Override
       public void actionPerformed(ActionEvent e){
           String nombreUsuario = nombreField.getText();
           String contraseña = contraField.getText();
           JOptionPane.showMessageDialog(dialogoDeInicio, "Se logro iniciar sesion correctamente");
           dialogoDeInicio.dispose();
       }
    });
    dialogoDeInicio.add(nombreLabel);
    dialogoDeInicio.add(nombreField);    
    dialogoDeInicio.add(contraLabel);    
    dialogoDeInicio.add(contraField);
    dialogoDeInicio.add(new JLabel());
    dialogoDeInicio.add(botonIniciar);
    
    dialogoDeInicio.setVisible(true);
    }
}
package finalprogramacioniii;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame {

    public Interfaz() {
        crearYMostrarGUI();
    }

    public void crearYMostrarGUI() {
        JFrame frame = new JFrame("Autenticación de Usuario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton botonRegistrar = new JButton("Registrarse");
        JButton botonIniciar = new JButton("Iniciar Sesión");

        botonRegistrar.addActionListener(e -> mostrarDialogoDeRegistro(frame));
        botonIniciar.addActionListener(e -> mostrarDialogoDeInicio(frame));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(botonRegistrar);
        panel.add(botonIniciar);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void mostrarDialogoDeRegistro(JFrame parentFrame) {
        JDialog dialogoDeRegistro = new JDialog(parentFrame, "Registro", true);
        dialogoDeRegistro.setSize(300, 200);
        dialogoDeRegistro.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel nombreLabel = new JLabel("Nombre de Usuario: ");
        JTextField nombreField = new JTextField();
        JLabel contraLabel = new JLabel("Contraseña: ");
        JPasswordField contraField = new JPasswordField();
        JLabel correoLabel = new JLabel("Correo Electrónico: ");
        JTextField correoField = new JTextField();
        JButton botonRegistrar = new JButton("Registrarse");

        botonRegistrar.addActionListener(e -> {
            Usuario usuario = new Usuario(0, nombreField.getText(), correoField.getText(), new String(contraField.getPassword()));
            usuario.RegistrarUsuario(nombreField, correoField, contraField);
            JOptionPane.showMessageDialog(dialogoDeRegistro, "¡Se registró correctamente!");
            dialogoDeRegistro.dispose();
        });

        dialogoDeRegistro.add(nombreLabel);
        dialogoDeRegistro.add(nombreField);
        dialogoDeRegistro.add(contraLabel);
        dialogoDeRegistro.add(contraField);
        dialogoDeRegistro.add(correoLabel);
        dialogoDeRegistro.add(correoField);
        dialogoDeRegistro.add(new JLabel()); // Empty placeholder
        dialogoDeRegistro.add(botonRegistrar);

        dialogoDeRegistro.setVisible(true);
    }

    private void mostrarDialogoDeInicio(JFrame parentFrame) {
        JDialog dialogoDeInicio = new JDialog(parentFrame, "Inicio de Sesión", true);
        dialogoDeInicio.setSize(300, 200);
        dialogoDeInicio.setLayout(null);

        JLabel correoLabel = new JLabel("Correo:");
        correoLabel.setBounds(10, 20, 80, 25);
        dialogoDeInicio.add(correoLabel);

        JTextField correoField = new JTextField(20);
        correoField.setBounds(100, 20, 165, 25);
        dialogoDeInicio.add(correoField);

        JLabel contraseniaLabel = new JLabel("Contraseña:");
        contraseniaLabel.setBounds(10, 50, 80, 25);
        dialogoDeInicio.add(contraseniaLabel);

        JPasswordField contraseniaField = new JPasswordField(20);
        contraseniaField.setBounds(100, 50, 165, 25);
        dialogoDeInicio.add(contraseniaField);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(10, 80, 255, 25);
        dialogoDeInicio.add(loginButton);

        loginButton.addActionListener(e -> {
            Usuario usuario = new Usuario(0, "", correoField.getText(), new String(contraseniaField.getPassword()));
            Usuario autenticado = usuario.iniciarSesion(correoField, contraseniaField);
            if (autenticado != null) {
                JOptionPane.showMessageDialog(dialogoDeInicio, "Inicio de sesión exitoso.");
                dialogoDeInicio.dispose();
                mostrarHomePage();
            } else {
                JOptionPane.showMessageDialog(dialogoDeInicio, "Correo o contraseña incorrectos.");
            }
        });

        dialogoDeInicio.setVisible(true);
    }

    public void mostrarHomePage() {
        JFrame homeFrame = new JFrame("Home");
        homeFrame.setSize(400, 200);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JButton btnBuscarVuelo = new JButton("Buscar Vuelo");
        JButton btnVerReservas = new JButton("Ver Reservas");
        JButton btnVerUsuario = new JButton("Ver Usuario");

        btnBuscarVuelo.addActionListener(e -> JOptionPane.showMessageDialog(null, "Buscar Vuelo"));
        btnVerReservas.addActionListener(e -> JOptionPane.showMessageDialog(null, "Ver Reservas"));
        btnVerUsuario.addActionListener(e -> JOptionPane.showMessageDialog(null, "Ver Usuario"));

        panel.add(btnBuscarVuelo);
        panel.add(btnVerReservas);
        panel.add(btnVerUsuario);

        homeFrame.add(panel);
        homeFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interfaz::new);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalprogramacioniii;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author DBTecnología
 */
public class Usuario {
    int id;
    String nombre;
    String correo;
    String contraseña;
    long numTelefono;
    long numTarjeta;
    long numCBU;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public Usuario(int id, String nombre, String correo, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public long getNumTelefono() {
        return numTelefono;
    }

    public void setNumTelefono(long numTelefono) {
        this.numTelefono = numTelefono;
    }

    public long getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(long numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public long getNumCBU() {
        return numCBU;
    }

    public void setNumCBU(long numCBU) {
        this.numCBU = numCBU;
    }
    public void RegistrarUsuario (JTextField paramNombre, JTextField paramCorreo, JTextField paramContraseña, JTextField paramNumTelefono, JTextField paramNumTarjeta, JTextField paramNumCBU){
        setNombre(paramNombre.getText());
        setCorreo(paramCorreo.getText());
        setContraseña(paramContraseña.getText());
           try {
               setNumTelefono(Long.parseLong(paramNumTelefono.getText()));
               setNumTarjeta(Long.parseLong(paramNumTarjeta.getText()));
               setNumCBU(Long.parseLong(paramNumCBU.getText()));
           }catch(NumberFormatException e){
               JOptionPane.showMessageDialog(null, "Número de teléfono, tarjeta o CBU inválido.");
               return;
           }
       

        Conexion objetoConexion = new Conexion();
        
        String consulta = ("insert into usuario (nombreUsuario, correoUsuario, contraseñaUsuario, numTelefonoUsuario, numTarjetaUsuario, numCBUUsuario) values (?,?,?,?,?,?);");
        try {
            CallableStatement cs = objetoConexion.estableConexion().prepareCall(consulta);
            
            cs.setString(1, getNombre());
            cs.setString(2, getCorreo());
            cs.setString(3, getContraseña());
            cs.setLong(4, getNumTelefono());
            cs.setLong(5, getNumTarjeta());
            cs.setLong(6,getNumCBU());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario.");
        } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario."+e.toString());
        }
    }
    public Usuario iniciarSesion(JTextField paramCorreo, JTextField paramContraseña) {
        setCorreo(paramCorreo.getText());
        setContraseña(paramContraseña.getText());

        Usuario usuario = null;
        Conexion objetoConexion = new Conexion();
        String consulta = "SELECT * FROM usuario WHERE correoUsuario = ? AND contraseñaUsuario = ?";

        try (Connection con = objetoConexion.estableConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {

            ps.setString(1, getCorreo());
            ps.setString(2, getContraseña());

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("idUsuario");
                    String nombre = resultSet.getString("nombreUsuario");
                    String correo = resultSet.getString("correoUsuario");
                    String contraseña = resultSet.getString("contraseñaUsuario");
                    usuario = new Usuario(id, nombre, correo, contraseña);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
   public void MostrarReservasUsuarios(JTable paramTablaReservas, JTextField numReserva){
       int numeroReserva= Integer.parseInt(numReserva.getText()); 
       
   }
}


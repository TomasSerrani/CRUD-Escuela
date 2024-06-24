/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalprogramacioniii;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;

/**
 *
 * @author DBTecnología
 */
public class Usuario {
    int id;
    String nombre;
    String correo;
    String contrasenia;
    long numTelefono;
    long numTarjeta;
    long numCBU;
    public Usuario(){

    };
    public Usuario(int id, String nombre, String correo, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contraseña;
    }
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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
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
    public void RegistrarUsuario (JTextField paramNombre, JTextField paramCorreo, JTextField paramContrasenia){
        setNombre(paramNombre.getText());
        setCorreo(paramCorreo.getText());
        setContrasenia(paramContrasenia.getText());

        Conexion objetoConexion = new Conexion();
        
        String consulta = ("insert into usuario (nombreUsuario, correoUsuario, contraseñaUsuario) values (?,?,?);");
        try {
            CallableStatement cs = objetoConexion.estableConexion().prepareCall(consulta);
            
            cs.setString(1, getNombre());
            cs.setString(2, getCorreo());
            cs.setString(3, getContrasenia());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el usuario.");
        } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se insertó correctamente el usuario."+e.toString());
        }
    }
    public Usuario iniciarSesion(JTextField paramCorreo, JTextField paramContraseña) {
        setCorreo(paramCorreo.getText());
        setContrasenia(paramContraseña.getText());

        Usuario usuario = null;
        Conexion objetoConexion = new Conexion();
        String consulta = "SELECT * FROM usuario WHERE correoUsuario = ? AND contraseñaUsuario = ?";

        try (Connection con = objetoConexion.estableConexion();
             PreparedStatement ps = con.prepareStatement(consulta)) {

            ps.setString(1, getCorreo());
            ps.setString(2, getContrasenia());

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


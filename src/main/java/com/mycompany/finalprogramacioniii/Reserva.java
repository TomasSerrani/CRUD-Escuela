package com.mycompany.finalprogramacioniii;


import com.mycompany.finalprogramacioniii.Conexion;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author DBTecnología
 */
public class Reserva {
    //creacion de variables
    int numReserva;
    int id;
    Date fechaReserva;
    String estadoReserva;
    //getters y setters

    public int getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(int numReserva) {
        this.numReserva = numReserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }
    //constructor

    public Reserva(int numReserva, int id, Date fechaReserva, String estadoReserva) {
        this.numReserva = numReserva;
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.estadoReserva = estadoReserva;
    }
    //métodos principales
    public void InsertarReserva(JTextField numReserva, JTextField fechaDeReserva, JTextField estadoReserva){
        try{
            setNumReserva(Integer.parseInt(numReserva.getText()));
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Número de reserva inválido");
            return;
        }
         try{
            setFechaReserva(Date.valueOf(fechaDeReserva.getText()));
            
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null, "Número de reserva inválido");
            return;
        }
        
        setEstadoReserva(estadoReserva.getText());
        Conexion objetoConexion = new Conexion();
        
        String consulta = ("insert into reservas (numReserva, fechaReserva, estadoReserva) values (?,?,?);");
        try {
            CallableStatement cs = objetoConexion.estableConexion().prepareCall(consulta);
            
            cs.setInt(1, getNumReserva());
            cs.setDate(2, getFechaReserva());
            cs.setString(3, getEstadoReserva());            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la reserva.");
        } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se insertó correctamente la reserva."+e.toString());
        }
    }
      public void MostrarReservas(JTable paramTablaReservas){
            
            Conexion objetoConexion = new Conexion();
            
            DefaultTableModel modelo = new DefaultTableModel();
            
            TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
            paramTablaReservas.setRowSorter(OrdenarTabla);
            
            String sql = "";
            
            modelo.addColumn("Número");
            modelo.addColumn("Fecha");
            modelo.addColumn("Estado");
            
            paramTablaReservas.setModel(modelo);
            
            sql = "select   from ;";
            
            String[] datos = new String[3];
            Statement st;
            try {
                st = objetoConexion.estableConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    datos[0]=rs.getString(2);
                    datos[1]=rs.getString(3);
                    datos[2]=rs.getString(4);
                    
                    modelo.addRow(datos);
                }
                
                    paramTablaReservas.setModel(modelo);
                
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: "+e.toString());
            }
                    
        }
    
}

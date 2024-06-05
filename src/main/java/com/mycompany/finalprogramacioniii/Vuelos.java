/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalprogramacioniii;

import java.sql.CallableStatement;
import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author DBTecnología
 */
public class Vuelos {
    int numVuelo;
    String destino;
    String origen;
    String[] escalas;
    int cantAsientos;
    int cantPasajeros;
    int cantAsientosDisponibles;
    String duracion;
    String aerolinea;
    float precio;
    Date fechaSalida;
    String numAsiento;
    String estadoVuelo;
    // getters y setters

    public int getNumVuelo() {
        return numVuelo;
    }

    public void setNumVuelo(int numVuelo) {
        this.numVuelo = numVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String[] getEscalas() {
        return escalas;
    }

    public void setEscalas(String[] escalas) {
        this.escalas = escalas;
    }

    public int getCantAsientos() {
        return cantAsientos;
    }

    public void setCantAsientos(int cantAsientos) {
        this.cantAsientos = cantAsientos;
    }

    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public void setCantPasajeros(int cantPasajeros) {
        this.cantPasajeros = cantPasajeros;
    }

    public int getCantAsientosDisponibles() {
        return cantAsientosDisponibles;
    }

    public void setCantAsientosDisponibles(int cantAsientosDisponibles) {
        this.cantAsientosDisponibles = cantAsientosDisponibles;
    }
    
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(String numAsiento) {
        this.numAsiento = numAsiento;
    }

    public String getEstadoVuelo() {
        return estadoVuelo;
    }

    public void setEstadoVuelo(String estadoVuelo) {
        this.estadoVuelo = estadoVuelo;
    }
    // metodos principales
    public void InsertarVuelo(JTextField numVuelo,JTextField cantAsientos, JTextField cantPasajeros, JTextField cantAsientosDisponibles, JTextField precio, JTextField fechaSalida, JTextField destino, JTextField origen, JTextField escalas, JTextField duracion, JTextField aerolinea, JTextField estadoVuelo){
        try {
            setNumVuelo(Integer.parseInt(numVuelo.getText()));
            setCantAsientos(Integer.parseInt(cantAsientos.getText()));
            setCantPasajeros(Integer.parseInt(cantPasajeros.getText()));
            setCantAsientosDisponibles(Integer.parseInt(cantAsientosDisponibles.getText()));
            setPrecio(Float.parseFloat(precio.getText()));
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Número de vuelo, cantidad de asientos, cantidad de pasajeros o cantidad de asientos disponibles inválidos");
        }
        try {
            setFechaSalida(Date.valueOf(fechaSalida.getText()));
            
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(null,"Fecha de salida inválida");
            return;
        }
        
        setDestino(destino.getText());
        setOrigen(origen.getText());
        setEscalas(escalas.getText().split(","));
        setDuracion(duracion.getText());
        setAerolinea(aerolinea.getText());
        setEstadoVuelo(estadoVuelo.getText());
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = ("insert into vuelos (numVuelo, cantTotalAsientosVuelo, cantPasajerosVuelo, cantAsientosDispVuelo, precioVuelo, horarioSalidaVuelo, destinoVuelo, origenVuelo, escalasVuelo, duracionVuelo, aerolineaVuelo, estadoVuelo) values (?,?,?,?,?,?,?,?,?,?,?,?);");
        try {
            CallableStatement cs = objetoConexion.estableConexion().prepareCall(consulta);
            
            cs.setInt(1, getNumVuelo());
            cs.setInt(2, getCantAsientos());
            cs.setInt(3, getCantPasajeros()); 
            cs.setInt(4, getCantAsientosDisponibles());
            cs.setFloat(5,getPrecio());
            cs.setDate(6, getFechaSalida());
            cs.setString(7, getDestino());
            cs.setString(8,getOrigen());
            cs.setString(9,String.join(",", getEscalas()));
            cs.setString(10, getDuracion());
            cs.setString(11,getAerolinea());
            cs.setString(12,getEstadoVuelo());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el vuelo.");
        } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se insertó correctamente el vuelo."+e.toString());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crudescuela;

import com.mysql.cj.xdevapi.Result;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Tomás
 */
public class CAlumnos {
    
    int codigo;
    String nombreAlumno;
    String apellidoAlumno;
    int dni;
    int legajo;
    String beca;
    
     public String getBeca() {
        return beca;
    }

    public void setBeca(String beca) {
        this.beca = beca;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    

    public void InsertarAlumno(JTextField paramNombre, JTextField paramApellido, JTextField paramDni, JTextField paramLegajo, JComboBox paramBeca){
        setNombreAlumno(paramNombre.getText());
        setApellidoAlumno(paramApellido.getText());
        setDni(Integer.parseInt(paramDni.getText()));
        setLegajo(Integer.parseInt(paramLegajo.getText()));
        setBeca(paramBeca.getSelectedItem().toString());

        CConexion objetoConexion = new CConexion();
        
        String consulta = ("insert into alumnos (Nombre_Alumno, Apellido_Alumno, DNI_Alumno, Legajo_Alumno, Beca_Alumno) values (?,?,?,?,?);");
  
        try {
            CallableStatement cs = objetoConexion.estableConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumno());
            cs.setString(2, getApellidoAlumno());
            cs.setInt(3, getDni());
            cs.setInt(4, getLegajo());
            cs.setString(5, getBeca());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente un alumno.");
        } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "No se insertó correctamente un alumno."+e.toString());
        }
    }

        public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
            
            CConexion objetoConexion = new CConexion();
            
            DefaultTableModel modelo = new DefaultTableModel();
            
            TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
            paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
            
            String sql;
            
            modelo.addColumn("id");
            modelo.addColumn("Nombres");
            modelo.addColumn("Apellidos");
            modelo.addColumn("Dni");
            modelo.addColumn("Legajo");
            modelo.addColumn("Beca");
            
            paramTablaTotalAlumnos.setModel(modelo);
            
            sql = "select id_Alumno,Nombre_Alumno, Apellido_Alumno, DNI_Alumno, Legajo_Alumno, becas.Tipo_Beca  from alumnos JOIN becas ON alumnos.Beca_Alumno = becas.id_beca;";
            
            String[] datos = new String[6];
            Statement st;
            try {
                st = objetoConexion.estableConexion().createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    datos[0]=rs.getString(1);
                    datos[1]=rs.getString(2);
                    datos[2]=rs.getString(3);
                    datos[3]=rs.getString(4);
                    datos[4]=rs.getString(5);
                    datos[5]=rs.getString(6);
                    
                    modelo.addRow(datos);
                }
                
                    paramTablaTotalAlumnos.setModel(modelo);
                
            } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros, error: "+e.toString());
            }
                    
        }
            public void SeleccionarAlumnos(JTable paramTablaAlumnos, JTextField paramID, JTextField paramNombres, JTextField paramApellidos, JTextField paramDni, JTextField paramLegajo, JComboBox paramBeca){
                try {
                    int fila = paramTablaAlumnos.getSelectedRow();
                    if(fila >= 0){
                        paramID.setText((paramTablaAlumnos.getValueAt(fila, 0).toString()));
                        paramNombres.setText((paramTablaAlumnos.getValueAt(fila, 1).toString()));
                        paramApellidos.setText((paramTablaAlumnos.getValueAt(fila, 2).toString()));
                        paramDni.setText((paramTablaAlumnos.getValueAt(fila, 3).toString()));
                        paramLegajo.setText((paramTablaAlumnos.getValueAt(fila, 4).toString()));
                        
                                         }
                    else{
                        JOptionPane.showMessageDialog(null, "Fila no seleccionada");
                    }
                } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error de selección, error: "+e.toString());
                        
                }
                
            } 
    
            public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos, JTextField paramDni, JTextField paramLegajo, JComboBox paramBeca){
                setCodigo(Integer.parseInt(paramCodigo.getText()));
                setNombreAlumno(paramNombres.getText());
                setApellidoAlumno(paramApellidos.getText());
                setDni(Integer.parseInt(paramDni.getText()));
                setLegajo(Integer.parseInt(paramLegajo.getText()));
                setBeca((paramBeca.getSelectedItem().toString()));
                
                
                CConexion objetoConexion = new CConexion();
                String consulta = "UPDATE alumnos SET alumnos.Nombre_Alumno = ?, alumnos.apellido_Alumno = ?, alumnos.DNI_Alumno = ?, alumnos.Legajo_Alumno = ?, alumnos.Beca_Alumno= ? WHERE alumnos.id_Alumno=?;";
                
                try {
                    
                    CallableStatement cs = objetoConexion.estableConexion().prepareCall(consulta);
                    cs.setString(1, getNombreAlumno());
                    cs.setString(2, getApellidoAlumno());
                    cs.setInt(3, getDni());
                    cs.setInt(4, getLegajo());
                    cs.setString(5, getBeca());
                    cs.setInt(6, getCodigo());

                    
                    cs.execute();
                    
                    JOptionPane.showMessageDialog(null, "Modificación exitosa.");
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "No se pudo modificar."+e.toString());

                }
            }
            public void EliminarAlumnos(JTextField paramCodigo){
                setCodigo(Integer.parseInt(paramCodigo.getText()));
                
                CConexion objetoConexion = new CConexion();
                String consulta = "DELETE FROM Alumnos WHERE alumnos.id_Alumno=?;";
                
                try {
                    CallableStatement cs = objetoConexion.estableConexion().prepareCall(consulta);
                    cs.setInt(1, getCodigo());
                    cs.execute();
                    
                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente el alumno.");
                } catch (Exception e) {
                
                    JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: "+e.toString());
                }
                
            }
}

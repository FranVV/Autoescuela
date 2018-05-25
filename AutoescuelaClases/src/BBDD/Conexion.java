/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
holaaa
 */
package BBDD;


import Clases.Alumno;
import Clases.Clase;
import Clases.Persona;
import Clases.Profesor;
import com.toedter.calendar.JCalendar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author alumno
 */
public class Conexion {
    private String url;
    private String usuario;
    private String contraseña;
    private Connection con;

    public Conexion() {
        this.url = "jdbc:mysql://localhost:3307/autoescuela";
        this.usuario ="root";
        this.contraseña ="usbw";
    }

    public Conexion(String url, String usuario, String contraseña) {
        this.url = url;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }
    public void conectar() {
        try {
            con = DriverManager.getConnection(url,usuario, contraseña);
        } catch (SQLException ex) {
            System.out.println("Fallo al conectar con la BBDD");
        }
    }
    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.println("Fallo al desconectar de la BBDD");
        }
    }
    public boolean consultarPersonasUsuarioContraseña(String nombre, String pass) {
        boolean encontrado =false;
        try {
            PreparedStatement st;
            conectar();
            st = con.prepareStatement("Select nombre,contraseña from persona where nombre = ?;");
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
                            
            while (rs.next()) {
                    if (rs.getString(2).equalsIgnoreCase(pass)){
                        encontrado =  true;
                    }
                        
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los usuarios y contraseñas");
        }
        
        return encontrado ; 
    }
    public String consultarPersonaNombre(String dniAlumno) {
        String nombre="";
        try {
            PreparedStatement st;
            conectar();
            st = con.prepareStatement("Select nombre from persona where dni = ?;");
            st.setString(1, dniAlumno);
            ResultSet rs = st.executeQuery();
                            
            while (rs.next()) {
                    nombre=rs.getString(1);
                        
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los usuarios y contraseñas");
        }
        
        return nombre ; 
    }
    public ArrayList<Clase> consultarClases(String fecha) {
        ArrayList<Clase> vClase= new ArrayList<>();
        try {
            PreparedStatement st;
            conectar();
            //Select * from clase where fecha between CAST(? AS DATE) AND CAST(? AS DATE)
            st = con.prepareStatement("Select * from clase where fecha between ?  AND ? ;");//fecha entre hoy y 5 dias por delante
 st.setDate(1, java.sql.Date.valueOf(fecha));
            //Sumamos 5 dias a la fecha base
           Calendar calendar = Calendar.getInstance();
      calendar.setTime(java.sql.Date.valueOf(fecha)); 
      calendar.add(Calendar.DAY_OF_YEAR, 4);
            
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaS = formato.format(calendar.getTime());
            st.setDate(2, java.sql.Date.valueOf(fechaS));
            
            
            ResultSet rs = st.executeQuery();
                            
            while (rs.next()) {
                    vClase.add(new Clase(rs.getString(3),rs.getString(4) , rs.getString(2), rs.getDate(1).toString(), rs.getInt(6), rs.getBoolean(5)));
                        
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los usuarios y contraseñas");
       }
        
        return vClase; 
    }
    
    private  String calculardia(String dia, int n) {
    int diaInt = Integer.parseInt(dia)+n;
    dia=Integer.toString(diaInt);
        return dia;
    }
    public ArrayList<String> mostrarDatosTablaporCaposconcretos( String tabla, String campo) {
        ArrayList<String> vMatriculas = new ArrayList<>();
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("Select "+campo+" from "+tabla+";");
            
            ResultSet rs = pt.executeQuery();
                            
            while (rs.next()) {
                    vMatriculas.add(rs.getString(1));
                        
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los campos de la tabla");
        }
        
        return vMatriculas ; 
    }
    public ArrayList<String> mostrarDatosClases( String tabla, String campo) {
        ArrayList<String> vMatriculas = new ArrayList<>();
        try {
            conectar();
            PreparedStatement pt;
            JCalendar jCalendar1 = new JCalendar();
           
            Date fecha = jCalendar1.getCalendar().getTime();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaS = formato.format(fecha);

            pt = con.prepareStatement("Select "+campo+" from "+tabla+" where fecha>=now() ;");
            
            ResultSet rs = pt.executeQuery();
                            
            while (rs.next()) {
                    vMatriculas.add(rs.getString(1)+" "+rs.getInt(2));
                        
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los campos de la tabla");
        }
        
        return vMatriculas ; 
    }
    public ArrayList<String> mostrarDatosTablaporCaposconcretosNombreAlumno( String tabla, String campo, String nombre) {
        ArrayList<String> vMatriculas = new ArrayList<>();
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("Select "+campo+" from "+tabla+" where nombre='"+nombre+"';");
            
            ResultSet rs = pt.executeQuery();
                            
            while (rs.next()) {
                if(rs.getString(6).equalsIgnoreCase("Alumno")){
                    vMatriculas.add("Nombre: "+rs.getString(1)+" | ID: "+rs.getString(8));
                } else{
                    vMatriculas.add("EL nombre introducido no"+"\n" +" pertenece a un alumno");
                } 
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los campos de la tabla");
        }
        
        return vMatriculas ; 
    }
    public ArrayList<String> mostrarDatosTablaporCaposconcretosNombreProfesor( String tabla, String campo, String nombre) {
        ArrayList<String> vMatriculas = new ArrayList<>();
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("Select "+campo+" from "+tabla+" where nombre='"+nombre+"';");
            
            ResultSet rs = pt.executeQuery();
                            
            while (rs.next()) {
                if(rs.getString(6).equalsIgnoreCase("Profesor")){
                    vMatriculas.add("Nombre: "+rs.getString(1)+" | DNI: "+rs.getString(2));
                } else{
                    vMatriculas.add("EL nombre introducido no"+"\n" +" pertenece a un profesor");
                }
                
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los campos de la tabla");
        }
        
        return vMatriculas ; 
    }
    public ArrayList<String> mostrarDatosTablaporCaposconcretosporMatricula( String tabla, String campo, String matricula) {
        ArrayList<String> vMatriculas = new ArrayList<>();
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("Select "+campo+" from "+tabla+" where matricula='"+matricula+"';");
            
            ResultSet rs = pt.executeQuery();
                            
            while (rs.next()) {
                    vMatriculas.add("Matricula: "+rs.getString(1)+" | Modelo: "+rs.getString(2)+" | Cilindrada: "+rs.getString(3)+" | Tipo camion:"+rs.getString(4));
                        
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los campos de la tabla");
        }
        
        return vMatriculas ; 
    }
    public ArrayList<String> mostrarDatosTablaporCaposconcretosmasTipo( String tabla, String campo, String condicion) {
        ArrayList<String> vMatriculas = new ArrayList<>();
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("Select "+campo+" from "+tabla+" where tipo='"+condicion+"';");
            
            ResultSet rs = pt.executeQuery();
                            
            while (rs.next()) {
                    vMatriculas.add(rs.getString(1));
                        
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los campos de la tabla");
        }
        
        return vMatriculas ; 
    }
    public ArrayList<String> mostrarDatosTablaporCaposconcretosmasTipoTeorica( String tabla, String campo, String condicion, boolean condicion2) {
        ArrayList<String> vMatriculas = new ArrayList<>();
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("Select "+campo+" from "+tabla+" where tipo='"+condicion+"' and teorica="+condicion2+";");
            
            ResultSet rs = pt.executeQuery();
                            
            while (rs.next()) {
                    vMatriculas.add(rs.getString(1));
                        
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los campos de la tabla");
        }
        
        return vMatriculas ; 
    }
    public boolean eliminar( String tabla,String condicion,String dato) {
        boolean bandera= false;
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("DELETE FROM "+tabla+" WHERE "+condicion+"='"+dato+"';");
            
            int rs = pt.executeUpdate();
              
            if(rs>0){
             bandera= true;   
            }
            
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al eliminar "+tabla);
        }
        
        return bandera ; 
    }
    public boolean eliminarClase( String tabla,String condicion,String dato,String condicion2,int dato2) {
        boolean bandera= false;
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("DELETE FROM "+tabla+" WHERE "+condicion+"='"+dato+"' and "+condicion2+"="+dato2+";");
            
            int rs = pt.executeUpdate();
              
            if(rs>0){
             bandera= true;   
            }
            
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al eliminar "+tabla);
        }
        
        return bandera ; 
    }
    public boolean insertarDatosVehiculo( String matricula,String modelo,int potencia, String tipo) {
        boolean bandera= false;
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("INSERT INTO `vehiculo`(`matricula`, `modelo`, `potencia`, `tipo`) VALUES ('"+matricula+"','"+modelo+"',"+potencia+",'"+tipo+"');");
            
            int rs = pt.executeUpdate();
              
            if(rs>0){
             bandera= true;   
            }
            
            desconectar();
        } catch (SQLException ex) { 
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bandera ; 
    }
    public boolean insertarDatosCalse( String matricula,String idAlumno,String dniProfesor, String fecha, int Hora) {
        boolean bandera= false;
        String dniAlumno = null;
        try {
            conectar();
            PreparedStatement pt;
            pt = con.prepareStatement("Select dni from persona where id='"+idAlumno+"';");
            
            ResultSet rs1 = pt.executeQuery();
            while(rs1.next()){
                dniAlumno= rs1.getString(1);
            }
            
            pt = con.prepareStatement("INSERT INTO `clase`(`fecha`, `matricula`, `dniAlumno`, `dniProfesor`, `asignada`, `Hora`) VALUES ('"+fecha+"','"+matricula+"','"+dniAlumno+"','"+dniProfesor+"',True,"+Hora+");");
            
            int rs = pt.executeUpdate();
              
            if(rs>0){
             bandera= true;   
            }
            
            desconectar();
        } catch (SQLException ex) { 
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bandera ; 
    }
   
    
    public boolean insertarDatosPersona( String nombre,String dni,String fechanacimiento, String permisos,int tlf,String tipo,String correo,int id,boolean admin,String contraseña) {
        boolean bandera= false;
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("INSERT INTO `persona`(`nombre`, `dni`, `fechaNacimiento`, `permisosPosee`, `nTelefono`, `tipo`, `correo`, `id`, `admin`, `contraseña`, `teorica`, `practica`) VALUES ('"+nombre+"','"+dni+"','"+fechanacimiento+"','"+permisos+"',"+tlf+",'"+tipo+"','"+correo+"',"+id+","+admin+",'"+contraseña+"',false,flase);");
            
            int rs = pt.executeUpdate();
              
            if(rs>0){
             bandera= true;   
            }
            
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bandera ; 
    }
    
    public boolean updateAlumnoTeoricaPractica( boolean teorica,boolean practica, int id) {
        boolean bandera= false;
        try {
            conectar();
            PreparedStatement pt;
            pt = con.prepareStatement("Select dni from persona where id=?;");
            pt.setInt(1, id);      
            ResultSet rs = pt.executeQuery();
            rs.next();
            pt = con.prepareStatement("UPDATE  persona SET  teorica = ? ,practica = ? WHERE dni = ?;");
            pt.setBoolean(1, teorica);
            pt.setBoolean(2, practica);
            pt.setString(3, rs.getString(1));
            int rss = pt.executeUpdate();
            if(rss>0){
             bandera= true;   
            }
            
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bandera ; 
    }

    

}

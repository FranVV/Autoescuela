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
    /**
     * 
     * @param nombre Strin del nombre de la persona
     * @param pass string de la contraseña de la persona
     * @return true si la contraseña y usuario coinciden sino false
     */
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
    /**
     * 
     * @param id String 
     * @return comprueba si el id se encuentra en la bbdd si lo esta devuelde true si no false
     */
    public boolean comprobarid(String id) {
        boolean encontrado =false;
        try {
            PreparedStatement st;
            conectar();
            st = con.prepareStatement("Select id from persona where id = ?;");
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
                            
            while (rs.next()) {
                    if (!rs.getString(1).equalsIgnoreCase(null)){
                        encontrado =  true;
                    }
                        
            }
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Fallo al consultar los usuarios y contraseñas");
        }
        
        return encontrado ; 
    }
    
    /**
     * 
     * @param dniAlumno String
     * @return devuelve el nombre 
     */
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
    /**
     * 
     * @param fecha String 
     * @return devuelve las clases que hay entre la fecha pasada + 4 dias
     */
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
    /**
     * 
     * @param tabla String 
     * @param campo String
     * @return devuelve el datos selecionado en un ArrayList
     */
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
    /**
     * 
     * @param tabla String  
     * @param campo String
     * @return muestra los datos de clases a partir del dia en curso
     */
    public ArrayList<String> mostrarDatosClases( String tabla, String campo) {
        ArrayList<String> vMatriculas = new ArrayList<>();
        try {
            conectar();
            PreparedStatement pt;
            JCalendar jCalendar1 = new JCalendar();
           
            Date fecha = jCalendar1.getCalendar().getTime();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaS = formato.format(fecha);

            pt = con.prepareStatement("Select "+campo+" from "+tabla+" where fecha>=now()- (1100000) ;");
            
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
    /**
     * 
     * @param tabla String
     * @param campo String
     * @param nombre String
     * @return mostrar datos por nombre 
     */
    public ArrayList<String> mostrarDatosTablaporCaposconcretosNombreAlumno( String tabla, String campo, String nombre) {
        ArrayList<String> vMatriculas = new ArrayList<>();
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("Select "+campo+" from "+tabla+" where nombre='"+nombre+"';");
            
            ResultSet rs = pt.executeQuery();
                            
            while (rs.next()) {
                if(rs.getString(6).equalsIgnoreCase("Alumno")){
                    
                    vMatriculas.add("Nombre: "+rs.getString(1)+" | ID: "+rs.getString(8)+" | DNI: "+rs.getString(2)+" |Fecha_Nacimiento: "+rs.getString(3)+" | Permisos_Posesion: "+rs.getString(4)+" | TLF: "+rs.getString(5)+" | Correo: "+rs.getString(7)+" | Teorica: "+rs.getBoolean(9)+" | Practica: "+rs.getString(10));
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
    /**
     * 
     * @param tabla String
     * @param campo String
     * @param nombre String
     * @return mostrar los datos del profesor nombre y dni
     */
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
    /**
     * 
     * @param tabla String
     * @param campo String
     * @param matricula String
     * @return devuelve los con una matricula concreta
     */
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
    /**
     * 
     * @param tabla String
     * @param campo String
     * @param condicion String
     * @return motrar los datos pr tipo 
     */
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
    /**
     * 
     *  @param tabla String
     * @param campo String
     * @param condicion String
     * @param condicion2 boolean
     * @return devuelde los datos filtrando pro tipo y teoria
     */
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
    /**
     * 
     * @param tabla String
     * @param condicion String
     * @param dato String
     * @return eliminacion de datos 
     */
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
    }/**
     * 
     * @param tabla String
     * @param condicion String
     * @param dato String
     * @param condicion2 String
     * @param dato2 String
     * @return  true si elimna el dato y fglase sino
     */
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
    /**
     * 
     * @param matricula pra coche moto camion
     * @param modelo  para coche moto camion
     * @param potencia para moto 
     * @param tipo para camion
     * @return  true si inserta el dato y fglase sino de vehiculo
     */
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
    /**
     * 
     * @param matricula String
     * @param idAlumno String
     * @param dniProfesor String
     * @param fecha String
     * @param Hora int
     * @return  true si inserta el dato y fglase sino de clase
     */
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
   
    /**
     * 
     * @param nombre String
     * @param dni String
     * @param fechanacimiento String
     * @param permisos String
     * @param tlf int
     * @param tipo String
     * @param correo String
     * @param id String
     * @param admin boolean
     * @param contraseña String
     * @return  true si inserta el dato y fglase sino de persona
     */
    public boolean insertarDatosPersona( String nombre,String dni,String fechanacimiento, String permisos,int tlf,String tipo,String correo,int id,boolean admin,String contraseña) {
        boolean bandera= false;
        try {
            conectar();
            PreparedStatement pt;
            
            pt = con.prepareStatement("INSERT INTO `persona`(`nombre`, `dni`, `fechaNacimiento`, `permisosPosee`, `nTelefono`, `tipo`, `correo`, `id`, `admin`, `contraseña`, `teorica`, `practica`) VALUES ('"+nombre+"','"+dni+"','"+fechanacimiento+"','"+permisos+"',"+tlf+",'"+tipo+"','"+correo+"',"+id+","+admin+",'"+contraseña+"',false,false);");
            
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
    /**
     * 
     * @param teorica String
     * @param practica String
     * @param id String
     * @return  true si aptualiza el dato y fglase sino de alumno
     */
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

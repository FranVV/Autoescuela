/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BBDD;


import Clases.Alumno;
import Clases.Persona;
import Clases.Profesor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    
    
    
}

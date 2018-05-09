/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author alumno
 */
public final class Alumno extends Persona{
    private String correo;
    private int id;

    public Alumno(String correo, int id, String nombre, String dni, String fechaNacimiento, String premisosPosee, String contraseña, int telefono) {
        super(nombre, dni, fechaNacimiento, premisosPosee, contraseña, telefono);
        this.correo = correo;
        this.id = id;
    }

    

    @Override
    public String toString() {
        return super.toString()+", correo= "+getCorreo()+", Id= "+getId(); 
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}

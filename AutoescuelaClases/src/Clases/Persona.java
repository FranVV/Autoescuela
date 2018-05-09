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
public  abstract class Persona {
    protected String nombre;
    protected  String dni;
    protected  String fechaNacimiento;
    protected  String premisosPosee;
    protected  String contraseña;
    protected  int telefono;

    public Persona(String nombre, String dni, String fechaNacimiento, String premisosPosee, String contraseña, int telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.premisosPosee = premisosPosee;
        this.contraseña = contraseña;
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "nombre=" + nombre + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", premisosPosee=" + premisosPosee + ", contrase\u00f1a=" + contraseña + ", telefono=" + telefono;
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return the fechaNacimiento
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to set
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the premisosPosee
     */
    public String getPremisosPosee() {
        return premisosPosee;
    }

    /**
     * @param premisosPosee the premisosPosee to set
     */
    public void setPremisosPosee(String premisosPosee) {
        this.premisosPosee = premisosPosee;
    }

    /**
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
    
    
    
}

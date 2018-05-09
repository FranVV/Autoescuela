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
    private String nombre;
    private  String dni;
    private  String fechaNacimiento;
    private  String premisosPosee;
    private  String contraseña;
    private  int telefono;

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
        return "nombre=" + getNombre() + ", dni=" + getDni() + ", fechaNacimiento=" + getFechaNacimiento() + ", premisosPosee=" + getPremisosPosee() + ", contrase\u00f1a=" + getContraseña() + ", telefono=" + getTelefono();
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

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    
    
    
}

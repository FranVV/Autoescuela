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
public final class Profesor extends Persona implements Interfaz.Interface{
    private boolean admin;
    private Vehiculo vVehiculo;

    public Profesor(String contraseña, String usuario) {
        super(usuario, null, null, null, contraseña, 0);
    }

    public Profesor(boolean admin, Vehiculo vVehiculo, String nombre, String dni, String fechaNacimiento, String premisosPosee, String contraseña, int telefono) {
        super(nombre, dni, fechaNacimiento, premisosPosee, contraseña, telefono);
        this.admin = admin;
        this.vVehiculo = vVehiculo;
    }

    

    @Override
    public String toString() {
        return super.toString()+", Admin= "+isAdmin()+", Vehiculos= "+getvVehiculo(); 
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @return the vVehiculo
     */
    public Vehiculo getvVehiculo() {
        return vVehiculo;
    }

    /**
     * @param vVehiculo the vVehiculo to set
     */
    public void setvVehiculo(Vehiculo vVehiculo) {
        this.vVehiculo = vVehiculo;
    }

    @Override
    public void comprobarClases() {
        
    }
    
    
}

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
public final class Coche extends Vehiculo{
    private String modelo;

    public Coche(String modelo, String Matricula) {
        super(Matricula);
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return super.toString()+", modelo= "+getModelo();
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
}

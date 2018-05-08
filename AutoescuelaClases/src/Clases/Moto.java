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
public final class Moto extends Vehiculo{
    private String potencia;

    public Moto(String potencia, String Matricula) {
        super(Matricula);
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return super.toString()+", potencia= "+getPotencia(); 
    }

    /**
     * @return the potencia
     */
    public String getPotencia() {
        return potencia;
    }

    /**
     * @param potencia the potencia to set
     */
    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

   
    
}

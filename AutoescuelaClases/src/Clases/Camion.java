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
public final class Camion extends Vehiculo{
    private String tipo;

    public Camion(String tipo, String Matricula) {
        super(Matricula);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString()+", tipo= "+getTipo(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}

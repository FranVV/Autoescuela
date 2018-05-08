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
public abstract class Vehiculo {
    protected  String Matricula;

    public Vehiculo(String Matricula) {
        this.Matricula = Matricula;
    }

    @Override
    public String toString() {
        return "Matricula= " + getMatricula();
    }

    /**
     * @return the Matricula
     */
    public String getMatricula() {
        return Matricula;
    }

    /**
     * @param Matricula the Matricula to set
     */
    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    
    
}

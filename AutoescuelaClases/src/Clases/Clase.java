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
public class Clase {

    private Alumno alumno;
    private Profesor profesor;
    private Vehiculo vehiculo;
    private Calendario calendario;
    private boolean asignado;

    public Clase(Alumno alumno, Profesor profesor, Vehiculo vehiculo, boolean asignado) {
        this.alumno = alumno;
        this.profesor = profesor;
        this.vehiculo = vehiculo;
        this.calendario = new Calendario();
        this.asignado = asignado;
    }

    @Override
    public String toString() {
        return "alumno=" + alumno + ", profesor=" + profesor + ", vehiculo=" + vehiculo + ", calendario=" + calendario + ", asignado=" + asignado;
    }

}

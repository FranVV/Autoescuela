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
    private String calendario;
    private boolean asignado;

    public Clase(Alumno alumno, Profesor profesor, Vehiculo vehiculo, String calendario, boolean asignado) {
        this.alumno = alumno;
        this.profesor = profesor;
        this.vehiculo = vehiculo;
        this.calendario = calendario;
        this.asignado = asignado;
    }

    

    @Override
    public String toString() {
        return "alumno=" + getAlumno() + ", profesor=" + getProfesor() + ", vehiculo=" + getVehiculo() + ", calendario=" + getCalendario() + ", asignado=" + isAsignado();
    }

    /**
     * @return the alumno
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * @param alumno the alumno to set
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    /**
     * @return the profesor
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     * @param profesor the profesor to set
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /**
     * @return the vehiculo
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the calendario
     */
    public String getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(String calendario) {
        this.calendario = calendario;
    }

    /**
     * @return the asignado
     */
    public boolean isAsignado() {
        return asignado;
    }

    /**
     * @param asignado the asignado to set
     */
    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }
    
}

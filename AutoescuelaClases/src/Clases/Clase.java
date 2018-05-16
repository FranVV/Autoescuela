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

    private String alumnodni;
    private String profesordni;
    private String matricula;
    private String fecha;
    private int hora;
    private boolean asignado;

    public Clase(String alumnodni, String profesordni, String matricula, String fecha, int hora, boolean asignado) {
        this.alumnodni = alumnodni;
        this.profesordni = profesordni;
        this.matricula = matricula;
        this.fecha = fecha;
        this.hora = hora;
        this.asignado = asignado;
    }

    @Override
    public String toString() {
        return "alumnodni=" + getAlumnodni() + ", profesordni=" + getProfesordni() + ", matricula=" + getMatricula() + ", fecha=" + getFecha() + ", hora=" + getHora() + ", asignado=" + isAsignado();
    }

    /**
     * @return the alumnodni
     */
    public String getAlumnodni() {
        return alumnodni;
    }

    /**
     * @param alumnodni the alumnodni to set
     */
    public void setAlumnodni(String alumnodni) {
        this.alumnodni = alumnodni;
    }

    /**
     * @return the profesordni
     */
    public String getProfesordni() {
        return profesordni;
    }

    /**
     * @param profesordni the profesordni to set
     */
    public void setProfesordni(String profesordni) {
        this.profesordni = profesordni;
    }

    /**
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public int getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(int hora) {
        this.hora = hora;
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

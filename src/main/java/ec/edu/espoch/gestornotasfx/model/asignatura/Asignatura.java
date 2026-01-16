/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asignatura;

import ec.edu.espoch.gestornotasfx.model.docentes.Docente;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiante;

/**
 *
 * @author Admin
 */
public class Asignatura {

    private int codigoAsignatura;
    private Docente docente;
    private String nombreAsignatura;
    private String periodo;

    public Asignatura() {
    }
    
    

    public Asignatura(int codigoAsignatura, String nombreAsignatura, String periodo, Docente docente) {
        this.codigoAsignatura = codigoAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.periodo = periodo;
        this.docente = docente; // El docente que tiene la cédula (FK)
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(int codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "codigoAsignatura=" + codigoAsignatura + ", nombreAsignatura=" + nombreAsignatura + ", periodo=" + periodo + '}';
    }
}

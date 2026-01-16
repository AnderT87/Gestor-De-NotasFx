/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.estudiantes;

import ec.edu.espoch.gestornotasfx.model.docentes.Docente;

/**
 *
 * @author Admin
 */
public class Estudiante  {
    
    
    private int codigoEstudiante;
    private String nombreEstudiante;
    private String apellidoEstudiantes;
    private String correoInstEstudinate;
    
    public Estudiante() {
        
    }

    public Estudiante(int codigoEstudiante, String nombreEstudiante, String apellidoEstudiantes, String correoInstEstudinate) {
        this.codigoEstudiante = codigoEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.apellidoEstudiantes = apellidoEstudiantes;
        this.correoInstEstudinate = correoInstEstudinate;
    }

    

    public int getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(int codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getApellidoEstudiantes() {
        return apellidoEstudiantes;
    }

    public void setApellidoEstudiantes(String apellidoEstudiantes) {
        this.apellidoEstudiantes = apellidoEstudiantes;
    }

    public String getCorreoInstEstudinate() {
        return correoInstEstudinate;
    }

    public void setCorreoInstEstudinate(String correoInstEstudinate) {
        this.correoInstEstudinate = correoInstEstudinate;
    }

    @Override
    public String toString() {
        return "Estudiantes{" + "codigoEstudiante=" + codigoEstudiante + ", nombreEstudiante=" + nombreEstudiante + ", apellidoEstudiantes=" + apellidoEstudiantes + ", correoInstEstudinate=" + correoInstEstudinate + '}';
    }
}

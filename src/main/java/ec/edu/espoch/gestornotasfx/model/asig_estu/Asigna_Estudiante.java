/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asig_estu;

import ec.edu.espoch.gestornotasfx.model.asignatura.Asignatura;

import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiante;

/**
 *
 * @author Admin
 */
public class Asigna_Estudiante {

    private Asignatura asignatura;
    private Estudiante estudiantes;

    private String fecha;
    private double recuperacion;
    private double promedioCiclos;
    private double medioCiclo;
    private double finCiclo;

    public Asigna_Estudiante() {
    }

    public Asigna_Estudiante(Asignatura asignatura, Estudiante estudiantes, String fecha, double recuperacion, double promedioCiclos, double medioCiclo, double finCiclo) {
        this.asignatura = asignatura;
        this.estudiantes = estudiantes;
        this.fecha = fecha;
        this.recuperacion = recuperacion;
        this.promedioCiclos = promedioCiclos;
        this.medioCiclo = medioCiclo;
        this.finCiclo = finCiclo;
    }

    public double calcularPromedio() {
        double base = (medioCiclo + finCiclo) / 2;
        return recuperacion > base ? recuperacion : base;
    }

    public double getPromedioCiclos() {
        return calcularPromedio();
    }

    public void setPromedioCiclos(double promedioCiclos) {
        this.promedioCiclos = promedioCiclos;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Estudiante getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Estudiante estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getRecuperacion() {
        return recuperacion;
    }

    public void setRecuperacion(double recuperacion) {
        this.recuperacion = recuperacion;
    }

    public double getMedioCiclo() {
        return medioCiclo;
    }

    public void setMedioCiclo(double medioCiclo) {
        this.medioCiclo = medioCiclo;
    }

    public double getFinCiclo() {
        return finCiclo;
    }

    public void setFinCiclo(double finCiclo) {
        this.finCiclo = finCiclo;
    }

    @Override
    public String toString() {
        return "Asigna_Estudiante{" + "asignatura=" + asignatura + ", estudiantes=" + estudiantes + ", fecha=" + fecha + ", recuperacion=" + recuperacion + ", medioCiclo=" + medioCiclo + ", finCiclo=" + finCiclo + '}';
    }

}

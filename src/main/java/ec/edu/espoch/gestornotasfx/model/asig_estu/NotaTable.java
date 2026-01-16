/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asig_estu;

/**
 *
 * @author Admin
 */
public class NotaTable {

    private String estudiante;
    private String asignatura;
    private String fecha;
    private double medioCiclo;
    private double finCiclo;
    private double promedioCiclos;
    private double recuperacion;

    public NotaTable(Asigna_Estudiante ae) {

        // Estudiante
        this.estudiante
                = ae.getEstudiantes().getCodigoEstudiante() + " - "
                + ae.getEstudiantes().getNombreEstudiante() + " "
                + ae.getEstudiantes().getApellidoEstudiantes();

        // Asignatura
        this.asignatura = ae.getAsignatura().getNombreAsignatura();

        this.fecha = ae.getFecha();
        this.medioCiclo = ae.getMedioCiclo();
        this.finCiclo = ae.getFinCiclo();
        this.promedioCiclos = ae.getPromedioCiclos();
        this.recuperacion = ae.getRecuperacion();
    }

    public String getEstudiante() {
        return estudiante;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public String getFecha() {
        return fecha;
    }

    public double getMedioCiclo() {
        return medioCiclo;
    }

    public double getFinCiclo() {
        return finCiclo;
    }

    public double getPromedioCiclos() {
        return promedioCiclos;
    }

    public double getRecuperacion() {
        return recuperacion;
    }
}

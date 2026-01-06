/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asig_estu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
import java.util.ArrayList;
import java.util.List;

public class Asigna_Estudiantes implements IAsigna_Estudiantes{
    
    private List<Asigna_Estudiante> listaAsignaciones;

    public Asigna_Estudiantes() {
        this.listaAsignaciones = new ArrayList<>();
    }

    // CREATE: Agregar una nueva nota
    public boolean agregar(Asigna_Estudiante nueva) {
        // Validación: Evitar que el mismo estudiante tenga dos registros en la misma materia
        for (Asigna_Estudiante ae : listaAsignaciones) {
            if (ae.getEstudiantes().getCodigoEstudiante() == nueva.getEstudiantes().getCodigoEstudiante() &&
                ae.getAsignatura().getCodigoAsignatura() == nueva.getAsignatura().getCodigoAsignatura()) {
                return false; // Ya existe el registro
            }
        }
        return listaAsignaciones.add(nueva);
    }

    // READ: Obtener todos los registros
    public List<Asigna_Estudiante> obtenerTodo() {
        return new ArrayList<>(listaAsignaciones);
    }

    // READ ONE: Buscar por código de estudiante y asignatura
    public Asigna_Estudiante buscar(int codEstudiante, int codAsignatura) {
        for (Asigna_Estudiante ae : listaAsignaciones) {
            if (ae.getEstudiantes().getCodigoEstudiante() == codEstudiante &&
                ae.getAsignatura().getCodigoAsignatura() == codAsignatura) {
                return ae;
            }
        }
        return null;
    }

    // UPDATE: Actualizar notas de un registro existente
    public boolean actualizar(Asigna_Estudiante aeActualizada) {
        for (int i = 0; i < listaAsignaciones.size(); i++) {
            Asigna_Estudiante actual = listaAsignaciones.get(i);
            if (actual.getEstudiantes().getCodigoEstudiante() == aeActualizada.getEstudiantes().getCodigoEstudiante() &&
                actual.getAsignatura().getCodigoAsignatura() == aeActualizada.getAsignatura().getCodigoAsignatura()) {
                
                listaAsignaciones.set(i, aeActualizada);
                return true;
            }
        }
        return false;
    }

    // DELETE: Eliminar un registro
    public boolean eliminar(int codEstudiante, int codAsignatura) {
        return listaAsignaciones.removeIf(ae -> 
            ae.getEstudiantes().getCodigoEstudiante() == codEstudiante &&
            ae.getAsignatura().getCodigoAsignatura() == codAsignatura
        );
    }
}
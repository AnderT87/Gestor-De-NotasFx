/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asig_estu;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface IAsigna_Estudiantes {

    public boolean agregar(Asigna_Estudiante nueva);

    public boolean actualizar(Asigna_Estudiante aeActualizada);

    public boolean eliminar(int codEstudiante, int codAsignatura);

    public Asigna_Estudiante buscar(int codEstudiante, int codAsignatura);
    
    public List<Asigna_Estudiante> obtenerTodo();
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.estudiantes;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface IEstudiantes {
     public void agregar(Estudiante estudiante);
    public void actualizar(Estudiante estudianteActualizado);
    public void eliminar(int codigoEstudiante);
    public Estudiante obtener(int codigoEstudiante);
    public List<Estudiante> obtenerTodos();
}

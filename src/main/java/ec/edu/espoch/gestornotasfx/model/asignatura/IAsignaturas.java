/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asignatura;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface IAsignaturas {

    public void agregar(Asignatura asignatura);

    public void actualizar(Asignatura asignaturaActualizada);

    public void eliminar(int codigoAsignatura);

    public Asignatura obtener(int codigoAsignatura);

    public ArrayList<Asignatura> obtenerTodos();
}

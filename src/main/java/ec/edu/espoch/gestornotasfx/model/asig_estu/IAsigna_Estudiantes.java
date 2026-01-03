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

    public void agregar(Asigna_Estudiante ae);

    public void actualizar(Asigna_Estudiante ae);

    public void eliminar(String fecha, int codA, int codE);

    public List<Asigna_Estudiante> buscar(int codA, String nombrePeriodo);
    
    public List<Asigna_Estudiante> obtenerTodo();
}

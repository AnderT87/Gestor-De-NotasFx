/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asig_estu;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IAsigna_Estudiantes {

    public boolean agregar(Asigna_Estudiante nueva, String strMedio, String strFin, String strRecu) throws SQLException; ;

   public boolean actualizar(Asigna_Estudiante aeActualizada, String strMedio, String strFin, String strRecu) throws SQLException;

    public boolean eliminar(int codEstudiante, int codAsignatura) throws SQLException;

    public Asigna_Estudiante obtener(int codEstudiante, int codAsignatura) throws SQLException;
    
    public List<Asigna_Estudiante> obtenerTodo() throws SQLException;
}

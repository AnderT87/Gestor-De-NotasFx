/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.estudiantes;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IEstudiantes {

    public void agregar(Estudiante estudiante) throws SQLException;

    public boolean actualizar(int codigo_estudiante, Estudiante nuevo) throws SQLException ;

    public boolean eliminar(int codigoEstudiante) throws SQLException;

    public Estudiante obtener(int codigoEstudiante) throws SQLException;

    public List<Estudiante> Listar() throws SQLException ;
}

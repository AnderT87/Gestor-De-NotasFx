/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asignatura;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IAsignaturas {

    public void agregar(Asignatura asignatura) throws SQLException;

    public void actualizar(Asignatura asignaturaActualizada) throws SQLException;

    public void eliminar(int codigoAsignatura) throws SQLException;

    public Asignatura obtener(int codigoAsignatura) throws SQLException ;

    public List<Asignatura> obtenerTodos() throws SQLException ;
}

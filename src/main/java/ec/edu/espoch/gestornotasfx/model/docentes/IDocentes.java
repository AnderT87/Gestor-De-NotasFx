/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.docentes;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IDocentes {
    public void agregar(Docente d) throws SQLException;
    public Docente obtener(String cedula) throws SQLException;
    public boolean actualizar(String cedula, Docente nuevo) throws SQLException;
    public boolean eliminar(String cedula) throws SQLException ;
    public List<Docente> listar() throws SQLException;
}

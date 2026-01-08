/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.estudiantes;

import ec.edu.espoch.gestornotasfx.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Estudiantes implements IEstudiantes {

    private List<Estudiante> estudiantes;

    public Estudiantes() {
        estudiantes = new ArrayList<>();
    }

    public void agregar(Estudiante estudiante) throws SQLException {
        
        String sql = "INSERT INTO estudiantes(codigo_estudiante, nombre, apellido, correo_institucional) VALUES(?,?,?,?)";

        
        String textoCodigo = String.valueOf(estudiante.getCodigoEstudiante());

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, textoCodigo);
            ps.setString(2, estudiante.getNombreEstudiante());
            ps.setString(3, estudiante.getApellidoEstudiantes());
            ps.setString(4, estudiante.getCorreoInstEstudinate());

           
            ps.executeUpdate();

            
            if (this.estudiantes != null) {
                this.estudiantes.add(estudiante);
            }
        }
    }

    public boolean actualizar(int codigo, Estudiante actualizado) throws SQLException {
        
        String sql = "UPDATE estudiantes SET nombre = ?, apellido = ?, correo_institucional = ? WHERE codigo_estudiante = ?";

        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            
            ps.setString(1, actualizado.getNombreEstudiante());

            
            ps.setString(2, actualizado.getApellidoEstudiantes());

           
            ps.setString(3, actualizado.getCorreoInstEstudinate());

            
            ps.setString(4, String.valueOf(codigo));

            int filas = ps.executeUpdate();

            return filas > 0;
        }
    }

    @Override
    public boolean eliminar(int codigoEstudiante) throws SQLException {
        
        String sql = "DELETE FROM estudiantes WHERE codigo_estudiante = ?";

        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

           
             
            ps.setString(1, String.valueOf(codigoEstudiante));

           
            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        }
    }

    @Override
    public Estudiante obtener(int codigoEstudiante) throws SQLException {
        String sql = "SELECT * FROM estudiantes WHERE codigo_estudiante = ?";

        try (Connection conn = Conexion.getConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, String.valueOf(codigoEstudiante));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    return new Estudiante(
                            
                            rs.getInt("codigo_estudiante"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo_institucional")
                    );
                }
            }
        }
        return null;
    }

    public List<Estudiante> Listar() throws SQLException {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";

        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int codigo = Integer.parseInt(rs.getString("codigo_estudiante"));
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo_institucional");
                lista.add(new Estudiante(codigo, nombre, apellido, correo));
            }
            return lista;
        } catch (Exception e) {
        }
        return estudiantes;
    }
}

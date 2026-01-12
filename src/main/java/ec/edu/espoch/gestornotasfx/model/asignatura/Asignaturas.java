/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asignatura;

import ec.edu.espoch.gestornotasfx.Conexion;
import ec.edu.espoch.gestornotasfx.model.docentes.Docente;
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
public class Asignaturas implements IAsignaturas {

    private List<Asignatura> asignaturas;

    public Asignaturas() {
        asignaturas = new ArrayList<>();
    }

    @Override
    public void agregar(Asignatura asignatura) throws SQLException {
        // El SQL debe incluir la columna de la cédula (ajusta el nombre según tu BD)
        String sql = "INSERT INTO asignatura(codigo_asignatura, nombre, periodo, cedula) VALUES(?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, asignatura.getCodigoAsignatura());
            ps.setString(2, asignatura.getNombreAsignatura());
            ps.setString(3, asignatura.getPeriodo());
            // Extraemos la cédula del objeto Docente que tiene la asignatura
            ps.setString(4, asignatura.getDocente().getCedula());

            ps.executeUpdate();
            asignaturas.add(asignatura); // Actualiza lista local
        }
    }

    @Override
    public void actualizar(Asignatura asignaturaActualizada) throws SQLException {
        String sql = "UPDATE asignatura SET nombre = ?, periodo = ?, cedula = ? WHERE codigo_asignatura = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, asignaturaActualizada.getNombreAsignatura());
            ps.setString(2, asignaturaActualizada.getPeriodo());
            // Enviamos la cédula del objeto docente vinculado
            ps.setString(3, asignaturaActualizada.getDocente().getCedula());
            ps.setInt(4, asignaturaActualizada.getCodigoAsignatura());

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(int codigoAsignatura) throws SQLException {
        String sql = "DELETE FROM asignatura WHERE codigo_asignatura = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoAsignatura);
            ps.executeUpdate();
        }
    }

    @Override
    public Asignatura obtener(int codigoAsignatura) throws SQLException {
        String sql = "SELECT * FROM asignatura WHERE codigo_asignatura = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigoAsignatura);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Creamos el docente solo con la cédula (luego podrías cargar sus datos completos)
                    Docente d = new Docente();
                    d.setCedula(rs.getString("cedula"));

                    return new Asignatura(
                            rs.getInt("codigo_asignatura"),
                            rs.getString("nombre"),
                            rs.getString("periodo"),
                            d
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Asignatura> obtenerTodos() throws SQLException {
        List<Asignatura> lista = new ArrayList<>();
        String sql = "SELECT * FROM asignatura";

        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Docente d = new Docente();
                d.setCedula(rs.getString("cedula"));

                lista.add(new Asignatura(
                        rs.getInt("codigo_asignatura"),
                        rs.getString("nombre"),
                        rs.getString("periodo"),
                        d
                ));
            }
        }
        return lista;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asig_estu;

import ec.edu.espoch.gestornotasfx.Conexion;
import ec.edu.espoch.gestornotasfx.model.asignatura.Asignatura;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiante;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asigna_Estudiantes implements IAsigna_Estudiantes {

    private List<Asigna_Estudiante> listaAsignaciones;

    public Asigna_Estudiantes() {
        this.listaAsignaciones = new ArrayList<>();
    }

    // CREATE: Agregar una nueva nota
    @Override
    public boolean agregar(Asigna_Estudiante nueva, String strMedio, String strFin, String strRecu) throws SQLException {

        String sqlCheck = "SELECT COUNT(*) FROM asignatura_estudiante WHERE codigo_asignatura = ? AND codigo_estudiante = ?";

        String sqlInsert = "INSERT INTO asignatura_estudiante (codigo_asignatura, codigo_estudiante, medio_ciclo, fin_ciclo, recuperacion) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion()) {

            try (PreparedStatement psCheck = con.prepareStatement(sqlCheck)) {
                psCheck.setString(1, String.valueOf(nueva.getAsignatura().getCodigoAsignatura()));
                psCheck.setString(2, String.valueOf(nueva.getEstudiantes().getCodigoEstudiante()));

                try (ResultSet rs = psCheck.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {

                        return false;
                    }
                }
            }

            try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
                psInsert.setString(1, String.valueOf(nueva.getAsignatura().getCodigoAsignatura()));
                psInsert.setString(2, String.valueOf(nueva.getEstudiantes().getCodigoEstudiante()));
                psInsert.setString(3, String.valueOf(nueva.getMedioCiclo()));
                psInsert.setString(4, String.valueOf(nueva.getFinCiclo()));
                psInsert.setString(5, String.valueOf(nueva.getRecuperacion()));

                int filasAfectadas = psInsert.executeUpdate();
                return filasAfectadas > 0;
            }
        }
    }

    @Override
    public boolean actualizar(Asigna_Estudiante aeActualizada, String strMedio, String strFin, String strRecu) throws SQLException {
        // Definimos la consulta SQL apuntando a las notas y filtrando por las llaves foráneas
        String sql = "UPDATE asignatura_estudiante SET medio_ciclo = ?, fin_ciclo = ?, recuperacion = ? "
                + "WHERE codigo_estudiante = ? AND codigo_asignatura = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            // 1. Convertimos los Strings a Float de forma segura
            // Si el campo está vacío, ponemos 0.0f; si tiene coma, la cambiamos por punto
            float medio = (strMedio == null || strMedio.isEmpty()) ? 0.0f : Float.parseFloat(strMedio.replace(',', '.'));
            float fin = (strFin == null || strFin.isEmpty()) ? 0.0f : Float.parseFloat(strFin.replace(',', '.'));
            float recu = (strRecu == null || strRecu.isEmpty()) ? 0.0f : Float.parseFloat(strRecu.replace(',', '.'));

            // 2. Pasamos los valores de las notas al PreparedStatement
            ps.setFloat(1, medio);
            ps.setFloat(2, fin);
            ps.setFloat(3, recu);

            // 3. Pasamos los códigos (llaves foráneas) para identificar el registro
            // Convertimos a String porque en tu BD son VARCHAR(10)
            ps.setString(4, String.valueOf(aeActualizada.getEstudiantes().getCodigoEstudiante()));
            ps.setString(5, String.valueOf(aeActualizada.getAsignatura().getCodigoAsignatura()));

            // 4. Ejecutamos la actualización
            int filasAfectadas = ps.executeUpdate();

            // Si filasAfectadas > 0, significa que se encontró el registro y se actualizó
            return filasAfectadas > 0;

        } catch (NumberFormatException e) {
            // Lanzamos una excepción clara si el usuario escribió algo que no es un número
            throw new SQLException("Error de formato: Las notas deben ser números válidos.");
        }
    }

    @Override
    public boolean eliminar(int codEstudiante, int codAsignatura) throws SQLException {
        // Definimos la consulta SQL para borrar el registro específico
        String sql = "DELETE FROM asignatura_estudiante WHERE codigo_estudiante = ? AND codigo_asignatura = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Pasamos los parámetros al PreparedStatement
            // Convertimos a String con String.valueOf() porque en tu BD son VARCHAR(10)
            ps.setString(1, String.valueOf(codEstudiante));
            ps.setString(2, String.valueOf(codAsignatura));

            // Ejecutamos la operación
            int filasAfectadas = ps.executeUpdate();

            // Si filasAfectadas > 0, significa que el registro existía y fue eliminado
            return filasAfectadas > 0;

        } catch (SQLException e) {
            // Log del error o relanzar la excepción para que el controlador la maneje
            throw new SQLException("Error al intentar eliminar la asignación: " + e.getMessage());
        }
    }

    @Override
    public Asigna_Estudiante obtener(int codEstudiante, int codAsignatura) throws SQLException {
        // Consulta SQL con JOIN para traer los nombres reales desde las otras tablas
        String sql = "SELECT ae.*, e.nombre, e.apellido, a.nombre_asignatura "
                + "FROM asignatura_estudiante ae "
                + "INNER JOIN estudiantes e ON ae.codigo_estudiante = e.codigo_estudiante "
                + "INNER JOIN asignatura a ON ae.codigo_asignatura = a.codigo_asignatura "
                + "WHERE ae.codigo_estudiante = ? AND ae.codigo_asignatura = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, String.valueOf(codEstudiante));
            ps.setString(2, String.valueOf(codAsignatura));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // 1. Creamos el objeto Estudiante con sus datos de la BD
                    Estudiante est = new Estudiante(
                            Integer.parseInt(rs.getString("codigo_estudiante")),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            "" // Correo (si no lo necesitas aquí)
                    );

                    // 2. Creamos el objeto Asignatura con sus datos de la BD
                    Asignatura asig = new Asignatura(
                            Integer.parseInt(rs.getString("codigo_asignatura")),
                            rs.getString("nombre_asignatura"),
                            "", // Periodo
                            null // Docente
                    );

                    // 3. Construimos el objeto de asignación con las notas
                    Asigna_Estudiante ae = new Asigna_Estudiante();
                    ae.setEstudiantes(est);
                    ae.setAsignatura(asig);
                    ae.setMedioCiclo(rs.getFloat("medio_ciclo"));
                    ae.setFinCiclo(rs.getFloat("fin_ciclo"));
                    ae.setRecuperacion(rs.getFloat("recuperacion"));

                    return ae;
                }
            }
        }
        return null; // Si no se encuentra el registro
    }

    @Override
    public List<Asigna_Estudiante> obtenerTodo() throws SQLException {
        List<Asigna_Estudiante> lista = new ArrayList<>();

        // Consulta con JOIN para traer información de las 3 tablas relacionadas
        String sql = "SELECT ae.*, e.nombre AS nombre_est, e.apellido AS apellido_est, a.nombre "
                + "FROM asignatura_estudiante ae "
                + "INNER JOIN estudiantes e ON ae.codigo_estudiante = e.codigo_estudiante "
                + "INNER JOIN asignatura a ON ae.codigo_asignatura = a.codigo_asignatura";

        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // 1. Creamos el objeto Estudiante con sus datos reales
                Estudiante est = new Estudiante(
                        Integer.parseInt(rs.getString("codigo_estudiante")),
                        rs.getString("nombre_est"),
                        rs.getString("apellido_est"),
                        "" // El correo no suele ser necesario para la tabla resumen
                );

                // 2. Creamos el objeto Asignatura con su nombre real
                Asignatura asig = new Asignatura(
                        Integer.parseInt(rs.getString("codigo_asignatura")),
                        rs.getString("nombre"),
                        "", // Periodo
                        null // Docente (no es necesario para esta vista)
                );

                // 3. Creamos el objeto principal y le pasamos las notas (float)
                Asigna_Estudiante ae = new Asigna_Estudiante();
                ae.setEstudiantes(est);
                ae.setAsignatura(asig);
                ae.setMedioCiclo(rs.getFloat("medio_ciclo"));
                ae.setFinCiclo(rs.getFloat("fin_ciclo"));
                ae.setRecuperacion(rs.getFloat("recuperacion"));

                // Opcional: Si quieres guardar la fecha que genera el current_timestamp
                // ae.setFecha(rs.getTimestamp("fecha")); 
                lista.add(ae);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al cargar la lista de asignaciones: " + e.getMessage());
        }

        return lista;
    }

    public List<Map<String, String>> consultarNotasPorId(String idEstudiante) {
    List<Map<String, String>> lista = new ArrayList<>();
    String sql = "SELECT e.nombre, e.apellido, a.nombre AS asig_nom, ae.medio_ciclo, ae.fin_ciclo "
               + "FROM estudiantes e "
               + "INNER JOIN asignatura_estudiante ae ON e.codigo_estudiante = ae.codigo_estudiante "
               + "INNER JOIN asignatura a ON ae.codigo_asignatura = a.codigo_asignatura "
               + "WHERE e.codigo_estudiante = ?";

    try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, idEstudiante);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, String> fila = new HashMap<>();
            String nombreFull = rs.getString("nombre") + " " + rs.getString("apellido");
            fila.put("nombreCompleto", nombreFull);
            fila.put("materia", rs.getString("asig_nom"));
            fila.put("notaUno", rs.getString("medio_ciclo"));
            fila.put("notaDos", rs.getString("fin_ciclo"));
            
            // ESTO ES PARA DEPURAR:
            System.out.println("Fila encontrada: " + nombreFull + " - " + rs.getString("asig_nom"));
            
            lista.add(fila);
        }
        
        if (lista.isEmpty()) {
            System.out.println("OJO: La consulta no devolvió nada para el ID: " + idEstudiante);
        }

    } catch (SQLException e) {
        System.err.println("Error en la consulta: " + e.getMessage());
    }
    return lista;
}
}

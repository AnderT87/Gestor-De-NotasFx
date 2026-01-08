/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.docentes;

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
public class Docentes implements IDocentes {

    private List<Docente> docentes;

    public Docentes() {
        docentes = new ArrayList<>();
    }

    
    public void agregar(Docente d) throws SQLException {

        String sql = "INSERT INTO docentes(cedula,nombre,apellido,correo_institucional,titulo_academico) VALUES(?,?,?,?,?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getCedula());
            ps.setString(2, d.getNombre());
            ps.setString(3, d.getApellido());
            ps.setString(4, d.getCorreo_Institucional());
            ps.setString(5, d.getTitulo_Academico());
            ps.executeUpdate();
        }
        docentes.add(d);
    }

    public Docente obtener(String cedula) throws SQLException {
    String sql = "SELECT * FROM docentes WHERE cedula = ?";

    try (Connection conn = Conexion.getConexion();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, cedula);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                
                Docente d = new Docente(
                    rs.getString("cedula"),
                    rs.getString("nombre"),   
                    rs.getString("apellido"), 
                    rs.getString("correo_institucional"),
                    rs.getString("titulo_academico")
                );
                return d;
            }
        }
    }
    
    return null;
}


    @Override
    public boolean actualizar(String cedula, Docente nuevo) throws SQLException {
        
        String sql = "UPDATE docentes SET nombre = ?, apellido = ?, correo_institucional = ?, titulo_academico = ? WHERE cedula = ?";

        try (Connection conn = Conexion.getConexion(); 
                 PreparedStatement ps = conn.prepareStatement(sql)) {

            
            ps.setString(1, nuevo.getNombre());   
            ps.setString(2, nuevo.getApellido()); 
            ps.setString(3, nuevo.getTitulo_Academico());
            ps.setString(4, nuevo.getCorreo_Institucional());
            ps.setString(5, cedula);

            
            int filas = ps.executeUpdate();

           
            return filas > 0;
        }
    }

    public boolean eliminar(String cedula) throws SQLException {
    String sql = "DELETE FROM docentes WHERE cedula = ?";

    try (Connection conn = Conexion.getConexion(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {

        
        ps.setString(1, cedula);

       
        int filas = ps.executeUpdate();

        
        return filas > 0;
    }
}


    public List<Docente> listar() throws SQLException {
        List<Docente> lista = new ArrayList<>();
        String sql = "SELECT * FROM docentes";
        try (Connection con = Conexion.getConexion(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Docente(rs.getString("cedula"), rs.getString("nombre"),
                        rs.getString("apellido"), rs.getString("correo_institucional"), rs.getString("titulo_academico")));
            }
        }
        return lista;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model;

import ec.edu.espoch.gestornotasfx.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Usuarios implements IUsuarios {

    @Override
    public Usuario login(String usuario, String clave) throws SQLException {

        Connection conn = Conexion.getConexion();

        String sql = "SELECT usuario, clave, rol FROM usuarios WHERE usuario = ? AND clave = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuario);
        ps.setString(2, clave);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Usuario(
                    rs.getString("usuario"),
                    rs.getString("clave"),
                    rs.getString("rol")
            );
        }

        return null;
    }
}


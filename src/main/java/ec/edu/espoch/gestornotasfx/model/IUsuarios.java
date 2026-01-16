/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model;

import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public interface IUsuarios {
    
public Usuario login(String usuario, String clave) throws SQLException;
}

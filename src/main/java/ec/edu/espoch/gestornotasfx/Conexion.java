/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Conexion {

    private static final String DATABASE = "GestorNotas";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection conexion = null;

    public static Connection getConexion(){
        try{
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexion establecida");
            }
        }catch(ClassNotFoundException e){
            System.out.println("Error: No se encontro el Driver de MySQl");
        }catch(SQLException e){
            System.out.println("Error de SQL" + e.getMessage());
        }
        return conexion;
    }

}

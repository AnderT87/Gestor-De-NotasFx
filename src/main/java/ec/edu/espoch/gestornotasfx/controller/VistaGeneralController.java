/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.App;
import ec.edu.espoch.gestornotasfx.Conexion;
import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiante;
import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiantes;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class VistaGeneralController {

    @FXML
    public void initialize() {
        try {
            if (Conexion.getConexion() != null && !Conexion.getConexion().isClosed()) {
                System.out.println("Conexión activa");
                // Opcional: mostrar una pequeña alerta al iniciar
                // mostrarInfo("Conectado a la base de datos gestor_notas");
            }
        } catch (SQLException e) {
            mostrarError("No se pudo conectar a la base de datos: " + e.getMessage());
        }
    }

    @FXML
    private void menuDocentes() {
        App.setPerfilSeleccionado("DOCENTE");
        App.cambiarVista("view-login");
    }

    @FXML
    private void menuInformacion() {
        App.setPerfilSeleccionado("ESTUDIANTE");
        App.cambiarVista("view-login");
    }

    private void mostrarError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}

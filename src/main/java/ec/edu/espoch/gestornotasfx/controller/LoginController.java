/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Admin
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.App;
import ec.edu.espoch.gestornotasfx.model.IUsuarios;
import ec.edu.espoch.gestornotasfx.model.Usuario;
import ec.edu.espoch.gestornotasfx.model.Usuarios;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;

import javafx.scene.control.Alert;

import java.sql.SQLException;

import ec.edu.espoch.gestornotasfx.App;

import ec.edu.espoch.gestornotasfx.App;

import ec.edu.espoch.gestornotasfx.model.Usuario;

import ec.edu.espoch.gestornotasfx.App;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtClave;

    private IUsuarios usuarioDAO = new Usuarios();

    @FXML
    private void login() throws SQLException {

        String user = txtUsuario.getText();
        String pass = txtClave.getText();

        Usuario u = usuarioDAO.login(user, pass);

        if (u == null) {
            mostrarMensaje("Usuario o clave incorrectos");
            return;
        }

        // VALIDAR PERFIL SELECCIONADO
        if (!u.getRol().equalsIgnoreCase(App.getPerfilSeleccionado())) {
            mostrarMensaje("Usuario o clave incorrectos");
            return;
        }

        // ✔ LOGIN CORRECTO
        App.setPerfilSeleccionado(u.getRol());

        // REDIRECCION
        if ("DOCENTE".equalsIgnoreCase(u.getRol())) {
            App.cambiarVista("view-docentes");
        } else {
            App.cambiarVista("view-informacion");
        }
    }

    private void mostrarMensaje(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

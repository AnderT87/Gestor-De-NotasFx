/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.App;
import ec.edu.espoch.gestornotasfx.model.asignatura.Asignatura;
import ec.edu.espoch.gestornotasfx.model.asignatura.Asignaturas;
import ec.edu.espoch.gestornotasfx.model.asignatura.IAsignaturas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Admin
 */
public class AsignaturaController {

    private IAsignaturas asignaturas;
    private ObservableList<Asignatura> listaObservable;

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre; // Usaremos este para el nombre de la materia
    @FXML
    private TextField txtPeriodo;

    @FXML
    private TableView<Asignatura> tablaAsignaturas;
    @FXML
    private TableColumn<Asignatura, Integer> colCodigo;
    @FXML
    private TableColumn<Asignatura, String> colNombre;
    @FXML
    private TableColumn<Asignatura, String> colPeriodo;

    // --- NAVEGACIÓN ---
    @FXML private void menuDocentes() { App.cambiarVista("view-docentes"); }
    @FXML private void menuAsignaturas() { App.cambiarVista("view-asignaturas"); }
    @FXML private void menuEstudiantes() { App.cambiarVista("view-estudiantes"); }
    @FXML private void menuAsigEstudiantes() { App.cambiarVista("view-notas"); }

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoAsignatura"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreAsignatura"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));

        listaObservable = FXCollections.observableArrayList();
        tablaAsignaturas.setItems(listaObservable);
        asignaturas = new Asignaturas();
        actualizarTabla(); // Cargar datos existentes al abrir
    }

    @FXML
    private void agregar() {
        try {
            if (txtCodigo.getText().isEmpty() || txtNombre.getText().isEmpty()) {
                mostrarError("Por favor, llene todos los campos.");
                return;
            }

            Asignatura a = new Asignatura(
                    Integer.parseInt(txtCodigo.getText()),
                    txtNombre.getText(),
                    txtPeriodo.getText()
            );

            asignaturas.agregar(a);
            mostrarInfo("Asignatura agregada con éxito");
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarError("El código debe ser un número válido.");
        }
    }

    @FXML
    private void buscar() {
        try {
            if (txtCodigo.getText().isEmpty()) {
                mostrarError("Ingrese un código para buscar.");
                return;
            }

            int codigo = Integer.parseInt(txtCodigo.getText());
            Asignatura a = asignaturas.obtener(codigo);

            if (a != null) {
                txtNombre.setText(a.getNombreAsignatura());
                txtPeriodo.setText(a.getPeriodo());
            } else {
                mostrarError("Asignatura no encontrada.");
            }
        } catch (NumberFormatException e) {
            mostrarError("El código debe ser numérico.");
        }
    }

    @FXML
    private void actualizar() {
        try {
            if (txtCodigo.getText().isEmpty()) {
                mostrarError("Debe ingresar el código de la asignatura a actualizar.");
                return;
            }

            Asignatura a = new Asignatura(
                    Integer.parseInt(txtCodigo.getText()),
                    txtNombre.getText(),
                    txtPeriodo.getText()
            );

            asignaturas.actualizar(a);
            mostrarInfo("Asignatura actualizada con éxito");
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarError("Error en los datos ingresados.");
        }
    }

    @FXML
    private void eliminar() {
        try {
            if (txtCodigo.getText().isEmpty()) {
                mostrarError("Ingrese un código para eliminar.");
                return;
            }

            int codigo = Integer.parseInt(txtCodigo.getText());
            asignaturas.eliminar(codigo);
            mostrarInfo("Asignatura eliminada con éxito");
            actualizarTabla();
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarError("Código no válido.");
        }
    }

    private void actualizarTabla() {
        if (asignaturas.obtenerTodos() != null) {
            listaObservable.setAll(asignaturas.obtenerTodos());
        }
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtPeriodo.clear();
    }

    private void mostrarInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void mostrarError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

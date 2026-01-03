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

    
    private Asignaturas asignaturas = new Asignaturas();
    private ObservableList<Asignatura> listaObservable;

    
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtAsignatura;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPeriodo;

    
    @FXML
    private TableView<Asignatura> tablaAsignaturas;
    @FXML
    private TableColumn<Asignatura, Integer> colCodigo;
    @FXML
    private TableColumn<Asignatura, Integer> colAsignatura;
    @FXML
    private TableColumn<Asignatura, String> colNombre;
    @FXML
    private TableColumn<Asignatura, String> colPeriodo;

    
    @FXML
    private void menuDocentes() {
        
        App.cambiarVista("view-docentes");
    }

    @FXML
    private void menuAsignaturas() {
        App.cambiarVista("view-asignaturas");
    }

    @FXML
    private void menuEstudiantes() {
        App.cambiarVista("view-estudiantes");
    }

    @FXML
    private void menuAsigEstudiantes() {
        
        App.cambiarVista("view-notas");
    }

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigoAsignatura"));
        colAsignatura.setCellValueFactory(
                new PropertyValueFactory<>("asignatura"));
        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombreAsignatura"));
        colPeriodo.setCellValueFactory(
                new PropertyValueFactory<>("periodo"));

        listaObservable = FXCollections.observableArrayList();
        tablaAsignaturas.setItems(listaObservable);
    }

    
    @FXML
    private void agregar() {
        Asignatura a = new Asignatura(
                Integer.parseInt(txtCodigo.getText()),
                Integer.parseInt(txtAsignatura.getText()),
                txtNombre.getText(),
                txtPeriodo.getText()
        );

        asignaturas.agregar(a);
        actualizarTabla();
        limpiarCampos();
    }

    
    @FXML
    private void buscar() {
        int codigo = Integer.parseInt(txtCodigo.getText());
        Asignatura a = asignaturas.obtener(codigo);

        if (a != null) {
            txtAsignatura.setText(String.valueOf(a.getAsignatura()));
            txtNombre.setText(a.getNombreAsignatura());
            txtPeriodo.setText(a.getPeriodo());
        }
    }

    
    @FXML
    private void actualizar() {
        Asignatura a = new Asignatura(
                Integer.parseInt(txtCodigo.getText()),
                Integer.parseInt(txtAsignatura.getText()),
                txtNombre.getText(),
                txtPeriodo.getText()
        );

        asignaturas.actualizar(a);
        actualizarTabla();
        limpiarCampos();
    }

    
    @FXML
    private void eliminar() {
        int codigo = Integer.parseInt(txtCodigo.getText());
        asignaturas.eliminar(codigo);
        actualizarTabla();
        limpiarCampos();
    }

    
    private void actualizarTabla() {
        listaObservable.setAll(asignaturas.obtenerTodos());
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtAsignatura.clear();
        txtNombre.clear();
        txtPeriodo.clear();
    }

    private void mostrarInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    private void mostrarError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}

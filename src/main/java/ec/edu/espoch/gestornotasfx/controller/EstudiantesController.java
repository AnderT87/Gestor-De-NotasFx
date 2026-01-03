/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.App;
import javafx.event.ActionEvent;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiante;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiantes;
import ec.edu.espoch.gestornotasfx.model.estudiantes.IEstudiantes;
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
public class EstudiantesController {

    private Estudiantes modelo;
    private ObservableList<Estudiante> listaObservable;

    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtCorreo;

    //==========Tabla=========
    @FXML
    private TableView<Estudiante> tablaEstudiantes;
    @FXML
    private TableColumn<Estudiante, Integer> colCodigo;
    @FXML
    private TableColumn<Estudiante, String> colNombre;
    @FXML
    private TableColumn<Estudiante, String> colApellido;
    @FXML
    private TableColumn<Estudiante, String> colCorreo;

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
    private void initialize() {
        
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoEstudiante"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEstudiante"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellidoEstudiantes"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correoInstEstudinate"));

        listaObservable = FXCollections.observableArrayList();
        tablaEstudiantes.setItems(listaObservable);
        modelo = new Estudiantes();

        
        refrescarTabla();
    }

    
    @FXML
    private void agregar() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());

            Estudiante e = new Estudiante(
                    codigo,
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtCorreo.getText()
            );

            modelo.agregar(e);

            
            listaObservable.add(e);
            
            refrescarTabla();
            mostrarInfo("Estudiante agregado correctamente");
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarError("El código debe ser numérico");
        }
    }

    
    @FXML
    private void buscar() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            Estudiante e = modelo.obtener(codigo);

            if (e != null) {
                txtNombre.setText(e.getNombreEstudiante());
                txtApellido.setText(e.getApellidoEstudiantes());
                txtCorreo.setText(e.getCorreoInstEstudinate());
            } else {
                mostrarError("Estudiante no encontrado");
            }

        } catch (NumberFormatException e) {
            mostrarError("Ingrese un código válido");
        }
    }

    
    @FXML
    private void actualizar() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());

            Estudiante actualizado = new Estudiante(
                    codigo,
                    txtNombre.getText(),
                    txtApellido.getText(),
                    txtCorreo.getText()
            );

            modelo.actualizar(actualizado);
            refrescarTabla();
            mostrarInfo("Estudiante actualizado");
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarError("Código inválido");
        }
    }

    
    @FXML
    private void eliminar() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            modelo.eliminar(codigo);
            mostrarInfo("Estudiante eliminado");
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarError("Ingrese un código válido");
        }
    }

    
    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
    }

    private void refrescarTabla() {
        
        if (modelo != null && modelo.obtenerTodos()!= null) {
            listaObservable.setAll(modelo.obtenerTodos());
        }
    }

    private void mostrarInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    private void mostrarError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }
}

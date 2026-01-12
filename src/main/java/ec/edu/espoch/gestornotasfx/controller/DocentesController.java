/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.App;
import ec.edu.espoch.gestornotasfx.model.docentes.Docente;
import ec.edu.espoch.gestornotasfx.model.docentes.Docentes;
import ec.edu.espoch.gestornotasfx.model.docentes.IDocentes;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiante;
import java.sql.SQLException;
import java.util.List;
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
public class DocentesController {
    
     
    private IDocentes docentes;
     private ObservableList<Docente> listaObservable;
    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtCorreo;

   
    
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
    private TableView<Docente> tablaDocentes;
    @FXML
    private TableColumn<Docente, String> colCedula;
    @FXML
    private TableColumn<Docente, String> colNombre;
    @FXML
    private TableColumn<Docente, String> colApellido;
    @FXML
    private TableColumn<Docente, String> colCorreo;
    @FXML
    private TableColumn<Docente, String> colTitulo;
    
   
    
    @FXML
    private void initialize() {
        colCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("titulo_Academico"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("correo_Institucional"));

        listaObservable = FXCollections.observableArrayList();
        tablaDocentes.setItems(listaObservable);
        docentes = new Docentes();
        refrescarTabla();
    }

    
    @FXML
    private void agregar() throws SQLException {

        if (txtCedula.getText().isEmpty()) {
            mostrarAlerta("Error", "La cédula es obligatoria");
            return;
        }

        if (docentes.obtener(txtCedula.getText()) != null) {
            mostrarAlerta("Error", "Ya existe un docente con esa cédula");
            return;
        }

        Docente d = new Docente(
                txtCedula.getText(),
                txtNombre.getText(),
                txtApellido.getText(),
                txtTitulo.getText(),
                txtCorreo.getText()
        );

        docentes.agregar(d);
        listaObservable.add(d);
        refrescarTabla();
        mostrarAlerta("Éxito", "Docente agregado correctamente");
        limpiarCampos();
    }

    
    @FXML
    private void buscar() throws SQLException {

        String cedula = txtCedula.getText();

        if (cedula.isEmpty()) {
            mostrarAlerta("Error", "Ingrese la cédula");
            return;
        }

        Docente d = docentes.obtener(cedula);

        if (d != null) {
            txtNombre.setText(d.getNombre());
            txtApellido.setText(d.getApellido());
            txtTitulo.setText(d.getTitulo_Academico());
            txtCorreo.setText(d.getCorreo_Institucional());
        } else {
            mostrarAlerta("No encontrado", "Docente no existe");
            limpiarCampos();
        }
    }

    
    @FXML
    private void actualizar() throws SQLException {

        String cedula = txtCedula.getText();

        if (docentes.obtener(cedula) == null) {
            mostrarAlerta("Error", "Debe buscar un docente primero");
            return;
        }

        Docente nuevo = new Docente(
                cedula,
                txtNombre.getText(),
                txtApellido.getText(),
                txtTitulo.getText(),
                txtCorreo.getText()
        );

        docentes.actualizar(cedula, nuevo);
        refrescarTabla();
        mostrarAlerta("Éxito", "Docente actualizado");
    }

    
    @FXML
    private void eliminar() throws SQLException {

        String cedula = txtCedula.getText();

        if (docentes.eliminar(cedula)) {
            mostrarAlerta("Éxito", "Docente eliminado");
            refrescarTabla();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "Docente no encontrado");
        }
    }

    
    private void limpiarCampos() {
        txtCedula.clear();
        txtNombre.clear();
        txtApellido.clear();
        txtTitulo.clear();
        txtCorreo.clear();
    }

    private void refrescarTabla() {
    try {
        
        if (docentes != null) {
          
            List<Docente> lista = docentes.listar();
            
            
            if (lista != null) {
                listaObservable.setAll(lista);
            }
        }
    } catch (SQLException e) {
        
        mostrarError("No se pudo refrescar la tabla: " + e.getMessage());
        e.printStackTrace(); 
    }
}
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
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

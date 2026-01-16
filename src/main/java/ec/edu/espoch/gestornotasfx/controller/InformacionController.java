/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.App;
import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiante;
import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiantes;
import ec.edu.espoch.gestornotasfx.model.asig_estu.IAsigna_Estudiantes;
import ec.edu.espoch.gestornotasfx.model.asig_estu.NotaTable;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiante;

import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class InformacionController {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TableView<NotaTable> tablaNotas;

    @FXML
    private TableColumn<NotaTable, String> colEstudiante;
    @FXML
    private TableColumn<NotaTable, String> colAsignatura;
    
    @FXML
    private TableColumn<NotaTable, Double> colMedio;
    @FXML
    private TableColumn<NotaTable, Double> colFin;
    @FXML
   private TableColumn<NotaTable, Double> colRecuperación;
   @FXML
   private TableColumn<NotaTable, Double> colPromedio;

    private ObservableList<NotaTable> datos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colEstudiante.setCellValueFactory(new PropertyValueFactory<>("estudiante"));
        colAsignatura.setCellValueFactory(new PropertyValueFactory<>("asignatura"));
        
        colMedio.setCellValueFactory(new PropertyValueFactory<>("medioCiclo"));
        colFin.setCellValueFactory(new PropertyValueFactory<>("finCiclo"));
        colRecuperación.setCellValueFactory(new PropertyValueFactory<>("recuperacion"));
       colPromedio.setCellValueFactory(new PropertyValueFactory<>("promedioCiclos"));

        tablaNotas.setItems(datos);
    }

    @FXML
    private void buscarPorCodigo() {

        String codigo = txtCodigo.getText();

        if (codigo == null || codigo.isEmpty()) {
            mostrarAlerta("Ingrese el código del estudiante");
            return;
        }

        Asigna_Estudiantes dao = new Asigna_Estudiantes();
        List<Asigna_Estudiante> lista = dao.consultarNotasPorId(codigo);

        datos.clear();

        if (lista.isEmpty()) {
            mostrarAlerta("No se encontraron notas");
            return;
        }

        for (Asigna_Estudiante ae : lista) {
            datos.add(new NotaTable(ae));
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    

    @FXML
    private void volverMenuPrincipal() {
        try {
            App.cambiarVista("view-seleccion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarDatos(List<Asigna_Estudiante> lista) {
        datos.clear();
        for (Asigna_Estudiante ae : lista) {
            //datos.add(new NotaTabla(ae));
        }
    }
}

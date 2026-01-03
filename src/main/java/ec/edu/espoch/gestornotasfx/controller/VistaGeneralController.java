/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiante;
import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiantes;
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

    // ====== CAMPOS ======
    @FXML
    private TextField txtPeriodo;

    @FXML
    private TableView<Asigna_Estudiante> tabla;
    @FXML
    private TableColumn<Asigna_Estudiante, String> colDocente;
    @FXML
    private TableColumn<Asigna_Estudiante, String> colAsignatura;
    @FXML
    private TableColumn<Asigna_Estudiante, String> colEstudiante;
    @FXML
    private TableColumn<Asigna_Estudiante, Double> colRecuperacion;
    @FXML
    private TableColumn<Asigna_Estudiante, Double> colMedio;
    @FXML
    private TableColumn<Asigna_Estudiante, Double> colFinal;

    private Asigna_Estudiantes modelo;

    /* ====== INIT ======
    @FXML
    private void initialize() {
        modelo = new Asigna_Estudiantes();

        colDocente.setCellValueFactory(d
                -> new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getAsignatura().getDocente().getNombre()
                        + " "
                        + d.getValue().getAsignatura().getDocente().getApellido()
                )
        );

        colAsignatura.setCellValueFactory(d
                -> new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getAsignatura().getNombreAsignatura()
                )
        );

        colEstudiante.setCellValueFactory(d
                -> new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getEstudiantes().getNombreEstudiante()
                        + " "
                        + d.getValue().getEstudiantes().getApellidoEstudiantes()
                )
        );

        colRecuperacion.setCellValueFactory(d
                -> new javafx.beans.property.SimpleDoubleProperty(
                        d.getValue().getRecuperacion()
                ).asObject()
        );

        colMedio.setCellValueFactory(d
                -> new javafx.beans.property.SimpleDoubleProperty(
                        d.getValue().getMedioCiclo()
                ).asObject()
        );

        colFinal.setCellValueFactory(d
                -> new javafx.beans.property.SimpleDoubleProperty(
                        d.getValue().getFinCiclo()
                ).asObject()
        );
    }

    // ====== BUSCAR POR PERIODO ======
    @FXML
    private void buscar() {

        String periodo = txtPeriodo.getText().trim();

        if (periodo.isEmpty()) {
            alerta("Advertencia", "Ingrese el periodo");
            return;
        }

        ObservableList<Asigna_Estudiante> resultado
                = FXCollections.observableArrayList();

        for (Asigna_Estudiante ae : modelo.obtenerPorAsignatura(0)) {
            if (ae.getAsignatura().getPeriodo().equalsIgnoreCase(periodo)) {
                resultado.add(ae);
            }
        }

        tabla.setItems(resultado);

        if (resultado.isEmpty()) {
            alerta("Información", "No hay registros para ese periodo");
        }
    }

    private void alerta(String titulo, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(titulo);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }*/
}

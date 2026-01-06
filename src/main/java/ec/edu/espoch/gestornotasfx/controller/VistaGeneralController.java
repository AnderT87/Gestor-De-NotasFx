/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.App;
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

    
}

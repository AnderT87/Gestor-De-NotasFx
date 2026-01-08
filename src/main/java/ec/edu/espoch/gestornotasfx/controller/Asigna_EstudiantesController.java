/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.App;
import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiante;
import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiantes;
import ec.edu.espoch.gestornotasfx.model.asig_estu.IAsigna_Estudiantes;
import ec.edu.espoch.gestornotasfx.model.asignatura.Asignatura;
import ec.edu.espoch.gestornotasfx.model.asignatura.Asignaturas;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiante;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiantes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Admin
 */
public class Asigna_EstudiantesController {

    @FXML
    private TextField txtCodEstudiante, txtCodAsignatura, txtMedioCiclo, txtFinCiclo, txtRecuperacion;
    @FXML
    private Label lblNombreEstudiante, lblNombreAsignatura;
    @FXML
    private TableView<Asigna_Estudiante> tablaAsignaciones;
    @FXML
    private TableColumn<Asigna_Estudiante, String> colEstudiante, colAsignatura;
    @FXML
    private TableColumn<Asigna_Estudiante, Double> colMedio, colFin, colPromedio;

    private ObservableList<Asigna_Estudiante> masterData = FXCollections.observableArrayList();
    private Estudiante seleccionadoEst;
    private Asignatura seleccionadoAsig;

    @FXML
    public void initialize() {
        // Configuración de columnas para obtener datos de objetos anidados
        colEstudiante.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getEstudiantes().getNombreEstudiante() + " " + cellData.getValue().getEstudiantes().getApellidoEstudiantes()));

        colAsignatura.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getAsignatura().getNombreAsignatura()));

        colMedio.setCellValueFactory(new PropertyValueFactory<>("medioCiclo"));
        colFin.setCellValueFactory(new PropertyValueFactory<>("finCiclo"));
        colPromedio.setCellValueFactory(new PropertyValueFactory<>("promedioCiclos"));

        tablaAsignaciones.setItems(masterData);
    }

    @FXML
    private void buscarEstudiante() {
        int cod = Integer.parseInt(txtCodEstudiante.getText());
        // Aquí llamas a tu método global de búsqueda de estudiantes
        // seleccionadoEst = App.buscarEstudiante(cod); 

        if (seleccionadoEst != null) {
            lblNombreEstudiante.setText(seleccionadoEst.getNombreEstudiante() + " " + seleccionadoEst.getApellidoEstudiantes());
        } else {
            lblNombreEstudiante.setText("⚠️ No encontrado");
        }
    }

    @FXML
    private void buscarAsignatura() {
        int cod = Integer.parseInt(txtCodAsignatura.getText());
        // Aquí llamas a tu método global de búsqueda de asignaturas
        // seleccionadoAsig = App.buscarAsignatura(cod);

        if (seleccionadoAsig != null) {
            lblNombreAsignatura.setText(seleccionadoAsig.getNombreAsignatura());
        } else {
            lblNombreAsignatura.setText("⚠️ No encontrada");
        }
    }

    @FXML
    private void agregar() {
        if (seleccionadoEst != null && seleccionadoAsig != null) {
            double m = Double.parseDouble(txtMedioCiclo.getText());
            double f = Double.parseDouble(txtFinCiclo.getText());
            double r = Double.parseDouble(txtRecuperacion.getText());
            double p = (m + f) / 2;

            Asigna_Estudiante nuevo = new Asigna_Estudiante(seleccionadoAsig, seleccionadoEst, "", r, p, m, f);
            masterData.add(nuevo);
            limpiar();
        }
    }

    @FXML
    private void actualizar() {
        Asigna_Estudiante sel = tablaAsignaciones.getSelectionModel().getSelectedItem();
        if (sel != null) {
            sel.setMedioCiclo(Double.parseDouble(txtMedioCiclo.getText()));
            sel.setFinCiclo(Double.parseDouble(txtFinCiclo.getText()));
            sel.setRecuperacion(Double.parseDouble(txtRecuperacion.getText()));
            // Recalcular promedio
            sel.setPromedioCiclos((sel.getMedioCiclo() + sel.getFinCiclo()) / 2);
            tablaAsignaciones.refresh();
        }
    }

    @FXML
    private void eliminar() {
        Asigna_Estudiante sel = tablaAsignaciones.getSelectionModel().getSelectedItem();
        masterData.remove(sel);
    }

    private void limpiar() {
        txtCodEstudiante.clear();
        txtCodAsignatura.clear();
        txtMedioCiclo.clear();
        txtFinCiclo.clear();
        lblNombreEstudiante.setText("Estudiante: ---");
        lblNombreAsignatura.setText("Materia: ---");
        seleccionadoEst = null;
        seleccionadoAsig = null;
    }

    // Métodos de navegación (Deben estar implementados para cambiar de escena)
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

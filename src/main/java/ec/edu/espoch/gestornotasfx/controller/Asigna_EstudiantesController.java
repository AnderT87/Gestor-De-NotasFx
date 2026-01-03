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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Admin
 */
public class Asigna_EstudiantesController {

    /* ================== MODELOS ================== 
    private Asigna_Estudiantes modelo = new Asigna_Estudiantes();
    private Asignaturas modeloAsignaturas = new Asignaturas();
    private Estudiantes modeloEstudiantes = new Estudiantes();

    private ObservableList<Asigna_Estudiante> datosTabla
            = FXCollections.observableArrayList();

    /* ================= CAMPOS ================= 
    @FXML
    private TextField txtFecha;
    @FXML
    private TextField txtCodigoAsignatura;
    @FXML
    private TextField txtCodigoEstudiante;
    @FXML
    private TextField txtRecuperacion;
    @FXML
    private TextField txtMedioCiclo;
    @FXML
    private TextField txtFinCiclo;

    /* ================= TABLA ================= 
    @FXML
    private TableView<Asigna_Estudiante> tblAsignaciones;

    @FXML
    private TableColumn<Asigna_Estudiante, String> colFecha;
    @FXML
    private TableColumn<Asigna_Estudiante, Integer> colCodAsignatura;
    @FXML
    private TableColumn<Asigna_Estudiante, Integer> colCodEstudiante;
    @FXML
    private TableColumn<Asigna_Estudiante, Double> colRecuperacion;
    @FXML
    private TableColumn<Asigna_Estudiante, Double> colMedioCiclo;
    @FXML
    private TableColumn<Asigna_Estudiante, Double> colFinCiclo;

    /* ================= INIT ================= 
    @FXML
    private void menuDocentes() {
        // Llama al método de App pasándole el nombre del FXML de docentes
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
        // Asumiendo que esta es la vista de notas
        App.cambiarVista("view-notas");
    }
    
    @FXML
    private void initialize() {

        colFecha.setCellValueFactory(d
                -> new javafx.beans.property.SimpleStringProperty(
                        d.getValue().getFecha()));

        colCodAsignatura.setCellValueFactory(d
                -> new javafx.beans.property.SimpleIntegerProperty(
                        d.getValue().getAsignatura().getCodigoAsignatura()).asObject());

        colCodEstudiante.setCellValueFactory(d
                -> new javafx.beans.property.SimpleIntegerProperty(
                        d.getValue().getEstudiantes().getCodigoEstudiante()).asObject());

        colRecuperacion.setCellValueFactory(d
                -> new javafx.beans.property.SimpleDoubleProperty(
                        d.getValue().getRecuperacion()).asObject());

        colMedioCiclo.setCellValueFactory(d
                -> new javafx.beans.property.SimpleDoubleProperty(
                        d.getValue().getMedioCiclo()).asObject());

        colFinCiclo.setCellValueFactory(d
                -> new javafx.beans.property.SimpleDoubleProperty(
                        d.getValue().getFinCiclo()).asObject());

        tblAsignaciones.setItems(datosTabla);
    }


    @FXML
    private void agregar() {
        Asigna_Estudiante ae = construirDesdeFormulario();
        modelo.agregar(ae);
        refrescarTabla();
        limpiar();
    }

    @FXML
    private void actualizar() {
        Asigna_Estudiante ae = construirDesdeFormulario();
        modelo.actualizar(ae);
        refrescarTabla();
        limpiar();
    }

    @FXML
    private void eliminar() {
        modelo.eliminar(
                txtFecha.getText(),
                Integer.parseInt(txtCodigoAsignatura.getText()),
                Integer.parseInt(txtCodigoEstudiante.getText())
        );
        refrescarTabla();
        limpiar();
    }

    @FXML
    private void buscar() {
        Asigna_Estudiante ae = modelo.obtener(
                txtFecha.getText(),
                Integer.parseInt(txtCodigoAsignatura.getText()),
                Integer.parseInt(txtCodigoEstudiante.getText())
        );

        if (ae != null) {
            txtRecuperacion.setText(String.valueOf(ae.getRecuperacion()));
            txtMedioCiclo.setText(String.valueOf(ae.getMedioCiclo()));
            txtFinCiclo.setText(String.valueOf(ae.getFinCiclo()));
        }
    }

    @FXML
    private void limpiar() {
        txtFecha.clear();
        txtCodigoAsignatura.clear();
        txtCodigoEstudiante.clear();
        txtRecuperacion.clear();
        txtMedioCiclo.clear();
        txtFinCiclo.clear();
    }

    /* ================= AUXILIARES ================= 
    private Asigna_Estudiante construirDesdeFormulario() {
    try {
        // 1. Obtener códigos base
        int codA = Integer.parseInt(txtCodigoAsignatura.getText().trim());
        int codE = Integer.parseInt(txtCodigoEstudiante.getText().trim());

        // 2. Obtener notas de ciclos (Obligatorias)
        double medio = Double.parseDouble(txtMedioCiclo.getText().replace(",", "."));
        double fin = Double.parseDouble(txtFinCiclo.getText().replace(",", "."));

        // 3. Lógica de recuperación condicional
        double recuperacion = 0;
        double promedioCiclos = (medio + fin) / 2;

        if (promedioCiclos < 7.0) {
            // Si el promedio es menor a 7, la recuperación es obligatoria
            if (txtRecuperacion.getText().trim().isEmpty()) {
                throw new IllegalArgumentException("El promedio es " + promedioCiclos + ". Se requiere nota de recuperación.");
            }
            recuperacion = Double.parseDouble(txtRecuperacion.getText().replace(",", "."));
        } else {
            // Si el promedio es >= 7, ignoramos la recuperación o usamos lo que haya (usualmente 0)
            if (!txtRecuperacion.getText().trim().isEmpty()) {
                recuperacion = Double.parseDouble(txtRecuperacion.getText().replace(",", "."));
            }
        }

        // 4. Obtener objetos de modelo
        Asignatura asignatura = modeloAsignaturas.obtener(codA);
        Estudiante estudiante = modeloEstudiantes.obtener(codE);

        return new Asigna_Estudiante(
                asignatura,
                estudiante,
                txtFecha.getText(),
                recuperacion,
                medio,
                fin
        );
    } catch (NumberFormatException e) {
        //mostrarAlerta("Error de Formato", "Asegúrese de que las notas sean números válidos.");
        throw e;
    } catch (IllegalArgumentException e) {
        //mostrarAlerta("Nota de Recuperación Requerida", e.getMessage());
        throw e;
    }
}

    private void refrescarTabla() {
        datosTabla.setAll(modelo.obtenerPorAsignatura(
                Integer.parseInt(txtCodigoAsignatura.getText())));
    }*/
}

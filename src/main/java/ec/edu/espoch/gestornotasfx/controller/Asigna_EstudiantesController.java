/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiante;
import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiantes; // Tu DAO
import ec.edu.espoch.gestornotasfx.model.asig_estu.IAsigna_Estudiantes;
import ec.edu.espoch.gestornotasfx.model.asignatura.Asignatura;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiante;
import ec.edu.espoch.gestornotasfx.App;
import ec.edu.espoch.gestornotasfx.model.asignatura.Asignaturas;
import ec.edu.espoch.gestornotasfx.model.asignatura.IAsignaturas;
import ec.edu.espoch.gestornotasfx.model.estudiantes.Estudiantes;
import ec.edu.espoch.gestornotasfx.model.estudiantes.IEstudiantes;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.util.List;

public class Asigna_EstudiantesController {

    // Instancia del DAO para conectar con la base de datos
    private IAsigna_Estudiantes service = new Asigna_Estudiantes();
    private ObservableList<Asigna_Estudiante> masterData = FXCollections.observableArrayList();
    private IEstudiantes estudianteService = new Estudiantes(); // O como se llame tu implementación
    private IAsignaturas asignaturaService = new Asignaturas();

    private Estudiante seleccionadoEst;
    private Asignatura seleccionadoAsig;

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

    // --- NAVEGACIÓN ---
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
        // 1. Configurar cómo se muestran los objetos anidados (Nombre Estudiante y Materia)
        colEstudiante.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getEstudiantes().getNombreEstudiante() + " "
                        + cellData.getValue().getEstudiantes().getApellidoEstudiantes()));

        colAsignatura.setCellValueFactory(cellData
                -> new SimpleStringProperty(cellData.getValue().getAsignatura().getNombreAsignatura()));

        // 2. Configurar columnas numéricas
        colMedio.setCellValueFactory(new PropertyValueFactory<>("medioCiclo"));
        colFin.setCellValueFactory(new PropertyValueFactory<>("finCiclo"));
        colPromedio.setCellValueFactory(new PropertyValueFactory<>("promedioCiclos"));

        // 3. Vincular lista a la tabla y cargar datos iniciales de la BD
        tablaAsignaciones.setItems(masterData);
        actualizarTablaDesdeBD();
    }

    @FXML
    private void buscarEstudiante() {
        try {
            if (txtCodEstudiante.getText().isEmpty()) {
                return;
            }
            int cod = Integer.parseInt(txtCodEstudiante.getText());

            // LLAMADA REAL AL DAO DE ESTUDIANTES
            seleccionadoEst = estudianteService.obtener(cod);

            if (seleccionadoEst != null) {
                lblNombreEstudiante.setText(seleccionadoEst.getNombreEstudiante() + " " + seleccionadoEst.getApellidoEstudiantes());
            } else {
                lblNombreEstudiante.setText("⚠️ No encontrado");
                seleccionadoEst = null;
            }
        } catch (NumberFormatException e) {
            mostrarError("El código debe ser numérico.");
        } catch (SQLException e) {
            mostrarError("Error al conectar con la base de datos.");
        }
    }

    @FXML
    private void buscarAsignatura() {
        try {
            if (txtCodAsignatura.getText().isEmpty()) {
                return;
            }
            int cod = Integer.parseInt(txtCodAsignatura.getText());

            // LLAMADA REAL AL DAO DE ASIGNATURAS
            seleccionadoAsig = asignaturaService.obtener(cod);

            if (seleccionadoAsig != null) {
                lblNombreAsignatura.setText(seleccionadoAsig.getNombreAsignatura());
            } else {
                lblNombreAsignatura.setText("⚠️ No encontrada");
                seleccionadoAsig = null;
            }
        } catch (NumberFormatException e) {
            mostrarError("El código debe ser numérico.");
        } catch (SQLException e) {
            mostrarError("Error al conectar con la base de datos.");
        }
    }

    @FXML
    private void agregar() {
        try {
            if (seleccionadoEst == null || seleccionadoAsig == null) {
                mostrarError("Debe buscar un estudiante y una asignatura válidos.");
                return;
            }

            // Capturamos los Strings directamente para el DAO
            String mStr = txtMedioCiclo.getText();
            String fStr = txtFinCiclo.getText();
            String rStr = txtRecuperacion.getText();

            // Creamos el objeto para la lista local (opcional si refrescas desde BD)
            double m = mStr.isEmpty() ? 0 : Double.parseDouble(mStr);
            double f = fStr.isEmpty() ? 0 : Double.parseDouble(fStr);
            double r = rStr.isEmpty() ? 0 : Double.parseDouble(rStr);
            double p = (m + f) / 2;

            Asigna_Estudiante nuevo = new Asigna_Estudiante(seleccionadoAsig, seleccionadoEst, "", r, p, m, f);

            // GUARDAR EN BASE DE DATOS
            if (service.agregar(nuevo, mStr, fStr, rStr)) {
                mostrarInfo("Nota registrada con éxito.");
                actualizarTablaDesdeBD();
                limpiar();
            } else {
                mostrarError("Error: El estudiante ya tiene una nota en esta materia.");
            }

        } catch (NumberFormatException e) {
            mostrarError("Asegúrese de ingresar números válidos en las notas.");
        } catch (SQLException e) {
            mostrarError("Error de BD: " + e.getMessage());
        }
    }

    @FXML
    private void actualizar() {
        Asigna_Estudiante sel = tablaAsignaciones.getSelectionModel().getSelectedItem();
        if (sel != null) {
            try {
                if (service.actualizar(sel, txtMedioCiclo.getText(), txtFinCiclo.getText(), txtRecuperacion.getText())) {
                    mostrarInfo("Registro actualizado.");
                    actualizarTablaDesdeBD();
                    limpiar();
                }
            } catch (SQLException e) {
                mostrarError("Error al actualizar: " + e.getMessage());
            }
        } else {
            mostrarError("Seleccione un registro de la tabla.");
        }
    }

    @FXML
    private void eliminar() {
        Asigna_Estudiante sel = tablaAsignaciones.getSelectionModel().getSelectedItem();
        if (sel != null) {
            try {
                if (service.eliminar(sel.getEstudiantes().getCodigoEstudiante(),
                        sel.getAsignatura().getCodigoAsignatura())) {
                    mostrarInfo("Registro eliminado.");
                    actualizarTablaDesdeBD();
                    limpiar();
                }
            } catch (SQLException e) {
                mostrarError("Error al eliminar: " + e.getMessage());
            }
        }
    }

    private void actualizarTablaDesdeBD() {
        try {
            List<Asigna_Estudiante> lista = service.obtenerTodo();
            masterData.setAll(lista);
        } catch (SQLException e) {
            System.err.println("Error al cargar tabla: " + e.getMessage());
        }
    }

    private void limpiar() {
        txtCodEstudiante.clear();
        txtCodAsignatura.clear();
        txtMedioCiclo.clear();
        txtFinCiclo.clear();
        txtRecuperacion.clear();
        lblNombreEstudiante.setText("Estudiante: ---");
        lblNombreAsignatura.setText("Materia: ---");
        seleccionadoEst = null;
        seleccionadoAsig = null;
    }

    private void mostrarInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void mostrarError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

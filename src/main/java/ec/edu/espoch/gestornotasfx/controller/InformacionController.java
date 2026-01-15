/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.controller;

import ec.edu.espoch.gestornotasfx.App;
import ec.edu.espoch.gestornotasfx.model.asig_estu.Asigna_Estudiantes;
import ec.edu.espoch.gestornotasfx.model.asig_estu.IAsigna_Estudiantes;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;

/**
 *
 * @author Admin
 */
public class InformacionController {

    @FXML
    private TextField txtIdBusqueda;
    @FXML
    private Label lblBienvenida;
    @FXML
    private TableView<Map<String, String>> asignatura_estudiante;

    // Columnas de la tabla - CORREGIDOS NOMBRES PARA COINCIDIR CON FXML
    @FXML
    private TableColumn<Map<String, String>, String> colNombreEstudiante; // Agregué la 'o'
    @FXML
    private TableColumn<Map<String, String>, String> colAsignatura;
    @FXML
    private TableColumn<Map<String, String>, String> colNotaUno; // Coincide con tu FXML previo
    @FXML
    private TableColumn<Map<String, String>, String> colNotaDos; // Coincide con tu FXML previo
    @FXML
    private TableColumn<Map<String, String>, String> colPromedio;

    private IAsigna_Estudiantes objetoDao = new Asigna_Estudiantes();

    @FXML
    public void initialize() {
        // Usamos las llaves exactas que pusiste en el DAO: "nombreCompleto", "materia", "notaUno", "notaDos"
        colNombreEstudiante.setCellValueFactory(cd
                -> new javafx.beans.property.SimpleStringProperty(cd.getValue().get("nombreCompleto")));

        colAsignatura.setCellValueFactory(cd
                -> new javafx.beans.property.SimpleStringProperty(cd.getValue().get("materia")));

        colNotaUno.setCellValueFactory(cd
                -> new javafx.beans.property.SimpleStringProperty(cd.getValue().get("notaUno")));

        colNotaDos.setCellValueFactory(cd
                -> new javafx.beans.property.SimpleStringProperty(cd.getValue().get("notaDos")));

        // Si quieres calcular el promedio al vuelo para la columna:
        if (colPromedio != null) {
            colPromedio.setCellValueFactory(cd -> {
                try {
                    double n1 = Double.parseDouble(cd.getValue().get("notaUno"));
                    double n2 = Double.parseDouble(cd.getValue().get("notaDos"));
                    return new javafx.beans.property.SimpleStringProperty(String.format("%.2f", (n1 + n2) / 2));
                } catch (Exception e) {
                    return new javafx.beans.property.SimpleStringProperty("0.00");
                }
            });
        }
    }

    @FXML
    private void buscarNotasEstudiante() {
        String id = txtIdBusqueda.getText().trim();
        if (id.isEmpty()) {
            return;
        }

        List<Map<String, String>> listaResultados = objetoDao.consultarNotasPorId(id);

        if (asignatura_estudiante != null) {
            if (!listaResultados.isEmpty()) {
                ObservableList<Map<String, String>> datosCargados = FXCollections.observableArrayList(listaResultados);

                // ASIGNAR Y REFRESCAR
                asignatura_estudiante.setItems(null); // Limpiamos primero
                asignatura_estudiante.setItems(datosCargados);
                asignatura_estudiante.refresh(); // <--- ESTO FUERZA A LA TABLA A PINTARSE

                lblBienvenida.setText("Estudiante: " + listaResultados.get(0).get("nombreCompleto"));
            } else {
                asignatura_estudiante.getItems().clear();
                lblBienvenida.setText("No se encontraron registros.");
            }
        }
    }

    private void actualizarTabla(String id) {
    try {
        if (objetoDao != null) {
            // 1. Obtenemos la lista desde el DAO
            List<Map<String, String>> lista = objetoDao.consultarNotasPorId(id);
            
            if (lista != null) {
                // 2. Convertimos a ObservableList y cargamos la tabla
                ObservableList<Map<String, String>> datos = FXCollections.observableArrayList(lista);
                asignatura_estudiante.setItems(datos);
                
                // 3. Opcional: Refrescar el mensaje de bienvenida
                if (!lista.isEmpty()) {
                    lblBienvenida.setText("Estudiante: " + lista.get(0).get("nombreCompleto"));
                } else {
                    lblBienvenida.setText("No se encontraron registros para el ID: " + id);
                }
            }
        }
    } catch (Exception e) {
        System.err.println("No se pudo refrescar la tabla: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    // ... los demás métodos de menú se mantienen igual
    @FXML
    private void volverMenuPrincipal() {
        App.cambiarVista("view-seleccion");
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asig_estu;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Asigna_Estudiantes implements IAsigna_Estudiantes {

    private List<Asigna_Estudiante> lista = new ArrayList<>();

    public void agregar(Asigna_Estudiante ae) {
        lista.add(ae);
    }

    public void actualizar(Asigna_Estudiante ae) {
        for (int i = 0; i < lista.size(); i++) {
            Asigna_Estudiante actual = lista.get(i);
            // Validación de seguridad para evitar NullPointerException
            if (actual.getAsignatura() != null && actual.getEstudiantes() != null) {
                if (actual.getFecha().equals(ae.getFecha())
                        && actual.getAsignatura().getCodigoAsignatura() == ae.getAsignatura().getCodigoAsignatura()
                        && actual.getEstudiantes().getCodigoEstudiante() == ae.getEstudiantes().getCodigoEstudiante()) {
                    lista.set(i, ae);
                    return;
                }
            }
        }
    }

    public void eliminar(String fecha, int codA, int codE) {
        for (int i = 0; i < lista.size(); i++) {
            Asigna_Estudiante ae = lista.get(i);

            if (ae.getFecha().equals(fecha)
                    && ae.getAsignatura().getCodigoAsignatura() == codA
                    && ae.getEstudiantes().getCodigoEstudiante() == codE) {

                lista.remove(i);
                return; // Eliminamos solo el primero que coincida
            }
        }
    }

    public List<Asigna_Estudiante> buscar(int codA, String nombrePeriodo) {
        List<Asigna_Estudiante> resultados = new ArrayList<>();

        for (Asigna_Estudiante ae : lista) {
            if (ae.getAsignatura() != null && ae.getAsignatura().getPeriodo() != null) {

                boolean mismaAsignatura = ae.getAsignatura().getCodigoAsignatura() == codA;

                String textoPeriodo = ae.getAsignatura().getPeriodo();

                boolean mismoPeriodo = textoPeriodo.trim().equalsIgnoreCase(nombrePeriodo.trim());

                if (mismaAsignatura && mismoPeriodo) {
                    resultados.add(ae);
                }
            }
        }
        return resultados;
    }

    public List<Asigna_Estudiante> obtenerTodo() {
        return new ArrayList<>(lista);
    }
}

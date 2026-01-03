/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.estudiantes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Estudiantes implements IEstudiantes {
    private List<Estudiante> estudiantes;

    public Estudiantes() {
        estudiantes = new ArrayList<>();
    }

    
    public void agregar(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    
    public void actualizar(Estudiante estudianteActualizado) {
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante e = estudiantes.get(i);
            if (e.getCodigoEstudiante() == estudianteActualizado.getCodigoEstudiante()) {
                estudiantes.set(i, estudianteActualizado);
                return;
            }
        }
    }

    
    public void eliminar(int codigoEstudiante) {
        estudiantes.removeIf(e -> e.getCodigoEstudiante() == codigoEstudiante);
    }

    
    public Estudiante obtener(int codigoEstudiante) {
        for (Estudiante e : estudiantes) {
            if (e.getCodigoEstudiante() == codigoEstudiante) {
                return e;
            }
        }
        return null;
    }

    
    public List<Estudiante> obtenerTodos() {
        return estudiantes;
    }
}

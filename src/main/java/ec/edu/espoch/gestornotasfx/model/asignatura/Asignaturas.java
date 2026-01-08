/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.asignatura;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Asignaturas implements IAsignaturas {
     private List<Asignatura> asignaturas;

    public Asignaturas() {
        asignaturas = new ArrayList<>();
    }

    
    public void agregar(Asignatura asignatura) {
        asignaturas.add(asignatura);
    }

    
    public void actualizar(Asignatura asignaturaActualizada) {
        for (int i = 0; i < asignaturas.size(); i++) {
            Asignatura a = asignaturas.get(i);
            if (a.getCodigoAsignatura() == asignaturaActualizada.getCodigoAsignatura()) {
                asignaturas.set(i, asignaturaActualizada);
                return;
            }
        }
    }

    
    public void eliminar(int codigoAsignatura) {
        asignaturas.removeIf(a -> a.getCodigoAsignatura() == codigoAsignatura);
    }

    
    public Asignatura obtener(int codigoAsignatura) {
        for (Asignatura a : asignaturas) {
            if (a.getCodigoAsignatura() == codigoAsignatura) {
                return a;
            }
        }
        return null;
    }

    
    public List<Asignatura> obtenerTodos() {
        return asignaturas;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.docentes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Docentes implements IDocentes {
    private List<Docente> docentes;

    public Docentes(){
        docentes = new ArrayList<>();
    }
    
    
    public void agregar(Docente d) {
        docentes.add(d);
    }

    public Docente obtener(String cedula) {
        for (Docente d : docentes) {
            if (d.getCedula().equals(cedula)) {
                return d;
            }
        }
        return null;
    }

    public boolean actualizar(String cedula, Docente nuevo) {
        for (int i = 0; i < docentes.size(); i++) {
            if (docentes.get(i).getCedula().equals(cedula)) {
                docentes.set(i, nuevo);
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String cedula) {
        return docentes.removeIf(d -> d.getCedula().equals(cedula));
    }

    public List<Docente> obtenerTodos() {
        return docentes;
    }
}

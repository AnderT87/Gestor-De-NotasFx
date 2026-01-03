/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.gestornotasfx.model.docentes;

/**
 *
 * @author Admin
 */
public class Docente {
    private String cedula;
    private String nombre;
    private String apellido;
    private String titulo_Academico;
    private String correo_Institucional;

    public Docente(String cedula, String nombre, String apellido, String titulo_Academico, String correo_Institucional) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo_Academico = titulo_Academico;
        this.correo_Institucional = correo_Institucional;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTitulo_Academico() {
        return titulo_Academico;
    }

    public void setTitulo_Academico(String titulo_Academico) {
        this.titulo_Academico = titulo_Academico;
    }

    public String getCorreo_Institucional() {
        return correo_Institucional;
    }

    public void setCorreo_Institucional(String correo_Institucional) {
        this.correo_Institucional = correo_Institucional;
    }

    @Override
    public String toString() {
        return "Docentes{" + "cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", titulo_Academico=" + titulo_Academico + ", correo_Institucional=" + correo_Institucional + '}';
    }
}

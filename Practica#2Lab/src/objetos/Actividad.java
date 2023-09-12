/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author lopez
 */
public class Actividad implements Serializable {

    String nombre;
    String descripcion;
    float ponderacion;
    String nombreCurso;
    ArrayList<NotaAlumno> listaNotas = new ArrayList<>();

    public Actividad(String nombre, String descripcion, float ponderacion, String nombreCurso) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ponderacion = ponderacion;
        this.nombreCurso = nombreCurso;
    }
    
    public Actividad(){
        
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(float ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public ArrayList<NotaAlumno> getListaNotas() {
        return listaNotas;
    }

    public void setListaNotas(ArrayList<NotaAlumno> listaNotas) {
        this.listaNotas = listaNotas;
    }

   

}

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
public class Curso implements Serializable{

    int codigo;
    String nombreCurso;
    int creditos;
    String profesor;
    String apellidoProfesor;
    int alumnos;
    int codigoProfesor;
    ArrayList<Alumno> listaAlumnosCurso= new ArrayList<>();
    ArrayList<Actividad> listaActividades= new ArrayList<>();
    

    public Curso(int codigo, String nombreCurso, int creditos, String profesor, String apellidoProfesor, int alumnos, int codigoProfesor) {
        this.codigo = codigo;
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
        this.profesor = profesor;
        this.apellidoProfesor = apellidoProfesor;
        this.alumnos = listaAlumnosCurso.size();
        this.codigoProfesor = codigoProfesor;
    }
    public Curso(){
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getApellidoProfesor() {
        return apellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        this.apellidoProfesor = apellidoProfesor;
    }

    public int getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(int alumnos) {
        this.alumnos = alumnos;
    }

    public int getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(int codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public ArrayList<Alumno> getListaAlumnosCurso() {
        return listaAlumnosCurso;
    }

    public void setListaAlumnosCurso(ArrayList<Alumno> listaAlumnosCurso) {
        this.listaAlumnosCurso = listaAlumnosCurso;
    }

    public ArrayList<Actividad> getListaActividades() {
        return listaActividades;
    }

    public void setListaActividades(ArrayList<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    
    

   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.io.Serializable;

/**
 *
 * @author lopez
 */
public class NotaAlumno implements Serializable {

    int codigoAlumno;
    float notaActividad;
    String cursoNota;

    public NotaAlumno(int codigoAlumno, float notaActividad, String cursoNota) {
        this.codigoAlumno = codigoAlumno;
        this.notaActividad = notaActividad;
        this.cursoNota = cursoNota;
    }
    public NotaAlumno(){
        
    }

    public int getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(int codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
    }

    public float getNotaActividad() {
        return notaActividad;
    }

    public void setNotaActividad(float notaActividad) {
        this.notaActividad = notaActividad;
    }

    public String getCursoNota() {
        return cursoNota;
    }

    public void setCursoNota(String cursoNota) {
        this.cursoNota = cursoNota;
    }
    
   
}

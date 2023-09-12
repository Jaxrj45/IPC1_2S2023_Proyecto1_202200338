/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.io.Serializable;
import java.util.ArrayList;

public class Alumno implements Serializable {

    int codigo;
    String nombre;
    String apellido;
    String correo;
    String genero;
    String password;
    String imagen;
    int cursoAsignado;
    ArrayList<Curso> listaCursosAsignados = new ArrayList<Curso>();

    public Alumno(int codigo, String nombre, String apellido, String correo, String genero, String password, String imagen, int cursoAsignado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.genero = genero;
        this.password = password;
        this.imagen = imagen;
        this.cursoAsignado = cursoAsignado;
    }

    public Alumno() {

    }

    public ArrayList<Curso> getListaCursosAsignados() {
        return listaCursosAsignados;
    }

    public void setListaCursosAsignados(ArrayList<Curso> listaCursosAsignados) {
        this.listaCursosAsignados = listaCursosAsignados;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCursoAsignado() {
        return cursoAsignado;
    }

    public void setCursoAsignado(int cursoAsignado) {
        this.cursoAsignado = cursoAsignado;
    }

}

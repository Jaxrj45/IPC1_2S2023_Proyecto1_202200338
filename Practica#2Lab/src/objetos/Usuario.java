/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetos;

import java.io.Serializable;
import java.util.ArrayList;


public class Usuario implements Serializable{

    int codigo;
    String nombre;
    String apellido;
    String correo;
    String password;
    String genero;
    String imagen;
    int rol;
    ArrayList<Curso> listaCursosProfesor= new ArrayList<>();

    public Usuario(int codigo, String nombre, String apellido, String correo, String password, String genero, String imagen, int rol) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
        this.genero = genero;
        this.imagen = imagen;
        this.rol = rol;
        
    }
    
    
    public Usuario(){
        
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public ArrayList<Curso> getListaCursosProfesor() {
        return listaCursosProfesor;
    }

    public void setListaCursosProfesor(ArrayList<Curso> listaCursosProfesor) {
        this.listaCursosProfesor = listaCursosProfesor;
    }

  

   

   
}

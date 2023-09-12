/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Archivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import objetos.Alumno;
import objetos.Curso;
import objetos.Usuario;

/**
 *
 * @author lopez
 */
public class Binario {

    String profesores;
    String Cursos;
    String alumnos;

    public Binario() {
        this.profesores = "C://Users//lopez//OneDrive//Desktop//Proyectos Java//Practica#2Lab//DatosAlmacenados//Profesores.bin";
        this.Cursos = "C://Users//lopez//OneDrive//Desktop//Proyectos Java//Practica#2Lab//DatosAlmacenados//Cursos.bin";
        this.alumnos = "C://Users//lopez//OneDrive//Desktop//Proyectos Java//Practica#2Lab//DatosAlmacenados//Alumnos.bin";
        
    }

    public boolean guardarProfesorBinario(ArrayList<Usuario> lista) {

        try {
            File fProfesor = new File(profesores);
            if (fProfesor.exists()) {
                fProfesor.delete();
            }
            if (!fProfesor.exists()) {
                FileOutputStream fos = new FileOutputStream(profesores);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(lista);
                oos.close();
                fos.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public ArrayList<Usuario> obtener() {
        ArrayList<Usuario> lista = new ArrayList<>();

        try {
            
            File fProfesor = new File(profesores);
            if (fProfesor.exists()) {
                FileInputStream fis = new FileInputStream(profesores);
                ObjectInputStream ois = new ObjectInputStream(fis);
                lista = (ArrayList<Usuario>) ois.readObject();
                ois.close();
                fis.close();
            }
            return lista;
        } catch (IOException | ClassNotFoundException e) {
            return lista;
        }

    }

    public boolean guardarCursoBinario(ArrayList<Curso> lista) {

        try {
            File fBinario = new File(Cursos);
            if (fBinario.exists()) {
                fBinario.delete();
            }
            if (!fBinario.exists()) {
                FileOutputStream fos = new FileOutputStream(Cursos);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(lista);
                oos.close();
                fos.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public ArrayList<Curso> ObtenerCursos() {
        ArrayList<Curso> listaC = new ArrayList<>();

        try {
            File fBinario = new File(Cursos);
            if (fBinario.exists()) {
                FileInputStream fis = new FileInputStream(Cursos);
                ObjectInputStream ois = new ObjectInputStream(fis);
                listaC = (ArrayList<Curso>) ois.readObject();
                ois.close();
                fis.close();
            }
            return listaC;
        } catch (IOException | ClassNotFoundException e) {
            return listaC;
        }

    }
    
    public boolean guardarAlumnoBinario(ArrayList<Alumno> lista) {

        try {
            File f = new File(alumnos);
            if (f.exists()) {
                f.delete();
            }
            if (!f.exists()) {
                FileOutputStream fos = new FileOutputStream(alumnos);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(lista);
                oos.close();
                fos.close();
            }
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public ArrayList<Alumno> obtenerAlumno() {
        ArrayList<Alumno> listaA = new ArrayList<>();

        try {
            File f = new File(this.alumnos);
            if (f.exists()) {
                FileInputStream fis = new FileInputStream(this.alumnos);
                ObjectInputStream ois = new ObjectInputStream(fis);
                listaA = (ArrayList<Alumno>) ois.readObject();
                ois.close();
                fis.close();
            }
            return listaA;
        } catch (IOException | ClassNotFoundException e) {
            return listaA;
        }

    }
}

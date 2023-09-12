/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import static Funciones.funcionesAlumno.listaAlumnos;
import static Funciones.funcionesUsuario.listaUsuarios;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import objetos.Actividad;
import objetos.Alumno;

import objetos.Curso;
import objetos.Usuario;

/**
 *
 * @author lopez
 */
public class funcionesActividad {

    public static ArrayList<Actividad> listaActividades = new ArrayList<>();

    public static void agregarActividad(String nombre, String Descripcion, float ponderacion, String nombreCurso) {

        Actividad actividad = new Actividad(nombre, Descripcion, ponderacion, nombreCurso);
        for (Curso curso : funcionesCurso.listaCursos) {
            if (curso.getCodigo() == Integer.parseInt(nombreCurso)) {
                curso.getListaActividades().add(actividad);
                JOptionPane.showMessageDialog(null, "Agregado exitosamente");
            }
        }

    }

    public static void setearCurso(Curso cursoNuevo, int codigoProfe) {

        for (Usuario a : listaUsuarios) {
            if (a.getCodigo() == codigoProfe) {
                for (Curso c : a.getListaCursosProfesor()) {
                    for (int i = 0; i < a.getListaCursosProfesor().size(); i++) {
                        if (c.getNombreCurso().equals(cursoNuevo.getNombreCurso())) {
                            a.getListaCursosProfesor().set(i, cursoNuevo);
                            System.out.println("true");
                            break;
                        }
                    }
                    break;
                }
            }
        }

    }

    public static void setearCursoaAlumno(Curso cursoNuevo, int codigoAlumno) {

        for (Alumno a : listaAlumnos) {
            if (a.getCodigo() == codigoAlumno) {
                for (Curso c : a.getListaCursosAsignados()) {
                    for (int i = 0; i < a.getListaCursosAsignados().size(); i++) {
                        if (c.getNombreCurso().equals(cursoNuevo.getNombreCurso())) {
                            a.getListaCursosAsignados().set(i, cursoNuevo);
                            break;
                        }
                    }
                    break;
                }
            }
        }

    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import static Funciones.funcionesUsuario.listaUsuarios;
import static Funciones.funcionesUsuario.quitarCursosaProfesor;
import Vista.modulo_Administracion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetos.Alumno;
import objetos.Curso;
import objetos.Usuario;
import static vistaAdmin.agregarCurso.profesorCurso;

public class funcionesCurso {

    public static ArrayList<Curso> listaCursos = new ArrayList<Curso>();
    public static int num[] = new int[listaCursos.size()];

    public static boolean verificarCodigoCurso(int codigo) {

        for (Curso cur : listaCursos) {
            if (codigo == cur.getCodigo()) {
                return true;
            }
        }
        return false;

    }

    public static void agregarCurso(int codigoCurso, String nombreCurso, int creditos, String profesor) {

        String nombreProfe = "";
        int codigoProfe = 0;

        if (!verificarCodigoCurso(codigoCurso)) {

            String[] separar = profesor.split("-");
            nombreProfe = separar[1];
            codigoProfe = Integer.parseInt(separar[0]);

            String apellidoProfe = funcionesUsuario.retornarApellidoProfe(codigoProfe);

            Curso curso = new Curso(codigoCurso, nombreCurso, creditos, nombreProfe, apellidoProfe, 0, codigoProfe);

            listaCursos.add(curso);

            JOptionPane.showMessageDialog(null, "Agregado Exitosamente");
            asignacionCursoProfesor(codigoProfe, curso);

            modulo_Administracion.actualizarTablaCurso();
            contadorCurso1();

            profesorCurso.removeAllItems();

        } else {
            JOptionPane.showMessageDialog(null, "El codigo ya existe");
        }
    }

    public static Curso buscarCurso(int codigo) {

        for (Curso cur : listaCursos) {
            if (codigo == cur.getCodigo()) {
                return cur;
            }
        }
        return null;

    }

    public static void actualizarCurso(Curso modificarC, int codigo, String nombreCurso, int creditos, String profesor, int alumnos, int codigoProfe) {

        quitarCursosaProfesor(modificarC, modificarC.getCodigoProfesor());

        modificarC.setCodigo(codigo);
        modificarC.setNombreCurso(nombreCurso);
        modificarC.setCreditos(creditos);
        modificarC.setProfesor(profesor);
        modificarC.setAlumnos(alumnos);
        modificarC.setCodigoProfesor(codigoProfe);

        asignacionCursoProfesor(codigoProfe, modificarC);

    }

    public static void eliminarCurso(Curso eliminar) {

        if (eliminar.getListaAlumnosCurso().isEmpty()) {
            quitarCursosaProfesor(eliminar, eliminar.getCodigoProfesor());
            listaCursos.remove(eliminar);
            JOptionPane.showMessageDialog(null, "Curso \"" + eliminar.getNombreCurso() + "\" eliminado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "No se puede Eliminar Este Curso porque tiene alumnos asignados");
        }

    }

    public static boolean buscarProfesorExistente(int profesor) {

        for (Usuario us : funcionesUsuario.listaUsuarios) {
            if (us.getRol() == 1) {
                if (us.getCodigo() == profesor) {
                    return true;
                }
            }

        }

        return false;
    }

    public static String retornarNombreProfesorC(int profesor) {

        for (Usuario us : funcionesUsuario.listaUsuarios) {
            if (us.getRol() == 1) {
                if (us.getCodigo() == profesor) {
                    return us.getNombre();
                }
            }

        }

        return null;
    }

    public static void pdfCursos() {

        try {

            Document documento = new Document();

            String destino = "ListadoCursos.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));

            documento.open();
            Paragraph tituloPDF = new Paragraph("LISTA DE CURSOS");
            tituloPDF.setAlignment(1);
            documento.add(tituloPDF);

            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(5);
            float[] medidaCeldas = {0.45f, 0.9f, 0.9f, 1.3f, 1.3f};
            tabla.setWidths(medidaCeldas);
            PdfPCell titulo = new PdfPCell(new Paragraph("Listado de Cursos"));

            titulo.setBackgroundColor(BaseColor.RED);
            titulo.setColspan(5);
            tabla.addCell(titulo);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Creditos");
            tabla.addCell("Alumnos");
            tabla.addCell("Profesor");

            for (Curso curso : listaCursos) {
                tabla.addCell(String.valueOf(curso.getCodigo()));
                tabla.addCell(curso.getNombreCurso());
                tabla.addCell(String.valueOf(curso.getCreditos()));
                tabla.addCell(String.valueOf(curso.getAlumnos()));
                tabla.addCell(curso.getProfesor() + " " + curso.getApellidoProfesor());
            }

            documento.add(tabla);

            documento.close();
            JOptionPane.showMessageDialog(null, "Se ha creado el pdf Exitosamente");

        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(funcionesUsuario.class.getName()).log(Level.SEVERE, "El archivo esta abierto", ex);
            JOptionPane.showMessageDialog(null, "El archivo esta abierto");
        }

    }

    public static int contadorCurso1() {

        int alumnosCurso = 0;;

        for (Curso curso : listaCursos) {
            if (alumnosCurso < curso.getAlumnos()) {
                alumnosCurso = curso.getAlumnos();
            }

        }

        return alumnosCurso;

    }

    public static void asignacionCursoProfesor(int codigoProfesor, Curso nuevoCurso) {

        for (Usuario us : listaUsuarios) {
            if (us.getCodigo() == codigoProfesor) {
                us.getListaCursosProfesor().add(nuevoCurso);
            }

        }

    }

    public static void setearCursoProfe(int codigoProfesor, Curso nuevoCurso, int codigoCurso) {

        for (Usuario us : listaUsuarios) {
            if (us.getCodigo() == codigoProfesor) {
                us.getListaCursosProfesor().set(codigoCurso, nuevoCurso);
            }

        }

    }

    public static boolean verificarAsignacion(int codigo, Curso curso) {

        for (Curso listaC : listaCursos) {
            for (Alumno listaAlumnosC : listaC.getListaAlumnosCurso()) {
                if (curso.getNombreCurso().equals(listaC.getNombreCurso())) {
                    if (codigo == listaAlumnosC.getCodigo()) {
                        JOptionPane.showMessageDialog(null, "Este Alumno Ya esta Registrado " + codigo);
                        return true;

                    }
                }

            }
        }
        return false;
    }

    public static void cursosConMasEstudiantes() {
        int variabl = 0;
        int contador=-1;
        num = new int[listaCursos.size()];
        
            for (Curso c : listaCursos) {
                contador++;
                variabl = 0;
                variabl = c.getListaAlumnosCurso().size();
                num[contador]=variabl;
                
            }
            
        
        
    }

    public static void ordenar() {

        for (int i = 0; i < num.length; i++) {

            for (int j = 0; j < num.length - 1; j++) {

                if (num[j] < num[j + 1]) {
                    int temporal = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temporal;
                }
            }
        }
    }

}

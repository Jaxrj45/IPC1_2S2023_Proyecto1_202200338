/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import static Funciones.funcionesCurso.buscarCurso;
import static Funciones.funcionesCurso.listaCursos;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objetos.Alumno;
import objetos.Curso;

/**
 *
 * @author lopez
 */
public class funcionesAlumno {

    public static ArrayList<Alumno> listaAlumnos = new ArrayList<>();

    public static boolean buscarcodigoAlumno(int codigo) {

        for (Alumno al : listaAlumnos) {
            if (al.getCodigo() == codigo) {
                return true;
            }
        }

        return false;
    }

    public static void pdfAlumno() {

        try {

            Document documento = new Document();

            String destino = "ListadoAlumnos.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));

            documento.open();
            Paragraph tituloPDF = new Paragraph("**LISTADO DE ALUMNOS**");
            tituloPDF.setAlignment(1);
            documento.add(tituloPDF);

            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);
            PdfPTable tabla = new PdfPTable(5);
            float[] medidaCeldas = {1.1f, 0.9f, 0.9f, 1.5f, 0.6f};
            tabla.setWidths(medidaCeldas);
            PdfPCell titulo = new PdfPCell(new Paragraph("Listado de Alumnos"));

            titulo.setBackgroundColor(BaseColor.ORANGE);
            titulo.setColspan(5);
            tabla.addCell(titulo);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("correo");
            tabla.addCell("Genero");

            for (Alumno alumno : listaAlumnos) {
                tabla.addCell(String.valueOf(alumno.getCodigo()));
                tabla.addCell(alumno.getNombre());
                tabla.addCell(alumno.getApellido());
                tabla.addCell(alumno.getCorreo());
                tabla.addCell(alumno.getGenero());
            }

            documento.add(tabla);

            documento.close();
            JOptionPane.showMessageDialog(null, "PDF CREADO " + destino);

        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(funcionesUsuario.class.getName()).log(Level.SEVERE, "El archivo esta abierto", ex);
            JOptionPane.showMessageDialog(null, "El archivo esta abierto");
        }

    }

    public static int contadorAlumnosH() {

        int contarAl = 0;
        for (Alumno al : listaAlumnos) {
            if (al.getGenero().toLowerCase().equals("m")) {
                contarAl++;
            }
        }

        return contarAl;
    }

    public static int contadorAlumnosM() {

        int contarAlMujeres = 0;
        for (Alumno al : listaAlumnos) {
            if (al.getGenero().toLowerCase().equals("f")) {
                contarAlMujeres++;
            }
        }

        return contarAlMujeres;
    }

    public static boolean mostrarVentanaAlumno(String codigoAlumno, String passwordAlumno) {

        for (Alumno al : listaAlumnos) {

            if (String.valueOf(al.getCodigo()).equals(codigoAlumno) && al.getPassword().equals(passwordAlumno)) {
                return true;
            }
        }
        return false;
    }

    public static Alumno buscarAlumno(int codigo) {

        for (Alumno al : listaAlumnos) {
            if (al.getCodigo() == codigo) {
                return al;
            }
        }

        return null;

    }

    public static void asignacionCursoAlumno(int codigoCurso, Alumno nuevoAlumno) {

        for (Curso curso : listaCursos) {
            if (curso.getCodigo() == codigoCurso) {
                curso.getListaAlumnosCurso().add(nuevoAlumno);

            }
        }

    }

    public static void obtenerCursoAsignado(int codigoAlumno, Curso cursoAsignado) {

        for (Alumno al : listaAlumnos) {

            if (al.getCodigo() == codigoAlumno) {
                al.getListaCursosAsignados().add(cursoAsignado);
                break;

            }
        }

    }

    public static void eliminarAlumno(Alumno eliminar, int codigoCurso) {

        eliminadodelCurso(codigoCurso, eliminar.getCodigo());
        int contador = -1;
        for (Curso c : listaCursos) {
            if (c.getCodigo() == codigoCurso) {
                for (Alumno alumno : c.getListaAlumnosCurso()) {
                    contador++;
                    if (alumno.getCodigo() == eliminar.getCodigo()) {
                        c.getListaAlumnosCurso().remove(contador);
                        JOptionPane.showMessageDialog(null, "Alumno " + alumno.getNombre() + " Eliminado");
                        break;
                    }
                }
            }
        }
    }

    public static void eliminadodelCurso(int codigoCurso, int codigoAlumno) {

        int contador = -1;
        for (Alumno a : listaAlumnos) {
            if (a.getCodigo() == codigoAlumno) {
                for (Curso c : a.getListaCursosAsignados()) {
                    contador++;
                    if (c.getCodigo() == codigoCurso) {
                        a.getListaCursosAsignados().remove(contador);
                        break;
                    }
                }
            }
        }
    }

    public static void actualizarAlumno(Alumno alumnoActualizar, String nombre, String apellido, String Correo,
            String password, String Genero) {

        alumnoActualizar.setNombre(nombre);
        alumnoActualizar.setApellido(apellido);
        alumnoActualizar.setCorreo(Correo);
        alumnoActualizar.setPassword(password);
        alumnoActualizar.setGenero(Genero);
        JOptionPane.showMessageDialog(null, "Datos Actualizados");
        
    }
    
    

}

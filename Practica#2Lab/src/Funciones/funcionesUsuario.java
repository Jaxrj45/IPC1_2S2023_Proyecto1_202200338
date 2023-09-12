/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import Archivos.Binario;
import static Vista.modulo_Administracion.limpiarG;
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
import objetos.Curso;

import objetos.Usuario;

/**
 *
 * @author lopez
 */
public class funcionesUsuario {

    public static Binario binario;
    public static int tamanio;
    public static ArrayList<Usuario> listaUsuarios = new ArrayList<>();

    public static boolean verificarCodigo(int codigo) {

        for (Usuario usu : listaUsuarios) {
            if (codigo == usu.getCodigo()) {
                return true;
            }
        }
        return false;

    }

    public static void agregarUsuario(int codigo, String nombre, String apellido, String correo, String password, String genero, String imagen, int rol) {

        if (!verificarCodigo(codigo)) {
            if (genero.toLowerCase().equals("masculino")) {
                genero = "M";
            } else if (genero.toLowerCase().equals("femenino")) {
                genero = "F";
            }
            Usuario usuario = new Usuario(codigo, nombre, apellido, correo, password, genero, imagen, rol);
            listaUsuarios.add(usuario);

            JOptionPane.showMessageDialog(null, "Agregado con Exito");
            contarHombres();
            contarMujeres();

        } else {
            JOptionPane.showMessageDialog(null, "El codigo ya existe");
        }

    }

    public static void actualizarUsuario(Usuario modificable, String nombre, String apellido, String correo, String password, String genero) {

        modificable.setNombre(nombre);
        modificable.setApellido(apellido);
        modificable.setCorreo(correo);
        modificable.setPassword(password);
        modificable.setGenero(genero);

        JOptionPane.showMessageDialog(null, "Actualizacion exitosa");
        limpiarG();

    }

    public static Usuario buscarUsuario(int codigo) {

        for (Usuario usu : listaUsuarios) {
            if (codigo == usu.getCodigo()) {
                return usu;

            }
        }
        return null;

    }

    public static String valorComboBox(int index) {

        String genero = "";
        if (index == 0) {
            genero = "F";
            return genero;
        } else if (index == 1) {
            genero = "M";
        }

        return genero;

    }

    public static void eliminarUsuario(Usuario eliminar) {

        listaUsuarios.remove(eliminar);
        JOptionPane.showMessageDialog(null, "Usuario \"" + eliminar.getNombre() + "\" eliminado exitosamente");

    }

    public static void pdf() {

        try {

            Document documento = new Document();

            String destino = "ListadoProfesores.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(destino));

            documento.open();
            Paragraph tituloPDF = new Paragraph("LISTADO DE PROFESORES");
            tituloPDF.setAlignment(1);
            documento.add(tituloPDF);

            documento.add(Chunk.NEWLINE);
            documento.add(Chunk.NEWLINE);

            PdfPTable tabla = new PdfPTable(5);
            float[] medidaCeldas = {0.45f, 0.9f, 0.9f, 1.3f, 0.45f};
            tabla.setWidths(medidaCeldas);
            PdfPCell titulo = new PdfPCell(new Paragraph("Listado de Usuarios"));

            titulo.setBackgroundColor(BaseColor.GREEN);
            titulo.setColspan(5);
            tabla.addCell(titulo);
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("Correo");
            tabla.addCell("Genero");

            for (Usuario imprimirU : listaUsuarios) {
                tabla.addCell(String.valueOf(imprimirU.getCodigo()));
                tabla.addCell(imprimirU.getNombre());
                tabla.addCell(imprimirU.getApellido());
                tabla.addCell(imprimirU.getCorreo());
                tabla.addCell(imprimirU.getGenero());
            }

            documento.add(tabla);

            documento.close();
            JOptionPane.showMessageDialog(null, "PDF CREADO " + destino);

        } catch (FileNotFoundException | DocumentException ex) {
            Logger.getLogger(funcionesUsuario.class.getName()).log(Level.SEVERE, "El archivo esta abierto", ex);
            JOptionPane.showMessageDialog(null, "El archivo esta abierto");
        }
    }

    public static String retornarApellidoProfe(int codigo) {

        for (Usuario usu : listaUsuarios) {
            if (codigo == usu.getCodigo()) {
                return usu.getApellido();
            }
        }
        return null;

    }

    public static int contarHombres() {

        int contadorH = 0;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getGenero().equals("M")) {
                contadorH++;

            }

        }

        return contadorH;

    }

    public static int contarMujeres() {

        int contadorM = 0;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getGenero().equals("F")) {
                contadorM++;

            }

        }

        return contadorM;

    }

    public static boolean mostrarVentanaProfesor(String codigoP, String passwordP) {

        for (Usuario us : listaUsuarios) {
            if (String.valueOf(us.getCodigo()).equals(codigoP) && us.getPassword().equals(passwordP)) {
                return true;
            }
        }
        return false;
    }

    public static void quitarCursosaProfesor(Curso cursoaEliminar, int codigoProfesor) {

        for (Usuario a : listaUsuarios) {
            if (a.getCodigo() == codigoProfesor) {
                for (Curso c : a.getListaCursosProfesor()) {
                    for (int i = 0; i < a.getListaCursosProfesor().size(); i++) {
                        if (c.getNombreCurso().equals(cursoaEliminar.getNombreCurso())) {
                            a.getListaCursosProfesor().remove(i);
                            break;
                        }
                    }
                    break;
                }
            }
        }

    }

    public static void aaaaaaaaaaaaaaaa(int codigoC, int codigoProfesor) {

        System.out.println(codigoProfesor + "aaaaaaaaaaaaa");

        for (Usuario a : listaUsuarios) {
            if (a.getCodigo() == codigoProfesor) {
                if (codigoC == 1) {
                    a.getListaCursosProfesor().remove(0);
                    tamanio = a.getListaCursosProfesor().size();
                } else {
                    a.getListaCursosProfesor().remove(codigoC - 1);
                }

                for (Curso c : a.getListaCursosProfesor()) {
                    System.out.println("---------------cursos finales");
                    System.out.println(c.getNombreCurso());
                }

            }
        }

    }
    
    

}

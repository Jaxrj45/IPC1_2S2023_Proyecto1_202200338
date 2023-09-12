/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Archivos.Binario;
import Funciones.funcionesAlumno;
import static Funciones.funcionesAlumno.contadorAlumnosH;
import static Funciones.funcionesAlumno.contadorAlumnosM;
import static Funciones.funcionesAlumno.listaAlumnos;
import static Funciones.funcionesAlumno.pdfAlumno;
import Funciones.funcionesCurso;
import static Funciones.funcionesCurso.asignacionCursoProfesor;
import static Funciones.funcionesCurso.buscarProfesorExistente;
import static Funciones.funcionesCurso.cursosConMasEstudiantes;
import static Funciones.funcionesCurso.listaCursos;
import static Funciones.funcionesCurso.num;
import static Funciones.funcionesCurso.ordenar;
import static Funciones.funcionesCurso.pdfCursos;
import static Funciones.funcionesCurso.retornarNombreProfesorC;
import Funciones.funcionesUsuario;
import static Funciones.funcionesUsuario.contarHombres;
import static Funciones.funcionesUsuario.contarMujeres;
import static Funciones.funcionesUsuario.listaUsuarios;
import static Funciones.funcionesUsuario.pdf;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import objetos.Alumno;
import objetos.Curso;
import objetos.Usuario;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import vistaAdmin.actualizarCurso;
import static vistaAdmin.actualizarCurso.profesorCursoActualizado;
import vistaAdmin.actualizarProfesor;
import vistaAdmin.agregarCurso;
import vistaAdmin.agregarProfesor;

/**
 *
 * @author lopez
 */
public class modulo_Administracion extends javax.swing.JFrame {

    public static DefaultTableModel modelo;
    public static DefaultTableModel modeloCurso;
    public static DefaultTableModel modeloAlumno;
    public static Object codigoSeleccionado = null;
    public static Object codigoCursoSeleccionado = null;
    public static Object nombreProfesor = null;
    public static DefaultPieDataset datos = new DefaultPieDataset();
    public static ChartPanel panel = null;
    public static DefaultPieDataset datosAlumno = new DefaultPieDataset();
    public static DefaultCategoryDataset datosCurso = new DefaultCategoryDataset();
    public static ChartPanel panelAlumno = null;
    public static ChartPanel panelCurso = null;
    public static int contarH = 0;
    public static int contarM = 0;
    public static int contarAlumnosH = 0;
    public static int contarAlumnosM = 0;
    public static int valor1 = 0;
    public static int valor2 = 0;
    public static int valor3 = 0;
    Binario binario;

    public modulo_Administracion() {
        initComponents();
        actualizarTabla();
        actualizarTablaCurso();
        actualizarTablaAlumno();
        cursosConMasEstudiantes();
        ordenar();

        this.binario = new Binario();

    }

    public void subirDatos() {
        listaUsuarios = binario.obtener();
        funcionesCurso.listaCursos = binario.ObtenerCursos();
        listaAlumnos = binario.obtenerAlumno();
        actualizarTabla();
        actualizarTablaCurso();
        actualizarTablaAlumno();

    }

    public static void actualizarTabla() {

        modelo = new DefaultTableModel() {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Correo");
        modelo.addColumn("Genero");

        for (Usuario usuario : funcionesUsuario.listaUsuarios) {

            String[] fila = new String[5];
            fila[0] = String.valueOf(usuario.getCodigo());
            fila[1] = usuario.getNombre();
            fila[2] = usuario.getApellido();
            fila[3] = usuario.getCorreo();
            fila[4] = usuario.getGenero();

            modelo.addRow(fila);
        }
        tablaProfesor1.setModel(modelo);
    }

    public static void actualizarTablaAlumno() {

        modeloAlumno = new DefaultTableModel() {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloAlumno.addColumn("Codigo");
        modeloAlumno.addColumn("Nombre");
        modeloAlumno.addColumn("Apellido");
        modeloAlumno.addColumn("Correo");
        modeloAlumno.addColumn("Genero");

        for (Alumno alumno : funcionesAlumno.listaAlumnos) {

            String[] fila = new String[5];
            fila[0] = String.valueOf(alumno.getCodigo());
            fila[1] = alumno.getNombre();
            fila[2] = alumno.getApellido();
            fila[3] = alumno.getCorreo();
            fila[4] = alumno.getGenero();

            modeloAlumno.addRow(fila);

        }
        tablaAlumno.setModel(modeloAlumno);

    }

    public static void actualizarTablaCurso() {

        modeloCurso = new DefaultTableModel() {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloCurso.addColumn("Codigo");
        modeloCurso.addColumn("Nombre");
        modeloCurso.addColumn("Creditos");
        modeloCurso.addColumn("Alumnos");
        modeloCurso.addColumn("Profesor");

        for (Curso curso : funcionesCurso.listaCursos) {

            String[] fila = new String[5];
            fila[0] = String.valueOf(curso.getCodigo());
            fila[1] = curso.getNombreCurso();
            fila[2] = String.valueOf(curso.getCreditos());
            fila[3] = String.valueOf(curso.getListaAlumnosCurso().size());
            fila[4] = curso.getProfesor();

            modeloCurso.addRow(fila);
        }
        tablaCurso.setModel(modeloCurso);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Vcurso = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        Listado1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProfesor1 = new javax.swing.JTable();
        crearProfesor = new javax.swing.JButton();
        btnactualizarProfesor = new javax.swing.JButton();
        exportarListadoProfesor = new javax.swing.JButton();
        eliminarProfesor = new javax.swing.JButton();
        cargaMasivaProfesor = new javax.swing.JButton();
        graficaP = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        Listado2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaCurso = new javax.swing.JTable();
        crearCurso = new javax.swing.JButton();
        actualizarCursoVista = new javax.swing.JButton();
        exportarListadoCurso = new javax.swing.JButton();
        eliminarCurso = new javax.swing.JButton();
        cargaMasivaCurso = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        graficaCursos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumno = new javax.swing.JTable();
        cargaMasivaAlumno = new javax.swing.JButton();
        exportarListadoAlumnos = new javax.swing.JButton();
        Listado = new javax.swing.JLabel();
        graficaAlumnos = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(6, 29, 50));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Vcurso.setForeground(new java.awt.Color(0, 0, 51));
        Vcurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VcursoMouseClicked(evt);
            }
        });

        Listado1.setText("Listado Oficial");

        tablaProfesor1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Apellido", "Correo", "Genero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProfesor1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProfesor1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProfesor1);

        crearProfesor.setText("Crear");
        crearProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearProfesorActionPerformed(evt);
            }
        });

        btnactualizarProfesor.setText("Actualizar");
        btnactualizarProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarProfesorActionPerformed(evt);
            }
        });

        exportarListadoProfesor.setText("Exportar Listado a PDF");
        exportarListadoProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarListadoProfesorActionPerformed(evt);
            }
        });

        eliminarProfesor.setText("Eliminar");
        eliminarProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProfesorActionPerformed(evt);
            }
        });

        cargaMasivaProfesor.setText("Carga Masiva");
        cargaMasivaProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargaMasivaProfesorActionPerformed(evt);
            }
        });

        graficaP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                graficaPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout graficaPLayout = new javax.swing.GroupLayout(graficaP);
        graficaP.setLayout(graficaPLayout);
        graficaPLayout.setHorizontalGroup(
            graficaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 265, Short.MAX_VALUE)
        );
        graficaPLayout.setVerticalGroup(
            graficaPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );

        jButton2.setText("Login");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(Listado1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(crearProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cargaMasivaProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnactualizarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(eliminarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(exportarListadoProfesor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(graficaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(crearProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cargaMasivaProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnactualizarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eliminarProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(exportarListadoProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(graficaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(Listado1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        Vcurso.addTab("Profesores", jPanel2);

        Listado2.setText("Listado Oficial");

        tablaCurso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Creditos", "Alumnos", "Profesor"
            }
        ));
        tablaCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaCursoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaCurso);

        crearCurso.setText("Crear");
        crearCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearCursoActionPerformed(evt);
            }
        });

        actualizarCursoVista.setText("Actualizar");
        actualizarCursoVista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarCursoVistaActionPerformed(evt);
            }
        });

        exportarListadoCurso.setText("Exportar Listado a PDF");
        exportarListadoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarListadoCursoActionPerformed(evt);
            }
        });

        eliminarCurso.setText("Eliminar");
        eliminarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCursoActionPerformed(evt);
            }
        });

        cargaMasivaCurso.setText("Carga Masiva");
        cargaMasivaCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargaMasivaCursoActionPerformed(evt);
            }
        });

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout graficaCursosLayout = new javax.swing.GroupLayout(graficaCursos);
        graficaCursos.setLayout(graficaCursosLayout);
        graficaCursosLayout.setHorizontalGroup(
            graficaCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 268, Short.MAX_VALUE)
        );
        graficaCursosLayout.setVerticalGroup(
            graficaCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(exportarListadoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(crearCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cargaMasivaCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(actualizarCursoVista, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(eliminarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(graficaCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(18, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(Listado2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(crearCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cargaMasivaCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(actualizarCursoVista, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(eliminarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(exportarListadoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(graficaCursos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(Listado2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        Vcurso.addTab("Cursos", jPanel3);

        tablaAlumno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Código", "Nombre", "Apellido", "Correo", "Género"
            }
        ));
        tablaAlumno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlumnoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlumno);

        cargaMasivaAlumno.setText("Carga Masiva");
        cargaMasivaAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargaMasivaAlumnoActionPerformed(evt);
            }
        });

        exportarListadoAlumnos.setText("Exportar Listado a PDF");
        exportarListadoAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarListadoAlumnosActionPerformed(evt);
            }
        });

        Listado.setText("Listado Oficial");

        javax.swing.GroupLayout graficaAlumnosLayout = new javax.swing.GroupLayout(graficaAlumnos);
        graficaAlumnos.setLayout(graficaAlumnosLayout);
        graficaAlumnosLayout.setHorizontalGroup(
            graficaAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graficaAlumnosLayout.setVerticalGroup(
            graficaAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

        jButton3.setText("Login");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Listado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cargaMasivaAlumno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(exportarListadoAlumnos, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(graficaAlumnos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(33, 33, 33)
                        .addComponent(cargaMasivaAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(exportarListadoAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(graficaAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(13, Short.MAX_VALUE)
                        .addComponent(Listado)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );

        Vcurso.addTab("Alumnos", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Vcurso)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Vcurso)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnactualizarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarProfesorActionPerformed

        try {
            Usuario usuarioEncontrado = funcionesUsuario.buscarUsuario(Integer.parseInt((String) codigoSeleccionado));
            if (usuarioEncontrado != null) {
                actualizarProfesor.usuarioModificable = usuarioEncontrado;
                actualizarProfesor ap = new actualizarProfesor();
                ap.setVisible(true);

            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario");
        }


    }//GEN-LAST:event_btnactualizarProfesorActionPerformed

    private void actualizarCursoVistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarCursoVistaActionPerformed

        try {
            Curso cursoEncontrado = funcionesCurso.buscarCurso(Integer.parseInt((String) codigoCursoSeleccionado));
            if (cursoEncontrado != null) {
                actualizarCurso.cursoModificable = cursoEncontrado;
                actualizarCurso ap = new actualizarCurso();
                ap.setVisible(true);

            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccione un Curso");
        }
    }//GEN-LAST:event_actualizarCursoVistaActionPerformed

    private void crearProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearProfesorActionPerformed

        //this.setVisible(false);
        agregarProfesor AgregarP = new agregarProfesor();
        AgregarP.setVisible(true);


    }//GEN-LAST:event_crearProfesorActionPerformed

    private void tablaProfesor1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProfesor1MouseClicked

        int seleccionar = tablaProfesor1.rowAtPoint(evt.getPoint());
        codigoSeleccionado = tablaProfesor1.getValueAt(seleccionar, 0);
        //JOptionPane.showMessageDialog(null, codigoSeleccionado);
    }//GEN-LAST:event_tablaProfesor1MouseClicked

    private void eliminarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProfesorActionPerformed

        try {
            Usuario usuarioEncontradoEliminar = funcionesUsuario.buscarUsuario(Integer.parseInt((String) codigoSeleccionado));
            if (usuarioEncontradoEliminar != null) {
                funcionesUsuario.eliminarUsuario(usuarioEncontradoEliminar);
                actualizarTabla();
                binario.guardarProfesorBinario(listaUsuarios);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccione un usuario");
        }
    }//GEN-LAST:event_eliminarProfesorActionPerformed

    private void exportarListadoProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarListadoProfesorActionPerformed

        pdf();

    }//GEN-LAST:event_exportarListadoProfesorActionPerformed

    private void crearCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearCursoActionPerformed

        agregarCurso agregarC = new agregarCurso();
        agregarC.setVisible(true);


    }//GEN-LAST:event_crearCursoActionPerformed

    private void tablaCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaCursoMouseClicked
        int seleccionar = tablaCurso.rowAtPoint(evt.getPoint());
        codigoCursoSeleccionado = tablaCurso.getValueAt(seleccionar, 0);
    }//GEN-LAST:event_tablaCursoMouseClicked

    private void VcursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VcursoMouseClicked
        modulo_Administracion.actualizarTablaCurso();
    }//GEN-LAST:event_VcursoMouseClicked
    public static void llenarCombo() {
        try {
            for (Usuario us : listaUsuarios) {
                agregarCurso.profesorCurso.addItem(us.getCodigo() + "-" + us.getNombre());
            }
        } catch (Exception e) {

        }

    }

    public static void llenarComboActualizar() {

        for (Usuario us : listaUsuarios) {
            profesorCursoActualizado.addItem(us.getCodigo() + "-" + us.getNombre());
        }
    }


    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        //DefaultPieDataset datos = new DefaultPieDataset();
        contarH = contarHombres();
        contarM = contarMujeres();
        datos.setValue("Hombres", contarH);
        datos.setValue("Mujeres", contarM);

        //generoGrafica de profesores ///////////////////////////////////////////////////////////////////
        JFreeChart graficoCircular = ChartFactory.createPieChart(
                "Genero de Profesores",
                datos,
                true,
                true,
                false);

        panel = new ChartPanel(graficoCircular);
        panel.setPreferredSize(new Dimension(250, 200));

        graficaP.setLayout(new BorderLayout());
        graficaP.add(panel, BorderLayout.NORTH);
        pack();
        repaint();
        //*********************Segunda Grafica*********************************************************
        contarAlumnosH = contadorAlumnosH();
        contarAlumnosM = contadorAlumnosM();
        datosAlumno.setValue("Hombres", contarAlumnosH);
        datosAlumno.setValue("Mujeres", contarAlumnosM);

        //generoGrafica;
        JFreeChart graficoCircularAlumno = ChartFactory.createPieChart(
                "Genero de Alumnos",
                datosAlumno,
                true,
                true,
                false);

        panelAlumno = new ChartPanel(graficoCircularAlumno);
        panelAlumno.setPreferredSize(new Dimension(250, 200));

        graficaAlumnos.setLayout(new BorderLayout());
        graficaAlumnos.add(panelAlumno, BorderLayout.NORTH);
        pack();
        repaint();

        //Tercera Grafica********************************************************************************
        try {
            valor1 = num[0];
            valor2 = num[1];
            valor3 = num[2];
        } catch (Exception e) {
        }

        String nombreV1 = "";
        String nombreV2 = "";
        String nombreV3 = "";
        int contador = 0;
        for (Curso c : listaCursos) {

            if (valor1 == c.getListaAlumnosCurso().size() && contador != 1) {
                nombreV1 = c.getNombreCurso();
                contador = 1;
            }
            if (contador != 1 && contador != 2) {
                if (valor2 == c.getListaAlumnosCurso().size()) {
                    nombreV2 = c.getNombreCurso();
                    contador = 2;

                }
            }
            if (contador != 2 && contador != 1) {
                if (valor3 == c.getListaAlumnosCurso().size()) {
                    nombreV3 = c.getNombreCurso();

                }
            }

        }
        datosCurso.setValue(valor1, nombreV1, "1");
        datosCurso.setValue(valor2, nombreV2, "2");
        datosCurso.setValue(valor3, nombreV3, "3");

        JFreeChart graficoBarra = ChartFactory.createBarChart3D("Cursos con mas Estudiantes",
                "Curso 1",
                "Cantidad",
                datosCurso,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        panelCurso = new ChartPanel(graficoBarra);
        panelCurso.setMouseWheelEnabled(false);
        panelCurso.setPreferredSize(new Dimension(graficaCursos.getWidth(), graficaCursos.getHeight()));

        graficaCursos.setLayout(new BorderLayout());
        graficaCursos.add(panelCurso, BorderLayout.NORTH);
        pack();
        repaint();


    }//GEN-LAST:event_formWindowOpened

    public static void limpiarG() {

        int contarH = contarHombres();
        int contarM = contarMujeres();
        datos.clear();
        datos.setValue("Hombres", contarH);
        datos.setValue("Mujeres", contarM);

        //generoGrafica;
        JFreeChart graficoCircular = ChartFactory.createPieChart(
                "Genero de Profesores",
                datos,
                true,
                true,
                false);

        panel = new ChartPanel(graficoCircular);
        panel.setPreferredSize(new Dimension(250, 200));

        graficaP.setLayout(new BorderLayout());
        graficaP.add(panel, BorderLayout.NORTH);

        //pack();
        //repaint();
    }

    public static void limpiarGraficaCursos() {
        cursosConMasEstudiantes();
        ordenar();
        datosCurso.clear();
        valor1 = num[0];
        valor2 = num[1];
        valor3 = num[3];

        datosCurso.setValue(valor1, "Curso1", "a");
        datosCurso.setValue(valor2, "Curso 2", "a");
        datosCurso.setValue(valor3, "Curso 3", "a");

        //generoGrafica;
        JFreeChart graficoBarra = ChartFactory.createBarChart3D("Cursos con mas Estudiantes",
                "Curso 1",
                "Cantidad",
                datosCurso,
                PlotOrientation.VERTICAL,
                true,
                true,
                false);

        panelCurso = new ChartPanel(graficoBarra);
        panelCurso.setMouseWheelEnabled(false);
        panelCurso.setPreferredSize(new Dimension(graficaCursos.getWidth(), graficaCursos.getHeight()));

        graficaCursos.setLayout(new BorderLayout());
        graficaCursos.add(panelCurso, BorderLayout.NORTH);

    }

    public static void limpiargraficaAlumno() {

        int contarAlumnosH = contadorAlumnosH();
        int contarAlumnosM = contadorAlumnosM();
        datosAlumno.clear();
        datosAlumno.setValue("Hombres", contarAlumnosH);
        datosAlumno.setValue("Mujeres", contarAlumnosM);

        //generoGrafica;
        JFreeChart graficoCircularAlumno = ChartFactory.createPieChart(
                "Genero de Alumnos",
                datosAlumno,
                true,
                true,
                false);

        panelAlumno = new ChartPanel(graficoCircularAlumno);
        panelAlumno.setPreferredSize(new Dimension(250, 200));

        graficaAlumnos.setLayout(new BorderLayout());
        graficaAlumnos.add(panelAlumno, BorderLayout.NORTH);

        //pack();
        //repaint();
    }
    private void eliminarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarCursoActionPerformed

        try {
            Curso cursoEliminar = funcionesCurso.buscarCurso(Integer.parseInt((String) codigoCursoSeleccionado));
            if (cursoEliminar != null) {
                funcionesCurso.eliminarCurso(cursoEliminar);
                actualizarTablaCurso();

                binario.guardarCursoBinario(funcionesCurso.listaCursos);
                binario.guardarProfesorBinario(listaUsuarios);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Seleccione un Curso");
        }

    }//GEN-LAST:event_eliminarCursoActionPerformed
    public void guardarArchivo() {
        JFileChooser seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");

        seleccionarArchivo.setFileFilter(filtro);

        int seleccionar = seleccionarArchivo.showOpenDialog(this);

        if (seleccionar == JFileChooser.APPROVE_OPTION) {
            File archivo = seleccionarArchivo.getSelectedFile();
            guardarArchivo2(archivo);
        }
    }

    public void guardarArchivo2(File archivo) {
        FileWriter fichero = null;
        PrintWriter pw = null;

        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);

            for (Usuario us : listaUsuarios) {
                String linea = us.getCodigo() + "," + us.getNombre() + "," + us.getApellido() + "," + us.getCorreo() + "," + us.getPassword()
                        + "," + us.getGenero() + "," + us.getImagen() + "," + us.getRol();
                pw.println(linea);

            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error3");
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error 4");
            }
        }
    }
    private void cargaMasivaProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargaMasivaProfesorActionPerformed

        JFileChooser seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");

        seleccionarArchivo.setFileFilter(filtro);

        int seleccionar = seleccionarArchivo.showOpenDialog(this);

        if (seleccionar == JFileChooser.APPROVE_OPTION) {
            File archivo = seleccionarArchivo.getSelectedFile();
            cargarArchivo(archivo);
            //actualizando la grafica 
            limpiarG();
            binario.guardarProfesorBinario(listaUsuarios);
            binario.guardarProfesorBinario(listaUsuarios);
        }
    }//GEN-LAST:event_cargaMasivaProfesorActionPerformed

    private void cargaMasivaCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargaMasivaCursoActionPerformed

        JFileChooser seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");

        seleccionarArchivo.setFileFilter(filtro);

        int seleccionar = seleccionarArchivo.showOpenDialog(this);

        if (seleccionar == JFileChooser.APPROVE_OPTION) {
            File archivo = seleccionarArchivo.getSelectedFile();
            cargarArchivoCurso(archivo);
            binario.guardarCursoBinario(funcionesCurso.listaCursos);
            binario.guardarProfesorBinario(listaUsuarios);

        }
    }//GEN-LAST:event_cargaMasivaCursoActionPerformed

    private void cargaMasivaAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargaMasivaAlumnoActionPerformed

        JFileChooser seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");

        seleccionarArchivo.setFileFilter(filtro);

        int seleccionar = seleccionarArchivo.showOpenDialog(this);

        if (seleccionar == JFileChooser.APPROVE_OPTION) {
            File archivo = seleccionarArchivo.getSelectedFile();
            cargarArchivoAlumno(archivo);
            binario.guardarAlumnoBinario(listaAlumnos);
        }


    }//GEN-LAST:event_cargaMasivaAlumnoActionPerformed

    private void tablaAlumnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaAlumnoMouseClicked

    private void exportarListadoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarListadoCursoActionPerformed
        pdfCursos();
    }//GEN-LAST:event_exportarListadoCursoActionPerformed

    private void exportarListadoAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarListadoAlumnosActionPerformed
        pdfAlumno();
    }//GEN-LAST:event_exportarListadoAlumnosActionPerformed

    private void graficaPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graficaPMouseClicked


    }//GEN-LAST:event_graficaPMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        binario.guardarCursoBinario(funcionesCurso.listaCursos);
        binario.guardarProfesorBinario(listaUsuarios);
        login volver = new login();
        volver.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        binario.guardarAlumnoBinario(listaAlumnos);
        binario.guardarCursoBinario(funcionesCurso.listaCursos);
        binario.guardarProfesorBinario(listaUsuarios);
        this.dispose();
        login volver = new login();
        volver.setVisible(true);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        binario.guardarAlumnoBinario(listaAlumnos);
        binario.guardarCursoBinario(funcionesCurso.listaCursos);
        binario.guardarProfesorBinario(listaUsuarios);
        this.dispose();
        login volver = new login();
        volver.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed
    public static void cargarArchivoCurso(File archivo) {

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null) {
                String arreglo[] = linea.split(",");
                if (arreglo.length >= 3) {
                    Curso c = new Curso();

                    c.setCodigo(Integer.parseInt(arreglo[0]));
                    c.setNombreCurso(arreglo[1]);
                    c.setCreditos(Integer.parseInt(arreglo[2]));
                    c.setCodigoProfesor(Integer.parseInt(arreglo[3]));
                    c.setAlumnos(0);
                    //buscando el nombre del profesor con el codigo ingresado
                    String nombreProfe = retornarNombreProfesorC(Integer.parseInt(arreglo[3]));
                    String apellidoProfe = funcionesUsuario.retornarApellidoProfe(Integer.parseInt(arreglo[0]));
                    c.setProfesor(nombreProfe);
                    c.setApellidoProfesor(apellidoProfe);

                    boolean cursoE = funcionesCurso.verificarCodigoCurso(Integer.parseInt(arreglo[0]));
                    boolean profesorEncontrado = buscarProfesorExistente(Integer.parseInt(arreglo[3]));
                    if (!cursoE) {
                        if (profesorEncontrado) {
                            funcionesCurso.listaCursos.add(c);
                            asignacionCursoProfesor(Integer.parseInt(arreglo[0]), c);
                        }
                    }

                }
            }

            actualizarTablaCurso();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error");
        } finally {
            try {

                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error2");
            }
        }

    }

    public static void cargarArchivoAlumno(File archivo) {

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null) {

                String arreglo[] = linea.split(",");
                if (arreglo.length >= 3) {
                    Alumno a = new Alumno();

                    a.setCodigo(Integer.parseInt(arreglo[0]));
                    a.setNombre(arreglo[1]);
                    a.setApellido(arreglo[2]);
                    a.setCorreo(arreglo[3]);
                    a.setPassword("1234");
                    a.setGenero(arreglo[4]);
                    a.setImagen("prueba.jpg");
                    boolean alumnoE = funcionesAlumno.buscarcodigoAlumno(Integer.parseInt(arreglo[0]));
                    if (!alumnoE) {
                        if (listaAlumnos.size() <= 50) {
                            funcionesAlumno.listaAlumnos.add(a);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Hay alumnos con los mismos codigos");
                    }

                }
            }

            actualizarTablaAlumno();
            limpiargraficaAlumno();

        } catch (HeadlessException | IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "error");
        } finally {
            try {

                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error2");
            }
        }

    }

    public static void cargarArchivo(File archivo) {

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null) {
                String arreglo[] = linea.split(",");
                if (arreglo.length >= 3) {
                    Usuario u = new Usuario();

                    u.setCodigo(Integer.parseInt(arreglo[0]));
                    u.setNombre(arreglo[1]);
                    u.setApellido(arreglo[2]);
                    u.setCorreo(arreglo[3]);
                    u.setPassword("1234");
                    u.setGenero(arreglo[4]);
                    u.setImagen("prueba.jpg");
                    u.setRol(1);
                    boolean usE = funcionesUsuario.verificarCodigo((Integer.parseInt(arreglo[0])));
                    if (!usE) {
                        if (listaUsuarios.size() <= 50) {
                            listaUsuarios.add(u);
                        }

                    }

                }
            }

            actualizarTabla();

        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "error");
        } finally {
            try {

                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "error2");
            }
        }

    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(modulo_Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modulo_Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modulo_Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modulo_Administracion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modulo_Administracion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Listado;
    private javax.swing.JLabel Listado1;
    private javax.swing.JLabel Listado2;
    private javax.swing.JTabbedPane Vcurso;
    private javax.swing.JButton actualizarCursoVista;
    private javax.swing.JButton btnactualizarProfesor;
    private javax.swing.JButton cargaMasivaAlumno;
    private javax.swing.JButton cargaMasivaCurso;
    private javax.swing.JButton cargaMasivaProfesor;
    private javax.swing.JButton crearCurso;
    private javax.swing.JButton crearProfesor;
    private javax.swing.JButton eliminarCurso;
    private javax.swing.JButton eliminarProfesor;
    private javax.swing.JButton exportarListadoAlumnos;
    private javax.swing.JButton exportarListadoCurso;
    private javax.swing.JButton exportarListadoProfesor;
    public static javax.swing.JPanel graficaAlumnos;
    public static javax.swing.JPanel graficaCursos;
    public static javax.swing.JPanel graficaP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTable tablaAlumno;
    public static javax.swing.JTable tablaCurso;
    public static javax.swing.JTable tablaProfesor1;
    // End of variables declaration//GEN-END:variables
}

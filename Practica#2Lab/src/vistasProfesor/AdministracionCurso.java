/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistasProfesor;

import Archivos.Binario;
import Funciones.funcionesActividad;
import static Funciones.funcionesActividad.agregarActividad;
import Funciones.funcionesAlumno;
import static Funciones.funcionesAlumno.buscarAlumno;
import static Funciones.funcionesAlumno.buscarcodigoAlumno;
import static Funciones.funcionesAlumno.listaAlumnos;
import Funciones.funcionesCurso;
import static Funciones.funcionesCurso.cursosConMasEstudiantes;
import static Funciones.funcionesCurso.ordenar;
import static Funciones.funcionesCurso.verificarAsignacion;
import static Funciones.funcionesUsuario.listaUsuarios;
import static Vista.modulo_Administracion.actualizarTablaCurso;
import static Vista.modulo_Administracion.limpiarGraficaCursos;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import objetos.Actividad;
import objetos.Alumno;
import objetos.Curso;
import objetos.NotaAlumno;
import static vistasProfesor.moduloProfesor.cursoSeleccionado;
import static vistasProfesor.moduloProfesor.usuarioLogeado;

/**
 *
 * @author lopez
 */
public class AdministracionCurso extends javax.swing.JFrame {

    public static DefaultTableModel modeloTablaAlumno;
    public static DefaultTableModel modeloActividad;
    public static Object alumnoSeleccionado = 0;
    public static Alumno cargarAlumno = null;
    public static NotaAlumno subirNotasAlumno = new NotaAlumno();
    public static Binario bin;
    public static float[] listaA = new float[listaAlumnos.size()];
    public static int [] listaPosicion = new int[listaAlumnos.size()];

    public AdministracionCurso() {
        initComponents();
        iniciarComponentes2();
        actualizarTablaAlumnosCurso();
        actualizartablaActividades();
        this.bin = new Binario();
    }

    private void iniciarComponentes2() {
        //buscar el nombre del curso dentro del listado de cursos del usuario 
        for (Curso cursoA : usuarioLogeado.getListaCursosProfesor()) {
            if (cursoA.getCodigo() == Integer.parseInt(cursoSeleccionado)) {
                labelTitulo.setText(cursoA.getNombreCurso());

            }

        }

    }

    public static void actualizarTablaAlumnosCurso() {

        modeloTablaAlumno = new DefaultTableModel() {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloTablaAlumno.addColumn("Codigo");
        modeloTablaAlumno.addColumn("Nombre");
        modeloTablaAlumno.addColumn("Apellido");
        modeloTablaAlumno.addColumn("Correo");
        modeloTablaAlumno.addColumn("Acciones");

        for (Curso alumnoCurso : funcionesCurso.listaCursos) {
            for (Alumno al : alumnoCurso.getListaAlumnosCurso()) {
                if (alumnoCurso.getCodigo() == Integer.parseInt(cursoSeleccionado)) {
                    String[] fila = new String[5];
                    fila[0] = String.valueOf(al.getCodigo());
                    fila[1] = al.getNombre();
                    fila[2] = al.getApellido();
                    fila[3] = al.getCorreo();
                    fila[4] = "ver mas informacion";

                    modeloTablaAlumno.addRow(fila);
                }

            }
        }
        tablaAlumnos.setModel(modeloTablaAlumno);
    }

    public static void actualizartablaActividades() {

        modeloActividad = new DefaultTableModel() {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modeloActividad.addColumn("Nombre");
        modeloActividad.addColumn("descripcion");
        modeloActividad.addColumn("ponderacion");
        modeloActividad.addColumn("promedio");

        for (Curso actividadCurso : funcionesCurso.listaCursos) {
            for (Actividad ac : actividadCurso.getListaActividades()) {
                if (actividadCurso.getCodigo() == Integer.parseInt(cursoSeleccionado)) {

                    String[] fila = new String[4];
                    fila[0] = ac.getNombre();
                    fila[1] = ac.getDescripcion();
                    fila[2] = String.valueOf(ac.getPonderacion());
                    float notas = 0;
                    for (NotaAlumno NA : ac.getListaNotas()) {

                        notas = notas + NA.getNotaActividad();
                    }
                    float promedio = notas / ac.getListaNotas().size();
                    fila[3] = String.valueOf(promedio);

                    modeloActividad.addRow(fila);
                }
            }
        }
        tablaActividades.setModel(modeloActividad);
    }

    public static void cargarArchivoAlumnoCurso(File archivo) {

        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null) {
                String arreglo[] = linea.split(",");
                if (arreglo.length >= 0) {
                    //cargar los alumnos al curso
                    boolean alumnoE = funcionesAlumno.buscarcodigoAlumno(Integer.parseInt(arreglo[0]));
                    Alumno subirAlumno = buscarAlumno(Integer.parseInt(arreglo[0])); //buscar el codigo del alumno y retorna el objeto alumno
                    Curso enviarCurso = funcionesCurso.buscarCurso(Integer.parseInt(cursoSeleccionado));
                    boolean alumnoAsignado = verificarAsignacion(Integer.parseInt(arreglo[0]), enviarCurso);//verificar si el alumno ya fue registrado por el admin 

                    if (alumnoE) {//verificar si el alumno existe

                        if (!alumnoAsignado) { //verificar que el alumno no este asignado
                            for (Curso cursoProfesor : usuarioLogeado.getListaCursosProfesor()) {//recorrer el listado de cursos del profesor
                                if (cursoProfesor.getCodigo() == Integer.parseInt(cursoSeleccionado)) {
                                    //agregamos un alumno a la lista de listaAlumnosCurso
                                    funcionesAlumno.asignacionCursoAlumno((int) cursoProfesor.getCodigo(), subirAlumno);
                                    //funcionesActividad.setearCurso(enviarCurso, cursoProfesor.getCodigoProfesor());

                                }
                            }
                            funcionesAlumno.obtenerCursoAsignado(Integer.parseInt(arreglo[0]), enviarCurso);

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Hay alumnos que no estan registrados");
                    }
                }

            }

            actualizarTablaAlumnosCurso();
            actualizarTablaCurso();

        } catch (HeadlessException | IOException | NumberFormatException e) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaActividades = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtNombreActividad = new javax.swing.JTextField();
        txtDescripcionActividad = new javax.swing.JTextField();
        txtPonderacion = new javax.swing.JTextField();
        notasCSV = new javax.swing.JButton();
        btncrearActividad = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        cargaMasivaAlumnos = new javax.swing.JButton();
        paneltitulo = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setText("Listado de Alumnos");

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlumnos);

        tablaActividades.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaActividades);

        jLabel3.setText("Actividades");

        jLabel4.setText("Acumulado:");

        jLabel5.setText("Crear Actividad");

        jLabel6.setText("Nombre");

        jLabel7.setText("Descripcion");

        jLabel8.setText("Ponderacion");

        jLabel9.setText("Notas");

        notasCSV.setText("Seleccionar Archivo CSV");
        notasCSV.setEnabled(false);
        notasCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notasCSVActionPerformed(evt);
            }
        });

        btncrearActividad.setText("Crear Actividad");
        btncrearActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrearActividadActionPerformed(evt);
            }
        });

        jButton3.setText("Top 5 - Estudiantes con Mejor Promedio");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Top 5 - Estudiantes con Peor Promedio");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Reportes");

        cargaMasivaAlumnos.setText("Carga Masiva Alumnos");
        cargaMasivaAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargaMasivaAlumnosActionPerformed(evt);
            }
        });

        paneltitulo.setLayout(new java.awt.GridLayout(1, 0));

        labelTitulo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        labelTitulo.setText("jLabel1");

        btnLogin.setText("Volver");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cargaMasivaAlumnos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btncrearActividad, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(80, 80, 80)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4)
                                        .addComponent(txtNombreActividad)
                                        .addComponent(txtDescripcionActividad, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                        .addComponent(txtPonderacion, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                        .addComponent(notasCSV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGap(47, 47, 47))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnLogin)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(paneltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173)
                .addComponent(labelTitulo)
                .addGap(431, 431, 431))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelTitulo)
                        .addComponent(btnLogin))
                    .addComponent(paneltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtNombreActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cargaMasivaAlumnos, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDescripcionActividad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtPonderacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(notasCSV)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncrearActividad)
                    .addComponent(jButton4))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        PromedioBajo pb= new PromedioBajo();
        pb.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cargaMasivaAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargaMasivaAlumnosActionPerformed

        JFileChooser seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");

        seleccionarArchivo.setFileFilter(filtro);

        int seleccionar = seleccionarArchivo.showOpenDialog(this);

        if (seleccionar == JFileChooser.APPROVE_OPTION) {
            File archivo = seleccionarArchivo.getSelectedFile();
            cargarArchivoAlumnoCurso(archivo);

            bin.guardarCursoBinario(funcionesCurso.listaCursos);
            bin.guardarProfesorBinario(listaUsuarios);
            bin.guardarAlumnoBinario(listaAlumnos);
            cursosConMasEstudiantes();
            ordenar();

        }


    }//GEN-LAST:event_cargaMasivaAlumnosActionPerformed

    public static void cargarNotas(File archivo) {

        FileReader fr = null;
        BufferedReader br = null;
        int contador = 0;

        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;

            while ((linea = br.readLine()) != null) {

                contador++;
                String arregloNotas[] = linea.split(",");
                if (arregloNotas.length == 2) {
                    // Actividad actividad = new Actividad();

                    boolean alumnoEncontrado = buscarcodigoAlumno((Integer.parseInt(arregloNotas[0])));
                    Curso enviarCurso2 = funcionesCurso.buscarCurso(Integer.parseInt(cursoSeleccionado));
                    if (alumnoEncontrado) {
                        //utilizando una variable global para almacenar los datos 
                        for (Curso c : funcionesCurso.listaCursos) {
                            for (Actividad ac : c.getListaActividades()) {
                                NotaAlumno SubirN = new NotaAlumno(Integer.parseInt(arregloNotas[0]), Integer.parseInt(arregloNotas[1]), "0");
                                System.out.println("obteniendo ayuda dios" + ac.getNombre());
                                if (ac.getNombre().equals(txtNombreActividad.getText())) {
                                    ac.getListaNotas().add(SubirN);

                                }

                            }
                        }
                        JOptionPane.showMessageDialog(null, "Notas Cargadas");
                        funcionesActividad.setearCursoaAlumno(enviarCurso2, Integer.parseInt(arregloNotas[0]));

                    }
                }
            }

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

    private void btncrearActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearActividadActionPerformed
        try {
            String nombre = txtNombreActividad.getText();
            String descripcion = txtDescripcionActividad.getText();
            float ponderacion = Float.parseFloat(txtPonderacion.getText());
            agregarActividad(nombre, descripcion, ponderacion, cursoSeleccionado);
            //setearCursoaAlumno();

            notasCSV.setEnabled(true);
            btncrearActividad.setEnabled(false);
            txtDescripcionActividad.setEnabled(false);
            txtNombreActividad.setEnabled(false);
            txtPonderacion.setEnabled(false);
            actualizarTablaCurso();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ha ocurrido un error");
        }


    }//GEN-LAST:event_btncrearActividadActionPerformed

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        //obtener el alumno codigo del alumno seleccionado en la tabla
        int seleccionar = tablaAlumnos.rowAtPoint(evt.getPoint());
        alumnoSeleccionado = tablaAlumnos.getValueAt(seleccionar, 0);

        if (evt.getClickCount() == 2) {

            try {
                //enviar el codigo del alumno seleccionado a una funcion que busca el alumno y regresa el objeto
                cargarAlumno = buscarAlumno(Integer.parseInt((String) alumnoSeleccionado));
                if (cargarAlumno != null) {
                    informacionEstudiante ventanaInfo = new informacionEstudiante();
                    ventanaInfo.setVisible(true);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Seleccione un usuario");
            }

        }
    }//GEN-LAST:event_tablaAlumnosMouseClicked

    private void notasCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notasCSVActionPerformed
        JFileChooser seleccionarArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos csv", "csv");

        seleccionarArchivo.setFileFilter(filtro);

        int seleccionar = seleccionarArchivo.showOpenDialog(this);

        if (seleccionar == JFileChooser.APPROVE_OPTION) {
            File archivo = seleccionarArchivo.getSelectedFile();
            cargarNotas(archivo);

            btncrearActividad.setEnabled(true);
            txtDescripcionActividad.setEnabled(true);
            txtNombreActividad.setEnabled(true);
            txtPonderacion.setEnabled(true);
            bin.guardarAlumnoBinario(listaAlumnos);
            bin.guardarCursoBinario(funcionesCurso.listaCursos);
            bin.guardarProfesorBinario(listaUsuarios);
            actualizartablaActividades();
        }
    }//GEN-LAST:event_notasCSVActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        bin.guardarAlumnoBinario(listaAlumnos);
        bin.guardarCursoBinario(funcionesCurso.listaCursos);
        bin.guardarProfesorBinario(listaUsuarios);
        this.dispose();


    }//GEN-LAST:event_btnLoginActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        moduloProfesor mp = new moduloProfesor();
        mp.dispose();
    }//GEN-LAST:event_formWindowOpened

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        PromedioAlto pa= new PromedioAlto();
        pa.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed
    
    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AdministracionCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministracionCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministracionCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministracionCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministracionCurso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btncrearActividad;
    private javax.swing.JButton cargaMasivaAlumnos;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JButton notasCSV;
    private javax.swing.JPanel paneltitulo;
    public static javax.swing.JTable tablaActividades;
    public static javax.swing.JTable tablaAlumnos;
    private static javax.swing.JTextField txtDescripcionActividad;
    private static javax.swing.JTextField txtNombreActividad;
    private static javax.swing.JTextField txtPonderacion;
    // End of variables declaration//GEN-END:variables
}

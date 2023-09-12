/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica.pkg2lab;

import Vista.login;
import Vista.modulo_Administracion;



/**
 *
 * @author lopez
 */
public class Practica2Lab {

   
    public static void main(String[] args) {
       modulo_Administracion md = new modulo_Administracion();
        md.subirDatos();
        login log = new login();
        log.setVisible(true);
        
    }
    
}

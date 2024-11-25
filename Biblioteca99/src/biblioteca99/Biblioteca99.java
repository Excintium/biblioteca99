/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblioteca99;

import Controllers.LibroController;
import DataBase.Conexion;
import Views.MenuPrincipal;

/**
 *
 * @author Nicol
 */
public class Biblioteca99 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion cx = new Conexion();
        cx.conectar();
        
        
        LibroController  lc = new LibroController();
        //System.out.println(lc.obtenerLibros());
        
        MenuPrincipal m = new MenuPrincipal();//para que el menu se vea hay que setearlo aqui
        m.setVisible(true);//con este comando se muestra el menu
    }
    
}

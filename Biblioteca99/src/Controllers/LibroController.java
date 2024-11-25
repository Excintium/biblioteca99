/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import DataBase.Conexion;
import Models.Libro;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Statement;
/**
 *
 * @author Nicol
 */
public class LibroController {
    
    Conexion cx;
    HelperController helper = new HelperController();
    
    public LibroController() {
    cx = new Conexion();
    cx.conectar();
    }
    
    //Obtener lista con todos los libros
    
    public List<Libro> obtenerLibros(int id){
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM LIBRO WHERE id_categoria = " + id + ";";
        try {
            ResultSet rs = cx.EjecutarQuery(query);
            while(rs.next()){
                libros.add(new Libro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("id_categoria")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error obtener libro: " + e.getMessage());
        }
        return libros;
    }
    
    public void agregarLibro(String titulo, int idCategoria){
        String query ="INSERT INTO `libro`(titulo, id_categoria) VALUES (?,?)";
        
        try {
            PreparedStatement st = cx.getConnection().prepareStatement(query);
            
            st.setString(1, titulo);
            st.setInt(2, idCategoria);
            st.executeUpdate();//retorna la cantidad de filas afectadas
            //System.out.println("Libro agregado correctamente");
            helper.showInformation("Libro agregado correctamente");
        } catch (Exception e) {
            //System.out.println("Error agregar libro" + e.getMessage());
            helper.showError(e.getMessage());
        }
    }
    
    public Libro buscarPorid(int id){
        Libro libroEncontrado = null;
        String query = "SELECT * FROM libro WHERE ID = " + id + ";";
        
        try {
            ResultSet rs = cx.EjecutarQuery(query);
            if(rs.next()){
                libroEncontrado = new Libro();
                libroEncontrado.setId(rs.getInt("id"));
                libroEncontrado.setTitulo(rs.getString("titulo"));
                libroEncontrado.setIdCategoria(rs.getInt("id_categoria"));
            }
        } catch (Exception e) {
            System.out.println("Libro no encontrado " + e.getMessage());
        }
        return libroEncontrado;
    }

    public void editarLibro (int idLibro, String titulo, int idCategoria){
        String query = "UPDATE libro SET titulo = '" + titulo +
                        "', id_categoria = " + idCategoria +
                        " WHERE id = " + idLibro + ";"; 
        System.out.println(query);//asi comprobamos la sintaxis del query
        
        try {
            Libro libroEncontrado = buscarPorid(idLibro);
            if(libroEncontrado != null){
                Statement st = cx.getConnection().createStatement();
                st.executeUpdate(query);
                System.out.println("Libro actualizado!");
            }else{
                System.out.println("El libro no se encontro");
            }
        } catch (Exception e) {
            System.out.println("No se ah podido actualizar el libro" + e.getMessage());
        }
    }

public void eliminarLibro (int id){
    String query = "DELETE FROM libro WHERE id = " + id + ";";
    
    try {
        Libro libroEncontrado = buscarPorid(id);
        if(libroEncontrado != null){
            Statement st = cx.getConnection().createStatement();
            st.executeUpdate(query);
            System.out.println("Libro eliminado correctamente");
        }else{
            System.out.println("El libro no se encontro");
        }
    } catch (Exception e) {
        System.out.println("No se ah podido eliminar el libro" + e.getMessage());
    }
}




}




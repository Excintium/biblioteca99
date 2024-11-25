/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import DataBase.Conexion;
import Models.Categoria;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Nicol
 */
public class CategoriaController {
    Conexion cx;

    public CategoriaController() {
        cx = new Conexion();
        cx.conectar();
    }
     
    public List<Categoria> obtenerCategorias(){
        List<Categoria> categorias = new ArrayList<>();
        String query = "SELECT * FROM CATEGORIA";
        
        try {
            ResultSet rs = cx.EjecutarQuery(query);
            while(rs.next()){
                categorias.add(new Categoria(
                    rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error al obtener categorias " + e.getMessage());
        }
        return categorias;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.*;
/**
 *
 * @author Roa
 */
public class ProductoDAO {
    Conexion conexion;
    
    public ProductoDAO(){
        conexion = new Conexion();
    }
    
    public String insertProducto(long codigoProducto, String nombre, String descripcion, String unidad, long precioProducto, long precioCompra, int stock, String ubicacionBodega){
        String rptaRegistro=null;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_insertProducto(?,?,?,?,?,?,?,?)}");
            cs.setLong(1, codigoProducto);
            cs.setString(2, nombre);
            cs.setString(3, descripcion);
            cs.setString(4, unidad);
            cs.setLong(5, precioProducto);
            cs.setLong(6, precioCompra);
            cs.setInt(7, stock);
            cs.setString(8, ubicacionBodega);
            
            int numFAfectas = cs.executeUpdate();
            
            if(numFAfectas>0){
                rptaRegistro="Registro exitoso";
            }    
        } catch (Exception e) {   
        }
        return rptaRegistro;
    }
    
    public ArrayList<MProducto> listProducto(){
        ArrayList listaProducto = new ArrayList();
        MProducto producto;
        try {
            Connection acceDB = conexion.getConexion();
            PreparedStatement ps = acceDB.prepareStatement("select * from tienda");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                producto = new MProducto();
                producto.setCod_producto(rs.getInt(1));
                producto.setNombre_producto(rs.getString(2));
                producto.setDescripcion_producto(rs.getString(3));
                producto.setUnidad_producto(rs.getString(4));
                producto.setPrecio_producto(rs.getInt(5));
                producto.setPrecio_compra(rs.getInt(6));
                producto.setStock_producto(rs.getInt(7));
                producto.setUbicacion_bodega(rs.getString(8));
               
                listaProducto.add(producto);
            }
        } catch (Exception e) {
        }
        return listaProducto;
    }
    
    public int editarProducto(long codigoProducto, String nombre, String descripcion, String unidad, long precioProducto, long precioCompra, int stock, String ubicacionBodega){
        int numFA = 0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_editProducto(?,?,?,?,?,?,?,?)}");
            cs.setLong(1, codigoProducto);
            cs.setString(2, nombre);
            cs.setString(3, descripcion);
            cs.setString(4, unidad);
            cs.setLong(5, precioProducto);
            cs.setLong(6, precioCompra);
            cs.setInt(7, stock);
            cs.setString(8, ubicacionBodega);
            
            numFA = cs.executeUpdate();
               
        }catch (Exception e) {
        }
        return numFA;
    }
    
      public int eliminarProducto(int codigoProducto){
        int numFA = 0;
        try {
            Connection accesoDB = conexion.getConexion();
            CallableStatement cs = accesoDB.prepareCall("{call sp_deletePersona(?)}");
            cs.setInt(1, codigoProducto); 
            
            numFA = cs.executeUpdate();
               
        }catch (Exception e) {
        }
        return numFA;
    }
      
}


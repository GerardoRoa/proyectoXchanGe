/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Roa
 */
public class Conexion {
    public Conexion() {
    }

    public Connection getConexion(){
        Connection con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://10.10.100.124:3306/ventas","ib","123456");
            System.out.println("Se ha establecido la conexion");
        } catch (Exception e) {
            System.out.println(" La conexion ha fallado");
        }
        
        return con;
    }

}


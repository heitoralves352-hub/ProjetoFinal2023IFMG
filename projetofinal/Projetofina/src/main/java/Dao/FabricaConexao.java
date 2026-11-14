/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Willian
 */
public class FabricaConexao {
    public static Connection getConnection(){
        Connection conexao = null;
        
        try {
            String URL= "jdbc:mysql://127.0.0.1/projeto_final";
            String Usuario="root";
            String Senha="";
            conexao= DriverManager.getConnection(URL, Usuario, Senha);
             
        } catch (SQLException e) {
            System.out.println("kkkkkkk: " + e.getMessage());
        }
        return conexao;
        
    }
    
}

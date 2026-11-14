/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class CategoriaDAO {
    
    public static final int cNavPrimeiro = 0;
    public static final int cNavAnterior = 1;
    public static final int cNavProximo = 2;
    public static final int cNavUltimo = 3;

    public static int PegaCodigoPelaNavegacao(int iopcao, int iCodigoAtual) {
        Connection conexao = FabricaConexao.getConnection();

        Statement consulta = null;
        ResultSet resultado = null;
        int CodigoEncontrado = -1;

        String sql = "";

        switch (iopcao) {
            case cNavPrimeiro:
                sql = "select min(CODIGO) as CODIGO from categoria";
                break;
            case cNavAnterior:
                sql = "select max(codigo) as Codigo from categoria where codigo < " + String.valueOf(iCodigoAtual);
                break;
            case cNavProximo:
                sql = "select min(codigo) as Codigo from categoria where codigo > " + String.valueOf(iCodigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(CODIGO) as CODIGO from categoria";
                break;
        }

        try {
            consulta = (Statement) conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            resultado.next();
            CodigoEncontrado = resultado.getInt("CODIGO");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL DE NAVEGAÇÃO" + e.getMessage());
        } finally {
            try {
                consulta.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao PegaCodigoProximaNavegaçao():" + e.getMessage());
            }
        }
        return CodigoEncontrado;
    }
    
    
    
    public static int ProximoCodigo() {
        Connection conexao = FabricaConexao.getConnection();

        Statement consulta = null;
        ResultSet resultado = null;
        int codigo = -1;

        String sql = "select max(codigo) as CODIGO from CATEGORIA";

        try {
            consulta = (Statement) conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            resultado.next();
            codigo = resultado.getInt("CODIGO");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao executar SQL DE NAVEGAÇÃO" + e.getMessage());
        } finally {
            try {
                consulta.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao função proximo codigo():" + e.getMessage());
            }
            return codigo + 1;
        }
    }

    public static void Salvar(Categoria categoria) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement insereSt = null;

        String sql = "insert into categoria (CODIGO, NOME, DESCRICAO) Values (?,?,?)";
        
        try {
            
            insereSt = conexao.prepareStatement(sql);
            insereSt.setInt(0, categoria.getCodigo());
            insereSt.setString(1, categoria.getNome());
            insereSt.setString(2, categoria.getDescricao());
            insereSt.executeUpdate();
            
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir Categoria" + e.getMessage());
        }finally{
             try {
               JOptionPane.showMessageDialog(null, "Categoria incluído com sucesso");
                insereSt.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao função proximo Salvar():" + e.getMessage());
            }
            
        }
    }
     
     public static void Excluir(int iCod){
        Connection conexao = FabricaConexao.getConnection();
        
        PreparedStatement excluiSt = null;
        
        String sql = "delete from categoria where codigo = " + iCod;
        
        try {
            excluiSt = conexao.prepareStatement(sql);
            excluiSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir categoria: " + e.getMessage());
        } finally {
            try {
                excluiSt.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função Excluir(): " + e.getMessage());
            }
        }
    }
    
    public static Categoria RecuperarCategoria(int iCod) {
        Connection conexao = FabricaConexao.getConnection();

        Categoria categoriarecuperado = new Categoria();

        Statement consulta = null;
        ResultSet resultado = null;

        String sql = "SELECT * FROM categoria WHERE codigo = " + iCod;

        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);

            resultado.next();

            if (resultado.getRow() == 1) {
                categoriarecuperado.setCodigo(resultado.getInt("codigo"));
                categoriarecuperado.setNome(resultado.getString("nome"));
                categoriarecuperado.setDescricao(resultado.getString("descricao"));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Recuperar Categoria" + e.getMessage());
        } finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Recuperar categoria():" + e.getMessage());
            }

        }
        return categoriarecuperado;
    }
    
     public static void Atualizar(Categoria categoria) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement atualizaSt = null;

        String sql = "update categoria set nome=?, descricao=? where codigo= ?";

        try {
            atualizaSt = conexao.prepareStatement(sql);
           
            atualizaSt.setString(1, categoria.getNome());
            atualizaSt.setString(2, categoria.getDescricao());
             atualizaSt.setInt(3, categoria.getCodigo());
            
            atualizaSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Categoria" + e.getMessage());
        } finally {
            try {
                atualizaSt.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Atualizar categoria():" + e.getMessage());
            }

        }
    }
     
     public static ArrayList<Categoria> RecuperaObjetos(String pCampo, String pValor){
        Connection conexao = FabricaConexao.getConnection();
        
        ArrayList<Categoria> categorias = new ArrayList<>();
        Statement consulta = null;
        ResultSet resultado = null;
        
        String sql = "select * from categoria where " + pCampo + " like '%" + pValor + "%'";      
        
        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            
            while(resultado.next()){
                Categoria categoriaTemp = new Categoria();
                categoriaTemp.setCodigo(resultado.getInt("CODIGO"));
                categoriaTemp.setNome(resultado.getString("NOME"));
                categoriaTemp.setDescricao(resultado.getString("DESCRICAO"));
                categorias.add(categoriaTemp);
            }            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar cliente: " + e.getMessage() + "\n" + sql);
        } finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função RecuperaObjetos(): " + e.getMessage());
            }
        }
        
        return categorias;        
    }
    

}

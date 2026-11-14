/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Produto;
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
public class ProdutoDAO {
    
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
                sql = "select min(CODIGO) as CODIGO from produto";
                break;
            case cNavAnterior:
                sql = "select max(codigo) as Codigo from produto where codigo < " + String.valueOf(iCodigoAtual);
                break;
            case cNavProximo:
                sql = "select min(codigo) as Codigo from produto where codigo > " + String.valueOf(iCodigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(CODIGO) as CODIGO from produto";
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

        String sql = "select max(codigo) as CODIGO from PRODUTO";

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

    public static void Salvar(Produto produto) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement insereSt = null;

        String sql = "insert into produto (CODIGO, NOME, ESTOQUE, PREcO, CODCATEGORIA) Values (?,?,?,?,?)";
        
        try {
            
            insereSt = conexao.prepareStatement(sql);
            insereSt.setInt(1, produto.getCodigo());
            insereSt.setString(2, produto.getNome());
            insereSt.setString(3, produto.getEstoque());
            insereSt.setString(4, produto.getPreco());
            insereSt.setInt(5, produto.getCodCategoria());
            insereSt.executeUpdate();
            
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir Produto" + e.getMessage());
        }finally{
             try {
                   JOptionPane.showMessageDialog(null, "Produto incluído com sucesso");
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
        
        String sql = "delete from produto where codigo = " + iCod;
        
        try {
            excluiSt = conexao.prepareStatement(sql);
            excluiSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + e.getMessage());
        } finally {
            try {
                excluiSt.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função Excluir(): " + e.getMessage());
            }
        }
    }
    public static Produto RecuperarProduto(int iCod) {
        Connection conexao = FabricaConexao.getConnection();

        Produto produtorecuperado = new Produto();

        Statement consulta = null;
        ResultSet resultado = null;

        String sql = "select produto.*, categoria.nome as nomecategoria from produto "
                + "inner join categoria on (categoria.codigo = produto.codCategoria) "
                + "where produto.codigo = " + iCod;  

        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);

            resultado.next();

            if (resultado.getRow() == 1) {
                produtorecuperado.setCodigo(resultado.getInt("codigo"));
                produtorecuperado.setNome(resultado.getString("nome"));
                produtorecuperado.setCategoria(resultado.getString("nomecategoria"));
                produtorecuperado.setEstoque(resultado.getString("estoque"));
                produtorecuperado.setPreco(resultado.getString("preco"));
                produtorecuperado.setCodCategoria(resultado.getInt("codcategoria"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Recuperar Produto" + e.getMessage());
        } finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Recuperar Produto():" + e.getMessage());
            }

        }
        return  produtorecuperado;
    }
    
     public static void Atualizar(Produto produto) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement atualizaSt = null;

        String sql = "update produto set nome=?, estoque=?, preco=?, codCategoria=? where codigo= ?";

        try {
            atualizaSt = conexao.prepareStatement(sql);
            atualizaSt.setString(1, produto.getNome());
            atualizaSt.setString(2, produto.getEstoque());
             atualizaSt.setString(3, produto.getPreco());
             atualizaSt.setInt(4, produto.getCodCategoria());
            atualizaSt.setInt(5, produto.getCodigo());
            
            atualizaSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Produto" + e.getMessage());
        } finally {
            try {
                atualizaSt.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Atualizar produto():" + e.getMessage());
            }

        }
    }
     
    public static ArrayList<Produto> RecuperaObjetos(String pCampo, String pValor){
        Connection conexao = FabricaConexao.getConnection();
        
        ArrayList<Produto> produtos = new ArrayList<>();
        Statement consulta = null;
        ResultSet resultado = null;
        
        String sql = "select produto.*, categoria.nome as nomecategoria from produto "
                + "inner join categoria on (categoria.codigo = produto.codCategoria) "
                + "where " + pCampo + " like '%" + pValor + "%'";      
        
        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            
            while(resultado.next()){
                Produto produtoTemp = new Produto();
                produtoTemp.setCodigo(resultado.getInt("CODIGO"));
                produtoTemp.setNome(resultado.getString("NOME"));
                produtoTemp.setCategoria(resultado.getString("NOMECATEGORIA"));
                produtoTemp.setPreco(resultado.getString("PRECO"));
                produtoTemp.setEstoque(resultado.getString("ESTOQUE"));
                produtoTemp.setCodCategoria(resultado.getInt("CODCATEGORIA"));
                produtos.add(produtoTemp);
            }            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar produto: " + e.getMessage() + "\n" + sql);
        } finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função RecuperaObjetos(): " + e.getMessage());
            }
        }
        
        return produtos;        
    }

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Fornecedor;
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
public class  FornecedorDAO{
    
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
                sql = "select min(CODIGO) as CODIGO from fornecedor";
                break;
            case cNavAnterior:
                sql = "select max(codigo) as Codigo from fornecedor where codigo < " + String.valueOf(iCodigoAtual);
                break;
            case cNavProximo:
                sql = "select min(codigo) as Codigo from fornecedor where codigo > " + String.valueOf(iCodigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(CODIGO) as CODIGO from fornecedor";
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

        String sql = "select max(codigo) as CODIGO from FORNECEDOR";

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

    public static void Salvar(Fornecedor fornecedor) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement insereSt = null;

        String sql = "insert into fornecedor (CODIGO, NOME, EMAIL, TELEFONE, CNPJ, ENDERECO) Values (?,?,?,?,?,?)";
        
        try {
            
            insereSt = conexao.prepareStatement(sql);
            insereSt.setInt(1, fornecedor.getCodigo());
            insereSt.setString(2, fornecedor.getNome());
            insereSt.setString(3, fornecedor.getEmail());
           insereSt.setString(4, fornecedor.getEndereco());
            insereSt.setString(5, fornecedor.getCNPJ());
            insereSt.setString(6, fornecedor.getTelefone());
            insereSt.executeUpdate();
            
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir Fornecedor " + e.getMessage());
        }finally{
             try {
               JOptionPane.showMessageDialog(null, "Fornecedor incluído com sucesso");
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
        
        String sql = "delete from fornecedor where codigo = " + iCod;
        
        try {
            excluiSt = conexao.prepareStatement(sql);
            excluiSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir fornecedor: " + e.getMessage());
        } finally {
            try {
                excluiSt.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função Excluir(): " + e.getMessage());
            }
        }
    }
    public static Fornecedor RecuperarFornecdeor(int iCod){
        Connection  conexao= FabricaConexao.getConnection();
        
        Fornecedor fornecedorrecuperado =new Fornecedor();
        
        Statement consulta = null;
        ResultSet resultado= null;
        
        String sql = "select * from fornecedor where codigo = " +iCod;
        
        try {
            consulta=conexao.createStatement();
            resultado=consulta.executeQuery(sql);
            
            resultado.next();
            
            if (resultado.getRow() == 1){
                fornecedorrecuperado.setCodigo(resultado.getInt("CODIGO"));
                fornecedorrecuperado.setNome(resultado.getString("NOME"));
                fornecedorrecuperado.setEmail(resultado.getString("EMAIL"));
                fornecedorrecuperado.setCNPJ(resultado.getString("CNPJ"));
                fornecedorrecuperado.setEndereco(resultado.getString("ENDERECO"));
                fornecedorrecuperado.setTelefone(resultado.getString("TELEFONE"));
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Recuperar Fornecedor" + e.getMessage());
        }finally{
             try {
                consulta.close();
                resultado.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Recuperar fornecedor():" + e.getMessage());
            }
            
        }
        return fornecedorrecuperado;
    }
    
     public static void Atualizar(Fornecedor fornecedor) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement atualizaSt = null;

        String sql = "update fornecedor set nome=?, email=?, endereco=?, CNPJ=?, telefone=? where codigo= ?";

        try {
            atualizaSt = conexao.prepareStatement(sql);
            atualizaSt.setString(1, fornecedor.getNome());
            atualizaSt.setString(2, fornecedor.getEmail());
            atualizaSt.setString(4, fornecedor.getEndereco());
            atualizaSt.setString(5, fornecedor.getCNPJ());
            atualizaSt.setString(3, fornecedor.getTelefone());
            atualizaSt.setInt(6, fornecedor.getCodigo());
            
            atualizaSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Fornecedor" + e.getMessage());
        } finally {
            try {
                atualizaSt.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Atualizar fornecedor():" + e.getMessage());
            }

        }
    }
     
     public static ArrayList<Fornecedor> RecuperaObjetos(String pCampo, String pValor){
        Connection conexao = FabricaConexao.getConnection();
        
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();
        Statement consulta = null;
        ResultSet resultado = null;
        
        String sql = "select * from fornecedor where " + pCampo + " like '%" + pValor + "%'";      
        
        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            
            while(resultado.next()){
                Fornecedor fornecedorTemp = new Fornecedor();
                fornecedorTemp.setCodigo(resultado.getInt("CODIGO"));
                fornecedorTemp.setNome(resultado.getString("NOME"));
                fornecedorTemp.setEndereco(resultado.getString("ENDERECO"));
                fornecedorTemp.setTelefone(resultado.getString("TELEFONE"));
                fornecedorTemp.setCNPJ(resultado.getString("CNPJ"));
                fornecedorTemp.setEmail(resultado.getString("EMAIL"));
                fornecedores.add(fornecedorTemp);
            }            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar fornecedor: " + e.getMessage() + "\n" + sql);
        } finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função RecuperaObjetos(): " + e.getMessage());
            }
        }
        
        return fornecedores;        
    }

}


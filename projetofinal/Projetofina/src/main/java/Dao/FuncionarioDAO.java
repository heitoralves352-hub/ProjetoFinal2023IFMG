/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Funcionario;
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
public class FuncionarioDAO {
    
    
    
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
                sql = "select min(CODIGO) as CODIGO from funcionario";
                break;
            case cNavAnterior:
                sql = "select max(codigo) as Codigo from funcionario where codigo < " + String.valueOf(iCodigoAtual);
                break;
            case cNavProximo:
                sql = "select min(codigo) as Codigo from funcionario where codigo > " + String.valueOf(iCodigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(CODIGO) as CODIGO from funcionario";
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

        Statement consulta = null ;
        ResultSet resultado = null;
        int codigo = -1;

        String sql = "select max(codigo) as CODIGO from funcionario";

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

    public static void Salvar(Funcionario Funcionario) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement insereSt = null;

        String sql = "insert into funcionario (CODIGO, NOME, EMAIL, CPF) Values (?,?,?,?)";
        
        try {
            
            insereSt = conexao.prepareStatement(sql);
            insereSt.setInt(1,Funcionario.getCodigo());
            insereSt.setString(2, Funcionario.getNome());
            insereSt.setString(3, Funcionario.getEmail());
            insereSt.setString(4, Funcionario.getCPF());
            insereSt.executeUpdate();
            
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao incluir Funcionario" + e.getMessage());
        }finally{
             try {
               JOptionPane.showMessageDialog(null, "Funcionario incluído com sucesso");
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
        
        String sql = "delete from funcionario where codigo = " + iCod;
        
        try {
            excluiSt = conexao.prepareStatement(sql);
            excluiSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario: " + e.getMessage());
        } finally {
            try {
                excluiSt.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função Excluir(): " + e.getMessage());
            }
        }
    }

    
     public static Funcionario RecuperarFuncionario(int iCod){
        Connection  conexao= FabricaConexao.getConnection();
        
        Funcionario funcionariorecuperado =new Funcionario();
        
        Statement consulta = null;
        ResultSet resultado= null;
        
        String sql = "select * from funcionario where codigo = " +iCod;
        
        try {
            consulta=conexao.createStatement();
            resultado=consulta.executeQuery(sql);
            
            resultado.next();
            
            if (resultado.getRow() == 1){
                funcionariorecuperado.setCodigo(resultado.getInt("CODIGO"));
                funcionariorecuperado.setNome(resultado.getString("NOME"));
                funcionariorecuperado.setEmail(resultado.getString("EMAIL"));
                funcionariorecuperado.setCPF(resultado.getString("CPF"));
                
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Recuperar Funcionario" + e.getMessage());
        }finally{
             try {
                consulta.close();
                resultado.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Recuperar funcionario():" + e.getMessage());
            }
            
        }
        return funcionariorecuperado;
    }
     
      public static void Atualizar(Funcionario funcionario) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement atualizaSt = null;

        String sql = "update funcionario set nome=?, email=?, cpf=? where codigo= ?";

        try {
            atualizaSt = conexao.prepareStatement(sql);
            atualizaSt.setString(1, funcionario.getNome());
            atualizaSt.setString(2, funcionario.getEmail());
            atualizaSt.setString(3, funcionario.getCPF());
            atualizaSt.setInt(4, funcionario.getCodigo());
            
            atualizaSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Funcionario" + e.getMessage());
        } finally {
            try {
                atualizaSt.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Atualizar funcionario():" + e.getMessage());
            }

        }
    }
      
     public static ArrayList<Funcionario> RecuperaObjetos(String pCampo, String pValor){
        Connection conexao = FabricaConexao.getConnection();
        
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        Statement consulta = null;
        ResultSet resultado = null;
        
        String sql = "select * from funcionario where " + pCampo + " like '%" + pValor + "%'";      
        
        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            
            while(resultado.next()){
                Funcionario FuncionarioTemp = new Funcionario();
                FuncionarioTemp.setCodigo(resultado.getInt("CODIGO"));
                FuncionarioTemp.setNome(resultado.getString("NOME"));
                FuncionarioTemp.setEmail(resultado.getString("EMAIL"));
                FuncionarioTemp.setCPF(resultado.getString("CPF"));
                funcionarios.add(FuncionarioTemp);
            }            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao recuperar funcionario: " + e.getMessage() + "\n" + sql);
        } finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função RecuperaObjetos(): " + e.getMessage());
            }
        }
        
        return funcionarios;        
    }

}
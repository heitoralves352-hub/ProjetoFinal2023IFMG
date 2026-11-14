/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Modelo.Cliente;
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
public class ClienteDAO {

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
                sql = "select min(CODIGO) as CODIGO from cliente";
                break;
            case cNavAnterior:
                sql = "select max(codigo) as Codigo from cliente where codigo < " + String.valueOf(iCodigoAtual);
                break;
            case cNavProximo:
                sql = "select min(codigo) as Codigo from cliente where codigo > " + String.valueOf(iCodigoAtual);
                break;
            case cNavUltimo:
                sql = "select max(CODIGO) as CODIGO from cliente";
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

        String sql = "select max(codigo) as CODIGO from CLIENTE";

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

    public static void Salvar(Cliente cliente) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement insereSt = null;

        String sql = "insert into cliente (CODIGO, NOME, EMAIL, TELEFONE) Values (?,?,?,?)";

        try {

            insereSt = conexao.prepareStatement(sql);
            insereSt.setInt(1, cliente.getCodigo());
            insereSt.setString(2, cliente.getNome());
            insereSt.setString(3, cliente.getEmail());
            insereSt.setString(4, cliente.getTelefone());
            insereSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir Cliente" + e.getMessage());
        } finally {
            try {
                JOptionPane.showMessageDialog(null, "Cliente incluído com sucesso");
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
        
        String sql = "delete from cliente where codigo = " + iCod;
        
        try {
            excluiSt = conexao.prepareStatement(sql);
            excluiSt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir clinete: " + e.getMessage());
        } finally {
            try {
                excluiSt.close();
                conexao.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar conexão na função Excluir(): " + e.getMessage());
            }
        }
    }

    public static Cliente RecuperarCliente(int iCod) {
        Connection conexao = FabricaConexao.getConnection();

        Cliente clienterecuperado = new Cliente();

        Statement consulta = null;
        ResultSet resultado = null;

        String sql = "SELECT * FROM cliente WHERE codigo = " + iCod;

        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);

            resultado.next();

            if (resultado.getRow() == 1) {
                clienterecuperado.setCodigo(resultado.getInt("codigo"));
                clienterecuperado.setNome(resultado.getString("nome"));
                clienterecuperado.setEmail(resultado.getString("email"));
                clienterecuperado.setTelefone(resultado.getString("telefone"));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Recuperar Cliente" + e.getMessage());
        } finally {
            try {
                consulta.close();
                resultado.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Recuperar cidade():" + e.getMessage());
            }

        }
        return clienterecuperado;
    }
    
    public static void Atualizar(Cliente cliente) {
        Connection conexao = FabricaConexao.getConnection();

        PreparedStatement atualizaSt = null;

        String sql = "update cliente set nome=?, email=?, telefone=? where codigo= ?";

        try {
            atualizaSt = conexao.prepareStatement(sql);
            atualizaSt.setString(1, cliente.getNome());
            atualizaSt.setString(2, cliente.getEmail());
            atualizaSt.setString(3, cliente.getTelefone());
            atualizaSt.setInt(4, cliente.getCodigo());
            
            atualizaSt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar Cliente" + e.getMessage());
        } finally {
            try {
                atualizaSt.close();
                conexao.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao encerrar a conexao na função Atualizar cliente():" + e.getMessage());
            }

        }
    }
    
    public static ArrayList<Cliente> RecuperaObjetos(String pCampo, String pValor){
        Connection conexao = FabricaConexao.getConnection();
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        Statement consulta = null;
        ResultSet resultado = null;
        
        String sql = "select * from cliente where " + pCampo + " like '%" + pValor + "%'";      
        
        try {
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            
            while(resultado.next()){
                Cliente clienteTemp = new Cliente();
                clienteTemp.setCodigo(resultado.getInt("CODIGO"));
                clienteTemp.setNome(resultado.getString("NOME"));
                clienteTemp.setEmail(resultado.getString("EMAIL"));
                clienteTemp.setTelefone(resultado.getString("TELEFONE"));
                clientes.add(clienteTemp);
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
        
        return clientes;        
    }
}

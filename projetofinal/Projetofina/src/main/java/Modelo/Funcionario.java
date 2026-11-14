/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


import Dao.FuncionarioDAO;
import java.util.ArrayList;

/**
 *
 * @author Willian
 */
public class  Funcionario{
    private int codigo;
    private String nome;
    private String email;
    private String CPF;
    
    public Funcionario(){
        this.codigo=-1;
        this.nome="";
        this.email="";
        this.CPF="";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public void setProximoCodigo(){
        this.codigo = FuncionarioDAO.ProximoCodigo();
    }
    
    public void Salvar(){
        FuncionarioDAO.Salvar(this);
    }
    public void Excluir(int chave){
        FuncionarioDAO.Excluir(chave);
    }
    public void Atualizar(){
        FuncionarioDAO.Atualizar(this);
    }
    public void RecuperaObjeto(int codigo){
        Funcionario FuncionarioTemp = FuncionarioDAO.RecuperarFuncionario(codigo);
        this.setCodigo(FuncionarioTemp.getCodigo());
        this.setNome(FuncionarioTemp.getNome());
        this.setEmail(FuncionarioTemp.getEmail());
        this.setCPF(FuncionarioTemp.getCPF());
        
    }
     public void RecuperObjetoNavegacao(int opcao, int CodAtual){
        int codigoNav = FuncionarioDAO.PegaCodigoPelaNavegacao(opcao, CodAtual);
        RecuperaObjeto(codigoNav);
    }
     
    public ArrayList<Funcionario> RecuperaObjetos(String pCampo, String pValor) {

        String NomeCampo = "";

        if (pCampo.equalsIgnoreCase("Código")) {
            NomeCampo = "CODIGO";
        } else if (pCampo.equalsIgnoreCase("Nome")) {
            NomeCampo = "NOME";
        } else if (pCampo.equalsIgnoreCase("Email")) {
            NomeCampo = "EMAIL";
        } else if (pCampo.equalsIgnoreCase("Cpf")) {
            NomeCampo = "TELEFONE";
        }

        return FuncionarioDAO.RecuperaObjetos(NomeCampo, pValor);
    }
}
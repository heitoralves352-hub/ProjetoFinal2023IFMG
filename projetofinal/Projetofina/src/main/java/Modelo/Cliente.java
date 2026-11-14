/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Dao.ClienteDAO;
import java.util.ArrayList;

/**
 *
 * @author Willian
 */
public class Cliente {
    private int codigo;
    private String nome;
    private String email;
    private String telefone;
    
    public Cliente(){
        this.codigo=-1;
        this.nome="";
        this.email="";
        this.telefone="";
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setProximoCodigo(){
        this.codigo = ClienteDAO.ProximoCodigo();
    }
    
    public void Salvar(){
        ClienteDAO.Salvar(this);
    }
    public void Excluir(int chave){
        ClienteDAO.Excluir(chave);
    }
    public void Atualizar(){
        ClienteDAO.Atualizar(this);
    }
    public void RecuperaObjeto(int codigo){
        Cliente ClienteTemp = ClienteDAO.RecuperarCliente(codigo);
        this.setCodigo(ClienteTemp.getCodigo());
        this.setNome(ClienteTemp.getNome());
        this.setEmail(ClienteTemp.getEmail());
        this.setTelefone(ClienteTemp.getTelefone());
        
        
    }
    public void RecuperObjetoNavegacao(int opcao, int CodAtual){
        int codigoNav = ClienteDAO.PegaCodigoPelaNavegacao(opcao, CodAtual);
        RecuperaObjeto(codigoNav);
    }
    
    public ArrayList<Cliente> RecuperaObjetos(String pCampo, String pValor) {

        String NomeCampo = "";

        if (pCampo.equalsIgnoreCase("Código")) {
            NomeCampo = "CODIGO";
        } else if (pCampo.equalsIgnoreCase("Nome")) {
            NomeCampo = "NOME";
        } else if (pCampo.equalsIgnoreCase("Email")) {
            NomeCampo = "EMAIL";
        } else if (pCampo.equalsIgnoreCase("Telefone")) {
            NomeCampo = "TELEFONE";
        }

        return ClienteDAO.RecuperaObjetos(NomeCampo, pValor);
    }
    
}

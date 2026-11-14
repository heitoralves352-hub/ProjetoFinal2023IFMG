/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Dao.CategoriaDAO;
import java.util.ArrayList;

/**
 *
 * @author Willian
 */
public class Categoria {
    private int codigo;
    private String nome;
    private String descricao;
   
    
    public Categoria(){
        this.codigo=-1;
        this.nome="";
        this.descricao="";
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   
    public void setProximoCodigo(){
        this.codigo = CategoriaDAO.ProximoCodigo();
    }
    
    public void Salvar(){
        CategoriaDAO.Salvar(this);
    }
    public void Excluir(int chave){
        CategoriaDAO.Excluir(chave);
    }
    public void Atualizar(){
        CategoriaDAO.Atualizar(this);
    }
    public void RecuperaObjeto(int codigo){
        Categoria CategoriaTemp = CategoriaDAO.RecuperarCategoria(codigo);
        this.setCodigo(CategoriaTemp.getCodigo());
        this.setNome(CategoriaTemp.getNome());
        this.setDescricao(CategoriaTemp.getDescricao());
        
        
    }
    public void RecuperObjetoNavegacao(int opcao, int CodAtual){
        int codigoNav = CategoriaDAO.PegaCodigoPelaNavegacao(opcao, CodAtual);
        RecuperaObjeto(codigoNav);
    }
    
     public ArrayList<Categoria> RecuperaObjetos(String pCampo, String pValor) {

        String NomeCampo = "";

        if (pCampo.equalsIgnoreCase("Código")) {
            NomeCampo = "CODIGO";
        } else if (pCampo.equalsIgnoreCase("Nome")) {
            NomeCampo = "NOME";
        } else if (pCampo.equalsIgnoreCase("Descricao")) {
            NomeCampo = "DESCRICAO";
        } 

        return CategoriaDAO.RecuperaObjetos(NomeCampo, pValor);
    }
    
}


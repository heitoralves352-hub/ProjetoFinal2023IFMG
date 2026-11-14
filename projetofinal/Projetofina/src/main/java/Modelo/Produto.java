/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Dao.ProdutoDAO;
import java.util.ArrayList;

/**
 *
 * @author Willian
 */
public class Produto {
    private int codigo;
    private String nome;
    private String categoria;
    private String estoque;
    private String preco;
    private int codCategoria;
    
    public Produto(){
        this.codigo=-1;
        this.nome="";
        this.categoria="";
        this.estoque="";
        this.preco="";
        this.codCategoria=0;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

  

    
    public void setProximoCodigo(){
        this.codigo = ProdutoDAO.ProximoCodigo();
    }
    
    public void Salvar(){
        ProdutoDAO.Salvar(this);
    }
    public void Excluir(int chave){
        ProdutoDAO.Excluir(chave);
    }
    public void Atualizar(){
        ProdutoDAO.Atualizar(this);
    }
    

    public void RecuperaObjeto(int codigo) {
        Produto ProdutoTemp = ProdutoDAO.RecuperarProduto(codigo);
        this.setCodigo(ProdutoTemp.getCodigo());
        this.setNome(ProdutoTemp.getNome());
        this.setCategoria(ProdutoTemp.getCategoria());
        this.setEstoque(ProdutoTemp.getEstoque());
        this.setPreco(ProdutoTemp.getPreco());
        this.setCodCategoria(ProdutoTemp.getCodCategoria());

    }
    public void RecuperObjetoNavegacao(int opcao, int CodAtual){
        int codigoNav = ProdutoDAO.PegaCodigoPelaNavegacao(opcao, CodAtual);
        RecuperaObjeto(codigoNav);
    }
    public ArrayList<Produto> RecuperaObjetos(String pCampo, String pValor) {

        String NomeCampo = "";

        if (pCampo.equalsIgnoreCase("Código")) {
            NomeCampo = "produto.CODIGO";
        } else if (pCampo.equalsIgnoreCase("Nome")) {
            NomeCampo = "produto.NOME";
        } else if (pCampo.equalsIgnoreCase("Categoria")) {
            NomeCampo = "produto.CATEGORIA";
        } else if (pCampo.equalsIgnoreCase("Preco")) {
            NomeCampo = "produto.PRECO";
        }else if (pCampo.equalsIgnoreCase("Estoque")) {
            NomeCampo = "produto.ESTOQUE";
        }else if (pCampo.equalsIgnoreCase("Nome da Categoria")) {
            NomeCampo = "categoria.NOME";
        }

        return ProdutoDAO.RecuperaObjetos(NomeCampo, pValor);
    }   
}
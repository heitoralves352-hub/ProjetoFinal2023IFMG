/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Dao.FornecedorDAO;
import java.util.ArrayList;

/**
 *
 * @author Willian
 */
public class Fornecedor {

    private int codigo;
    private String nome;
    private String endereco;
    private String telefone;
    private String CNPJ;
    private String email;

    public Fornecedor() {
        this.codigo = -1;
        this.nome = "";
        this.email = "";
        this.telefone = "";
        this.CNPJ = "";
        this.endereco = "";
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public void setProximoCodigo() {
        this.codigo = FornecedorDAO.ProximoCodigo();
    }

    public void Salvar() {
        FornecedorDAO.Salvar(this);
    }

    public void Excluir(int chave) {
        FornecedorDAO.Excluir(chave);
    }

    public void Atualizar() {
        FornecedorDAO.Atualizar(this);
    }

    public void RecuperaObjeto(int codigo) {
        Fornecedor FornecedorTemp = FornecedorDAO.RecuperarFornecdeor(codigo);
        this.setCodigo(FornecedorTemp.getCodigo());
        this.setNome(FornecedorTemp.getNome());
        this.setEmail(FornecedorTemp.getEmail());
        this.setCNPJ(FornecedorTemp.getCNPJ());
        this.setEndereco(FornecedorTemp.getEndereco());
        this.setTelefone(FornecedorTemp.getTelefone());
    }

    public void RecuperObjetoNavegacao(int opcao, int CodAtual) {
        int codigoNav = FornecedorDAO.PegaCodigoPelaNavegacao(opcao, CodAtual);
        RecuperaObjeto(codigoNav);
    }

    public ArrayList<Fornecedor> RecuperaObjetos(String pCampo, String pValor) {

        String NomeCampo = "";

        if (pCampo.equalsIgnoreCase("Código")) {
            NomeCampo = "CODIGO";
        } else if (pCampo.equalsIgnoreCase("Nome")) {
            NomeCampo = "NOME";
        } else if (pCampo.equalsIgnoreCase("Endereco")) {
            NomeCampo = "ENDERECO";
        } else if (pCampo.equalsIgnoreCase("Telefone")) {
            NomeCampo = "TELEFONE";
        } else if (pCampo.equalsIgnoreCase("Cnpj")) {
            NomeCampo = "CNPJ";
        } else if (pCampo.equalsIgnoreCase("Email")) {
            NomeCampo = "EMAIL";
        }

        return FornecedorDAO.RecuperaObjetos(NomeCampo, pValor);
    }

}

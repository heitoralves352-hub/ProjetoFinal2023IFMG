/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Modelo.Fornecedor;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willian
 */
public class ctrlFornecedor {
    
    private final Fornecedor objFornecedor;
    
    public ctrlFornecedor(){
        this.objFornecedor = new Fornecedor();
    }
    public int Salvar(ArrayList<String> pLista){
        this.objFornecedor.setNome(pLista.get(1));
        this.objFornecedor.setEmail(pLista.get(2));
        this.objFornecedor.setTelefone(pLista.get(3));
        this.objFornecedor.setCNPJ(pLista.get(4));
        this.objFornecedor.setEndereco(pLista.get(5));
        this.objFornecedor.setProximoCodigo();
        this.objFornecedor.Salvar();
        return this.objFornecedor.getCodigo();
    }
    
    public void Excluir(int Chave){
        this.objFornecedor.setCodigo(Chave);
        this.objFornecedor.Excluir(Chave);
    }
    public void Atualizar(ArrayList<String> pLista){
        this.objFornecedor.setCodigo(Integer.valueOf(pLista.get(0)));
        this.objFornecedor.setNome(pLista.get(1));
        this.objFornecedor.setEmail(pLista.get(2));
        this.objFornecedor.setTelefone(pLista.get(3));
        this.objFornecedor.setEndereco(pLista.get(4));
        this.objFornecedor.setCNPJ(pLista.get(5));
        this.objFornecedor.Atualizar();
    }
    public ArrayList<String> ConverterObjetoParaArray(){
        ArrayList<String> vetCampos = new ArrayList<>();
        vetCampos.add(String.valueOf(this.objFornecedor.getCodigo()));
        vetCampos.add(this.objFornecedor.getNome());
        vetCampos.add(this.objFornecedor.getEmail());
        vetCampos.add(this.objFornecedor.getCNPJ());
        vetCampos.add(this.objFornecedor.getEndereco());
        vetCampos.add(this.objFornecedor.getTelefone());
        
        return vetCampos;
    }
    
    public ArrayList<String> RecuperarObjeto(int codigo){
        this.objFornecedor.RecuperaObjeto(codigo);
        return ConverterObjetoParaArray();
    }
    public ArrayList<String> RecuperaObjetoNavegacao(int opcao, int Codigo){
       this.objFornecedor.RecuperObjetoNavegacao(opcao, Codigo);
       return ConverterObjetoParaArray();
    }
    
     public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel ModeloTabela) {
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);

        ArrayList<Fornecedor> Fornecedores = this.objFornecedor.RecuperaObjetos(Campo, Valor);

        Vector<String> vetVetor;
        Fornecedor objFornecedorBuffer;

        for (int i = 0; i < Fornecedores.size(); i++) {
            vetVetor = new Vector<>();
            objFornecedorBuffer = Fornecedores.get(i);

            vetVetor.addElement(String.valueOf(objFornecedorBuffer.getCodigo()));
            vetVetor.addElement(objFornecedorBuffer.getNome());
            vetVetor.addElement(objFornecedorBuffer.getEndereco());
            vetVetor.addElement(objFornecedorBuffer.getTelefone());
            vetVetor.addElement(objFornecedorBuffer.getCNPJ());
            vetVetor.addElement(objFornecedorBuffer.getEmail());
            ModeloTabela.addRow(vetVetor);
        }

        return ModeloTabela;
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Modelo.Categoria;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willian
 */
public class ctrlCategoria {
    
    private final Categoria objCategoria;
    
    public ctrlCategoria(){
        this.objCategoria = new Categoria();
    }
    public int Salvar(ArrayList<String> pLista){
        this.objCategoria.setNome(pLista.get(1));
        this.objCategoria.setDescricao(pLista.get(2));
        this.objCategoria.setProximoCodigo();
        this.objCategoria.Salvar();
        return this.objCategoria.getCodigo();
    }
    
    public void Excluir(int Chave){
        this.objCategoria.setCodigo(Chave);
        this.objCategoria.Excluir(Chave);
    }
    public void Atualizar(ArrayList<String> pLista){
        this.objCategoria.setCodigo(Integer.valueOf(pLista.get(0)));
        this.objCategoria.setNome(pLista.get(1));
        this.objCategoria.setDescricao(pLista.get(2));
        
        
        this.objCategoria.Atualizar();
    }
     public ArrayList<String> ConverterObjetoParaArray(){
        ArrayList<String> vetCampos = new ArrayList<>();
        vetCampos.add(String.valueOf(this.objCategoria.getCodigo()));
        vetCampos.add(this.objCategoria.getNome());
        vetCampos.add(this.objCategoria.getDescricao());
        
        
        return vetCampos;
    }
    
    public ArrayList<String> RecuperaObjeto(int codigo){
        this.objCategoria.RecuperaObjeto(codigo);
        return ConverterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int opcao, int Codigo){
       this.objCategoria.RecuperObjetoNavegacao(opcao, Codigo);
       return ConverterObjetoParaArray();
    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel ModeloTabela) {
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);

        ArrayList<Categoria> Categorias = this.objCategoria.RecuperaObjetos(Campo, Valor);

        Vector<String> vetVetor;
        Categoria objCategoriaBuffer;

        for (int i = 0; i < Categorias.size(); i++) {
            vetVetor = new Vector<>();
            objCategoriaBuffer = Categorias.get(i);

            vetVetor.addElement(String.valueOf(objCategoriaBuffer.getCodigo()));
            vetVetor.addElement(objCategoriaBuffer.getNome());
            vetVetor.addElement(objCategoriaBuffer.getDescricao());
            ModeloTabela.addRow(vetVetor);
        }

        return ModeloTabela;
    }
}


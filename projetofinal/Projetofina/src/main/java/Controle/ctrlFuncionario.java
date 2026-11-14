/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Modelo.Funcionario;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willian
 */
public class ctrlFuncionario {
    
    private final Funcionario objFuncionario;
    
    public ctrlFuncionario(){
        this.objFuncionario = new Funcionario();
    }
    public int Salvar(ArrayList<String> pLista){
        this.objFuncionario.setNome(pLista.get(1));
        this.objFuncionario.setEmail(pLista.get(2));
        this.objFuncionario.setCPF(pLista.get(3));
        this.objFuncionario.setProximoCodigo();
        this.objFuncionario.Salvar();
        return this.objFuncionario.getCodigo();
    }
    
    public void Excluir(int Chave){
      this.objFuncionario.setCodigo(Chave);
      this.objFuncionario.Excluir(Chave);  
    }
    public void Atualizar(ArrayList<String> pLista){
        this.objFuncionario.setCodigo(Integer.valueOf(pLista.get(0)));
        this.objFuncionario.setNome(pLista.get(1));
        this.objFuncionario.setEmail(pLista.get(2));
        this.objFuncionario.setCPF(pLista.get(3));
        
        this.objFuncionario.Atualizar();
    }
    public ArrayList<String> ConverterObjetoParaArray(){
        ArrayList<String> vetCampos = new ArrayList<>();
        vetCampos.add(String.valueOf(this.objFuncionario.getCodigo()));
        vetCampos.add(this.objFuncionario.getNome());
        vetCampos.add(this.objFuncionario.getEmail());
        vetCampos.add(this.objFuncionario.getCPF());
        
        return vetCampos;
    }
    
    public ArrayList<String> RecuperarObjeto(int codigo){
        this.objFuncionario.RecuperaObjeto(codigo);
        return ConverterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int opcao, int Codigo){
       this.objFuncionario.RecuperObjetoNavegacao(opcao, Codigo);
       return ConverterObjetoParaArray();
    }
    
     public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel ModeloTabela) {
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);

        ArrayList<Funcionario> Funcionarios = this.objFuncionario.RecuperaObjetos(Campo, Valor);

        Vector<String> vetVetor;
        Funcionario objFuncionarioBuffer;

        for (int i = 0; i < Funcionarios.size(); i++) {
            vetVetor = new Vector<>();
            objFuncionarioBuffer = Funcionarios.get(i);

            vetVetor.addElement(String.valueOf(objFuncionarioBuffer.getCodigo()));
            vetVetor.addElement(objFuncionarioBuffer.getNome());
            vetVetor.addElement(objFuncionarioBuffer.getEmail());
            vetVetor.addElement(objFuncionarioBuffer.getCPF());
            ModeloTabela.addRow(vetVetor);
        }

        return ModeloTabela;
    }
    
}

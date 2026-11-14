/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Dao.ClienteDAO;
import Modelo.Cliente;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willian
 */
public class ctrlCliente {
    
    private final Cliente objCliente;
    
    public ctrlCliente(){
        this.objCliente = new Cliente();
    }
    public int Salvar(ArrayList<String> pLista){
        this.objCliente.setNome(pLista.get(1));
        this.objCliente.setEmail(pLista.get(2));
        this.objCliente.setTelefone(pLista.get(3));
        this.objCliente.setProximoCodigo();
        this.objCliente.Salvar();
        return this.objCliente.getCodigo();
    }
    
    public void Excluir(int Chave){
        this.objCliente.setCodigo(Chave);
        this.objCliente.Excluir(Chave);
    }
    public void Atualizar(ArrayList<String> pLista){
        this.objCliente.setCodigo(Integer.valueOf(pLista.get(0)));
        this.objCliente.setNome(pLista.get(1));
        this.objCliente.setEmail(pLista.get(2));
        this.objCliente.setTelefone(pLista.get(3));
        
        this.objCliente.Atualizar();
    }
    
    public ArrayList<String> ConverterObjetoParaArray(){
        ArrayList<String> vetCampos = new ArrayList<>();
        vetCampos.add(String.valueOf(this.objCliente.getCodigo()));
        vetCampos.add(this.objCliente.getNome());
        vetCampos.add(this.objCliente.getEmail());
        vetCampos.add(this.objCliente.getTelefone());
        
        return vetCampos;
    }
    
    public ArrayList<String> RecuperaObjeto(int codigo){
        this.objCliente.RecuperaObjeto(codigo);
        return ConverterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int opcao, int Codigo){
       this.objCliente.RecuperObjetoNavegacao(opcao, Codigo);
       return ConverterObjetoParaArray();
    }
    
    public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel ModeloTabela) {
        String Campo = Parametros.get(0);
        String Valor = Parametros.get(1);

        ArrayList<Cliente> Clientes = this.objCliente.RecuperaObjetos(Campo, Valor);

        Vector<String> vetVetor;
        Cliente objClienteBuffer;

        for (int i = 0; i < Clientes.size(); i++) {
            vetVetor = new Vector<>();
            objClienteBuffer = Clientes.get(i);

            vetVetor.addElement(String.valueOf(objClienteBuffer.getCodigo()));
            vetVetor.addElement(objClienteBuffer.getNome());
            vetVetor.addElement(objClienteBuffer.getEmail());
            vetVetor.addElement(objClienteBuffer.getTelefone());
            ModeloTabela.addRow(vetVetor);
        }

        return ModeloTabela;
    }
    
    
}

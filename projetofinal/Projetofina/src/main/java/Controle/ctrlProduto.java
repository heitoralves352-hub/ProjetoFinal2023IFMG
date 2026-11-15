/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

/**
 *
 * @author Willian
 */
import Modelo.Produto;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Willian
 */
public class ctrlProduto {
    
    private final Produto objProduto;
    
    public ctrlProduto(){
        this.objProduto = new Produto();
    }
    public int Salvar(ArrayList<String> pLista){
        this.objProduto.setNome(pLista.get(1));
        this.objProduto.setEstoque(pLista.get(2));
        this.objProduto.setPreco(pLista.get(3));
        this.objProduto.setCodCategoria(Integer.parseInt(pLista.get(4)));
        this.objProduto.setProximoCodigo();
        this.objProduto.Salvar(); 
        return this.objProduto.getCodigo();
    }
    
    public void Excluir(int Chave){
         this.objProduto.setCodigo(Chave);
        this.objProduto.Excluir(Chave);
    }
    public void Atualizar(ArrayList<String> pLista){
        this.objProduto.setCodigo(Integer.valueOf(pLista.get(0)));
        this.objProduto.setNome(pLista.get(1));
        this.objProduto.setEstoque(pLista.get(2));
        this.objProduto.setPreco(pLista.get(3));
        this.objProduto.setCodCategoria(Integer.parseInt(pLista.get(4)));
        
        this.objProduto.Atualizar();
    }
    
    public ArrayList<String> ConverterObjetoParaArray(){
        ArrayList<String> vetCampos = new ArrayList<>();
        vetCampos.add(String.valueOf(this.objProduto.getCodigo()));
        vetCampos.add(this.objProduto.getNome());
        vetCampos.add(this.objProduto.getCategoria());
        vetCampos.add(this.objProduto.getEstoque());
        vetCampos.add(this.objProduto.getPreco());
        vetCampos.add(String.valueOf(this.objProduto.getCodCategoria()));
        
        return vetCampos;
    }
    
    public ArrayList<String> RecuperaObjeto(int codigo){
        this.objProduto.RecuperaObjeto(codigo);
        return ConverterObjetoParaArray();
    }
    
    public ArrayList<String> RecuperaObjetoNavegacao(int opcao, int Codigo){
       this.objProduto.RecuperObjetoNavegacao(opcao, Codigo);
       return ConverterObjetoParaArray();
    }
     public DefaultTableModel PesquisaObjeto(ArrayList<String> Parametros, DefaultTableModel ModeloTabela) {
        String Campo = Parametros.get(0);
        switch (Campo) {
            case "Nome":              Campo = "produto.nome";         break;
            case "Codigo":            Campo = "produto.codigo";       break;
            case "Categoria":         Campo = "produto.codcategoria"; break;
            case "Preço":             Campo = "produto.preco";        break;
            case "Nome da Categoria": Campo = "categoria.nome";       break;
        }
        String Valor = Parametros.get(1);

        ArrayList<Produto> Clientes = this.objProduto.RecuperaObjetos(Campo, Valor);

        Vector<String> vetVetor;
        Produto objProdutoBuffer;

        for (int i = 0; i < Clientes.size(); i++) {
            vetVetor = new Vector<>();
            objProdutoBuffer = Clientes.get(i);

            vetVetor.addElement(String.valueOf(objProdutoBuffer .getCodigo()));
            vetVetor.addElement(objProdutoBuffer .getNome());
            vetVetor.addElement(objProdutoBuffer .getCategoria());
            vetVetor.addElement(objProdutoBuffer .getPreco());
            vetVetor.addElement(objProdutoBuffer .getEstoque());
            ModeloTabela.addRow(vetVetor);
        }

        return ModeloTabela;
    }
}



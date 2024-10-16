package modelo.entidade.estoque;

import java.util.HashSet;
import java.util.Set;

import modelo.entidade.Item.Item;
import modelo.entidade.produto.Produto;

public class Estoque {
	
    private Set<Item> produtosEstoque;

    public Estoque() {
        this.produtosEstoque = new HashSet<>();
    }

    public void adicionarProdutoEstoque(Produto produto, int quantidade) {
    	
        Item itemEstoque = buscarProdutoEstoque(produto.getCodigo());
        
        if (itemEstoque == null) {
            this.produtosEstoque.add(new Item(produto, quantidade));
            System.out.println("Produto " + produto.getNome() + " Cadastrado!");
        } else {
            int quantidadeItem = itemEstoque.getQuantidade() + quantidade;
            itemEstoque.setQuantidade(quantidadeItem);
        }
    }

    public void removerProdutoEstoque(String codigo, int quantidade) {
    	
        Item itemEstoque = buscarProdutoEstoque(codigo);
        
        if (itemEstoque != null) {
            int quantidadeItem = itemEstoque.getQuantidade() - quantidade;
            if (quantidadeItem < 0) {
                System.out.println("Temos apenas: " + itemEstoque.getQuantidade() + " unidades no estoque!");
            } else {
                if (quantidadeItem == 0) {
                    produtosEstoque.remove(itemEstoque);
                } else {
                    itemEstoque.setQuantidade(quantidadeItem);
                }
            }
        } else {
        	throw new NullPointerException("Não temos no estoque!");
        }
    }

    public Item buscarProdutoEstoque(String codigo) {
    	
        for (Item itemEstoque : produtosEstoque) {
            if (itemEstoque.getProduto().getCodigo().equals(codigo)) {
                return itemEstoque;
            }
        }
        return null;
    }
    
    public void mostrarEstoque() {
    	
        System.out.println("Estoque:");
        for (Item itemEstoque : produtosEstoque) {
            System.out.println(itemEstoque.getProduto().getNome() + " qtd: " + itemEstoque.getQuantidade() + " valor: " + itemEstoque.getProduto().getPreco() + " R$.");
        }
    }
}
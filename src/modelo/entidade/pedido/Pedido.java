package modelo.entidade.pedido;

import java.util.HashSet;
import java.util.Set;

import modelo.entidade.Item.Item;
import modelo.entidade.cliente.Cliente;
import modelo.entidade.estoque.Estoque;
import modelo.entidade.produto.Produto;

public class Pedido {
    private String id;
    private Cliente cliente;
    private double precoPedido;
    private Set<Item> produtosPedido;

    public Pedido(String id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.precoPedido = 0;
        this.produtosPedido = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getPrecoPedido() {
        return precoPedido;
    }

    public Set<Item> getProdutosPedido() {
        return produtosPedido;
    }

    public void adicionarProdutoPedido(Produto produto, int quantidade, Estoque estoque) {
    	
        Item item = estoque.buscarProdutoEstoque(produto.getCodigo());
        
        if (item != null) {
        	if(item.getQuantidade() >= quantidade) {
        		Item itemPedido = new Item(item.getProduto(), quantidade);
           		produtosPedido.add(itemPedido);
           		this.precoPedido += itemPedido.calcularPreco();
           		estoque.removerProdutoEstoque(produto.getCodigo(), quantidade);
        	}
        } else {
        	throw new NullPointerException("Quantidade insuficiente no estoque.");
        }
    }

    public void removerProdutoPedido(Produto produto, int quantidade, Estoque estoque) {
    	
        Item itemPedido = buscarProdutoPedido(produto);
        
        if (itemPedido != null) {
            if (itemPedido.getQuantidade() >= quantidade) {
                int novaQuantidade = itemPedido.getQuantidade() - quantidade;
                if (novaQuantidade == 0) {
                    produtosPedido.remove(itemPedido);
                } else {
                    itemPedido.setQuantidade(novaQuantidade);
                }
                this.precoPedido -= produto.getPreco() * quantidade;
                estoque.adicionarProdutoEstoque(produto, quantidade);
            } else {
                System.out.println("Diminua a quantidade!");
            }
        } else {
        	throw new NullPointerException("Produto não está no pedido!");
        }
    }

    public Item buscarProdutoPedido(Produto produto) {
    	
        for (Item itemPedido : produtosPedido) {
            if (itemPedido.getProduto().getCodigo().equals(produto.getCodigo())) {
                return itemPedido;
            }
        }
        return null;
    }
    
    public void mostrarPedido() {
    	
        System.out.println("Pedido de: " + this.cliente.getNome() + " " + this.cliente.getSobrenome());
        
        for (Item itemPedido : produtosPedido) {
            System.out.println(itemPedido.getProduto().getNome() + " qtd: " + itemPedido.getQuantidade() + " valor: " + itemPedido.getProduto().getPreco() + " R$ total: " + itemPedido.calcularPreco() + " R$.");
        }
        System.out.println("Total do pedido: " + this.getPrecoPedido() + " R$.");
    }
}
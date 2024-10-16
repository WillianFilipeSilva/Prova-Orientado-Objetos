package modelo.entidade.sistema;

import java.util.HashSet;
import java.util.Set;

import modelo.entidade.Item.Item;
import modelo.entidade.cliente.Cliente;
import modelo.entidade.estoque.Estoque;
import modelo.entidade.pedido.Pedido;
import modelo.entidade.produto.Produto;

public class Sistema {

    private Estoque estoque;
    
    private Set<Cliente> clientesCadastrados;
    private Set<Pedido> pedidosCadastrados;

    public Sistema() {
        this.estoque = new Estoque();
        this.clientesCadastrados = new HashSet<>();
        this.pedidosCadastrados = new HashSet<>();
    }

    public Set<Cliente> getClientesCadastrados() {
        return clientesCadastrados;
    }

    public Set<Pedido> getPedidosCadastrados() {
        return pedidosCadastrados;
    }

    public void cadastrarProdutoEstoque(String codigoProduto, String nome, String descricao, double preco, int quantidade) {
        Produto produto = new Produto(codigoProduto, nome, descricao, preco);
        estoque.adicionarProdutoEstoque(produto, quantidade);
    }

    public Produto buscarProduto(String codigoProduto) {
    	
        Item item = estoque.buscarProdutoEstoque(codigoProduto);
        
        if (item != null && item.getProduto().getCodigo().equals(codigoProduto)) {
            return item.getProduto();
        }
        return null;
    }

    public Item buscarItemEstoque(String codigoProduto) {
        return estoque.buscarProdutoEstoque(codigoProduto);
    }

    public void removerProdutoEstoque(String codigoProduto, int quantidade) {
        estoque.removerProdutoEstoque(codigoProduto, quantidade);
    }

    public void atualizarEstoque(String codigoProduto, int quantidade) {
    	
        Produto produto = buscarProduto(codigoProduto);
        
        if (produto != null) {
            estoque.adicionarProdutoEstoque(produto, quantidade);
            System.out.println("Estoque atualizado.");
        } else {
            throw new NullPointerException("Produto não encontrado!");
        }
    }

    public void cadastrarCliente(String nome, String sobrenome, String telefone) {
    	
        Cliente cliente = new Cliente(nome, sobrenome, telefone);
        
        if (clientesCadastrados.add(cliente)) {
            System.out.println("Cadastrado cliente: " + nome + " " + sobrenome + ".");
        } else {
            System.out.println("Cliente já cadastrado!");
        }
    }

    public Cliente buscarCliente(String telefone) {
    	
        for (Cliente cliente : clientesCadastrados) {
            if (cliente.getTelefone().equals(telefone)) {
                return cliente;
            }
        }
        return null;
    }

    public void removerCliente(String telefone) {
    	
        Cliente cliente = buscarCliente(telefone);
        
        if (cliente != null) {
            clientesCadastrados.remove(cliente);
            System.out.println("Cliente removido!");
        } else {
            throw new NullPointerException("Cliente não encontrado!");
        }
    }

    public void cadastrarPedido(String idPedido, Cliente cliente) {
    	
        Pedido pedido = new Pedido(idPedido, cliente);
        pedidosCadastrados.add(pedido);
        System.out.println("Cadastrado novo pedido para o cliente: " + cliente.getNome() + " " + cliente.getSobrenome() + ".");
    }

    public Pedido buscarPedido(String id) {
    	
        for (Pedido pedido : pedidosCadastrados) {
            if (pedido.getId().equals(id)) {
                return pedido;
            }
        }
        return null;
    }

    public void removerPedido(String idPedido) {
    	
        Pedido pedido = buscarPedido(idPedido);
        
        if (pedido != null) {
            pedidosCadastrados.remove(pedido);
            System.out.println("Pedido " + pedido.getId() + " removido!");
        } else {
            throw new NullPointerException("Pedido não encontrado!");
        }
    }

    public void adicionarItemPedido(String idPedido, String codigoProduto, int quantidade) {
    	
        Produto produto = buscarProduto(codigoProduto);
        Pedido pedido = buscarPedido(idPedido);
        
        if (produto != null && pedido != null) {
            pedido.adicionarProdutoPedido(produto, quantidade, estoque);
            System.out.println("Produto " + produto.getNome() + ", adicionado ao pedido.");
        } else {
            throw new NullPointerException("Produto ou Pedido não encontrado!");
        }
    }

    public void removerItemPedido(String idPedido, String codigoProduto, int quantidade) {
    	
        Produto produto = buscarProduto(codigoProduto);
        Pedido pedido = buscarPedido(idPedido);
        
        if (produto != null && pedido != null && quantidade > 0) {
            pedido.removerProdutoPedido(produto, quantidade, estoque);
        } else {
            throw new NullPointerException("Produto ou Pedido não encontrado!");
        }
    }

    public void mostrarEstoque() {
        estoque.mostrarEstoque();
    }

    public void mostrarPedido(String idPedido) {
    	
        Pedido pedido = buscarPedido(idPedido);
        
        if (pedido != null) {
            pedido.mostrarPedido();
        } else {
        	throw new NullPointerException("Pedido não encontrado!");
        }
    }
}
package modelo.entidade.Item;

import java.util.Objects;
import modelo.entidade.produto.Produto;

public class Item {
    
    private String codigo;
    private Produto produto;
    private int quantidade;

    public Item(Produto produto, int quantidade) {
        this.codigo = produto.getCodigo();
        this.produto = produto;
        this.quantidade = quantidade;
    }    

    public String getCodigo() {
        return codigo;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double calcularPreco() {
        return this.produto.getPreco() * this.quantidade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        return Objects.equals(codigo, other.codigo);
    }
}

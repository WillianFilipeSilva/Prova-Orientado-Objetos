package principal;
import modelo.entidade.cliente.Cliente;
import modelo.entidade.sistema.Sistema;

public class Principal {

	public static void main(String[] args) {
		
		Sistema sistema = new Sistema();
		
        sistema.cadastrarProdutoEstoque("CP001","Café Passado", "Café Passado Quentinho!", 4.0, 10);
        sistema.cadastrarProdutoEstoque("CE001", "Café Expresso", "Café Quentinho!", 3.5, 10);
        sistema.cadastrarProdutoEstoque("CC001", "Chá De Camomila", "Chá Quentinho!", 3.0, 10);

        sistema.cadastrarCliente("Willian", "Filipe", "12345678910");

        Cliente cliente = sistema.buscarCliente("12345678910");
        
        sistema.cadastrarPedido("Pedido1", cliente);

        sistema.adicionarItemPedido("Pedido1", "CP001", 1);
        sistema.adicionarItemPedido("Pedido1", "CE001", 2);
        sistema.adicionarItemPedido("Pedido1", "CC001", 4);

        sistema.mostrarPedido("Pedido1");
        
        sistema.removerItemPedido("Pedido1", "CC001", 1);
        
        sistema.mostrarPedido("Pedido1"); 
    }
}
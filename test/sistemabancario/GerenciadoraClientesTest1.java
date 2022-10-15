package sistemabancario;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GerenciadoraClientesTest1 {
	
	@Test
	public void testePesquisaCliente(){
		//Cenário
		Cliente cliente01 = new Cliente(1, "Terencio", 30, "terencio@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Joaquim", 23, "joaquim@gmail.com", 2, true);
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente01);
		clientes.add(cliente02);
		GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientes);
		
		//Execução 1
		Cliente cliente = gerClientes.pesquisaCliente(1);
		
		
		//Análise do resultado 1
		assertThat(cliente.getId(), is(1));
		assertThat(cliente.getEmail(), is("terencio@gmail.com"));
		
		//Execução 2
		Cliente cliente2 = gerClientes.pesquisaCliente(2);
		
		//Análise do resultado 2
		assertThat(cliente2.getId(), is(2));
		assertThat(cliente2.getEmail(), is("joaquim@gmail.com"));
	}
	
	@Test
	public void testeAdicionaCliente() {
		//Cenário
		Cliente cliente01 = new Cliente(1, "Terencio", 30, "terencio@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Joaquim", 23, "joaquim@gmail.com", 2, true);
		
		List<Cliente> clientes = new ArrayList<>();
		GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientes);
		
		//Execução 1
		gerClientes.adicionaCliente(cliente01);
		Cliente clienteAdicionado1 = gerClientes.pesquisaCliente(1); 
		
		//Análise do resultado 1
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertThat(clienteAdicionado1.getId(), is(1));
		assertThat(clienteAdicionado1.getEmail(), is("terencio@gmail.com"));
		
		//Execução 2
		gerClientes.adicionaCliente(cliente02);
		Cliente clienteAdicionado2 = gerClientes.pesquisaCliente(2);
		
		//Análise do resultado 2
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
		assertThat(clienteAdicionado2.getId(), is(2));
		assertThat(clienteAdicionado2.getEmail(), is("joaquim@gmail.com"));
	}
	
	@Test
	public void testeRemoveCliente() {
		//Cenário
		Cliente cliente01 = new Cliente(1, "Terencio", 30, "terencio@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Joaquim", 23, "joaquim@gmail.com", 2, true);
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente01);
		clientes.add(cliente02);
		
		GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientes);
		
		//Execução 1
		boolean clienteRemovido = gerClientes.removeCliente(2);
		
		//Análise do resultado 1
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(2));
		
		//Execução 2
		boolean clienteRemovido2 = gerClientes.removeCliente(1);
				
		//Análise do resultado 2
		assertThat(clienteRemovido2, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(0));
		assertNull(gerClientes.pesquisaCliente(1));
	}
}
package sistemabancario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GerenciadoraClientesTest2 {

	private GerenciadoraClientes gerClientes;
	
	private int idCliente01 = 1;
	private int idCliente02 = 2;
	
	@Before
	public void setUp() {
		//Cenário
		Cliente cliente01 = new Cliente(1, "Terencio", 30, "terencio@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(2, "Joaquim", 23, "joaquim@gmail.com", 2, true);
		
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(cliente01);
		clientes.add(cliente02);
		gerClientes = new GerenciadoraClientes(clientes);
	}
	
	@Test
	public void testPesquisaCliente() {				
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
	
	@After
	public void tearDown() {
		gerClientes.clear();
	}
}

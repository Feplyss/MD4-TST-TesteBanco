package sistemabancario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GerenciadoraClientesTest3 {

	private GerenciadoraClientes gerClientes;

	private int idCliente01 = 1;
	private int idCliente02 = 2;

	@Before
	public void setUp() {
		// Cenário
		Cliente cliente01 = new Cliente(idCliente01, "Terencio", 30, "terencio@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Joaquim", 23, "joaquim@gmail.com", 2, true);

		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
	}

	@Test
	public void testPesquisaCliente() {
		// Execução 1
		Cliente cliente = gerClientes.pesquisaCliente(idCliente01);

		// Análise do resultado 1
		assertThat(cliente.getId(), is(1));
		assertThat(cliente.getEmail(), is("terencio@gmail.com"));

		// Execução 2
		Cliente cliente2 = gerClientes.pesquisaCliente(idCliente02);

		// Análise do resultado 2
		assertThat(cliente2.getId(), is(2));
		assertThat(cliente2.getEmail(), is("joaquim@gmail.com"));
	}
	
	@Test
	public void testPesquisaClienteInexistente() {
		// Execução 1
		Cliente cliente = gerClientes.pesquisaCliente(10);

		// Análise do resultado 1
		assertNull(cliente);
	}

	@Test
	public void testRemoveCliente() {
		// Execução 1
		boolean clienteRemovido = gerClientes.removeCliente(idCliente02);

		// Análise do resultado 1
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(2));

		// Execução 2
		boolean clienteRemovido2 = gerClientes.removeCliente(idCliente01);

		// Análise do resultado 2
		assertThat(clienteRemovido2, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(0));
		assertNull(gerClientes.pesquisaCliente(1));
	}
	
	@Test
	public void testRemoveClienteInexistente() {
		// Execução 1
		boolean clienteRemovido = gerClientes.removeCliente(10);

		// Análise do resultado 1
		assertThat(clienteRemovido, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}
	
	@Test
	public void testClienteIdadePermitida() throws IdadeNaoPermitidaException {
		// Cenário customizado
		Cliente cliente = new Cliente(3, "Leopoldo", 48, "leopoldo@gmail.com", 1, true);
		
		// Execução 1
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		// Análise do resultado 1
		assertTrue(idadeValida);
	}
	
	@Test
	public void testClienteIdadePermitida18() throws IdadeNaoPermitidaException {
		// Cenário customizado
		Cliente cliente = new Cliente(4, "Marineia", 18, "marineira@gmail.com", 1, true);
		
		// Execução 1
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		// Análise do resultado 1
		assertTrue(idadeValida);
	}
	
	@Test
	public void testClienteIdadePermitida65() throws IdadeNaoPermitidaException {
		// Cenário customizado
		Cliente cliente = new Cliente(5, "Forênsio", 65, "forensio@gmail.com", 1, true);
		
		// Execução 1
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
		
		// Análise do resultado 1
		assertTrue(idadeValida);
	}
	
	@Test
	public void testClienteIdadePermitidaMenor18() throws IdadeNaoPermitidaException {
		// Cenário customizado
		Cliente cliente = new Cliente(6, "Elenor", 17, "elenor@gmail.com", 1, true);
		
		try {
			// Execução 1
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		}catch (Exception e){
			// Análise do resultado 1
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}
	
	@Test
	public void testClienteIdadePermitidaMaior65() throws IdadeNaoPermitidaException {
		// Cenário customizado
		Cliente cliente = new Cliente(7, "Hortênsia", 66, "hortensia@gmail.com", 1, true);
		
		try {
			// Execução 1
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		}catch (Exception e){
			// Análise do resultado 1
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}
	}
}

package sistemabancario;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



public class GerenciadoraContasTest {

	private GerenciadoraContas gerContas;
	
	@Test
	public void testTransfereValor() {
		//Cenário 1
		ContaCorrente conta01 = new ContaCorrente(1, 200, true);
		ContaCorrente conta02 = new ContaCorrente(2, 0, true);
		
		List<ContaCorrente> contaDoBanco = new ArrayList<>();
		contaDoBanco.add(conta01);
		contaDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contaDoBanco);
		
		//Execução 1
		boolean sucesso = gerContas.transfereValor(1, 100, 2);
		
		//Análise
		assertTrue(sucesso);
		assertThat(conta01.getSaldo(), is(100.0));
		assertThat(conta02.getSaldo(), is(100.0));
	}
}

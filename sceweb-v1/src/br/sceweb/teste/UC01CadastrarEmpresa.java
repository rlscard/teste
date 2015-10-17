package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC01CadastrarEmpresa {
	
	static EmpresaDAO empresaDAO;
	static Empresa empresa;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		empresa.setCnpj("87462111000106");
		empresa.setNomeDaEmpresa("Empresa2");
		empresa.setNomeFantasia("nomeFantasia2");
		empresa.setEndereco("rua 2");
		empresa.setTelefone("987654321");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		empresaDAO.exclui("879879879856889");		
	}

	@Test
	public void CT01UC01FBCadastrarEmpresa_com_sucesso() {
		//fail("Not yet implemented");
		empresaDAO.exclui(empresa.getCnpj());
		assertEquals(1, empresaDAO.adiciona(empresa));
		
	}
	
	@Test(expected = RuntimeException.class)
	public void CT02UC01A2CadastrarEmpresa_cnpj_cadastrado() {
		assertEquals(0, empresaDAO.adiciona(empresa));

	}
	
	@Test
	public void CT03UC01A3CadastrarEmpresa_cnpj_invalido() {
		Empresa empresa = new Empresa();
		try {
			empresa.setCnpj("879879879856889");
			fail("deveria disparar uma exception");			
		} catch(Exception e){
			assertEquals("CNPJ Inválido!", e.getMessage());
		}
	}
	
	@Test
	public void CT04UC01A4CadastrarEmpresa_com_dados_invalidos() {
		Empresa empresa = new Empresa();
		try{
			//empresa.setCnpj("24512799000163");
			empresa.setNomeDaEmpresa("");
			fail("deveria disparar uma exeption");
		} catch(Exception e) {
			assertEquals("Nome da Empresa Inválido!", e.getMessage());
		}
	}
}

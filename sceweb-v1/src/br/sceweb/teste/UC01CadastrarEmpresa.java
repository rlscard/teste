package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Controle;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;


public class UC01CadastrarEmpresa {
	
	static Controle controle;
	static Empresa empresa;
	static Empresa empresa2;
	static EmpresaDAO empresaDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		empresaDAO = new EmpresaDAO();
		controle = new Controle();
		empresa = new Empresa();
		empresa.setCnpj("87462111000106");
		empresa.setNomeDaEmpresa("Empresa2");
		empresa.setNomeFantasia("nomeFantasia2");
		empresa.setEndereco("rua 2");
		empresa.setTelefone("987654321");
		empresa2 = new Empresa();
		//controle.excluirEmpresa(empresa.getCnpj());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {		
		System.out.println(EmpresaDAO.listaEmpresa.size() +" "+ empresaDAO.consultaEmpresas().size());
		
	}

	@Test
	public void CT01UC01FBCadastrarEmpresa_com_sucesso() {
		controle.excluirEmpresa(empresa.getCnpj());
		//fail("Not yet implemented");
		String msg = controle.cadastrarEmpresa(empresa.getCnpj(), empresa.getNomeDaEmpresa(), empresa.getNomeFantasia(), empresa.getEndereco(), empresa.getTelefone());
		assertEquals("cadastro realizado com sucesso", msg);
		
		
	}
	
	@Test
	public void CT02UC01A2CadastrarEmpresa_cnpj_cadastrado() {
		controle.cadastrarEmpresa(empresa.getCnpj(), empresa.getNomeDaEmpresa(), empresa.getNomeFantasia(), empresa.getEndereco(), empresa.getTelefone());
		String msg = controle.cadastrarEmpresa(empresa.getCnpj(), empresa.getNomeDaEmpresa(), empresa.getNomeFantasia(), empresa.getEndereco(), empresa.getTelefone());
		assertEquals("com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException: Duplicate entry '" + empresa.getCnpj() + "' for key 'PRIMARY'", msg);
		controle.excluirEmpresa(empresa.getCnpj());		

	}
	
	@Test
	public void CT03UC01A3CadastrarEmpresa_cnpj_invalido() {		
		try {
			empresa2.setCnpj("87987987985894");
			fail("deveria disparar uma exception");
		} catch(Exception e){
			assertEquals("CNPJ Inválido!", e.getMessage());
		}
	}
	
	@Test
	public void CT04UC01A4CadastrarEmpresa_com_dados_invalidos() {
		Empresa empresa = new Empresa();
		try{
			empresa.setCnpj("24512799000163");
			empresa.setNomeDaEmpresa("");
			//fail("deveria disparar uma exception");
		} catch(Exception e) {
			assertEquals("Nome da Empresa Inválido!", e.getMessage());
		}
	}
}

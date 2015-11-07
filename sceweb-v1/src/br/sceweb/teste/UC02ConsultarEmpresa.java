package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC02ConsultarEmpresa {

	static EmpresaDAO empresaDAO;
	static Empresa empresa;
	
	@Before
	public void setUp() throws Exception {
		empresaDAO = new EmpresaDAO();
		empresa = new Empresa();
		empresa.setCnpj("87462111000106");
		empresa.setNomeDaEmpresa("Empresa2");
		empresa.setNomeFantasia("nomeFantasia2");
		empresa.setEndereco("rua 2");
		empresa.setTelefone("987654321");
		empresaDAO.exclui(empresa.getCnpj());
		empresaDAO.adiciona(empresa);
	}

	@After
	public void tearDown() throws Exception {
		empresaDAO.exclui(empresa.getCnpj());
	}

	@Test
	public void CT01UC02Consultar_Empresa_Com_Sucesso() {
		assertTrue(empresa.equals(empresaDAO.consultaEmpresas("87462111000106")));
	}
	
	@Test
	public void CT02UC02Consultar_Empresa_Nao_Cadastrado() {
		assertFalse(empresa.equals(empresaDAO.consultaEmpresas("87462111000102")));
	}
	
	@Test
	public void CT03UC02Consultar_Empresas_Com_Sucesso() {
		System.out.println(EmpresaDAO.listaEmpresa.size() +" "+ empresaDAO.consultaEmpresas().size());
		System.out.println(EmpresaDAO.listaEmpresa.get(0).getCnpj());
		System.out.println(EmpresaDAO.listaEmpresa.get(1).getCnpj());
		assertTrue(EmpresaDAO.listaEmpresa.size() == empresaDAO.consultaEmpresas().size());
	}
}

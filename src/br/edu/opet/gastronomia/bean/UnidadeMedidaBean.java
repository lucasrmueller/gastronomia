package br.edu.opet.gastronomia.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.opet.gastronomia.controller.UnidadeMedidaController;
import br.edu.opet.gastronomia.dao.UnidadeMedidaDao;
import br.edu.opet.gastronomia.model.UnidadeMedida;

@Named
@SessionScoped
public class UnidadeMedidaBean implements Serializable {


	private static final long serialVersionUID = -5021009997032216320L;
	
	private UnidadeMedida pUnidadeMedida;
	private UnidadeMedidaController unidadeMedidaController;
	private UnidadeMedidaDao unidadeMedidaDao;
	private List<UnidadeMedida> listaMedida;
	
	
	public UnidadeMedidaBean()
	{
		pUnidadeMedida = new UnidadeMedida();
		unidadeMedidaController = new UnidadeMedidaController();
		unidadeMedidaDao = new UnidadeMedidaDao();
	
	}
	
	
	public void cadastrar()
	{
		try
		{
			unidadeMedidaController.cadastrar(pUnidadeMedida);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void search() {
		try {
			unidadeMedidaController.pesquisar();
		}
		catch(Exception e) {
		
		}
	}
	
	public List<UnidadeMedida> searchByDescricao(String pDescricao) {
		
		if(pDescricao ==null)
		{
			return null;
		}
		else
		{
			listaMedida = unidadeMedidaDao.searchByDescricao(pDescricao);
			return listaMedida;
		}
		
		

}


	public UnidadeMedida getpUnidadeMedida() {
		return pUnidadeMedida;
	}


	public void setpUnidadeMedida(UnidadeMedida pUnidadeMedida) {
		this.pUnidadeMedida = pUnidadeMedida;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

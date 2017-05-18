package br.edu.opet.gastronomia.bean;

import java.io.Serializable;


import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import br.edu.opet.gastronomia.controller.ProdutoController;
import br.edu.opet.gastronomia.dao.ProdutoDao;
import br.edu.opet.gastronomia.model.Produto;

@Named
@SessionScoped
@ManagedBean
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = -3570025787611636611L;
	
	private Produto pProduto;
	private ProdutoController pProdutoController;
	private ProdutoDao pProdutoDao;
	
	public ProdutoBean() {
		pProduto = new Produto();
		pProdutoController = new ProdutoController();
		pProdutoDao = new ProdutoDao();
	}
	
	public void cadastrar() {
		try {
			pProdutoController.cadastrar(pProduto);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public Produto getpProduto() {
		return pProduto;
	}

	public void setpProduto(Produto pProduto) {
		this.pProduto = pProduto;
	}

	public ProdutoController getpProdutoController() {
		return pProdutoController;
	}

	public void setpProdutoController(ProdutoController pProdutoController) {
		this.pProdutoController = pProdutoController;
	}

	public ProdutoDao getpProdutoDao() {
		return pProdutoDao;
	}

	public void setpProdutoDao(ProdutoDao pProdutoDao) {
		this.pProdutoDao = pProdutoDao;
	}

	

}

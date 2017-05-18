package br.edu.opet.gastronomia.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.opet.gastronomia.controller.CategoriaController;
import br.edu.opet.gastronomia.dao.CategoriaDao;
import br.edu.opet.gastronomia.model.Categoria;


@Named
@SessionScoped
public class CategoriaBean implements Serializable  {

	private static final long serialVersionUID = 5283877114981096867L;

	private Categoria pCategoria;
	private CategoriaController pCategoriaController;
	private CategoriaDao pCategoriaDao;
	
	public CategoriaBean() {
		pCategoria = new Categoria();
		pCategoriaController = new CategoriaController();
		pCategoriaDao = new CategoriaDao();
	}

	public void cadastar() {
		try {
			CategoriaController.cadastrar(pCategoria);
		} catch (Exception e){
			e.printStackTrace();
		}		
	}

	public void recuperar() {
		try {		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean atualizar(Categoria pCategoria) {
		try {
			CategoriaController.atualizar(pCategoria);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		} return true;		
	}
	
	public boolean remover(int pId) {
		try {
			CategoriaController.remover(pId);
		} catch (Exception e){
			e.printStackTrace();
			return false;
		} return true;		
	}
	
	public void pesquisar() {
		try {
		} catch (Exception e){
			e.printStackTrace();
		}			
	}
	
	public void pesquisarPorDescricao(String pDescricao) {
		try {
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	public Categoria getpCategoria() {
		return pCategoria;
	}

	public void setpCategoria(Categoria pCategoria) {
		this.pCategoria = pCategoria;
	}

	public CategoriaController getpCategoriaController() {
		return pCategoriaController;
	}

	public void setpCategoriaController(CategoriaController pCategoriaController) {
		this.pCategoriaController = pCategoriaController;
	}

}

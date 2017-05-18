package br.edu.opet.gastronomia.controller;

import java.util.List;

import br.edu.opet.gastronomia.dao.AulaReceitaDao;
import br.edu.opet.gastronomia.dto.AulaReceitaDto;
import br.edu.opet.gastronomia.model.AulaReceita;



public class AulaReceitaController {

	public static AulaReceitaDto cadastrar(AulaReceita pAulaReceita) {
        if (pAulaReceita == null)
            return new AulaReceitaDto(false, "Tentativa de inserir um receita nulo");

        AulaReceitaDao tDao = new AulaReceitaDao();

        AulaReceita tAulaReceita = tDao.recovery(pAulaReceita.getId_aula());
        if (tAulaReceita != null)
            return new AulaReceitaDto(false, "Já existe AulaReceita com essa descrição");

        tAulaReceita = tDao.create(pAulaReceita);

        
        if (tAulaReceita == null)
            return new AulaReceitaDto(false, "Problemas na gravação de AulaReceita");

        
        return new AulaReceitaDto(true, "AulaReceita gravada com sucesso", tAulaReceita);
    }
	
	public AulaReceitaDto atualizar(AulaReceita pAulaReceita) {
	        if (pAulaReceita == null)
	            return new AulaReceitaDto(false, "Tentativa de atualizar AulaReceita nulo");

	        AulaReceitaDao tDao = new AulaReceitaDao();
	        AulaReceita tAulaReceita = tDao.update(pAulaReceita);
	        
	        if (tAulaReceita == null)
	            return new AulaReceitaDto(false, "AulaReceita não existe no cadastro");

	        return new AulaReceitaDto(true, "AulaReceita regravada com sucesso", tAulaReceita);
	    }
	 
	public AulaReceitaDto remover(int pId) {
	        
	        AulaReceitaDao tDao = new AulaReceitaDao();
	        if (! tDao.delete(pId))
	            return new AulaReceitaDto(false, "AulaReceita não existe no cadastro");

	  
	        return new AulaReceitaDto(true, "AulaReceita removida com sucesso");
	    }
	
	public AulaReceitaDto pesquisar() {
	 
	        AulaReceitaDao tDao = new AulaReceitaDao();
	        List<AulaReceita> tLista = tDao.search();

	    
	        if (tLista.isEmpty())
	            return new AulaReceitaDto(false, "Lista de AulaReceitas  vazia");

	     
	        return new AulaReceitaDto(true, "Lista de AulaReceitas recuperada", tLista);
	    }
	 
}

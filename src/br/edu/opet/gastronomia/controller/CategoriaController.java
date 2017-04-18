package br.edu.opet.gastronomia.controller;

import java.util.List;

import br.edu.opet.gastronomia.dao.CategoriaDao;
import br.edu.opet.gastronomia.dto.CategoriaDto;
import br.edu.opet.gastronomia.model.Categoria;

public class CategoriaController {
    // M�todo para criar uma categoria
    public static CategoriaDto cadastrar(Categoria pCategoria) {
        if (pCategoria == null)
            return new CategoriaDto(false, "Tentativa de inserir um categoria nulo");

        // Chamando a camada de persist�ncia
        CategoriaDao tDao = new CategoriaDao();

        Categoria tCategoria = tDao.recoveryByDescricao(pCategoria.getDescricao());
        if (tCategoria != null)
            return new CategoriaDto(false, "J� existe categoria com essa descri��o");

        tCategoria = tDao.create(pCategoria);

        // Verificando se houve erro de cria��o
        if (tCategoria == null)
            return new CategoriaDto(false, "Problemas na grava��o da categoria");

        // Retornando o indicativo de sucesso com o objeto criado
        return new CategoriaDto(true, "categoria gravada com sucesso", tCategoria);
    }

    // M�todo para recuperar uma categoria
    public static CategoriaDto recuperar(int pId) {
        // Lendo o objeto
        CategoriaDao tDao = new CategoriaDao();
        Categoria tCategoria = tDao.recovery(pId);

        // Verificando se houve erro de recupera��o
        if (tCategoria == null)
            return new CategoriaDto(false, "Categoria n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new CategoriaDto(true, "categoria lida com sucesso", tCategoria);
    }

    // M�todo para atualizar uma categoria
    public static CategoriaDto atualizar(Categoria pCategoria) {
        if (pCategoria == null)
            return new CategoriaDto(false, "Tentativa de atualizar uma categoria nulo");

        // Chamando a camada de persist�ncia
        CategoriaDao tDao = new CategoriaDao();

        Categoria tCategoria = tDao.recoveryByDescricao(pCategoria.getDescricao());
        if (tCategoria != null)
            return new CategoriaDto(false, "J� existe categoria com essa descri��o");

        tCategoria = tDao.update(pCategoria);

        // Verificando se houve erro de atualiza��o
        if (tCategoria == null)
            return new CategoriaDto(false, "Categoria n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new CategoriaDto(true, "Categoria regravada com sucesso", tCategoria);
    }

    // M�todo para deletar uma categoria
    public static CategoriaDto remover(int pId) {
        // Removendo o categoria e verificando se houve erro de remo��o
        CategoriaDao tDao = new CategoriaDao();
        if (! tDao.delete(pId))
            return new CategoriaDto(false, "Categoria n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new CategoriaDto(true, "Categoria removida com sucesso");
    }

    // M�todo para pesquisar todos as categorias
    public static CategoriaDto pesquisar() {
        // Obtendo a lista de categorias
        CategoriaDao tDao = new CategoriaDao();
        List<Categoria> tLista = tDao.search();

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new CategoriaDto(false, "Lista de categorias vazia");

        // Retornando a lista obtida
        return new CategoriaDto(true, "Lista de categorias recuperada", tLista);
    }

    // M�todo para pesquisar por descri��o todos as categorias
    public static CategoriaDto pesquisarPorDescricao(String pDescricao) {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pDescricao == null)
            return pesquisar();

        // Obtendo a lista de categorias
        CategoriaDao tDao = new CategoriaDao();
        List<Categoria> tLista = tDao.searchByDescricao(pDescricao);

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new CategoriaDto(false, "Nenhum registro encontrado com a descri��o '" + pDescricao + "'");

        // Retornando a lista obtida
        return new CategoriaDto(true, "Lista de categorias recuperada", tLista);
    }

}

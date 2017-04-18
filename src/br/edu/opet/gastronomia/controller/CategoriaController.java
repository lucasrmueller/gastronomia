package br.edu.opet.gastronomia.controller;

import java.util.List;

import br.edu.opet.gastronomia.dao.CategoriaDao;
import br.edu.opet.gastronomia.dto.CategoriaDto;
import br.edu.opet.gastronomia.model.Categoria;

public class CategoriaController {
    // Método para criar uma categoria
    public static CategoriaDto cadastrar(Categoria pCategoria) {
        if (pCategoria == null)
            return new CategoriaDto(false, "Tentativa de inserir um categoria nulo");

        // Chamando a camada de persistência
        CategoriaDao tDao = new CategoriaDao();

        Categoria tCategoria = tDao.recoveryByDescricao(pCategoria.getDescricao());
        if (tCategoria != null)
            return new CategoriaDto(false, "Já existe categoria com essa descrição");

        tCategoria = tDao.create(pCategoria);

        // Verificando se houve erro de criação
        if (tCategoria == null)
            return new CategoriaDto(false, "Problemas na gravação da categoria");

        // Retornando o indicativo de sucesso com o objeto criado
        return new CategoriaDto(true, "categoria gravada com sucesso", tCategoria);
    }

    // Método para recuperar uma categoria
    public static CategoriaDto recuperar(int pId) {
        // Lendo o objeto
        CategoriaDao tDao = new CategoriaDao();
        Categoria tCategoria = tDao.recovery(pId);

        // Verificando se houve erro de recuperação
        if (tCategoria == null)
            return new CategoriaDto(false, "Categoria não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new CategoriaDto(true, "categoria lida com sucesso", tCategoria);
    }

    // Método para atualizar uma categoria
    public static CategoriaDto atualizar(Categoria pCategoria) {
        if (pCategoria == null)
            return new CategoriaDto(false, "Tentativa de atualizar uma categoria nulo");

        // Chamando a camada de persistência
        CategoriaDao tDao = new CategoriaDao();

        Categoria tCategoria = tDao.recoveryByDescricao(pCategoria.getDescricao());
        if (tCategoria != null)
            return new CategoriaDto(false, "Já existe categoria com essa descrição");

        tCategoria = tDao.update(pCategoria);

        // Verificando se houve erro de atualização
        if (tCategoria == null)
            return new CategoriaDto(false, "Categoria não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new CategoriaDto(true, "Categoria regravada com sucesso", tCategoria);
    }

    // Método para deletar uma categoria
    public static CategoriaDto remover(int pId) {
        // Removendo o categoria e verificando se houve erro de remoção
        CategoriaDao tDao = new CategoriaDao();
        if (! tDao.delete(pId))
            return new CategoriaDto(false, "Categoria não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new CategoriaDto(true, "Categoria removida com sucesso");
    }

    // Método para pesquisar todos as categorias
    public static CategoriaDto pesquisar() {
        // Obtendo a lista de categorias
        CategoriaDao tDao = new CategoriaDao();
        List<Categoria> tLista = tDao.search();

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new CategoriaDto(false, "Lista de categorias vazia");

        // Retornando a lista obtida
        return new CategoriaDto(true, "Lista de categorias recuperada", tLista);
    }

    // Método para pesquisar por descrição todos as categorias
    public static CategoriaDto pesquisarPorDescricao(String pDescricao) {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pDescricao == null)
            return pesquisar();

        // Obtendo a lista de categorias
        CategoriaDao tDao = new CategoriaDao();
        List<Categoria> tLista = tDao.searchByDescricao(pDescricao);

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new CategoriaDto(false, "Nenhum registro encontrado com a descrição '" + pDescricao + "'");

        // Retornando a lista obtida
        return new CategoriaDto(true, "Lista de categorias recuperada", tLista);
    }

}

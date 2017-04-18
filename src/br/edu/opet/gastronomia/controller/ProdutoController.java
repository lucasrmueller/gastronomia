package br.edu.opet.gastronomia.controller;

import java.util.List;

import br.edu.opet.gastronomia.dao.ProdutoDao;
import br.edu.opet.gastronomia.dto.ProdutoDto;
import br.edu.opet.gastronomia.model.Produto;

public class ProdutoController {
    // Método para criar uma categoria
    public static ProdutoDto cadastrar(Produto pProduto) {
        if (pProduto == null)
            return new ProdutoDto(false, "Tentativa de inserir um categoria nulo");

        // Chamando a camada de persistência
        ProdutoDao tDao = new ProdutoDao();

        Produto tProduto = tDao.recoveryByNome(pProduto.getNome());
        if (tProduto != null)
            return new ProdutoDto(false, "Já existe categoria com essa descrição");

        tProduto = tDao.create(pProduto);

        // Verificando se houve erro de criação
        if (tProduto == null)
            return new ProdutoDto(false, "Problemas na gravação da categoria");

        // Retornando o indicativo de sucesso com o objeto criado
        return new ProdutoDto(true, "categoria gravada com sucesso", tProduto);
    }

    // Método para recuperar uma categoria
    public static ProdutoDto recuperar(int pId) {
        // Lendo o objeto
        ProdutoDao tDao = new ProdutoDao();
        Produto tProduto = tDao.recovery(pId);

        // Verificando se houve erro de recuperação
        if (tProduto == null)
            return new ProdutoDto(false, "Produto não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new ProdutoDto(true, "categoria lida com sucesso", tProduto);
    }

    // Método para atualizar uma categoria
    public static ProdutoDto atualizar(Produto pProduto) {
        if (pProduto == null)
            return new ProdutoDto(false, "Tentativa de atualizar uma categoria nulo");

        // Chamando a camada de persistência
        ProdutoDao tDao = new ProdutoDao();

        Produto tProduto = tDao.recoveryByNome(pProduto.getNome());
        if (tProduto != null)
            return new ProdutoDto(false, "Já existe categoria com essa descrição");

        tProduto = tDao.update(pProduto);

        // Verificando se houve erro de atualização
        if (tProduto == null)
            return new ProdutoDto(false, "Produto não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new ProdutoDto(true, "Produto regravada com sucesso", tProduto);
    }

    // Método para deletar uma categoria
    public static ProdutoDto remover(int pId) {
        // Removendo o categoria e verificando se houve erro de remoção
        ProdutoDao tDao = new ProdutoDao();
        if (! tDao.delete(pId))
            return new ProdutoDto(false, "Produto não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new ProdutoDto(true, "Produto removida com sucesso");
    }

    // Método para pesquisar todos as categorias
    public static ProdutoDto pesquisar() {
        // Obtendo a lista de categorias
        ProdutoDao tDao = new ProdutoDao();
        List<Produto> tLista = tDao.search();

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new ProdutoDto(false, "Lista de categorias vazia");

        // Retornando a lista obtida
        return new ProdutoDto(true, "Lista de categorias recuperada", tLista);
    }

    // Método para pesquisar por descrição todos as categorias
    public static ProdutoDto pesquisarPorDescricao(String pNome) {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pNome == null)
            return pesquisar();

        // Obtendo a lista de categorias
        ProdutoDao tDao = new ProdutoDao();
        List<Produto> tLista = tDao.searchByNome(pNome);

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new ProdutoDto(false, "Nenhum registro encontrado com a descrição '" + pNome + "'");

        // Retornando a lista obtida
        return new ProdutoDto(true, "Lista de categorias recuperada", tLista);
    }

}

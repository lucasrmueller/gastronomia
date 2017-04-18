package br.edu.opet.gastronomia.controller;

import java.util.List;

import br.edu.opet.gastronomia.dao.ProdutoDao;
import br.edu.opet.gastronomia.dto.ProdutoDto;
import br.edu.opet.gastronomia.model.Produto;

public class ProdutoController {
    // M�todo para criar uma categoria
    public static ProdutoDto cadastrar(Produto pProduto) {
        if (pProduto == null)
            return new ProdutoDto(false, "Tentativa de inserir um categoria nulo");

        // Chamando a camada de persist�ncia
        ProdutoDao tDao = new ProdutoDao();

        Produto tProduto = tDao.recoveryByNome(pProduto.getNome());
        if (tProduto != null)
            return new ProdutoDto(false, "J� existe categoria com essa descri��o");

        tProduto = tDao.create(pProduto);

        // Verificando se houve erro de cria��o
        if (tProduto == null)
            return new ProdutoDto(false, "Problemas na grava��o da categoria");

        // Retornando o indicativo de sucesso com o objeto criado
        return new ProdutoDto(true, "categoria gravada com sucesso", tProduto);
    }

    // M�todo para recuperar uma categoria
    public static ProdutoDto recuperar(int pId) {
        // Lendo o objeto
        ProdutoDao tDao = new ProdutoDao();
        Produto tProduto = tDao.recovery(pId);

        // Verificando se houve erro de recupera��o
        if (tProduto == null)
            return new ProdutoDto(false, "Produto n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new ProdutoDto(true, "categoria lida com sucesso", tProduto);
    }

    // M�todo para atualizar uma categoria
    public static ProdutoDto atualizar(Produto pProduto) {
        if (pProduto == null)
            return new ProdutoDto(false, "Tentativa de atualizar uma categoria nulo");

        // Chamando a camada de persist�ncia
        ProdutoDao tDao = new ProdutoDao();

        Produto tProduto = tDao.recoveryByNome(pProduto.getNome());
        if (tProduto != null)
            return new ProdutoDto(false, "J� existe categoria com essa descri��o");

        tProduto = tDao.update(pProduto);

        // Verificando se houve erro de atualiza��o
        if (tProduto == null)
            return new ProdutoDto(false, "Produto n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new ProdutoDto(true, "Produto regravada com sucesso", tProduto);
    }

    // M�todo para deletar uma categoria
    public static ProdutoDto remover(int pId) {
        // Removendo o categoria e verificando se houve erro de remo��o
        ProdutoDao tDao = new ProdutoDao();
        if (! tDao.delete(pId))
            return new ProdutoDto(false, "Produto n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new ProdutoDto(true, "Produto removida com sucesso");
    }

    // M�todo para pesquisar todos as categorias
    public static ProdutoDto pesquisar() {
        // Obtendo a lista de categorias
        ProdutoDao tDao = new ProdutoDao();
        List<Produto> tLista = tDao.search();

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new ProdutoDto(false, "Lista de categorias vazia");

        // Retornando a lista obtida
        return new ProdutoDto(true, "Lista de categorias recuperada", tLista);
    }

    // M�todo para pesquisar por descri��o todos as categorias
    public static ProdutoDto pesquisarPorDescricao(String pNome) {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pNome == null)
            return pesquisar();

        // Obtendo a lista de categorias
        ProdutoDao tDao = new ProdutoDao();
        List<Produto> tLista = tDao.searchByNome(pNome);

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new ProdutoDto(false, "Nenhum registro encontrado com a descri��o '" + pNome + "'");

        // Retornando a lista obtida
        return new ProdutoDto(true, "Lista de categorias recuperada", tLista);
    }

}

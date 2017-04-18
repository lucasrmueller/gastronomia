package br.edu.opet.gastronomia.controller;

import java.util.List;

import br.edu.opet.gastronomia.dao.ReceitaDao;
import br.edu.opet.gastronomia.dto.ReceitaDto;
import br.edu.opet.gastronomia.model.Receita;

public class ReceitaController {
    // Método para criar uma receita
    public static ReceitaDto cadastrar(Receita pReceita) {
        if (pReceita == null)
            return new ReceitaDto(false, "Tentativa de inserir um receita nulo");

        // Chamando a camada de persistência
        ReceitaDao tDao = new ReceitaDao();

        Receita tReceita = tDao.recoveryByDescricao(pReceita.getDescricao());
        if (tReceita != null)
            return new ReceitaDto(false, "Já existe receita com essa descrição");

        tReceita = tDao.create(pReceita);

        // Verificando se houve erro de criação
        if (tReceita == null)
            return new ReceitaDto(false, "Problemas na gravação da receita");

        // Retornando o indicativo de sucesso com o objeto criado
        return new ReceitaDto(true, "receita gravada com sucesso", tReceita);
    }

    // Método para recuperar uma receita
    public static ReceitaDto recuperar(int pId) {
        // Lendo o objeto
        ReceitaDao tDao = new ReceitaDao();
        Receita tReceita = tDao.recovery(pId);

        // Verificando se houve erro de recuperação
        if (tReceita == null)
            return new ReceitaDto(false, "Receita não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new ReceitaDto(true, "receita lida com sucesso", tReceita);
    }

    // Método para atualizar uma receita
    public static ReceitaDto atualizar(Receita pReceita) {
        if (pReceita == null)
            return new ReceitaDto(false, "Tentativa de atualizar uma receita nulo");

        // Chamando a camada de persistência
        ReceitaDao tDao = new ReceitaDao();

        Receita tReceita = tDao.recoveryByDescricao(pReceita.getDescricao());
        if (tReceita != null)
            return new ReceitaDto(false, "Já existe receita com essa descrição");

        tReceita = tDao.update(pReceita);

        // Verificando se houve erro de atualização
        if (tReceita == null)
            return new ReceitaDto(false, "Receita não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new ReceitaDto(true, "Receita regravada com sucesso", tReceita);
    }

    // Método para deletar uma receita
    public static ReceitaDto remover(int pId) {
        // Removendo o receita e verificando se houve erro de remoção
        ReceitaDao tDao = new ReceitaDao();
        if (! tDao.delete(pId))
            return new ReceitaDto(false, "Receita não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new ReceitaDto(true, "Receita removida com sucesso");
    }

    // Método para pesquisar todos as receitas
    public static ReceitaDto pesquisar() {
        // Obtendo a lista de receitas
        ReceitaDao tDao = new ReceitaDao();
        List<Receita> tLista = tDao.search();

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new ReceitaDto(false, "Lista de receitas vazia");

        // Retornando a lista obtida
        return new ReceitaDto(true, "Lista de receitas recuperada", tLista);
    }

    // Método para pesquisar por descrição todos as receitas
    public static ReceitaDto pesquisarPorDescricao(String pDescricao) {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pDescricao == null)
            return pesquisar();

        // Obtendo a lista de receitas
        ReceitaDao tDao = new ReceitaDao();
        List<Receita> tLista = tDao.searchByDescricao(pDescricao);

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new ReceitaDto(false, "Nenhum registro encontrado com a descrição '" + pDescricao + "'");

        // Retornando a lista obtida
        return new ReceitaDto(true, "Lista de receitas recuperada", tLista);
    }

}

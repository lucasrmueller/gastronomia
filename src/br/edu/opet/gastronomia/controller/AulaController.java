package br.edu.opet.gastronomia.controller;

import java.util.List;

import br.edu.opet.gastronomia.dao.AulaDao;
import br.edu.opet.gastronomia.dto.AulaDto;
import br.edu.opet.gastronomia.model.Aula;

public class AulaController
{
    // Método para criar uma aula
    public static AulaDto cadastrar(Aula pAula)
    {
        if (pAula == null)
            return new AulaDto(false, "Tentativa de inserir um aula nulo");

        // Chamando a camada de persistência
        AulaDao tDao = new AulaDao();

        Aula tAula = tDao.recoveryByDescricao(pAula.getDescricao());
        if (tAula != null)
            return new AulaDto(false, "Já existe aula com essa descrição");

        tAula = tDao.create(pAula);

        // Verificando se houve erro de criação
        if (tAula == null)
            return new AulaDto(false, "Problemas na gravação da aula");

        // Retornando o indicativo de sucesso com o objeto criado
        return new AulaDto(true, "aula gravada com sucesso", tAula);
    }

    // Método para recuperar uma aula
    public static AulaDto recuperar(int pId)
    {
        // Lendo o objeto
        AulaDao tDao = new AulaDao();
        Aula tAula = tDao.recovery(pId);

        // Verificando se houve erro de recuperação
        if (tAula == null)
            return new AulaDto(false, "Aula não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new AulaDto(true, "aula lida com sucesso", tAula);
    }

    // Método para atualizar uma aula
    public static AulaDto atualizar(Aula pAula)
    {
        if (pAula == null)
            return new AulaDto(false, "Tentativa de atualizar uma aula nulo");

        // Chamando a camada de persistência
        AulaDao tDao = new AulaDao();

        Aula tAula = tDao.recoveryByDescricao(pAula.getDescricao());
        if (tAula != null)
            return new AulaDto(false, "Já existe aula com essa descrição");

        tAula = tDao.update(pAula);

        // Verificando se houve erro de atualização
        if (tAula == null)
            return new AulaDto(false, "Aula não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new AulaDto(true, "Aula regravada com sucesso", tAula);
    }

    // Método para deletar uma aula
    public static AulaDto remover(int pId)
    {
        // Removendo o aula e verificando se houve erro de remoção
        AulaDao tDao = new AulaDao();
        if (! tDao.delete(pId))
            return new AulaDto(false, "Aula não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new AulaDto(true, "Aula removida com sucesso");
    }

    // Método para pesquisar todos as aulas
    public static AulaDto pesquisar()
    {
        // Obtendo a lista de aulas
        AulaDao tDao = new AulaDao();
        List<Aula> tLista = tDao.search();

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new AulaDto(false, "Lista de aulas vazia");

        // Retornando a lista obtida
        return new AulaDto(true, "Lista de aulas recuperada", tLista);
    }

    // Método para pesquisar por descrição todos as aulas
    public static AulaDto pesquisarPorDescricao(String pDescricao)
    {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pDescricao == null)
            return pesquisar();

        // Obtendo a lista de aulas
        AulaDao tDao = new AulaDao();
        List<Aula> tLista = tDao.searchByData(pDescricao);

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new AulaDto(false, "Nenhum registro encontrado com a descrição '" + pDescricao + "'");

        // Retornando a lista obtida
        return new AulaDto(true, "Lista de aulas recuperada", tLista);
    }

}

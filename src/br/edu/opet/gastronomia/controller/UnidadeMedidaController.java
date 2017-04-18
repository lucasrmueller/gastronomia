package br.edu.opet.gastronomia.controller;

import java.util.List;

import br.edu.opet.gastronomia.dao.UnidadeMedidaDao;
import br.edu.opet.gastronomia.dto.UnidadeMedidaDto;
import br.edu.opet.gastronomia.model.UnidadeMedida;

public class UnidadeMedidaController {
    // Método para criar uma unidade de medida
    public UnidadeMedidaDto cadastrar(UnidadeMedida pUnidadeMedida) {
        if (pUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Tentativa de inserir um unidade de medida nulo");

        // Chamando a camada de persistência
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();

        UnidadeMedida tUnidadeMedida = tDao.recoveryByDescricao(pUnidadeMedida.getDescricao());
        if (tUnidadeMedida != null)
            return new UnidadeMedidaDto(false, "Já existe unidade de medida com essa descrição");

        tUnidadeMedida = tDao.create(pUnidadeMedida);

        // Verificando se houve erro de criação
        if (tUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Problemas na gravação da unidade de medida");

        // Retornando o indicativo de sucesso com o objeto criado
        return new UnidadeMedidaDto(true, "Unidade de medida gravada com sucesso", tUnidadeMedida);
    }

    // Método para recuperar uma unidade de medida
    public UnidadeMedidaDto recuperar(int pId) {
        // Lendo o objeto
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        UnidadeMedida tUnidadeMedida = tDao.recovery(pId);

        // Verificando se houve erro de recuperação
        if (tUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Unidade de medida não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new UnidadeMedidaDto(true, "Unidade de medida lida com sucesso", tUnidadeMedida);
    }

    // Método para atualizar uma unidade de medida
    public UnidadeMedidaDto atualizar(UnidadeMedida pUnidadeMedida) {
        if (pUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Tentativa de atualizar uma unidade de medida nulo");

        // Chamando a camada de persistência
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        UnidadeMedida tUnidadeMedida = tDao.update(pUnidadeMedida);

        // Verificando se houve erro de atualização
        if (tUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Unidade de medida não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new UnidadeMedidaDto(true, "Unidade de medida regravada com sucesso", tUnidadeMedida);
    }

    // Método para deletar uma unidade de medida
    public UnidadeMedidaDto remover(int pId) {
        // Removendo o unidadeMedida e verificando se houve erro de remoção
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        if (! tDao.delete(pId))
            return new UnidadeMedidaDto(false, "Unidade de medida não existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new UnidadeMedidaDto(true, "Unidade de medida removida com sucesso");
    }

    // Método para pesquisar todos as unidades de medida
    public UnidadeMedidaDto pesquisar() {
        // Obtendo a lista de unidadeMedidas
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        List<UnidadeMedida> tLista = tDao.search();

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new UnidadeMedidaDto(false, "Lista de unidades de medida vazia");

        // Retornando a lista obtida
        return new UnidadeMedidaDto(true, "Lista de unidades de medida recuperada", tLista);
    }

    // Método para pesquisar por descrição todos as unidades de medida
    public UnidadeMedidaDto pesquisarPorDescricao(String pDescricao) {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pDescricao == null)
            return pesquisar();

        // Obtendo a lista de unidadeMedidas
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        List<UnidadeMedida> tLista = tDao.searchByDescricao(pDescricao);

        // Verificando se a lista está vazia
        if (tLista.isEmpty())
            return new UnidadeMedidaDto(false, "Nenhum registro encontrado com nome '" + pDescricao + "'");

        // Retornando a lista obtida
        return new UnidadeMedidaDto(true, "Lista de unidades de medida recuperada", tLista);
    }

}

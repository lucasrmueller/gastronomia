package br.edu.opet.gastronomia.controller;

import java.util.List;

import br.edu.opet.gastronomia.dao.UnidadeMedidaDao;
import br.edu.opet.gastronomia.dto.UnidadeMedidaDto;
import br.edu.opet.gastronomia.model.UnidadeMedida;

public class UnidadeMedidaController {
    // M�todo para criar uma unidade de medida
    public UnidadeMedidaDto cadastrar(UnidadeMedida pUnidadeMedida) {
        if (pUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Tentativa de inserir um unidade de medida nulo");

        // Chamando a camada de persist�ncia
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();

        UnidadeMedida tUnidadeMedida = tDao.recoveryByDescricao(pUnidadeMedida.getDescricao());
        if (tUnidadeMedida != null)
            return new UnidadeMedidaDto(false, "J� existe unidade de medida com essa descri��o");

        tUnidadeMedida = tDao.create(pUnidadeMedida);

        // Verificando se houve erro de cria��o
        if (tUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Problemas na grava��o da unidade de medida");

        // Retornando o indicativo de sucesso com o objeto criado
        return new UnidadeMedidaDto(true, "Unidade de medida gravada com sucesso", tUnidadeMedida);
    }

    // M�todo para recuperar uma unidade de medida
    public UnidadeMedidaDto recuperar(int pId) {
        // Lendo o objeto
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        UnidadeMedida tUnidadeMedida = tDao.recovery(pId);

        // Verificando se houve erro de recupera��o
        if (tUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Unidade de medida n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto recuperado
        return new UnidadeMedidaDto(true, "Unidade de medida lida com sucesso", tUnidadeMedida);
    }

    // M�todo para atualizar uma unidade de medida
    public UnidadeMedidaDto atualizar(UnidadeMedida pUnidadeMedida) {
        if (pUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Tentativa de atualizar uma unidade de medida nulo");

        // Chamando a camada de persist�ncia
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        UnidadeMedida tUnidadeMedida = tDao.update(pUnidadeMedida);

        // Verificando se houve erro de atualiza��o
        if (tUnidadeMedida == null)
            return new UnidadeMedidaDto(false, "Unidade de medida n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto atualizado
        return new UnidadeMedidaDto(true, "Unidade de medida regravada com sucesso", tUnidadeMedida);
    }

    // M�todo para deletar uma unidade de medida
    public UnidadeMedidaDto remover(int pId) {
        // Removendo o unidadeMedida e verificando se houve erro de remo��o
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        if (! tDao.delete(pId))
            return new UnidadeMedidaDto(false, "Unidade de medida n�o existe no cadastro");

        // Retornando o indicativo de sucesso com o objeto removido
        return new UnidadeMedidaDto(true, "Unidade de medida removida com sucesso");
    }

    // M�todo para pesquisar todos as unidades de medida
    public UnidadeMedidaDto pesquisar() {
        // Obtendo a lista de unidadeMedidas
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        List<UnidadeMedida> tLista = tDao.search();

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new UnidadeMedidaDto(false, "Lista de unidades de medida vazia");

        // Retornando a lista obtida
        return new UnidadeMedidaDto(true, "Lista de unidades de medida recuperada", tLista);
    }

    // M�todo para pesquisar por descri��o todos as unidades de medida
    public UnidadeMedidaDto pesquisarPorDescricao(String pDescricao) {
        // Caso o nome de pesquisa seja nulo, retorna a lista geral
        if (pDescricao == null)
            return pesquisar();

        // Obtendo a lista de unidadeMedidas
        UnidadeMedidaDao tDao = new UnidadeMedidaDao();
        List<UnidadeMedida> tLista = tDao.searchByDescricao(pDescricao);

        // Verificando se a lista est� vazia
        if (tLista.isEmpty())
            return new UnidadeMedidaDto(false, "Nenhum registro encontrado com nome '" + pDescricao + "'");

        // Retornando a lista obtida
        return new UnidadeMedidaDto(true, "Lista de unidades de medida recuperada", tLista);
    }

}

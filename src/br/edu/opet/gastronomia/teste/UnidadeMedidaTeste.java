package br.edu.opet.gastronomia.teste;

import java.util.List;

import br.edu.opet.gastronomia.dao.UnidadeMedidaDao;
import br.edu.opet.gastronomia.model.UnidadeMedida;

public class UnidadeMedidaTeste
{
    public static void main(String[] args)
    {
        UnidadeMedida tUnidadeMedida1 = new UnidadeMedida(0, "M", "Metro");
        UnidadeMedida tUnidadeMedida2 = new UnidadeMedida(0, "g", "Grama");

        UnidadeMedidaDao tDao = new UnidadeMedidaDao();

        // Criando os objetos
        UnidadeMedida tUnidadeMedida3 = tDao.create(tUnidadeMedida1);
        UnidadeMedida tUnidadeMedida4 = tDao.create(tUnidadeMedida2);

        System.out.println();
        System.out.println("Criando os objetos");
        System.out.println(tUnidadeMedida3);
        System.out.println(tUnidadeMedida4);

        System.out.println();
        System.out.println("Unidades de medidas cadastradas");
        List<UnidadeMedida> tLista = tDao.search();
        for (UnidadeMedida tUnidadeMedida : tLista)
        {
            System.out.println(tUnidadeMedida);
        }

        System.out.println();
        System.out.println("Unidades de medidas cadastradas");
        List<UnidadeMedida> tLista1 = tDao.searchByDescricao("ma");
        for (UnidadeMedida tUnidadeMedida : tLista1)
        {
            System.out.println(tUnidadeMedida);
        }
    }
}

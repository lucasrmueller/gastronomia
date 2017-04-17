package br.edu.opet.gastronomia.teste;

import java.util.List;

import br.edu.opet.gastronomia.dao.UnidadeMedidaDao;
import br.edu.opet.gastronomia.model.UnidadeMedida;

public class UnidadeMedidaDaoTeste
{
    public static void main(String[] args)
    {
        UnidadeMedida tObjeto1 = new UnidadeMedida(0, "Mt", "Metro");
        UnidadeMedida tObjeto2 = new UnidadeMedida(0, "gr", "Gramas");
        UnidadeMedida tObjeto3 = new UnidadeMedida(0, "Ki", "Quilograma");

        // Criando o objeto de persist�ncia
        UnidadeMedidaDao tUnidadeMedidaDao = new UnidadeMedidaDao();

        // Incluindo os objetos
        System.out.println();
        System.out.println("Incluindo as Unidade de Medidas");
        tObjeto1= tUnidadeMedidaDao.create(tObjeto1);
        if (tObjeto1 != null)
            System.out.println("Inclus�o......... : " + tObjeto1);
        else
            System.out.println("Erro na inclus�o : " + tObjeto1);
        tObjeto2 = tUnidadeMedidaDao.create(tObjeto2);
        if (tObjeto2 != null)
            System.out.println("Inclus�o......... : " + tObjeto2);
        else
            System.out.println("Erro na inclus�o : " + tObjeto2);
        tObjeto3 = tUnidadeMedidaDao.create(tObjeto3);
        if (tObjeto3 != null)
            System.out.println("Inclus�o......... : " + tObjeto3);
        else
            System.out.println("Erro na inclus�o : " + tObjeto3);

        // Recuperando os objetos
        System.out.println();
        System.out.println("Recuperando os objetos");
        UnidadeMedida tObjeto4 = tUnidadeMedidaDao.recovery(tObjeto1.getId());
        if (tObjeto4 != null)
            System.out.println("Recuperado....... : " + tObjeto4);
        else
            System.out.println("Erro na recupera��o : " + tObjeto4);
        UnidadeMedida tObjeto5 = tUnidadeMedidaDao.recovery(tObjeto2.getId());
        if (tObjeto5 != null)
            System.out.println("Recuperado....... : " + tObjeto5);
        else
            System.out.println("Erro na recupera��o : " + tObjeto5);
        UnidadeMedida tObjeto6 = tUnidadeMedidaDao.recovery(tObjeto3.getId());
        if (tObjeto6 != null)
            System.out.println("Recuperado....... : " + tObjeto6);
        else
            System.out.println("Erro na recupera��o : " + tObjeto6);

        // Atualizando os objetos
        System.out.println();
        System.out.println("Atualizando os objetos");
        tObjeto1.setSimbolo("M");
        tObjeto1.setDescricao("Metro Linear");

        tObjeto2.setSimbolo("g");
        tObjeto2.setDescricao("Grama");

        tObjeto3.setSimbolo("Kg");
        tObjeto3.setDescricao("Kilograma");
        UnidadeMedida tObjeto7 = tUnidadeMedidaDao.update(tObjeto1);
        if (tObjeto7 != null)
            System.out.println("Atualizado....... : " + tObjeto7);
        else
            System.out.println("Erro na atualiza��o : " + tObjeto7);
        UnidadeMedida tObjeto8 = tUnidadeMedidaDao.update(tObjeto2);
        if (tObjeto8 != null)
            System.out.println("Atualizado....... : " + tObjeto8);
        else
            System.out.println("Erro na atualiza��o : " + tObjeto8);
        UnidadeMedida tObjeto9 = tUnidadeMedidaDao.update(tObjeto3);
        if (tObjeto9 != null)
            System.out.println("Atualizado....... : " + tObjeto9);
        else
            System.out.println("Erro na atualiza��o : " + tObjeto9);

        // Recuperando os objetos atualizados
        System.out.println();
        System.out.println("Recuperando os objetos atualizados");
        UnidadeMedida tObjeto10 = tUnidadeMedidaDao.recovery(tObjeto1.getId());
        if (tObjeto10 != null)
            System.out.println("Recuperado....... : " + tObjeto10);
        else
            System.out.println("Erro na recupera��o : " + tObjeto10);
        UnidadeMedida tObjeto11 = tUnidadeMedidaDao.recovery(tObjeto2.getId());
        if (tObjeto11 != null)
            System.out.println("Recuperado....... : " + tObjeto11);
        else
            System.out.println("Erro na recupera��o : " + tObjeto11);
        UnidadeMedida tObjeto12 = tUnidadeMedidaDao.recovery(tObjeto3.getId());
        if (tObjeto12 != null)
            System.out.println("Recuperado....... : " + tObjeto12);
        else
            System.out.println("Erro na recupera��o : " + tObjeto12);

        // Listando os objetos do cadastro
        System.out.println();
        System.out.println("Listando os objetos do cadastro");
        List<UnidadeMedida> tLista1 = tUnidadeMedidaDao.search();
        for (UnidadeMedida tObjeto : tLista1)
        {
            System.out.println("Relação.......... : " + tObjeto);
        }

        // Listando os objetos do cadastro por nome
        System.out.println();
        System.out.println("Listando os objetos do cadastro por nome");
        List<UnidadeMedida> tLista2 = tUnidadeMedidaDao.searchByDescricao("grama");
        for (UnidadeMedida tObjeto : tLista2)
        {
            System.out.println("Relação por nome. : " + tObjeto);
        }

        // Removendo os objetos
        System.out.println();
        System.out.println("Removendo os objetos");
        if (tUnidadeMedidaDao.delete(tObjeto1.getId()))
            System.out.println("Removido......... : " + tObjeto1);
        else
            System.out.println("Erro na remo��o.. : " + tObjeto1);
        if (tUnidadeMedidaDao.delete(tObjeto2.getId()))
            System.out.println("Removido......... : " + tObjeto2);
        else
            System.out.println("Erro na remo��o.. : " + tObjeto2);
        if (tUnidadeMedidaDao.delete(tObjeto3.getId()))
            System.out.println("Removido......... : " + tObjeto3);
        else
            System.out.println("Erro na remo��o.. : " + tObjeto3);

        // Verificando a remo��o dos objetos
        System.out.println();
        System.out.println("Verificando a remo��o dos objetos");
        if (tUnidadeMedidaDao.delete(tObjeto1.getId()))
            System.out.println("Removido......... : " + tObjeto1);
        else
            System.out.println("Erro na remo��o.. : " + tObjeto1);
        if (tUnidadeMedidaDao.delete(tObjeto2.getId()))
            System.out.println("Removido......... : " + tObjeto2);
        else
            System.out.println("Erro na remo��o.. : " + tObjeto2);
        if (tUnidadeMedidaDao.delete(tObjeto3.getId()))
            System.out.println("Removido......... : " + tObjeto3);
        else
            System.out.println("Erro na remo��o.. : " + tObjeto3);
    }
}

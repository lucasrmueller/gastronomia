package br.edu.opet.gastronomia.teste;

import java.math.BigDecimal;
import java.util.List;

import br.edu.opet.gastronomia.dao.ProdutoDao;
import br.edu.opet.gastronomia.dao.UnidadeMedidaDao;
import br.edu.opet.gastronomia.model.Produto;
import br.edu.opet.gastronomia.model.UnidadeMedida;

public class ProdutoDaoTeste
{
    public static void main(String[] args)
    {
        UnidadeMedida tUnidadeMedida1 = new UnidadeMedida(0, "L", "Litro");
        UnidadeMedida tUnidadeMedida2 = new UnidadeMedida(0, "Kg", "Kilograma");

        // Criando o objeto de persistência
        UnidadeMedidaDao tUnidadeMedidaDao = new UnidadeMedidaDao();
        ProdutoDao tProdutoDao = new ProdutoDao();

        // Incluindo os objetos
        System.out.println();
        System.out.println("Incluindo as Unidade de Medidas");
        tUnidadeMedida1= tUnidadeMedidaDao.create(tUnidadeMedida1);
        if (tUnidadeMedida1 != null)
            System.out.println("Inclusão......... : " + tUnidadeMedida1);
        else
            System.out.println("Erro na inclusão : " + tUnidadeMedida1);
        tUnidadeMedida2 = tUnidadeMedidaDao.create(tUnidadeMedida2);
        if (tUnidadeMedida2 != null)
            System.out.println("Inclusão......... : " + tUnidadeMedida2);
        else
            System.out.println("Erro na inclusão : " + tUnidadeMedida2);


        Produto tObjeto1 = new Produto(0, "File", new BigDecimal("345.32"), 98, null, null, tUnidadeMedida2);
        Produto tObjeto2 = new Produto(0, "AÃ§ucar", new BigDecimal("500.00"), 100, null, null, tUnidadeMedida2);
        Produto tObjeto3 = new Produto(0, "Leite", new BigDecimal("56.32"), 100, null, null, tUnidadeMedida1);

        // Incluindo os objetos
        System.out.println();
        System.out.println("Incluindo as Unidade de Medidas");
        tObjeto1= tProdutoDao.create(tObjeto1);
        if (tObjeto1 != null)
            System.out.println("Inclusão......... : " + tObjeto1);
        else
            System.out.println("Erro na inclusão : " + tObjeto1);
        tObjeto2 = tProdutoDao.create(tObjeto2);
        if (tObjeto2 != null)
            System.out.println("Inclusão......... : " + tObjeto2);
        else
            System.out.println("Erro na inclusão : " + tObjeto2);
        tObjeto3 = tProdutoDao.create(tObjeto3);
        if (tObjeto3 != null)
            System.out.println("Inclusão......... : " + tObjeto3);
        else
            System.out.println("Erro na inclusão : " + tObjeto3);

        // Recuperando os objetos
        System.out.println();
        System.out.println("Recuperando os objetos");
        Produto tObjeto4 = tProdutoDao.recovery(tObjeto1.getId());
        if (tObjeto4 != null)
            System.out.println("Recuperado....... : " + tObjeto4);
        else
            System.out.println("Erro na recuperação : " + tObjeto4);
        Produto tObjeto5 = tProdutoDao.recovery(tObjeto2.getId());
        if (tObjeto5 != null)
            System.out.println("Recuperado....... : " + tObjeto5);
        else
            System.out.println("Erro na recuperação : " + tObjeto5);
        Produto tObjeto6 = tProdutoDao.recovery(tObjeto3.getId());
        if (tObjeto6 != null)
            System.out.println("Recuperado....... : " + tObjeto6);
        else
            System.out.println("Erro na recuperação : " + tObjeto6);

        // Atualizando os objetos
        System.out.println();
        System.out.println("Atualizando os objetos");
        tObjeto1.setNome("FilÃ© de Alcatra");
        tObjeto1.setQuantidadeEstoque(new BigDecimal("34.00"));
        tObjeto1.setCusto(new BigDecimal("25.89"));

        tObjeto2.setNome("AÃ§ucar Cristal");
        tObjeto2.setQuantidadeEstoque(new BigDecimal("13.00"));
        tObjeto2.setCusto(new BigDecimal("2.00"));

        tObjeto3.setNome("Leite Desnatado");
        tObjeto3.setQuantidadeEstoque(new BigDecimal("45"));
        tObjeto3.setCusto(new BigDecimal("3.56"));
        Produto tObjeto7 = tProdutoDao.update(tObjeto1);
        if (tObjeto7 != null)
            System.out.println("Atualizado....... : " + tObjeto7);
        else
            System.out.println("Erro na atualização : " + tObjeto7);
        Produto tObjeto8 = tProdutoDao.update(tObjeto2);
        if (tObjeto8 != null)
            System.out.println("Atualizado....... : " + tObjeto8);
        else
            System.out.println("Erro na atualização : " + tObjeto8);
        Produto tObjeto9 = tProdutoDao.update(tObjeto3);
        if (tObjeto9 != null)
            System.out.println("Atualizado....... : " + tObjeto9);
        else
            System.out.println("Erro na atualização : " + tObjeto9);

        // Recuperando os objetos atualizados
        System.out.println();
        System.out.println("Recuperando os objetos atualizados");
        Produto tObjeto10 = tProdutoDao.recovery(tObjeto1.getId());
        if (tObjeto10 != null)
            System.out.println("Recuperado....... : " + tObjeto10);
        else
            System.out.println("Erro na recuperação : " + tObjeto10);
        Produto tObjeto11 = tProdutoDao.recovery(tObjeto2.getId());
        if (tObjeto11 != null)
            System.out.println("Recuperado....... : " + tObjeto11);
        else
            System.out.println("Erro na recuperação : " + tObjeto11);
        Produto tObjeto12 = tProdutoDao.recovery(tObjeto3.getId());
        if (tObjeto12 != null)
            System.out.println("Recuperado....... : " + tObjeto12);
        else
            System.out.println("Erro na recuperação : " + tObjeto12);

        // Listando os objetos do cadastro
        System.out.println();
        System.out.println("Listando os objetos do cadastro");
        List<Produto> tLista1 = tProdutoDao.search();
        for (Produto tObjeto : tLista1)
        {
            System.out.println("RelaÃ§Ã£o.......... : " + tObjeto);
        }

        // Listando os objetos do cadastro por nome
        System.out.println();
        System.out.println("Listando os objetos do cadastro por nome");
        List<Produto> tLista2 = tProdutoDao.searchByNome("FilÃ©");
        for (Produto tObjeto : tLista2)
        {
            System.out.println("RelaÃ§Ã£o por nome. : " + tObjeto);
        }

        // Removendo os objetos
        System.out.println();
        System.out.println("Removendo os objetos");
        if (tProdutoDao.delete(tObjeto1.getId()))
            System.out.println("Removido......... : " + tObjeto1);
        else
            System.out.println("Erro na remoção.. : " + tObjeto1);
        if (tProdutoDao.delete(tObjeto2.getId()))
            System.out.println("Removido......... : " + tObjeto2);
        else
            System.out.println("Erro na remoção.. : " + tObjeto2);
        if (tProdutoDao.delete(tObjeto3.getId()))
            System.out.println("Removido......... : " + tObjeto3);
        else
            System.out.println("Erro na remoção.. : " + tObjeto3);

        // Verificando a remoção dos objetos
        System.out.println();
        System.out.println("Verificando a remoção dos objetos");
        if (tProdutoDao.delete(tObjeto1.getId()))
            System.out.println("Removido......... : " + tObjeto1);
        else
            System.out.println("Erro na remoção.. : " + tObjeto1);
        if (tProdutoDao.delete(tObjeto2.getId()))
            System.out.println("Removido......... : " + tObjeto2);
        else
            System.out.println("Erro na remoção.. : " + tObjeto2);
        if (tProdutoDao.delete(tObjeto3.getId()))
            System.out.println("Removido......... : " + tObjeto3);
        else
            System.out.println("Erro na remoção.. : " + tObjeto3);




        // Removendo os objetos
        System.out.println();
        System.out.println("Removendo as unidade de medida");
        if (tUnidadeMedidaDao.delete(tUnidadeMedida1.getId()))
            System.out.println("Removido......... : " + tUnidadeMedida1);
        else
            System.out.println("Erro na remoção.. : " + tUnidadeMedida1);
        if (tUnidadeMedidaDao.delete(tUnidadeMedida2.getId()))
            System.out.println("Removido......... : " + tUnidadeMedida2);
        else
            System.out.println("Erro na remoção.. : " + tUnidadeMedida2);
    }
}

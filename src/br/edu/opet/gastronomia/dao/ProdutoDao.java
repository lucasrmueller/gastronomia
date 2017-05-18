package br.edu.opet.gastronomia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.opet.gastronomia.jdbc.Conexao;
import br.edu.opet.gastronomia.model.Produto;
import br.edu.opet.gastronomia.model.UnidadeMedida;
import br.edu.opet.gastronomia.util.ExceptionUtil;

public class ProdutoDao {
    // Atributos estaticos
    private static String sTabela1 = "PRODUTO";
    private static String sTabela2 = "PRODUTO, UNIDADE_MEDIDA";
    private static String sCampos1 = "ID, NOME, QUANTIDADE_CALORICA, APROVEITAMENTO, QUANTIDADE_ESTOQUE, CUSTO, ID_UNIDADE_MEDIDA";
    private static String sCampos2 = sCampos1.replaceAll(",", " = ?,") + " = ?";
    private static String sCampos3 = sCampos2.replaceAll("[A-Z_]+ =", "");
    private static String sCampos4 = "ID, NOME, QUANTIDADE_CALORICA, APROVEITAMENTO, QUANTIDADE_ESTOQUE, CUSTO, ID_UNIDADE_MEDIDA, SIMBOLO, DESCRICAO";
    private static String sPrimaryKey = "ID = ?";
    private static String sOrdem = "ORDER BY UPPER(NOME)";
    private static String sAssociacoes = "PRODUTO.ID = UNIDADE_MEDIDA.ID";

    // Conexao com o Oracle
    private Connection sConexao = Conexao.getConexao();

    // Metodo para criar um objeto na base de dados (INSERT)
    public Produto create(Produto pProduto) {
        // Definindo o objeto de retorno
        Produto tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "INSERT INTO " + sTabela1 +
                                 " (" + sCampos1 + ") " +
                                 " VALUES (" + sCampos3 + ")";
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID,NOME,QUANTIDADE_CALORICA,APROVEITAMENTO,QUANTIDADE_ESTOQUE,CUSTO,ID_UNIDADE_MEDIDA"});

            // Colocando os parametros recebidos no comando JDBC
            int i = 1;
            
            tComandoJDBC.setLong(i++, pProduto.getId());
            tComandoJDBC.setString(i++, pProduto.getNome());
            tComandoJDBC.setBigDecimal(i++, pProduto.getQuantidadeCalorica());
            tComandoJDBC.setInt(i++, pProduto.getAproveitamento());
            tComandoJDBC.setBigDecimal(i++, pProduto.getQuantidadeEstoque());
            tComandoJDBC.setBigDecimal(i++, pProduto.getCusto());
            tComandoJDBC.setInt(i++, pProduto.getUnidadeMedida().getId());

            // Executando o comando de gravacao
            tComandoJDBC.executeUpdate();

           // Copiando o objeto para o retorno
           tObjeto = pProduto;

           // Recuperando o codigo gerado pelo banco de dados
           ResultSet tRsChave = tComandoJDBC.getGeneratedKeys();
           tRsChave.next();
           tObjeto.setId(tRsChave.getInt(1));

            // Liberando os recursos JDBC
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de criacao do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }

    // Metodo para recuperar um objeto da base de dados (SELECT WHERE PRIMARY KEY)
    public Produto recovery(long pProduto){
        // Definindo o objeto de retorno
        Produto tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos4 +
                                 " FROM " + sTabela2 +
                                 " WHERE " + sPrimaryKey +
                                 " AND " + sAssociacoes;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setLong(1, pProduto);

            // Executando o comando e salvando o ResultSet para processar
            ResultSet tResultSet = tComandoJDBC.executeQuery();

            // Verificando se um registro foi lido
            if (tResultSet.next()) {
                // Salvando o objeto para retornar depois
                tObjeto = carregarObjeto(tResultSet);
            }

            // Liberando os recursos JDBC
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de recuperação do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }
    
 // Método para recuperar um objeto da base de dados (SELECT WHERE PRIMARY KEY)
    public Produto recoveryByNome(String pNome) {
        // Definindo o objeto de retorno
        Produto tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela2 +
                                 " WHERE UPPER(NOME) = UPPER(?)";
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setString(1, pNome);

            // Executando o comando e salvando o ResultSet para processar
            ResultSet tResultSet = tComandoJDBC.executeQuery();

            // Verificando se um registro foi lido
            if (tResultSet.next()) {
                // Salvando o objeto para retornar depois
                tObjeto = carregarObjeto(tResultSet);
            }
            
            // Liberando os recursos JDBC
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de recuperacao do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }
   

    // Método para atualizar um objeto na base de dados (UPDATE)
    public Produto update(Produto pProduto) {
        // Definindo o objeto de retorno
        Produto tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "UPDATE " + sTabela1 +
                                 " SET " + sCampos2 +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os parametros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setInt(i++, pProduto.getId());
            tComandoJDBC.setString(i++, pProduto.getNome());
            tComandoJDBC.setBigDecimal(i++, pProduto.getQuantidadeCalorica());
            tComandoJDBC.setInt(i++, pProduto.getAproveitamento());
            tComandoJDBC.setBigDecimal(i++, pProduto.getQuantidadeEstoque());
            tComandoJDBC.setBigDecimal(i++, pProduto.getCusto());
            tComandoJDBC.setInt(i++, pProduto.getUnidadeMedida().getId());
            tComandoJDBC.setInt(i++, pProduto.getId());

            // Executando o comando de regravacao e salvando o numero de registros alterados
            int tQtdeReg = tComandoJDBC.executeUpdate();

            // Verificando se um registro foi alterado
            if (tQtdeReg == 1) {
                // Copiando o objeto para o retorno
                tObjeto = pProduto;
            }

            // Liberando os recursos JDBC
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de atualização do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }

    // Metodo para remover um objeto na base de dados (DELETE)
    public boolean delete(long pProduto) {
        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "DELETE " + sTabela1 +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setLong(1, pProduto);

            // Executando o comando de remocao e salvando o numero de registros removidos
            int tQtdeReg = tComandoJDBC.executeUpdate();

            // Liberando os recursos JDBC
            tComandoJDBC.close();

            // Verificando se um registro foi removido
            if (tQtdeReg == 1) {
                // Indicado que a remocao foi efetuado com sucesso
                return true;
            }
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de remoção do objeto");
        }

        // Se chegou nesse ponto a remocao nao foi efetuada
        return false;
    }

    // Metodo para pesquisar todos os objetos da base de dados (SELECT)
    public List<Produto> search() {
        // Criando a lista de objetos vazia
        List<Produto> tLista = new ArrayList<>();

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos4 +
                            " FROM " + sTabela2 +
                            " WHERE " + sAssociacoes;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Executando o comando e salvando o ResultSet para processar
            ResultSet tResultSet = tComandoJDBC.executeQuery();

            // Enquanto houver registros, processa
            while (tResultSet.next()) {
                // Salvando o objeto retornado para adicionar na lista
                Produto tObjeto = carregarObjeto(tResultSet);

                // Adicionando o objeto na lista
                tLista.add(tObjeto);
            }

            // Liberando os recursos JDBC
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de recuperação da lista de objetos");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    // Metodo para pesquisar por descricao todos os objetos da base de dados (SELECT WHERE)
    public List<Produto> searchByNome(String pNome) {
        // Acertando o critÃ©rio de pesquisa
        String tNomePesquisa = "%" + pNome + "%";

        // Criando a lista de objetos vazia
        List<Produto> tLista = new ArrayList<>();

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos4 +
                            " FROM " + sTabela2 +
                            " WHERE UPPER(NOME) LIKE UPPER(?) " +
                            " AND " + sAssociacoes +
                            " " + sOrdem;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os parametros recebidos no comando JDBC
            tComandoJDBC.setString(1, tNomePesquisa);

            // Executando o comando e salvando o ResultSet para processar
            ResultSet tResultSet = tComandoJDBC.executeQuery();

            // Enquanto houver registros, processa
            while (tResultSet.next()) {
                // Salvando o objeto retornado para adicionar na lista
                Produto tObjeto = carregarObjeto(tResultSet);

                // Adicionando o objeto na tLista
                tLista.add(tObjeto);
            }

            // Liberando os recursos JDBC
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no método da pesquisa por nome dos objetos");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    // Metodo para processar o ResultSet e retornar um objeto
    private Produto carregarObjeto(ResultSet tResultSet) throws SQLException {
        // Criando os objetos para armazenar as informacoes lidas
        UnidadeMedida tUnidadeMedida = new UnidadeMedida();
        Produto tProduto = new Produto();

        // Recuperando as informacoes do ResultSet e colocando no objeto criado
        tUnidadeMedida.setId(tResultSet.getInt("ID_UNIDADE_MEDIDA"));
        tUnidadeMedida.setSimbolo(tResultSet.getString("SIMBOLO"));
        tUnidadeMedida.setDescricao(tResultSet.getString("DESCRICAO"));

        tProduto.setId(tResultSet.getInt("ID"));
        tProduto.setNome(tResultSet.getString("NOME"));
        tProduto.setQuantidadeCalorica(tResultSet.getBigDecimal("QUANTIDADE_CALORICA"));
        tProduto.setAproveitamento(tResultSet.getInt("APROVEITAMENTO"));
        tProduto.setQuantidadeEstoque(tResultSet.getBigDecimal("QUANTIDADE_ESTOQUE"));
        tProduto.setCusto(tResultSet.getBigDecimal("CUSTO"));
        tProduto.setUnidadeMedida(tUnidadeMedida);

        // Retornando o objeto criado
        return tProduto;
    }
}

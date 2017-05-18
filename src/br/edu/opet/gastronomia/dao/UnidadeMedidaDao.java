package br.edu.opet.gastronomia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.opet.gastronomia.jdbc.Conexao;
import br.edu.opet.gastronomia.model.UnidadeMedida;
import br.edu.opet.gastronomia.util.ExceptionUtil;

public class UnidadeMedidaDao {
    // Atributos estaticos
    private static String sTabela = "UNIDADE_MEDIDA";
    private static String sCampos1 = "ID, SIMBOLO, DESCRICAO";
    private static String sCampos2 = sCampos1.replaceAll(",", " = ?,") + " = ?";
    private static String sCampos3 = sCampos2.replaceAll("[A-Z_]+ =", "");
    private static String sPrimaryKey = "ID = ?";
    private static String sOrdem = "ORDER BY UPPER(DESCRICAO)";

    // Conexao com o Oracle
    private Connection sConexao = Conexao.getConexao();

    // Metodo para criar um objeto na base de dados (INSERT)
    public UnidadeMedida create(UnidadeMedida pUnidadeMedida) {
        // Definindo o objeto de retorno
        UnidadeMedida tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "INSERT INTO " + sTabela +
                                 " (" + sCampos1 + ") " +
                                 " VALUES (" + sCampos3+ ")";
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID,SIMBOLO,DESCRICAO"});

            // Colocando os parametros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setLong(i++, pUnidadeMedida.getId());
            tComandoJDBC.setString(i++, pUnidadeMedida.getSimbolo());
            tComandoJDBC.setString(i++, pUnidadeMedida.getDescricao());

            // Executando o comando de gravacao
            tComandoJDBC.executeUpdate();

           // Copiando o objeto para o retorno
           tObjeto = pUnidadeMedida;

           // Recuperando o código gerado pelo banco de dados
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

    // Método para recuperar um objeto da base de dados (SELECT WHERE PRIMARY KEY)
    public UnidadeMedida recovery(long pUnidadeMedida) {
        // Definindo o objeto de retorno
        UnidadeMedida tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setLong(1, pUnidadeMedida);

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

    // Método para recuperar um objeto da base de dados (SELECT WHERE PRIMARY KEY)
    public UnidadeMedida recoveryByDescricao(String pDescricao) {
        // Definindo o objeto de retorno
        UnidadeMedida tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela +
                                 " WHERE UPPER(DESCRICAO) = UPPER(?)";
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setString(1, pDescricao);

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

    // Metodo para atualizar um objeto na base de dados (UPDATE)
    public UnidadeMedida update(UnidadeMedida pUnidadeMedida) {
        // Definindo o objeto de retorno
        UnidadeMedida tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "UPDATE " + sTabela +
                                 " SET " + sCampos2 +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os parâmetros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setInt(i++, pUnidadeMedida.getId());
            tComandoJDBC.setString(i++, pUnidadeMedida.getSimbolo());
            tComandoJDBC.setString(i++, pUnidadeMedida.getDescricao());
            tComandoJDBC.setInt(i++, pUnidadeMedida.getId());

            // Executando o comando de regravação e salvando o número de registros alterados
            int tQtdeReg = tComandoJDBC.executeUpdate();

            // Verificando se um registro foi alterado
            if (tQtdeReg == 1) {
            // Copiando o objeto para o retorno
                tObjeto = pUnidadeMedida;
            }

            // Liberando os recursos JDBC
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de atualização do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }

    // Metodo para remover um objeto na base de dados (DELETE)
    public boolean delete(long pUnidadeMedida) {
        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "DELETE FROM " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setLong(1, pUnidadeMedida);

            // Executando o comando de remocao e salvando o numero de registros removidos
            int tQtdeReg = tComandoJDBC.executeUpdate();

            // Liberando os recursos JDBC
            tComandoJDBC.close();

            // Verificando se um registro foi removido
            if (tQtdeReg == 1) {
                // Indicado que a remoção foi efetuado com sucesso
                return true;
            }
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de remoção do objeto");
        }

        // Se chegou nesse ponto a remoção não foi efetuada
        return false;
    }

    // Metodo para pesquisar todos os objetos da base de dados (SELECT)
    public List<UnidadeMedida> search() {
        // Criando a lista de objetos vazia
        List<UnidadeMedida> tLista = new ArrayList<>();

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Executando o comando e salvando o ResultSet para processar
            ResultSet tResultSet = tComandoJDBC.executeQuery();

            // Enquanto houver registros, processa
            while (tResultSet.next()) {
                // Salvando o objeto retornado para adicionar na lista
                UnidadeMedida tObjeto = carregarObjeto(tResultSet);

                // Adicionando o objeto na lista
                tLista.add(tObjeto);
            }

            // Liberando os recursos JDBC
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de recuperação da lista de objetos");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    // Metodo para pesquisar por descricao todos os objetos da base de dados (SELECT WHERE)
    public List<UnidadeMedida> searchByDescricao(String pDescricao) {
        // Acertando o critï¿½rio de pesquisa
        String tDescricaoPesquisa = "%" + pDescricao + "%";

        // Criando a lista de objetos vazia
        List<UnidadeMedida> tLista = new ArrayList<>();

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela +
                                 " WHERE UPPER(DESCRICAO) LIKE UPPER(?) " + sOrdem;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os parametros recebidos no comando JDBC
            tComandoJDBC.setString(1, tDescricaoPesquisa);

            // Executando o comando e salvando o ResultSet para processar
            ResultSet tResultSet = tComandoJDBC.executeQuery();

            // Enquanto houver registros, processa
            while (tResultSet.next()) {
                // Salvando o objeto retornado para adicionar na lista
                UnidadeMedida tObjeto = carregarObjeto(tResultSet);

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
    private UnidadeMedida carregarObjeto(ResultSet tResultSet) throws SQLException {
        // Criando um novo objeto para armazenar as informacoes lidas
        UnidadeMedida tUnidadeMedida = new UnidadeMedida();

        // Recuperando as informacoes do ResultSet e colocando no objeto criado
        tUnidadeMedida.setId(tResultSet.getInt("ID"));
        tUnidadeMedida.setSimbolo(tResultSet.getString("SIMBOLO"));
        tUnidadeMedida.setDescricao(tResultSet.getString("DESCRICAO"));

        // Retornando o objeto criado
        return tUnidadeMedida;
    }
}

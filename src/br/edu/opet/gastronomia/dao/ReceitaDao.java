package br.edu.opet.gastronomia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.opet.gastronomia.jdbc.Conexao;
import br.edu.opet.gastronomia.model.Receita;
import br.edu.opet.gastronomia.util.ExceptionUtil;

public class ReceitaDao {

	// Atributos est�ticos
    private static String sTabela = "RECEITA";
    private static String sCampos1 = "ID, RECEITA, SETOR";
    private static String sCampos2 = sCampos1.replaceAll(",", " = ?,") + " = ?";
    private static String sCampos3 = sCampos2.replaceAll("[A-Z_]+ =", "");
    private static String sPrimaryKey = "ID = ?";
    private static String sOrdem = "ORDER BY UPPER(RECEITA)";

    // Conex�o com o Oracle
    private Connection sConexao = Conexao.getConexao();

    // M�todo para criar um objeto na base de dados (INSERT)
    public Receita create(Receita pReceita) {

        // Definindo o objeto de retorno
        Receita tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "INSERT INTO " + sTabela +
                                 " (" + sCampos1 + ") " +
                                 " VALUES (" + sCampos3.replaceFirst("\\?", "AULA_SEQ.NEXTVAL") + ")";
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID"});

            // Colocando os par�metros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setString(i++, pReceita.getNome());

            // Executando o comando de grava��o
            tComandoJDBC.executeUpdate();

           // Copiando o objeto para o retorno
           tObjeto = pReceita;

           // Recuperando o c�digo gerado pelo banco de dados
           ResultSet tRsChave = tComandoJDBC.getGeneratedKeys();
           tRsChave.next();
           tObjeto.setId(tRsChave.getInt(1));

            // Liberando os recursos JDBC
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de cria��o do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }

    // M�todo para recuperar um objeto da base de dados (SELECT WHERE PRIMARY KEY)
    public Receita recovery(long pIsbn) {

        // Definindo o objeto de retorno
        Receita tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o par�metro recebido no comando JDBC
            tComandoJDBC.setLong(1, pIsbn);

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
            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de recupera��o do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }

    // M�todo para atualizar um objeto na base de dados (UPDATE)
    public Receita update(Receita pReceita) {

        // Definindo o objeto de retorno
        Receita tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "UPDATE " + sTabela +
                                 " SET " + sCampos2 +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os par�metros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setInt(i++, pReceita.getId());
            tComandoJDBC.setString(i++, pReceita.getNome());
            tComandoJDBC.setInt(i++, pReceita.getId());

            // Executando o comando de regrava��o e salvando o n�mero de registros alterados
            int tQtdeReg = tComandoJDBC.executeUpdate();

            // Verificando se um registro foi alterado
            if (tQtdeReg == 1) {
                // Copiando o objeto para o retorno
                tObjeto = pReceita;
            }

            // Liberando os recursos JDBC
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de atualiza��o do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }

    // M�todo para remover um objeto na base de dados (DELETE)
    public boolean delete(long pIsbn) {
        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "DELETE " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o par�metro recebido no comando JDBC
            tComandoJDBC.setLong(1, pIsbn);

            // Executando o comando de remo��o e salvando o n�mero de registros removidos
            int tQtdeReg = tComandoJDBC.executeUpdate();

            // Liberando os recursos JDBC
            tComandoJDBC.close();

            // Verificando se um registro foi removido
            if (tQtdeReg == 1) {
                // Indicado que a remo��o foi efetuado com sucesso
                return true;
            }
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de remo��o do objeto");
        }

        // Se chegou nesse ponto a remo��o não foi efetuada
        return false;
    }

    // M�todo para pesquisar todos os objetos da base de dados (SELECT)
    public List<Receita> search() {
        // Criando a lista de objetos vazia
        List<Receita> tLista = new ArrayList<>();

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
                Receita tObjeto = carregarObjeto(tResultSet);

                // Adicionando o objeto na lista
                tLista.add(tObjeto);
            }

            // Liberando os recursos JDBC
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo de recupera��o da lista de objetos");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    // M�todo para pesquisar por descri��o todos os objetos da base de dados (SELECT WHERE)
    public List<Receita> searchByReceita(String pReceita) {
        // Acertando o crit�rio de pesquisa
        String tDescricaoPesquisa = "%" + pReceita + "%";

        // Criando a lista de objetos vazia
        List<Receita> tLista = new ArrayList<>();

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela +
                                 " WHERE ASC(DATAAULA) LIKE ASC(?) " + sOrdem;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os par�metros recebidos no comando JDBC
            tComandoJDBC.setString(1, tDescricaoPesquisa);

            // Executando o comando e salvando o ResultSet para processar
            ResultSet tResultSet = tComandoJDBC.executeQuery();

            // Enquanto houver registros, processa
            while (tResultSet.next()) {
                // Salvando o objeto retornado para adicionar na lista
                Receita tObjeto = carregarObjeto(tResultSet);

                // Adicionando o objeto na tLista
                tLista.add(tObjeto);
            }

            // Liberando os recursos JDBC
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {

            ExceptionUtil.mostrarErro(tExcept, "Erro no m�todo da pesquisa por nome dos objetos");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    // M�todo para processar o ResultSet e retornar um objeto
    private Receita carregarObjeto(ResultSet tResultSet) throws SQLException {

        // Criando um novo objeto para armazenar as informa��es lidas
        Receita tReceita = new Receita();

        // Recuperando as informa��es do ResultSet e colocando no objeto criado
        tReceita.setId(tResultSet.getInt("ID"));
        tReceita.setNome(tResultSet.getString("RECEITA"));

        // Retornando o objeto criado
        return tReceita;
    }

}

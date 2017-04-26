package br.edu.opet.gastronomia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.opet.gastronomia.jdbc.Conexao;
import br.edu.opet.gastronomia.model.Aula;
import br.edu.opet.gastronomia.util.ExceptionUtil;

public class AulaDao {

	// Atributos estáticos
    private static String sTabela = "AULA";
    private static String sCampos1 = "ID, DATA, DESCRICAO";
    private static String sCampos2 = sCampos1.replaceAll(",", " = ?,") + " = ?";
    private static String sCampos3 = sCampos2.replaceAll("[A-Z_]+ =", "");
    private static String sPrimaryKey = "ID = ?";
    private static String sOrdem = "ORDER BY DATA";

    // Conexão com o Oracle
    private Connection sConexao = Conexao.getConexao();

    // Método para criar um objeto na base de dados (INSERT)
    public Aula create(Aula pAula) {

        // Definindo o objeto de retorno
        Aula tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "INSERT INTO " + sTabela +
                                 " (" + sCampos1 + ") " +
                                 " VALUES (" + sCampos3.replaceFirst("\\?", "AULA_SEQ.NEXTVAL") + ")";
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID"});

            // Colocando os parâmetros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setDate(i++, Date.valueOf(pAula.getData()));
            tComandoJDBC.setString(i++, pAula.getDescricao());

            // Executando o comando de gravação
            tComandoJDBC.executeUpdate();

           // Copiando o objeto para o retorno
           tObjeto = pAula;

           // Recuperando o código gerado pelo banco de dados
           ResultSet tRsChave = tComandoJDBC.getGeneratedKeys();
           tRsChave.next();
           tObjeto.setId(tRsChave.getInt(1));

            // Liberando os recursos JDBC
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de criação do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }

    // Método para recuperar um objeto da base de dados (SELECT WHERE PRIMARY KEY)
    public Aula recovery(long pAula) {

        // Definindo o objeto de retorno
        Aula tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parâmetro recebido no comando JDBC
            tComandoJDBC.setLong(1, pAula);

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
    public Aula recoveryByDescricao(String pDescricao) {
        // Definindo o objeto de retorno
        Aula tObjeto = null;

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

    // Método para atualizar um objeto na base de dados (UPDATE)
    public Aula update(Aula pAula) {

        // Definindo o objeto de retorno
        Aula tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "UPDATE " + sTabela +
                                 " SET " + sCampos2 +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os parâmetros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setInt(i++, pAula.getId());
            tComandoJDBC.setDate(i++, Date.valueOf(pAula.getData()));
            tComandoJDBC.setString(i++, pAula.getDescricao());
            tComandoJDBC.setInt(i++, pAula.getId());

            // Executando o comando de regravação e salvando o número de registros alterados
            int tQtdeReg = tComandoJDBC.executeUpdate();

            // Verificando se um registro foi alterado
            if (tQtdeReg == 1) {
                // Copiando o objeto para o retorno
                tObjeto = pAula;
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

    // Método para remover um objeto na base de dados (DELETE)
    public boolean delete(long pAula) {
        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "DELETE " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parâmetro recebido no comando JDBC
            tComandoJDBC.setLong(1, pAula);

            // Executando o comando de remoção e salvando o número de registros removidos
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
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de remoção do objeto");
        }

        // Se chegou nesse ponto a remoção nÃ£o foi efetuada
        return false;
    }

    // Método para pesquisar todos os objetos da base de dados (SELECT)
    public List<Aula> search() {
        // Criando a lista de objetos vazia
        List<Aula> tLista = new ArrayList<>();

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
                Aula tObjeto = carregarObjeto(tResultSet);

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

    // Método para pesquisar por descrição todos os objetos da base de dados (SELECT WHERE)
    public List<Aula> searchByData(String pData) {
        // Acertando o critério de pesquisa
        String tDescricaoPesquisa = "%" + pData + "%";

        // Criando a lista de objetos vazia
        List<Aula> tLista = new ArrayList<>();

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela +
                                 " WHERE ASC(DATAAULA) LIKE ASC(?) " + sOrdem;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os parâmetros recebidos no comando JDBC
            tComandoJDBC.setString(1, tDescricaoPesquisa);

            // Executando o comando e salvando o ResultSet para processar
            ResultSet tResultSet = tComandoJDBC.executeQuery();

            // Enquanto houver registros, processa
            while (tResultSet.next()) {
                // Salvando o objeto retornado para adicionar na lista
                Aula tObjeto = carregarObjeto(tResultSet);

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

    // Método para processar o ResultSet e retornar um objeto
    private Aula carregarObjeto(ResultSet tResultSet) throws SQLException {

        // Criando um novo objeto para armazenar as informações lidas
        Aula tAula = new Aula();

        // Recuperando as informações do ResultSet e colocando no objeto criado
        tAula.setId(tResultSet.getInt("ID"));
        tAula.setData(tResultSet.getDate("DATA").toLocalDate());

        // Retornando o objeto criado
        return tAula;
    }

}

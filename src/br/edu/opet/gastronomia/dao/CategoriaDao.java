package br.edu.opet.gastronomia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.opet.gastronomia.jdbc.Conexao;
import br.edu.opet.gastronomia.model.Categoria;
import br.edu.opet.gastronomia.util.ExceptionUtil;

public class CategoriaDao {
    // Atributos estaticos
    private static String sTabela = "CATEGORIA";
    private static String sCampos1 = "ID, DESCRICAO";
    private static String sCampos2 = sCampos1.replaceAll(",", " = ?,") + " = ?";
    private static String sCampos3 = sCampos2.replaceAll("[A-Z_]+ =", "");
    private static String sPrimaryKey = "ID = ?";
    private static String sOrdem = "ORDER BY UPPER(DESCRICAO)";

    // Conexao com o Oracle
    private Connection sConexao = Conexao.getConexao();

    // Metodo para criar um objeto na base de dados (INSERT)
    public Categoria create(Categoria pCategoria) {
        // Definindo o objeto de retorno
        Categoria tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "INSERT INTO " + sTabela +
                                 " (" + sCampos1 + ") " +
                                 " VALUES (" + sCampos3.replaceFirst("\\?", "CATEGORIA_SEQ.NEXTVAL") + ")";
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID"});

            // Colocando os parametros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setString(i++, pCategoria.getDescricao());

            // Executando o comando de gravacao
            tComandoJDBC.executeUpdate();

           // Copiando o objeto para o retorno
           tObjeto = pCategoria;

           // Recuperando o cÃ³digo gerado pelo banco de dados
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
    public Categoria recovery(long pCategoria) {
        // Definindo o objeto de retorno
        Categoria tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setLong(1, pCategoria);

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
    public Categoria recoveryByDescricao(String pDescricao) {
        // Definindo o objeto de retorno
        Categoria tObjeto = null;

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
    public Categoria update(Categoria pCategoria) {
        // Definindo o objeto de retorno
        Categoria tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "UPDATE " + sTabela +
                                 " SET " + sCampos2 +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os parâmetros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setInt(i++, pCategoria.getId());
            tComandoJDBC.setString(i++, pCategoria.getDescricao());
            tComandoJDBC.setInt(i++, pCategoria.getId());

            // Executando o comando de regravação e salvando o nÃºmero de registros alterados
            int tQtdeReg = tComandoJDBC.executeUpdate();

            // Verificando se um registro foi alterado
            if (tQtdeReg == 1) {
            // Copiando o objeto para o retorno
                tObjeto = pCategoria;
            }

            // Liberando os recursos JDBC
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de atualizaï¿½ï¿½o do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }

    // Metodo para remover um objeto na base de dados (DELETE)
    public boolean delete(long pCategoria) {
        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "DELETE " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setLong(1, pCategoria);

            // Executando o comando de remocao e salvando o numero de registros removidos
            int tQtdeReg = tComandoJDBC.executeUpdate();

            // Liberando os recursos JDBC
            tComandoJDBC.close();

            // Verificando se um registro foi removido
            if (tQtdeReg == 1) {
                // Indicado que a remoï¿½ï¿½o foi efetuado com sucesso
                return true;
            }
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de remoï¿½ï¿½o do objeto");
        }

        // Se chegou nesse ponto a remoï¿½ï¿½o nÃ£o foi efetuada
        return false;
    }

    // Metodo para pesquisar todos os objetos da base de dados (SELECT)
    public List<Categoria> search() {
        // Criando a lista de objetos vazia
        List<Categoria> tLista = new ArrayList<>();

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
                Categoria tObjeto = carregarObjeto(tResultSet);

                // Adicionando o objeto na lista
                tLista.add(tObjeto);
            }

            // Liberando os recursos JDBC
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de recuperaï¿½ï¿½o da lista de objetos");
        }

        // Retornando a lista de objetos
        return tLista;
    }

    // Metodo para pesquisar por descricao todos os objetos da base de dados (SELECT WHERE)
    public List<Categoria> searchByDescricao(String pDescricao) {
        // Acertando o critï¿½rio de pesquisa
        String tDescricaoPesquisa = "%" + pDescricao + "%";

        // Criando a lista de objetos vazia
        List<Categoria> tLista = new ArrayList<>();

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
                Categoria tObjeto = carregarObjeto(tResultSet);

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
    private Categoria carregarObjeto(ResultSet tResultSet) throws SQLException {
        // Criando um novo objeto para armazenar as informacoes lidas
        Categoria tCategoria = new Categoria();

        // Recuperando as informacoes do ResultSet e colocando no objeto criado
        tCategoria.setId(tResultSet.getInt("ID"));
        tCategoria.setDescricao(tResultSet.getString("DESCRICAO"));

        // Retornando o objeto criado
        return tCategoria;
    }
}

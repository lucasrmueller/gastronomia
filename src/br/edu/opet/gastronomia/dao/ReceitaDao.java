package br.edu.opet.gastronomia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.opet.gastronomia.jdbc.Conexao;
import br.edu.opet.gastronomia.model.Receita;
import br.edu.opet.gastronomia.model.Categoria;
import br.edu.opet.gastronomia.util.ExceptionUtil;

public class ReceitaDao {
    // Atributos estaticos
    private static String sTabela1 = "RECEITA";
    private static String sTabela2 = "RECEITA, CATEGORIA";
    private static String sCampos1 = "ID, NOME, CLASSIFICACAO, ID_CATEGORIA";
    private static String sCampos2 = sCampos1.replaceAll(",", " = ?,") + " = ?";
    private static String sCampos3 = sCampos2.replaceAll("[A-Z_]+ =", "");
    private static String sCampos4 = "RECEITA.ID, NOME, CLASSIFICACAO, ID_CATEGORIA, DESCRICAO";
    private static String sPrimaryKey = "RECEITA.ID = ?";
    private static String sOrdem = "ORDER BY UPPER(NOME)";
    private static String sAssociacoes = "RECEITA.ID = CATEGORIA.ID";

    // Conexao com o Oracle
    private Connection sConexao = Conexao.getConexao();

    // Metodo para criar um objeto na base de dados (INSERT)
    public Receita create(Receita pReceita) {
        // Definindo o objeto de retorno
        Receita tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "INSERT INTO " + sTabela1 +
                                 " (" + sCampos1 + ") " +
                                 " VALUES (" + sCampos3.replaceFirst("\\?", "RECEITA_SEQ.NEXTVAL") + ")";
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID"});

            // Colocando os parametros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setString(i++, pReceita.getNome());
            tComandoJDBC.setString(i++, pReceita.getClassificacao());
            tComandoJDBC.setInt(i++, pReceita.getCategoria().getId());

            // Executando o comando de gravacao
            tComandoJDBC.executeUpdate();

           // Copiando o objeto para o retorno
           tObjeto = pReceita;

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
    public Receita recovery(long pReceita) {
        // Definindo o objeto de retorno
        Receita tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "SELECT " + sCampos4 +
                                 " FROM " + sTabela2 +
                                 " WHERE " + sPrimaryKey +
                                 " AND " + sAssociacoes;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setLong(1, pReceita);

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

    // Método para atualizar um objeto na base de dados (UPDATE)
    public Receita update(Receita pReceita) {
        // Definindo o objeto de retorno
        Receita tObjeto = null;

        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "UPDATE " + sTabela1 +
                                 " SET " + sCampos2 +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando os parametros recebidos no comando JDBC
            int i = 1;
            tComandoJDBC.setInt(i++, pReceita.getId());
            tComandoJDBC.setString(i++, pReceita.getNome());
            tComandoJDBC.setString(i++, pReceita.getClassificacao());
            //TODO tComandoJDBC.setInt(i++, pReceita.getCategoria().getId());
            tComandoJDBC.setInt(i++, pReceita.getId());

            // Executando o comando de regravacao e salvando o numero de registros alterados
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
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de atualização do objeto");
        }

        // Retornando o objeto
        return tObjeto;
    }

    // Metodo para remover um objeto na base de dados (DELETE)
    public boolean delete(long pReceita) {
        try {
            // Criando o comando SQL e o comando JDBC
            String tComandoSQL = "DELETE " + sTabela1 +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            // Colocando o parametro recebido no comando JDBC
            tComandoJDBC.setLong(1, pReceita);

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
    public List<Receita> search() {
        // Criando a lista de objetos vazia
        List<Receita> tLista = new ArrayList<>();

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
                Receita tObjeto = carregarObjeto(tResultSet);

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
    public List<Receita> searchByNome(String pNome) {
        // Acertando o critÃ©rio de pesquisa
        String tNomePesquisa = "%" + pNome + "%";

        // Criando a lista de objetos vazia
        List<Receita> tLista = new ArrayList<>();

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
                Receita tObjeto = carregarObjeto(tResultSet);

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
    private Receita carregarObjeto(ResultSet tResultSet) throws SQLException {
        // Criando os objetos para armazenar as informacoes lidas
        Categoria tCategoria = new Categoria();
        Receita tReceita = new Receita();

        // Recuperando as informacoes do ResultSet e colocando no objeto criado
        tCategoria.setId(tResultSet.getInt("ID_CATEGORIA"));
        tCategoria.setDescricao(tResultSet.getString("DESCRICAO"));

        tReceita.setId(tResultSet.getInt("ID"));
        tReceita.setNome(tResultSet.getString("NOME"));
        tReceita.setNome(tResultSet.getString("CLASSIFICACAO"));
        //TODO tReceita.setCategoria(tCategoria);

        // Retornando o objeto criado
        return tReceita;
    }
}

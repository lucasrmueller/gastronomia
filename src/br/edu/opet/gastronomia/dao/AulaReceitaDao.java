package br.edu.opet.gastronomia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.opet.gastronomia.jdbc.Conexao;
import br.edu.opet.gastronomia.model.AulaReceita;
import br.edu.opet.gastronomia.util.ExceptionUtil;

public class AulaReceitaDao {
	
	private static String sTabela ="AULA_RECEITA";
	private static String sCampos1 = "ID_AULA, ID_PRODUTO";
	private static String sCampos2 = sCampos1.replaceAll(",", " = ?,") + " = ?";
	private static String sCampos3 = sCampos2.replaceAll("[A-Z_]+ =", "");
	private static String sPrimaryKey = "ID = ?";
	
	private Connection sConexao = Conexao.getConexao();
	
    public AulaReceita create(AulaReceita pAulaReceita)
	
	{
		AulaReceita tObjeto = null;
		
		  try {
	           
	            String tComandoSQL = "INSERT INTO " + sTabela +
	                                 " (" + sCampos1 + ") " +
	                                 " VALUES (" + sCampos3+ ")";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID_AULA,ID_RECEITA"});

	          
	            int i = 1;
	            tComandoJDBC.setLong(i++, pAulaReceita.getId_aula());
	            tComandoJDBC.setLong(i++, pAulaReceita.getId_receita());

	           
	            tComandoJDBC.executeUpdate();
	            
	           tObjeto = pAulaReceita;
	           
	           tComandoJDBC.close();
	        }
	        catch (SQLException tExcept) {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no método de criacao do objeto");
	        }

	        // Retornando o objeto
	        return tObjeto;
		
	}

    public AulaReceita recovery(long pAulaReceita) {
        
        AulaReceita tObjeto = null;

        try {
           
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

           
            tComandoJDBC.setLong(1, pAulaReceita);

          
            ResultSet tResultSet = tComandoJDBC.executeQuery();

          
            if (tResultSet.next()) {
              
                tObjeto = carregarObjeto(tResultSet);
            }

        
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no método de recuperacao do objeto");
        }
        
        return tObjeto;
    }
    
    public AulaReceita update(AulaReceita pAulaReceita) {
        
        AulaReceita tObjeto = null;

        try {
           
            String tComandoSQL = "UPDATE " + sTabela +
                                 " SET " + sCampos2 +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            
            int i = 1;
            tComandoJDBC.setInt(i++, pAulaReceita.getId_aula());
            tComandoJDBC.setInt(i++, pAulaReceita.getId_receita());
            

            int tQtdeReg = tComandoJDBC.executeUpdate();

            
            if (tQtdeReg == 1) {
            
                tObjeto = pAulaReceita;
            }

           
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de atualização do objeto");
        }

        
        return tObjeto;
    }
    
    public boolean delete (long pAulaReceita)
    {
    	try {
            
            String tComandoSQL = "DELETE FROM " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            
            tComandoJDBC.setLong(1, pAulaReceita);

            int tQtdeReg = tComandoJDBC.executeUpdate();

            
            tComandoJDBC.close();

            
            if (tQtdeReg == 1) {
               
                return true;
            }
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de remoção do objeto");
        }

        return false;
    }
    
    public List<AulaReceita> search()

	{
		 
        List<AulaReceita> tLista = new ArrayList<>();

        try {
            
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

         
            ResultSet tResultSet = tComandoJDBC.executeQuery();

           
            while (tResultSet.next()) {
              
                AulaReceita tObjeto = carregarObjeto(tResultSet);

              
                tLista.add(tObjeto);
            }

           
            tResultSet.close();
            tComandoJDBC.close();
        }
        catch (SQLException tExcept) {
            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de recuperação da lista de objetos");
        }

        return tLista;
	}
    
    private AulaReceita carregarObjeto(ResultSet tResultSet) throws SQLException {
        
        AulaReceita tAulaReceita = new AulaReceita();
        
        tAulaReceita.setId_aula(tResultSet.getInt("ID_AULA"));
        tAulaReceita.setId_receita(tResultSet.getInt("ID_RECEITA"));

        return tAulaReceita;
    }
}

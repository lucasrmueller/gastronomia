package br.edu.opet.gastronomia.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.opet.gastronomia.jdbc.Conexao;
import br.edu.opet.gastronomia.model.ReceitaProduto;
import br.edu.opet.gastronomia.util.ExceptionUtil;

public class ReceitaProdutoDao {
	
	private static String sTabela ="RECEITA_PRODUTO";
	private static String sCampos1 = "ID_RECEITA, ID_PRODUTO";
	private static String sCampos2 = sCampos1.replaceAll(",", " = ?,") + " = ?";
	private static String sCampos3 = sCampos2.replaceAll("[A-Z_]+ =", "");
	private static String sPrimaryKey = "ID = ?";
	
	private Connection sConexao = Conexao.getConexao();
	
	public ReceitaProduto create(ReceitaProduto pReceitaProduto)
	
	{
		ReceitaProduto tObjeto = null;
		
		  try {
	           
	            String tComandoSQL = "INSERT INTO " + sTabela +
	                                 " (" + sCampos1 + ") " +
	                                 " VALUES (" + sCampos3+ ")";
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL, new String [] {"ID_RECEITA,ID_PRODUTO"});

	          
	            int i = 1;
	            tComandoJDBC.setLong(i++, pReceitaProduto.getId_receita());
	            tComandoJDBC.setLong(i++, pReceitaProduto.getId_produto());

	           
	            tComandoJDBC.executeUpdate();
	            
	           tObjeto = pReceitaProduto;
	           
	           tComandoJDBC.close();
	        }
	        catch (SQLException tExcept) {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no método de criacao do objeto");
	        }

	        // Retornando o objeto
	        return tObjeto;
		
	}
	
	public ReceitaProduto recovery(long pReceitaProduto) {
	        
	        ReceitaProduto tObjeto = null;

	        try {
	           
	            String tComandoSQL = "SELECT " + sCampos1 +
	                                 " FROM " + sTabela +
	                                 " WHERE " + sPrimaryKey;
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	           
	            tComandoJDBC.setLong(1, pReceitaProduto);

	          
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

	public ReceitaProduto update(ReceitaProduto pReceitaProduto) {
	        
	        ReceitaProduto tObjeto = null;

	        try {
	           
	            String tComandoSQL = "UPDATE " + sTabela +
	                                 " SET " + sCampos2 +
	                                 " WHERE " + sPrimaryKey;
	            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

	            
	            int i = 1;
	            tComandoJDBC.setInt(i++, pReceitaProduto.getId_receita());
	            tComandoJDBC.setInt(i++, pReceitaProduto.getId_produto());
	            

	            int tQtdeReg = tComandoJDBC.executeUpdate();

	            
	            if (tQtdeReg == 1) {
	            
	                tObjeto = pReceitaProduto;
	            }

	           
	            tComandoJDBC.close();
	        }
	        catch (SQLException tExcept) {
	            ExceptionUtil.mostrarErro(tExcept, "Erro no Método de atualização do objeto");
	        }

	        
	        return tObjeto;
	    }

    public boolean delete (long pReceitaProduto)
    {
    	try {
            
            String tComandoSQL = "DELETE FROM " + sTabela +
                                 " WHERE " + sPrimaryKey;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

            
            tComandoJDBC.setLong(1, pReceitaProduto);

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

	public List<ReceitaProduto> search()

	{
		 
        List<ReceitaProduto> tLista = new ArrayList<>();

        try {
            
            String tComandoSQL = "SELECT " + sCampos1 +
                                 " FROM " + sTabela;
            PreparedStatement tComandoJDBC = sConexao.prepareStatement(tComandoSQL);

         
            ResultSet tResultSet = tComandoJDBC.executeQuery();

           
            while (tResultSet.next()) {
              
                ReceitaProduto tObjeto = carregarObjeto(tResultSet);

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
	
	private ReceitaProduto carregarObjeto(ResultSet tResultSet) throws SQLException {
        
        ReceitaProduto tReceitaProduto = new ReceitaProduto();
        
        tReceitaProduto.setId_receita(tResultSet.getInt("ID_RECEITA"));
        tReceitaProduto.setId_produto(tResultSet.getInt("ID_PRODUTO"));

        return tReceitaProduto;
    }
}



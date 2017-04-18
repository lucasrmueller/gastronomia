package br.edu.opet.gastronomia.model;

import java.util.Comparator;

public class Receita implements Comparable<Receita> {
		private int		id;
		private String	nome;
		private String	categoria;
		private String	classificacao;
		
		public Receita() {
			super();
		}

		public Receita(int pId, String pNome, String pCategoria, String pClassificacao) {
			super();
			id = pId;
			nome = pNome;
			categoria = pCategoria;
			classificacao = pClassificacao;
		}

		public int getId() {
			return id;
		}

		public void setId(int pId) {
			id = pId;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String pNome) {
			nome = pNome;
		}

		public String getCategoria() {
			return categoria;
		}

		public void setCategoria(String pCategoria) {
			categoria = pCategoria;
		}

		public String getClassificacao() {
			return classificacao;
		}

		public void setClassificacao(String pClassificacao) {
			classificacao = pClassificacao;
		}

		@Override
		public String toString() {
			StringBuilder tBuilder = new StringBuilder();
			tBuilder.append("Receita [id=");
			tBuilder.append(id);
			tBuilder.append(", nome=");
			tBuilder.append(nome);
			tBuilder.append(", categoria=");
			tBuilder.append(categoria);
			tBuilder.append(", classificacao=");
			tBuilder.append(classificacao);
			tBuilder.append("]");
			return tBuilder.toString();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Receita other = (Receita) obj;
			if (id != other.id)
				return false;
			return true;
		}

		@Override
		public int compareTo(Receita pArg0) {
			return id = pArg0.id;
		}
		
	    public static class NomeComparator implements Comparator<Receita> {
	        @Override
	        public int compare(Receita pArg1, Receita pArg2) {
	            return pArg1.nome.compareTo(pArg2.nome);
	        }
	    }

}

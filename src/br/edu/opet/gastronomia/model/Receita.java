package br.edu.opet.gastronomia.model;

import java.util.Comparator;

public class Receita implements Comparable<Receita> {
	private int 	id;
	private String 	nome;
	private String 	setor;

	public Receita() {
		super();
	}

	public Receita(int pId, String pNomeReceita, String pSetor) {
		super();
		id = pId;
		nome = pNomeReceita;
		setor = pSetor;
	}

	public int getId() {
		return id;
	}

	public void setId(int pId) {
		if (pId <= 0)
			throw new IllegalArgumentException("Id da receita inválida");
		id = pId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String pNome) {
		if (pNome == null || pNome.isEmpty() || pNome.length() > 100)
            throw new IllegalArgumentException("Nome da receita inválido");
		nome = pNome;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String pSetor) {
		if (pSetor == null || pSetor.isEmpty() || pSetor.length() > 100)
            throw new IllegalArgumentException("Setor da receita inválido");
		setor = pSetor;
	}

	@Override
	public String toString() {
		StringBuilder tBuilder = new StringBuilder();
		tBuilder.append("Receita [");
		tBuilder.append(id);
		tBuilder.append(", ");
		tBuilder.append(nome);
		tBuilder.append(", ");
		tBuilder.append(setor);
		tBuilder.append("]");
		return tBuilder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((setor == null) ? 0 : setor.hashCode());
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (setor == null) {
			if (other.setor != null)
				return false;
		} else if (!setor.equals(other.setor))
			return false;
		return true;
	}

	@Override
	public int compareTo(Receita pArg0) {
		return Long.compare(id, pArg0.id);
	}

	 public static class NomeReceitaComparator implements Comparator<Receita> {
	        @Override
	        public int compare(Receita pArg1, Receita pArg2) {
	            return pArg1.nome.compareTo(pArg2.nome);
	        }

	    }

	 public static class SetorComparator implements Comparator<Receita> {
	        @Override
	        public int compare(Receita pArg1, Receita pArg2) {
	            return pArg1.setor.compareTo(pArg2.setor);
	        }

	 }

}

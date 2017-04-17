package br.edu.opet.gastronomia.model;

import java.util.Comparator;

public class Categoria implements Comparable<Categoria> {
    private int    id;
    private String descricao;

    public Categoria() {
        super();
    }

    public Categoria(int pId, String pDescricao) {
        super();
        id = pId;
        descricao = pDescricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        if (pId <= 0)
            throw new IllegalArgumentException("Id da unidade de medida inválido");
        id = pId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String pDescricao) {
        if (pDescricao == null || pDescricao.isEmpty() || pDescricao.length() > 50)
            throw new IllegalArgumentException("Descrição da unidade de medida inválido");
        descricao = pDescricao;
    }

    @Override
    public String toString() {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append("UnidadeMedida [");
        tBuilder.append(id);
        tBuilder.append(", ");
        tBuilder.append(descricao);
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
        Categoria other = (Categoria) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int compareTo(Categoria pArg0) {
        return id = pArg0.id;
    }

    public static class DescricaoComparator implements Comparator<Categoria> {
        @Override
        public int compare(Categoria pArg1, Categoria pArg2)
        {
            return pArg1.descricao.compareTo(pArg2.descricao);
        }
    }
}

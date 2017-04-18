package br.edu.opet.gastronomia.model;

import java.util.Comparator;

public class UnidadeMedida implements Comparable<UnidadeMedida> {
    private int    id;
    private String simbolo;
    private String descricao;

    public UnidadeMedida() {
        super();
    }

    public UnidadeMedida(int pId, String pSimbolo, String pDescricao) {
        super();
        id = pId;
        simbolo = pSimbolo;
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

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String pSimbolo) {
        if (pSimbolo == null || pSimbolo.isEmpty() || pSimbolo.length() > 3)
            throw new IllegalArgumentException("Símbolo da unidade de medida inválido");
        simbolo = pSimbolo;
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
        tBuilder.append(simbolo);
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
        UnidadeMedida other = (UnidadeMedida) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int compareTo(UnidadeMedida pArg0) {
        return id = pArg0.id;
    }

    public static class DescricaoComparator implements Comparator<UnidadeMedida> {
        @Override
        public int compare(UnidadeMedida pArg1, UnidadeMedida pArg2) {
            return pArg1.descricao.compareTo(pArg2.descricao);
        }
    }
}

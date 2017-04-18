package br.edu.opet.gastronomia.model;

import java.math.BigDecimal;
import java.util.Comparator;

public class Produto implements Comparable<Produto> {
    private int           id;
    private String        nome;
    private BigDecimal    quantidadeCalorica;
    private int           aproveitamento;
    private BigDecimal    quantidadeEstoque;
    private BigDecimal    custo;
    private UnidadeMedida unidadeMedida;

    public Produto() {
        super();

    }

    public Produto(int pId, String pNome, BigDecimal pQuantidadeCalorica, int pAproveitamento, BigDecimal pQuantidadeEstoque, BigDecimal pCusto,
                    UnidadeMedida pUnidadeMedida) {
        super();
        id = pId;
        nome = pNome;
        quantidadeCalorica = pQuantidadeCalorica;
        aproveitamento = pAproveitamento;
        quantidadeEstoque = pQuantidadeEstoque;
        custo = pCusto;
        unidadeMedida = pUnidadeMedida;
    }

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        if (pId <= 0)
            throw new IllegalArgumentException("Id do produto inválido");
        id = pId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String pNome) {
        if (pNome == null || pNome.isEmpty() || pNome.length() > 100)
            throw new IllegalArgumentException("Simbolo do produto inválido");
        nome = pNome;
    }

    public BigDecimal getQuantidadeCalorica() {
        return quantidadeCalorica;
    }

    public void setQuantidadeCalorica(BigDecimal pQuantidadeCalorica) {
        if (pQuantidadeCalorica.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Quantidade calorica do produto inválido");
        quantidadeCalorica = pQuantidadeCalorica;
    }

    public int getAproveitamento() {
        return aproveitamento;
    }

    public void setAproveitamento(int pAproveitamento) {
        if (pAproveitamento <= 0)
            throw new IllegalArgumentException("Aproveitamento do produto inválido");
        aproveitamento = pAproveitamento;
    }

    public BigDecimal getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(BigDecimal pQuantidadeEstoque) {
        if (pQuantidadeEstoque != null && pQuantidadeEstoque.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Quantidade do estoque do produto inválido");
        quantidadeEstoque = pQuantidadeEstoque;
    }

    public BigDecimal getCusto() {
        return custo;
    }

    public void setCusto(BigDecimal pCusto) {
        if (pCusto != null && pCusto.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Custo do produto inválido");
        custo = pCusto;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida pUnidadeMedida) {
        if (pUnidadeMedida == null)
            throw new IllegalArgumentException("Unidade de medida do produto inválido");
        unidadeMedida = pUnidadeMedida;
    }

    @Override
    public String toString() {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(id);
        tBuilder.append(", ");
        tBuilder.append(nome);
        tBuilder.append(", ");
        tBuilder.append(quantidadeCalorica);
        tBuilder.append(", ");
        tBuilder.append(aproveitamento);
        tBuilder.append(", ");
        tBuilder.append(quantidadeEstoque);
        tBuilder.append(", ");
        tBuilder.append(custo);
        tBuilder.append(", ");
        tBuilder.append(unidadeMedida);
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
        Produto other = (Produto) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int compareTo(Produto pArg0) {
    	return id = pArg0.id;
    }

    public static class NomeComparator implements Comparator<Produto> {
        @Override
        public int compare(Produto pArg1, Produto pArg2) {
            return pArg1.nome.compareTo(pArg2.nome);
        }
    }

}

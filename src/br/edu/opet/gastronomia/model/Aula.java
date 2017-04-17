package br.edu.opet.gastronomia.model;

import java.time.LocalDate;
import java.util.Comparator;

public class Aula implements Comparable<Aula>
{

    private int       id;
    private LocalDate data;
    private String    descricao;

    public Aula()
    {
        super();
    }


    public Aula(int pId, LocalDate pData, String pDescricao)
    {
        super();
        id = pId;
        data = pData;
        descricao = pDescricao;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int pId)
    {
        id = pId;
    }

    public LocalDate getData()
    {
        return data;
    }

    public void setData(LocalDate pData)
    {
        data = pData;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String pDescricao)
    {
        descricao = pDescricao;
    }

    @Override
    public String toString()
    {
        StringBuilder tBuilder = new StringBuilder();
        tBuilder.append(" [");
        tBuilder.append(id);
        tBuilder.append(", ");
        tBuilder.append(data);
        tBuilder.append(", ");
        tBuilder.append(descricao);
        tBuilder.append("]");
        return tBuilder.toString();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Aula other = (Aula) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public int compareTo(Aula pArg0)
    {
        return Long.compare(id, pArg0.id);
    }

    public static class DataComparator implements Comparator<Aula>
    {
        @Override
        public int compare(Aula pArg1, Aula pArg2)
        {
            return pArg1.data.compareTo(pArg2.data);
        }

    }

}

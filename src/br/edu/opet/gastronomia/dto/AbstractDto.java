package br.edu.opet.gastronomia.dto;

import java.util.List;

public abstract class AbstractDto<T> {
	
    private boolean ok;
    private String  mensagem;
    private T       objeto;
    private List<T> lista;

    public AbstractDto(boolean pOk, String pMensagem) {
        super();
        ok = pOk;
        mensagem = pMensagem;
    }

    public AbstractDto(boolean pOk, String pMensagem, T pObjeto) {
        super();
        ok = pOk;
        mensagem = pMensagem;
        objeto = pObjeto;
    }

    public AbstractDto(boolean pOk, String pMensagem, List<T> pLista) {
        super();
        ok = pOk;
        mensagem = pMensagem;
        lista = pLista;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean pOk) {
        ok = pOk;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String pMensagem) {
        mensagem = pMensagem;
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T pObjeto) {
        objeto = pObjeto;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> pLista) {
        lista = pLista;
    }

}

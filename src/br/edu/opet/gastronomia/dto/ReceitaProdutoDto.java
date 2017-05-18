package br.edu.opet.gastronomia.dto;

import java.util.List;


import br.edu.opet.gastronomia.model.ReceitaProduto;

public class ReceitaProdutoDto extends AbstractDto<ReceitaProduto> {

	public ReceitaProdutoDto(boolean pOk, String pMensagem, ReceitaProduto pObjeto) {
        super(pOk, pMensagem, pObjeto);
    }

    public ReceitaProdutoDto(boolean pOk, String pMensagem, List<ReceitaProduto> pLista) {
        super(pOk, pMensagem, pLista);
    }

    public ReceitaProdutoDto(boolean pOk, String pMensagem) {
        super(pOk, pMensagem);
    }
}

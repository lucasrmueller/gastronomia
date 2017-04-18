package br.edu.opet.gastronomia.dto;

import java.util.List;
import br.edu.opet.gastronomia.model.Produto;

public class ProdutoDto extends AbstractDto<Produto> {

    public ProdutoDto(boolean pOk, String pMensagem, Produto pObjeto) {
        super(pOk, pMensagem, pObjeto);
    }

    public ProdutoDto(boolean pOk, String pMensagem, List<Produto> pLista) {
        super(pOk, pMensagem, pLista);
    }

    public ProdutoDto(boolean pOk, String pMensagem) {
        super(pOk, pMensagem);
    }

}

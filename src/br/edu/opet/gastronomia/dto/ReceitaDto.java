package br.edu.opet.gastronomia.dto;

import java.util.List;
import br.edu.opet.gastronomia.model.Receita;

public class ReceitaDto extends AbstractDto<Receita> {

    public ReceitaDto(boolean pOk, String pMensagem, Receita pObjeto) {
        super(pOk, pMensagem, pObjeto);
    }

    public ReceitaDto(boolean pOk, String pMensagem, List<Receita> pLista) {
        super(pOk, pMensagem, pLista);
    }

    public ReceitaDto(boolean pOk, String pMensagem) {
        super(pOk, pMensagem);
    }

}

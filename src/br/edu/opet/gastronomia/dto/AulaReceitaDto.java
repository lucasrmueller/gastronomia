package br.edu.opet.gastronomia.dto;

import java.util.List;

import br.edu.opet.gastronomia.model.AulaReceita;


public class AulaReceitaDto extends AbstractDto<AulaReceita> {

	public AulaReceitaDto(boolean pOk, String pMensagem, AulaReceita pObjeto) {
        super(pOk, pMensagem, pObjeto);
    }

    public AulaReceitaDto(boolean pOk, String pMensagem, List<AulaReceita> pLista) {
        super(pOk, pMensagem, pLista);
    }

    public AulaReceitaDto(boolean pOk, String pMensagem) {
        super(pOk, pMensagem);
    }
}

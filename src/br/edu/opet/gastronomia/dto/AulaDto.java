package br.edu.opet.gastronomia.dto;

import java.util.List;
import br.edu.opet.gastronomia.model.Aula;

public class AulaDto extends AbstractDto<Aula> {

    public AulaDto(boolean pOk, String pMensagem, Aula pObjeto) {
        super(pOk, pMensagem, pObjeto);
    }

    public AulaDto(boolean pOk, String pMensagem, List<Aula> pLista) {
        super(pOk, pMensagem, pLista);
    }

    public AulaDto(boolean pOk, String pMensagem) {
        super(pOk, pMensagem);
    }

}

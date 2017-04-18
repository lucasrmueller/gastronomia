package br.edu.opet.gastronomia.dto;

import java.util.List;
import br.edu.opet.gastronomia.model.Categoria;

public class CategoriaDto extends AbstractDto<Categoria> {

    public CategoriaDto(boolean pOk, String pMensagem, Categoria pObjeto) {
        super(pOk, pMensagem, pObjeto);
    }

    public CategoriaDto(boolean pOk, String pMensagem, List<Categoria> pLista) {
        super(pOk, pMensagem, pLista);
    }

    public CategoriaDto(boolean pOk, String pMensagem) {
        super(pOk, pMensagem);
    }

}

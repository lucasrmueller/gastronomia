package br.edu.opet.gastronomia.dto;

import java.util.List;

import br.edu.opet.gastronomia.model.UnidadeMedida;

public class UnidadeMedidaDto extends AbstractDto<UnidadeMedida>
{
    public UnidadeMedidaDto(boolean pOk, String pMensagem, List<UnidadeMedida> pLista)
    {
        super(pOk, pMensagem, pLista);
        // TODO Auto-generated constructor stub
    }

    public UnidadeMedidaDto(boolean pOk, String pMensagem, UnidadeMedida pObjeto)
    {
        super(pOk, pMensagem, pObjeto);
        // TODO Auto-generated constructor stub
    }

    public UnidadeMedidaDto(boolean pOk, String pMensagem)
    {
        super(pOk, pMensagem);
        // TODO Auto-generated constructor stub
    }
}

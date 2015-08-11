package service;

import model.TipoDenuncia;

public interface TipoDenunciaService extends SuperService<TipoDenuncia, Integer> {
	
	public TipoDenuncia consultarDescricao (String tipoDenuncia);
	
}

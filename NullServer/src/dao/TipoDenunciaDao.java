package dao;

import java.util.List;

import model.TipoDenuncia;

public interface TipoDenunciaDao extends SuperDao<TipoDenuncia, Integer> {
	public TipoDenuncia consultarDescricao (String tipoDenuncia);
	
}

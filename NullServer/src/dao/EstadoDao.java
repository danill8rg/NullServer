package dao;

import model.Estado;


public interface EstadoDao extends SuperDao<Estado, Integer> {
	public Estado consultarNome(String nomeEstado);
}

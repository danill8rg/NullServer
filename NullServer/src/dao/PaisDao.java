package dao;

import model.Pais;


public interface PaisDao extends SuperDao<Pais, Integer> {
	
	public Pais consultarNome(String nomePais);
			
}

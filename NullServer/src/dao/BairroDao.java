package dao;

import model.Bairro;


public interface BairroDao extends SuperDao<Bairro, Integer> {
	
	public Bairro consultarNome(String nomeBairro);
}

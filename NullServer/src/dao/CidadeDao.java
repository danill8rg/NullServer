package dao;

import model.Cidade;


public interface CidadeDao extends SuperDao<Cidade, Integer> {
	public Cidade consultaNome(String nomeCidade);
}

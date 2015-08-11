package service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import service.SuperService;
import dao.SuperDao;

public class SuperServiceImpl<T, ID extends Serializable> implements SuperService<T, ID> {
	
	protected SuperDao<T, ID> dao;

	@Override
	public T gravar(T object) {
		return (T) dao.gravar(object);
	}

	@Override
	public T atualizar(T object) {
		return (T) dao.atualizar(object);
	}

	@Override
	public void excluir(T object) {
		dao.excluir(object);
	}

	@Override
	public ArrayList<T> consultarTodos(T object) {
		return new ArrayList<T>(dao.consultarTodos(object));
	}

	@Override
	public T consultarObjetoId(int entityID) {
		return dao.consultarObjetoId(entityID);
	}

	public SuperDao<T, ID> getDao() {
		return dao;
	}

	public void setDao(SuperDao<T, ID> dao) {
		this.dao = dao;
	}

	
}

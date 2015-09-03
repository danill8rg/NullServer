package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.ViewMensagem;

public interface SuperService<T, ID extends Serializable> {

	public T gravar(T object);
	
	public T atualizar(T object);

	public void excluir(T object);

	public ArrayList<T> consultarTodos(T object);

	public T consultarObjetoId(int entityID);
}

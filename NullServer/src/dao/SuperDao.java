package dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface SuperDao<T, ID extends Serializable> {
	public Class<T> getObjectClass();

	public T gravar(T object);
	
	public T atualizar(T object);

	public void excluir(T object);

	public List<T> consultarTodos(T object);

	public List<T> consultarPorNamedQueryEParametros(String queryName,  Map<String, Object> parameters);
	
	public T consultarObjeto(String queryName, Map<String, Object> parameters);

	public T consultarObjetoId(int entityID);

}
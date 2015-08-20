package dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import dao.SuperDao;


public class SuperDaoImpl<T, ID extends Serializable> implements SuperDao<T, ID> {

	private final Class<T> oClass;// object class

	public Class<T> getObjectClass() {
		return this.oClass;
	}

	@SuppressWarnings("unchecked")
	public SuperDaoImpl() {
		this.oClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void excluir(T object) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(object);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(entityManager.isOpen()){
				entityManager.getTransaction().rollback();
			}
		} finally {
			if(entityManager.isOpen()){
				entityManager.close();
			}
		}
	}

	@SuppressWarnings("finally")
	public T gravar(T object) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(object);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(entityManager.isOpen()){
				entityManager.getTransaction().rollback();
			}
		} finally {
			if(entityManager.isOpen()){
				entityManager.close();
			}
			return object;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public T atualizar(T object) {
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(object);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(entityManager.isOpen()){
				entityManager.getTransaction().rollback();
			}
		} finally {
			if(entityManager.isOpen()){
				entityManager.close();
			}
			return object;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public List<T> consultarTodos(T object) {
		List<T> lista = null;
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
	        cq.select(cq.from(object.getClass()));
	        lista = entityManager.createQuery(cq).getResultList();
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			e.printStackTrace();
			if(entityManager.isOpen()){
				entityManager.getTransaction().rollback();
			}
			return lista;
		} finally {
			if(entityManager.isOpen()){
				entityManager.close();
			}
			return lista;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public List<T> consultarPorNamedQueryEParametros(String queryName,
			Map<String, Object> parameters) {
		List<T> lista = null;
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createNamedQuery(queryName);
			
			 // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
            	query = populateQueryParameters(query, parameters);
            }
 
            lista = (List<T>) query.getResultList();
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(entityManager.isOpen()){
				entityManager.getTransaction().rollback();
			}
		} finally {
			if(entityManager.isOpen()){
				entityManager.close();
			}
			return lista;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public T consultarObjeto(String queryName, Map<String, Object> parameters) {
		T result = null;
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createNamedQuery(queryName);
			
			 // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
            	query = populateQueryParameters(query, parameters);
            }
            List<T> lista = null;
            lista = (List<T>) query.getResultList();
            if(lista.isEmpty()){
            	result = null;
            }else{
            	result = lista.get(0);
            }
//            result = (T) query.getSingleResult();

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(entityManager.isOpen()){
				entityManager.getTransaction().rollback();
			}
		} finally {
			if(entityManager.isOpen()){
				entityManager.close();
			}
			return result;
		}
	}

	@SuppressWarnings("finally")
	@Override
	public T consultarObjetoId(int entityID) {
		T result = null;
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
            result = (T) entityManager.find(getObjectClass(), entityID);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(entityManager.isOpen()){
				entityManager.getTransaction().rollback();
			}
		} finally {
			if(entityManager.isOpen()){
				entityManager.close();
			}
			return result;
		}
	}

	protected Query populateQueryParameters(Query query, Map<String, Object> parameters) {
        for (Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query;
    }

}

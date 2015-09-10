package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.ViewMapDenuncia;
import dao.ViewMapDenunciaDao;

public class ViewMapDenunciaDaoImpl extends SuperDaoImpl<ViewMapDenuncia, Integer> implements ViewMapDenunciaDao {

	@Override
	public List<ViewMapDenuncia> consultarTodosWeb() {
		List<ViewMapDenuncia> listaDenuncias = null;
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createNamedQuery("ViewMapDenuncia.findAll");
			query.setHint("javax.persistence.cache.storeMode", "REFRESH");
			
			listaDenuncias = (List<ViewMapDenuncia>) query.getResultList();
            
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
			return (listaDenuncias);
		}
	}

}

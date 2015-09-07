package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.ImagemDenuncia;
import model.Usuario;
import dao.ImagemDenunciaDao;

public class ImagemDenunciaDaoImpl extends SuperDaoImpl<ImagemDenuncia, Integer> implements ImagemDenunciaDao {


	@Override
	public List<ImagemDenuncia> consultarPorIddenuncia(int idDenuncia) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDenuncia", idDenuncia); 
        return super.consultarPorNamedQueryEParametros("ImagemDenuncia.findByIdDenuncia", parameters);
	}

	@Override
	public String proximoIdImagem() {
		int result = 0;
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			
//			Query query = entityManager.createNativeQuery("SELECT id_imagem_denuncia FROM tb_imagem_denuncia order by id_imagem_denuncia desc limit 1");
//			result = (int)query.getSingleResult();
			
			Query query = entityManager.createNamedQuery("ImagemDenuncia.findUltimo");
			
			int aux = query.getMaxResults();
            List<ImagemDenuncia> lista = (List<ImagemDenuncia>) query.setMaxResults(1).getResultList();
            if(lista.isEmpty()){
            	result = 0;
            }else{
            	ImagemDenuncia image = lista.get(0);
            	result = image.getIdImagemDenuncia();
            }

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
			return ( "" + (result + 1));
		}
	}

	@Override
	public String caminhoPrimeiraImagem(int idDenuncia) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDenuncia", idDenuncia); 
        ImagemDenuncia image = consultarObjeto("ImagemDenuncia.findByIdDenuncia", parameters);
        if( image == null){
        	return null;
        }
        return image.getCaminho();
	}

}

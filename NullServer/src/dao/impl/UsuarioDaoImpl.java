package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.ImagemDenuncia;
import model.Usuario;
import dao.UsuarioDao;

public class UsuarioDaoImpl extends SuperDaoImpl<Usuario, Integer> implements UsuarioDao{

	@Override
	public List<Usuario> consultarNome(String valorConsulta) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nome", valorConsulta);     
        return super.consultarPorNamedQueryEParametros("SELECT u FROM Usuario u WHERE lower(u.nome) LIKE lower(:nome)", parameters);
	}

	@Override
	public List<Usuario> consultarEmail(String valorConsulta) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", valorConsulta);     
        return super.consultarPorNamedQueryEParametros("SELECT u FROM Usuario u WHERE lower(u.email) LIKE lower(:email)", parameters);
	}

	@Override
	public Usuario logar(String email, String Senha) {
		try{
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email); 
        parameters.put("senha", Senha);  
        return  super.consultarObjeto("Usuario.logarEmail", parameters);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Usuario> consultarAtivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validarEmail(String email) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email); 
        List<Usuario> user = super.consultarPorNamedQueryEParametros("Usuario.findByEmail", parameters);
        if(user.isEmpty()){
        	return true;
        }
		return false;
	}

	@Override
	public Usuario consultarPorEmail(String email) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email); 
        List<Usuario> listUser = super.consultarPorNamedQueryEParametros("Usuario.findByEmail", parameters);
        if(listUser.isEmpty()){
        	return null;
        }
		return listUser.get(0);
	}

	@Override
	public String proximoIdUsuario() {
int result = 0;
		
		EntityManager entityManager = JpaUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			
			Query query = entityManager.createNamedQuery("Usuario.findUltimo");
			
			int aux = query.getMaxResults();
            List<Usuario> lista = (List<Usuario>) query.setMaxResults(1).getResultList();
            if(lista.isEmpty()){
            	result = 0;
            }else{
            	Usuario image = lista.get(0);
            	result = image.getIdUsuario();
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
}

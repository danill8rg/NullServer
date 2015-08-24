package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}

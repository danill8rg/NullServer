package dao.impl;

import java.util.HashMap;
import java.util.Map;

import model.Estado;
import dao.EstadoDao;

public class EstadoDaoImpl extends SuperDaoImpl<Estado, Integer> implements EstadoDao {

	@Override
	public Estado consultarNome(String nomeEstado) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nome", nomeEstado);
        Estado estado = super.consultarObjeto("Estado.findByNome", parameters);
        if(estado != null ){
        	return estado;
        }
        return new Estado();
	}

}

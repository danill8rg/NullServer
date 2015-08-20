package dao.impl;

import java.util.HashMap;
import java.util.Map;

import model.Pais;
import dao.PaisDao;

public class PaisDaoImpl extends SuperDaoImpl<Pais, Integer> implements PaisDao {

	@Override
	public Pais consultarNome(String nomePais) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nome", nomePais);
        Pais pais = super.consultarObjeto("Pais.findByNome", parameters);
        if(pais != null ){
        	return pais;
        }
        return new Pais();
   }

}

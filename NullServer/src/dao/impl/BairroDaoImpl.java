package dao.impl;

import java.util.HashMap;
import java.util.Map;

import model.Bairro;
import dao.BairroDao;

public class BairroDaoImpl extends SuperDaoImpl<Bairro, Integer> implements BairroDao {

	@Override
	public Bairro consultarNome(String nomeBairro) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nome", nomeBairro);
        Bairro bairro = super.consultarObjeto("Bairro.findByNome", parameters);
        if(bairro != null ){
        	return bairro;
        }
        return new Bairro();
	}

}

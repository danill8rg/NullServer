package dao.impl;

import java.util.HashMap;
import java.util.Map;

import model.Cidade;
import dao.CidadeDao;

public class CidadeDaoImpl extends SuperDaoImpl<Cidade, Integer> implements CidadeDao {

	@Override
	public Cidade consultaNome(String nomeCidade) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("nome", nomeCidade);
        Cidade cidade = super.consultarObjeto("Cidade.findByNome", parameters);
        if(cidade != null ){
        	return cidade;
        }
        return new Cidade();
	}

}

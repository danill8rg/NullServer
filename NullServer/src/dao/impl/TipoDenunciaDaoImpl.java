package dao.impl;

import model.TipoDenuncia;

import java.util.HashMap;
import java.util.Map;

import dao.TipoDenunciaDao;

public class TipoDenunciaDaoImpl extends SuperDaoImpl<TipoDenuncia, Integer> implements TipoDenunciaDao  {

	@Override
	public TipoDenuncia consultarDescricao(String tipoDenuncia) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("descricao", tipoDenuncia); 
        TipoDenuncia tipo =  super.consultarObjeto("TipoDenuncia.findByDescricao", parameters);
        if(tipo != null){
        	return tipo;
        }
        return new TipoDenuncia();
	}

}

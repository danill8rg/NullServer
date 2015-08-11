package dao.impl;

import java.util.HashMap;
import java.util.Map;

import model.Denuncia;
import model.LocalDenuncia;
import dao.LocalDenunciaDao;

public class LocalDenunciaDaoImpl extends SuperDaoImpl<LocalDenuncia, Integer> implements LocalDenunciaDao {

	@Override
	public LocalDenuncia consultarIdDenuncia(Denuncia denuncia) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDenuncia", denuncia.getIdDenuncia()); 
        return super.consultarObjeto("SELECT l FROM LocalDenuncia l join l.denuncia WHERE l.denuncia.idDenuncia = :idDenuncia", parameters);
	}

}

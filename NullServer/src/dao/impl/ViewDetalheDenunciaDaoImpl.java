package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ViewDetalheDenuncia;
import dao.ViewDetalheDenunciaDao;

public class ViewDetalheDenunciaDaoImpl extends SuperDaoImpl<ViewDetalheDenuncia, Integer> implements ViewDetalheDenunciaDao {

	@Override
	public List<ViewDetalheDenuncia> consultarPorUsuario(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDenunciante", id);     
        return super.consultarPorNamedQueryEParametros("ViewDetalheDenuncia.findByIdDenunciante", parameters);
	}

}

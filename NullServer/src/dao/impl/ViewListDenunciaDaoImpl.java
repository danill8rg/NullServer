package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ViewListDenuncia;
import dao.ViewListDenunciaDao;

public class ViewListDenunciaDaoImpl extends SuperDaoImpl<ViewListDenuncia, Integer> implements ViewListDenunciaDao {

	@Override
	public ArrayList<ViewListDenuncia> consultarPorUsuario(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idUsuario", id);     
        List<ViewListDenuncia> lista = super.consultarPorNamedQueryEParametros("ViewListDenuncia.findByIdUsuario", parameters);
        if(lista != null && !lista.isEmpty() ){
        	return new ArrayList<ViewListDenuncia>(lista);
        }
        return new ArrayList<ViewListDenuncia>();
	}

}

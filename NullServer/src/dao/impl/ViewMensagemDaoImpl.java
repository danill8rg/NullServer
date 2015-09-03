package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ViewMensagem;
import dao.ViewMensagemDao;

public class ViewMensagemDaoImpl extends SuperDaoImpl<ViewMensagem, Integer> implements ViewMensagemDao {

	@Override
	public List<ViewMensagem> consultarPorDenuncia(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDenuncia", id);     
        return super.consultarPorNamedQueryEParametros("ViewMensagem.findByIdDenuncia", parameters);
	}

}

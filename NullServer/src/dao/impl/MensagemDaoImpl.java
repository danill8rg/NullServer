package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Denuncia;
import model.Mensagem;
import model.Usuario;
import dao.MensagemDao;

public class MensagemDaoImpl extends SuperDaoImpl<Mensagem, Integer> implements MensagemDao{

	
	public List<Mensagem> consultarDenuncia(Denuncia denuncia) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDenuncia", denuncia.getIdDenuncia()); 
        return super.consultarPorNamedQueryEParametros("SELECT m FROM Mensagem m join m.denuncia WHERE m.denuncia.idDenuncia = :idDenuncia", parameters);
	}

	
	public List<Mensagem> consultarUsuario(Usuario usuario) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idUsuario", usuario.getIdUsuario()); 
        return super.consultarPorNamedQueryEParametros("SELECT m FROM Mensagem m join m.usuario WHERE m.usuario.idUsuario = :idUsuario", parameters);
	}


	@Override
	public List<Mensagem> consultarPorDenuncia(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDenuncia", id); 
        return super.consultarPorNamedQueryEParametros("Mensagem.findByDenuncia", parameters);
   	}

}

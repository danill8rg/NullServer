package dao;

import java.util.List;

import model.Mensagem;

public interface MensagemDao extends SuperDao<Mensagem, Integer> {

	List<Mensagem> consultarPorDenuncia(int id);
	
}

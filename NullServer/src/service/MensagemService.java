package service;

import java.util.List;

import model.Mensagem;

public interface MensagemService extends SuperService<Mensagem, Integer> {

	List<Mensagem> consultarPorDenuncia(int id);
	
}

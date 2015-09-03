package service;

import java.util.List;

import model.ViewMensagem;

public interface ViewMensagemService extends SuperService<ViewMensagem, Integer> {

	List<ViewMensagem> consultarPorDenuncia(int id);

}

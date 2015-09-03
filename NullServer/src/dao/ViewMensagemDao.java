package dao;

import java.util.List;

import model.ViewMensagem;


public interface ViewMensagemDao extends SuperDao<ViewMensagem, Integer> {

	List<ViewMensagem> consultarPorDenuncia(int id);
}

package service;

import java.util.List;

import model.ViewDetalheDenuncia;

public interface ViewDetalheDenunciaService extends SuperService<ViewDetalheDenuncia, Integer> {

	public List<ViewDetalheDenuncia> consultarPorUsuario(int id);
}

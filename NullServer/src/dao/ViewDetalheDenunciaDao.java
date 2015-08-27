package dao;

import java.util.List;

import model.ViewDetalheDenuncia;


public interface ViewDetalheDenunciaDao extends SuperDao<ViewDetalheDenuncia, Integer> {

	public List<ViewDetalheDenuncia> consultarPorUsuario(int id);

}

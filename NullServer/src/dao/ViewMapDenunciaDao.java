package dao;

import java.util.List;

import model.ViewMapDenuncia;


public interface ViewMapDenunciaDao extends SuperDao<ViewMapDenuncia, Integer> {

	List<ViewMapDenuncia> consultarTodosWeb();

}

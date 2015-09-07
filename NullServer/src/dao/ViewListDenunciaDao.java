package dao;

import java.util.ArrayList;

import model.ViewListDenuncia;


public interface ViewListDenunciaDao extends SuperDao<ViewListDenuncia, Integer> {

	ArrayList<ViewListDenuncia> consultarPorUsuario(int id);

}

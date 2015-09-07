package service;

import java.util.ArrayList;

import model.ViewListDenuncia;

public interface ViewListDenunciaService extends SuperService<ViewListDenuncia, Integer> {

	ArrayList<ViewListDenuncia> consultarPorUsuario(int id);

}

package service;

import java.util.ArrayList;

import model.ViewMapDenuncia;

public interface ViewMapDenunciaService extends SuperService<ViewMapDenuncia, Integer> {

	ArrayList<ViewMapDenuncia> consultarTodosWeb();

}

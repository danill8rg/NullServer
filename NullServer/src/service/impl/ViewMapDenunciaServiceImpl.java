package service.impl;

import java.util.ArrayList;

import dao.ViewMapDenunciaDao;
import dao.impl.BairroDaoImpl;
import dao.impl.ViewMapDenunciaDaoImpl;
import model.Bairro;
import model.ViewMapDenuncia;
import service.ViewMapDenunciaService;

public class ViewMapDenunciaServiceImpl extends SuperServiceImpl<ViewMapDenuncia, Integer> implements ViewMapDenunciaService {
	
	@Override
	public ViewMapDenuncia gravar(ViewMapDenuncia object) {
		return null;
	}

	@Override
	public ViewMapDenuncia atualizar(ViewMapDenuncia object) {
			return null;
	}

	@Override
	public void excluir(ViewMapDenuncia object) {
		
	}

	@Override
	public ArrayList<ViewMapDenuncia> consultarTodos(ViewMapDenuncia object) {
		setDao(new ViewMapDenunciaDaoImpl());
		try{
			ArrayList<ViewMapDenuncia> array = new  ArrayList<ViewMapDenuncia>(getDao().consultarTodos(object));
			if(array != null){
				return array;
			}
			return new ArrayList<ViewMapDenuncia>();
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos Bairro");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<ViewMapDenuncia>();
		}		
	}

	@Override
	public ViewMapDenuncia consultarObjetoId(int entityID) {
		setDao(new ViewMapDenunciaDaoImpl());
		try{
			ViewMapDenuncia view = getDao().consultarObjetoId(entityID);	
			if(view != null){
				return view;
			}
			return new ViewMapDenuncia();
		}catch(Exception e){
			System.out.println("Erro ao consultar Bairro por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ViewMapDenuncia();
		}
	}

	@Override
	public ArrayList<ViewMapDenuncia> consultarTodosWeb() {
		ViewMapDenunciaDao daoView = new ViewMapDenunciaDaoImpl();
		ArrayList<ViewMapDenuncia> lista = new ArrayList<ViewMapDenuncia>(daoView.consultarTodosWeb());
		if(lista != null){
			return lista;
		}
		return new ArrayList<ViewMapDenuncia>();
	}
}

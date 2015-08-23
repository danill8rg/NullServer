package service.impl;

import java.util.ArrayList;

import model.ViewDetalheDenuncia;
import service.ViewDetalheDenunciaService;
import dao.impl.ViewDetalheDenunciaDaoImpl;

public class ViewDetalheDenunciaServiceImpl extends SuperServiceImpl<ViewDetalheDenuncia, Integer> implements ViewDetalheDenunciaService {
	
	@Override
	public ViewDetalheDenuncia gravar(ViewDetalheDenuncia object) {
		return null;
	}

	@Override
	public ViewDetalheDenuncia atualizar(ViewDetalheDenuncia object) {
			return null;
	}

	@Override
	public void excluir(ViewDetalheDenuncia object) {
		
	}

	@Override
	public ArrayList<ViewDetalheDenuncia> consultarTodos(ViewDetalheDenuncia object) {
		setDao(new ViewDetalheDenunciaDaoImpl());
		try{
			ArrayList<ViewDetalheDenuncia> array = new  ArrayList<ViewDetalheDenuncia>(getDao().consultarTodos(object));
			if(array != null){
				return array;
			}
			return new ArrayList<ViewDetalheDenuncia>();
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos Bairro");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<ViewDetalheDenuncia>();
		}		
	}

	@Override
	public ViewDetalheDenuncia consultarObjetoId(int entityID) {
		setDao(new ViewDetalheDenunciaDaoImpl());
		try{
			ViewDetalheDenuncia view = getDao().consultarObjetoId(entityID);	
			if(view != null){
				return view;
			}
			return new ViewDetalheDenuncia();
		}catch(Exception e){
			System.out.println("Erro ao consultar Bairro por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ViewDetalheDenuncia();
		}
	}
}

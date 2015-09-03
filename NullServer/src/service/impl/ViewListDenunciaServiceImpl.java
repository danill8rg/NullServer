package service.impl;

import java.util.ArrayList;

import model.ViewListDenuncia;
import service.ViewListDenunciaService;
import dao.impl.ViewListDenunciaDaoImpl;

public class ViewListDenunciaServiceImpl extends SuperServiceImpl<ViewListDenuncia, Integer> implements ViewListDenunciaService {
	
	@Override
	public ViewListDenuncia gravar(ViewListDenuncia object) {
		return null;
	}

	@Override
	public ViewListDenuncia atualizar(ViewListDenuncia object) {
			return null;
	}

	@Override
	public void excluir(ViewListDenuncia object) {
		
	}

	@Override
	public ArrayList<ViewListDenuncia> consultarTodos(ViewListDenuncia object) {
		setDao(new ViewListDenunciaDaoImpl());
		try{
			ArrayList<ViewListDenuncia> array = new  ArrayList<ViewListDenuncia>(getDao().consultarTodos(object));
			if(array != null){
				return array;
			}
			return new ArrayList<ViewListDenuncia>();
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos Bairro");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<ViewListDenuncia>();
		}		
	}

	@Override
	public ViewListDenuncia consultarObjetoId(int entityID) {
		setDao(new ViewListDenunciaDaoImpl());
		try{
			ViewListDenuncia view = getDao().consultarObjetoId(entityID);	
			if(view != null){
				return view;
			}
			return new ViewListDenuncia();
		}catch(Exception e){
			System.out.println("Erro ao consultar Bairro por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ViewListDenuncia();
		}
	}
}

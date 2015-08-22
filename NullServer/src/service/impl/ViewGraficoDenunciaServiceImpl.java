package service.impl;

import java.util.ArrayList;

import model.ViewGraficoDenuncia;
import model.ViewMapDenuncia;
import service.ViewGraficoDenunciaService;
import dao.impl.ViewGraficoDenunciaDaoImpl;
import dao.impl.ViewMapDenunciaDaoImpl;

public class ViewGraficoDenunciaServiceImpl extends SuperServiceImpl<ViewGraficoDenuncia, Integer> implements ViewGraficoDenunciaService {
	
	@Override
	public ViewGraficoDenuncia gravar(ViewGraficoDenuncia object) {
		return null;
	}

	@Override
	public ViewGraficoDenuncia atualizar(ViewGraficoDenuncia object) {
			return null;
	}

	@Override
	public void excluir(ViewGraficoDenuncia object) {
		
	}

	@Override
	public ArrayList<ViewGraficoDenuncia> consultarTodos(ViewGraficoDenuncia object) {
		setDao(new ViewGraficoDenunciaDaoImpl());
		try{
			ArrayList<ViewGraficoDenuncia> array = new  ArrayList<ViewGraficoDenuncia>(getDao().consultarTodos(object));
			if(array != null){
				return array;
			}
			return new ArrayList<ViewGraficoDenuncia>();
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos Bairro");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<ViewGraficoDenuncia>();
		}		
	}

	@Override
	public ViewGraficoDenuncia consultarObjetoId(int entityID) {
		setDao(new ViewGraficoDenunciaDaoImpl());
		try{
			ViewGraficoDenuncia view = getDao().consultarObjetoId(entityID);	
			if(view != null){
				return view;
			}
			return new ViewGraficoDenuncia();
		}catch(Exception e){
			System.out.println("Erro ao consultar Bairro por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ViewGraficoDenuncia();
		}
	}
}

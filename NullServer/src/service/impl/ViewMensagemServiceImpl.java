package service.impl;

import java.util.ArrayList;
import java.util.List;

import model.ViewDetalheDenuncia;
import model.ViewMensagem;
import service.ViewMensagemService;
import dao.ViewDetalheDenunciaDao;
import dao.ViewMensagemDao;
import dao.impl.ViewDetalheDenunciaDaoImpl;
import dao.impl.ViewMensagemDaoImpl;

public class ViewMensagemServiceImpl extends SuperServiceImpl<ViewMensagem, Integer> implements ViewMensagemService {
	
	@Override
	public ViewMensagem gravar(ViewMensagem object) {
		return null;
	}

	@Override
	public ViewMensagem atualizar(ViewMensagem object) {
			return null;
	}

	@Override
	public void excluir(ViewMensagem object) {
		
	}

	@Override
	public ArrayList<ViewMensagem> consultarTodos(ViewMensagem object) {
		setDao(new ViewMensagemDaoImpl());
		try{
			ArrayList<ViewMensagem> array = new  ArrayList<ViewMensagem>(getDao().consultarTodos(object));
			if(array != null){
				return array;
			}
			return new ArrayList<ViewMensagem>();
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos Bairro");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<ViewMensagem>();
		}		
	}

	@Override
	public ViewMensagem consultarObjetoId(int entityID) {
		setDao(new ViewMensagemDaoImpl());
		try{
			ViewMensagem view = getDao().consultarObjetoId(entityID);	
			if(view != null){
				return view;
			}
			return new ViewMensagem();
		}catch(Exception e){
			System.out.println("Erro ao consultar Bairro por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ViewMensagem();
		}
	}

	@Override
	public List<ViewMensagem> consultarPorDenuncia(int id) {
		ViewMensagemDao dao = new ViewMensagemDaoImpl();
		return dao.consultarPorDenuncia(id);
	}
}

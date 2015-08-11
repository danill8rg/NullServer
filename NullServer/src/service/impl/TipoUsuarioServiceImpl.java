package service.impl;

import java.util.ArrayList;

import model.TipoDenuncia;
import model.TipoUsuario;
import service.TipoUsuarioService;
import dao.impl.TipoDenunciaDaoImpl;
import dao.impl.TipoUsuarioDaoImpl;

public class TipoUsuarioServiceImpl extends SuperServiceImpl<TipoUsuario, Integer> implements TipoUsuarioService {
	
	@Override
	public TipoUsuario gravar(TipoUsuario object) {
		if(!validar(object)){
			return null;
		}
		setDao(new TipoUsuarioDaoImpl());
		try{
			object = getDao().gravar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao gravar TipoUsuario");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public TipoUsuario atualizar(TipoUsuario object) {
		if(!validar(object)){
			return null;
		}
		setDao(new TipoUsuarioDaoImpl());
		try{
			object = getDao().atualizar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar TipoUsuario");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(TipoUsuario object) {
		setDao(new TipoUsuarioDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao atualizar TipoUsuario");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
	}

	@Override
	public ArrayList<TipoUsuario> consultarTodos(TipoUsuario object) {
		setDao(new TipoUsuarioDaoImpl());
		try{
			ArrayList<TipoUsuario> arrayDenucia = new  ArrayList<TipoUsuario>(getDao().consultarTodos(object));
			for(TipoUsuario tipo : arrayDenucia){
				tipo.setUsuarioList(null);
			}
			return arrayDenucia;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos os TiposUsuario.");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<TipoUsuario>();
		}		
	}

	@Override
	public TipoUsuario consultarObjetoId(int entityID) {
		setDao(new TipoUsuarioDaoImpl());
		try{
			TipoUsuario tipo = getDao().consultarObjetoId(entityID);
			if(tipo != null ){
				tipo.setUsuarioList(null);
			}
			return tipo;
		}catch(Exception e){
			System.out.println("Erro ao consultar TipoUsuario por ID");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new TipoUsuario();
		}		
	}

	private boolean validar(TipoUsuario tipo) {
		if(tipo != null && tipo.getDescricao() != null &&
				!tipo.getDescricao().equalsIgnoreCase("")){
			return true;
		}
		return false;
	}
}

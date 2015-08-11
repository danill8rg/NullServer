package service.impl;

import java.util.ArrayList;

import model.LocalDenuncia;
import service.LocalDenunciaService;
import dao.impl.LocalDenunciaDaoImpl;

public class LocalDenunciaServiceImpl extends SuperServiceImpl<LocalDenuncia, Integer> implements LocalDenunciaService {
	
	@Override
	public LocalDenuncia gravar(LocalDenuncia object) {
		if(!validar(object)){
			return null;
		}
		setDao(new LocalDenunciaDaoImpl());
		try{
			object = getDao().gravar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao gravar LocalDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public LocalDenuncia atualizar(LocalDenuncia object) {
		if(!validar(object)){
			return null;
		}
		setDao(new LocalDenunciaDaoImpl());
		try{
			object = getDao().atualizar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar LocalDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(LocalDenuncia object) {
		setDao(new LocalDenunciaDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao excluir LocalDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
		
	}

	@Override
	public ArrayList<LocalDenuncia> consultarTodos(LocalDenuncia object) {
		setDao(new LocalDenunciaDaoImpl());
		try{
			ArrayList<LocalDenuncia> arrayLocais = new  ArrayList<LocalDenuncia>(getDao().consultarTodos(object));
			for(LocalDenuncia local : arrayLocais){
				local.setDenunciaList(null);
			}
			return arrayLocais;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas as imagem Denuncias");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return  new ArrayList<LocalDenuncia>();
		}				
	}

	@Override
	public LocalDenuncia consultarObjetoId(int entityID) {
		setDao(new LocalDenunciaDaoImpl());
		try{
			LocalDenuncia local= getDao().consultarObjetoId(entityID);
			if(local != null){
				local.setDenunciaList(null);
			}
			return local;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas as imagem Denuncias");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return  new LocalDenuncia();
		}		
		
		
	}

	private boolean validar(LocalDenuncia object) {
		if(object != null ){
			return true;
		}
		return false;
	}
}

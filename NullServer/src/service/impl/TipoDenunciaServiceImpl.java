package service.impl;

import java.util.ArrayList;

import model.Pais;
import model.TipoDenuncia;
import service.TipoDenunciaService;
import dao.TipoDenunciaDao;
import dao.impl.PaisDaoImpl;
import dao.impl.TipoDenunciaDaoImpl;

public class TipoDenunciaServiceImpl extends SuperServiceImpl<TipoDenuncia, Integer> implements TipoDenunciaService {
	
	@Override
	public TipoDenuncia gravar(TipoDenuncia object) {	
		if(!validar(object)){
			return null;
		}
		setDao(new TipoDenunciaDaoImpl());
		try{
			object = getDao().gravar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao gravar TipoDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public TipoDenuncia atualizar(TipoDenuncia object) {
		if(!validar(object)){
			return null;
		}
		setDao(new TipoDenunciaDaoImpl());
		try{
			object = getDao().gravar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar  TipoDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(TipoDenuncia object) {
		setDao(new TipoDenunciaDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao excluir  TipoDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
	}

	@Override
	public ArrayList<TipoDenuncia> consultarTodos(TipoDenuncia object) {
		setDao(new TipoDenunciaDaoImpl());
		try{
			ArrayList<TipoDenuncia> arrayDenucia = new  ArrayList<TipoDenuncia>(getDao().consultarTodos(object));
			for(TipoDenuncia tipo : arrayDenucia){
				tipo.setDenunciaList(null);
			}
			return arrayDenucia;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos os TiposDenuncias.");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<TipoDenuncia>();
		}		
	}

	@Override
	public TipoDenuncia consultarObjetoId(int entityID) {
		setDao(new TipoDenunciaDaoImpl());
		try{
			TipoDenuncia tipo = getDao().consultarObjetoId(entityID);
			if(tipo != null ){
				tipo.setDenunciaList(null);
			}
			return tipo;
		}catch(Exception e){
			System.out.println("Erro ao consultar TiposDenuncias por ID");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new TipoDenuncia();
		}		
	}

	private boolean validar(TipoDenuncia tipo) {
		if(tipo.getDescricao() != null && tipo.getDescricao() != null 
				&& !tipo.getDescricao().equalsIgnoreCase("")){
			return true;
		}
		return false;
	}

	@Override
	public TipoDenuncia consultarDescricao(String tipoDenuncia) {
		try{
			TipoDenunciaDao tipoDao = new TipoDenunciaDaoImpl();
			return tipoDao.consultarDescricao(tipoDenuncia);
		}catch(Exception e){
			System.out.println("Erro ao consultar TiposDenuncias por ID");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new TipoDenuncia();
		}		
	}
}

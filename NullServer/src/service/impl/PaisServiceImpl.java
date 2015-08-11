package service.impl;

import java.util.ArrayList;

import model.Pais;
import service.PaisService;
import dao.impl.PaisDaoImpl;

public class PaisServiceImpl extends SuperServiceImpl<Pais, Integer> implements PaisService {
	
	@Override
	public Pais gravar(Pais object) {
		if(!validar(object)){
			return null;
		}
		setDao(new PaisDaoImpl());
		try{
			object = getDao().gravar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao gravar Pais");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public Pais atualizar(Pais object) {
		if(!validar(object)){
			return null;
		}
		setDao(new PaisDaoImpl());
		try{
			object = getDao().atualizar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar Pais");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(Pais object) {
		setDao(new PaisDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao excluir Pais");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
	}

	@Override
	public ArrayList<Pais> consultarTodos(Pais object) {
		setDao(new PaisDaoImpl());
		try{
			ArrayList<Pais>  arrayPais = new  ArrayList<Pais>(getDao().consultarTodos(object));
			for(Pais pais : arrayPais){
				pais.setEstadoList(null);
			}
			return arrayPais;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos os Pais");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<Pais>();
		}		
	}

	@Override
	public Pais consultarObjetoId(int entityID) {
		setDao(new PaisDaoImpl());
		try{
			Pais  pais = getDao().consultarObjetoId(entityID);	
			if(pais != null){
				pais.setEstadoList(null);
			}
			return pais;
		}catch(Exception e){
			System.out.println("Erro ao consultar Pais por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new Pais();
		}
	}

	private boolean validar(Pais pais) {
		if(pais != null && pais.getNome() != null && !pais.getNome().equalsIgnoreCase("")){
			return true;
		}
		return false;
	}
}

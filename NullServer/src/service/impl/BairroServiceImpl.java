package service.impl;

import java.util.ArrayList;

import model.Bairro;
import service.BairroService;
import dao.impl.BairroDaoImpl;

public class BairroServiceImpl extends SuperServiceImpl<Bairro, Integer> implements BairroService {
	
	@Override
	public Bairro gravar(Bairro object) {
		if(!validar(object)){
			return null;
		}
		setDao(new BairroDaoImpl());
		try{
			object = getDao().gravar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao gravar Bairro...");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public Bairro atualizar(Bairro object) {
		if(!validar(object)){
			return null;
		}
		setDao(new BairroDaoImpl());
		try{
			object = getDao().atualizar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar Bairro...");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(Bairro object) {
		setDao(new BairroDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao excluir Bairro...");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
	}

	@Override
	public ArrayList<Bairro> consultarTodos(Bairro object) {
		setDao(new BairroDaoImpl());
		ArrayList<Bairro> arrayBairros = new ArrayList<Bairro>();
		try{
			arrayBairros = new  ArrayList<Bairro>(getDao().consultarTodos(object));
			for(Bairro bairro : arrayBairros){
				bairro.setLocalDenunciaList(null);
			}
			return arrayBairros;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos Bairro");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<Bairro>();
		}		
	}

	@Override
	public Bairro consultarObjetoId(int entityID) {
		setDao(new BairroDaoImpl());
		Bairro bairro = new Bairro();
		try{
			bairro = getDao().consultarObjetoId(entityID);	
			if(bairro != null){
				bairro.setLocalDenunciaList(null);
			}
			return bairro;
		}catch(Exception e){
			System.out.println("Erro ao consultar Bairro por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new Bairro();
		}
	}

	private boolean validar(Bairro object) {
		if(object != null && object.getNome() != null  && !object.getNome().equalsIgnoreCase("")
				&& object.getCidade() != null && object.getCidade().getIdCidade() != 0){
			return true;
		}
		return false;
	}
}

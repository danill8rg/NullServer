package service.impl;

import java.util.ArrayList;

import model.Cidade;
import service.CidadeService;
import dao.impl.CidadeDaoImpl;

public class CidadeServiceImpl extends SuperServiceImpl<Cidade, Integer> implements CidadeService {
	
	@Override
	public Cidade gravar(Cidade object) {
		if(!validar(object)){
			return null;
		}
		setDao(new CidadeDaoImpl());
		try{
			object = getDao().gravar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao gravar Cidade");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public Cidade atualizar(Cidade object) {
		if(!validar(object)){
			return null;
		}
		setDao(new CidadeDaoImpl());
		try{
			object = getDao().atualizar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar Cidade");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(Cidade object) {
		setDao(new CidadeDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao excluir Cidade");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
	}

	@Override
	public ArrayList<Cidade> consultarTodos(Cidade object) {
		setDao(new CidadeDaoImpl());
		ArrayList<Cidade> arrayCidade = new ArrayList<Cidade>();
		try{
			arrayCidade = new  ArrayList<Cidade>(getDao().consultarTodos(object));
			for(Cidade cidade : arrayCidade){
				cidade.setBairroList(null);
			}
			return arrayCidade;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas Cidades");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<Cidade>();
		}		
	}

	@Override
	public Cidade consultarObjetoId(int entityID) {
		setDao(new CidadeDaoImpl());
		try{
			Cidade cidade = getDao().consultarObjetoId(entityID);	
			if(cidade != null){
				cidade.setBairroList(null);
			}
			return cidade;
		}catch(Exception e){
			System.out.println("Erro ao consultar Usuarios por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new Cidade();
		}
	}

	private boolean validar(Cidade cidade) {
		if(cidade != null && cidade.getNome() != null && !cidade.getNome().equalsIgnoreCase("")
			&& cidade.getEstado() != null  && cidade.getEstado().getIdEstado() != 0 ){
			return true;
		}
		return false;
	}

}

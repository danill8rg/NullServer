package service.impl;

import java.util.ArrayList;

import model.Estado;
import service.EstadoService;
import dao.impl.EstadoDaoImpl;

public class EstadoServiceImpl extends SuperServiceImpl<Estado, Integer> implements EstadoService {
	
	@Override
	public Estado gravar(Estado estado) {
		if(!validar(estado)){
			return null;
		}
		setDao(new EstadoDaoImpl());
		try{
			estado = getDao().gravar(estado);
			return estado;
		}catch(Exception e){
			System.out.println("Erro ao gravar Estado");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new Estado();
		}		
	}

	@Override
	public Estado atualizar(Estado estado) {
		if(!validar(estado)){
			return null;
		}
		setDao(new EstadoDaoImpl());
		try{
			estado = getDao().atualizar(estado);
			return estado;
		}catch(Exception e){
			System.out.println("Erro ao gravar Estado");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new Estado();
		}		
	}

	@Override
	public void excluir(Estado estado) {
		setDao(new EstadoDaoImpl());
		try{
			getDao().excluir(estado);
		}catch(Exception e){
			System.out.println("Erro ao excluir Estado");
			System.out.println("Erro : " + e);
			System.out.println("Erro Mensgem : " + e.getMessage());
		}		
	}

	@Override
	public ArrayList<Estado> consultarTodos(Estado estado) {
		setDao(new EstadoDaoImpl());
		try{
			ArrayList<Estado> arrayEstado = new  ArrayList<Estado>(getDao().consultarTodos(estado));
			for(Estado staty : arrayEstado){
				staty.setCidadeList(null);
			}
			return arrayEstado;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos Usuarios.");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<Estado>();
		}		
	}

	@Override
	public Estado consultarObjetoId(int entityID) {
		setDao(new EstadoDaoImpl());
		Estado user = new Estado();
		try{
			user = getDao().consultarObjetoId(entityID);	
			if(user != null){
				
			}
			return user;
		}catch(Exception e){
			System.out.println("Erro ao consultar Usuarios por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return user;
		}
	}

	private boolean validar(Estado estado) {
		if(estado != null &&  estado.getNome() != null 
				&& !estado.getNome().equalsIgnoreCase("") && estado.getPais() != null
				&& estado.getPais().getIdPais() != 0){
			return true;
		}
		return false;
	}
}

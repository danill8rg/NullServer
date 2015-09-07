package service.impl;

import java.util.ArrayList;

import model.DadosContaUsuario;
import service.DadosContaUsuarioService;
import dao.impl.DadosContaUsuarioDaoImpl;

public class DadosContaUsuarioServiceImpl extends SuperServiceImpl<DadosContaUsuario, Integer> implements DadosContaUsuarioService {
	
	@Override
	public DadosContaUsuario gravar(DadosContaUsuario object) {
		if(!validar(object)){
			return null;
		}
		setDao(new DadosContaUsuarioDaoImpl());
		try{
			object.setIdContaUsuario(object.getUsuario().getIdUsuario());
			object = getDao().gravar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao gravar DadosContaUsuario.");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public DadosContaUsuario atualizar(DadosContaUsuario object) {
		if(!validar(object)){
			return null;
		}
		setDao(new DadosContaUsuarioDaoImpl());
		try{
			object = getDao().atualizar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar DadosContaUsuario.");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensagem :" + e.getMessage());
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public void excluir(DadosContaUsuario object) {
		setDao(new DadosContaUsuarioDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao excluir DadosContaUsuario.");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
	}

	@Override
	public ArrayList<DadosContaUsuario> consultarTodos(DadosContaUsuario object) {
		setDao(new DadosContaUsuarioDaoImpl());
		try{
			ArrayList<DadosContaUsuario> arrayUsuarios = new  ArrayList<DadosContaUsuario>(getDao().consultarTodos(object));
			return arrayUsuarios;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos os DadosContaUsuarios");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ArrayList<DadosContaUsuario>();
		}		
	}

	@Override
	public DadosContaUsuario consultarObjetoId(int entityID) {
		setDao(new DadosContaUsuarioDaoImpl());
		try{
			DadosContaUsuario dado = getDao().consultarObjetoId(entityID);	
			return dado;
		}catch(Exception e){
			System.out.println("Erro ao consultar Usuarios por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new DadosContaUsuario();
		}
	}

	private boolean validar(DadosContaUsuario dado) {
		if(dado != null  && dado.getUsuario() != null
				&& dado.getUsuario().getIdUsuario() != 0 ){
			return true;
		}
		return false;
	}

}

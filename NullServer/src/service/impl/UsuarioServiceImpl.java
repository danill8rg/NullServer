package service.impl;

import java.util.ArrayList;

import model.DadosContaUsuario;
import model.Usuario;
import service.DadosContaUsuarioService;
import service.UsuarioService;
import util.UtilEmail;
import dao.UsuarioDao;
import dao.impl.TipoUsuarioDaoImpl;
import dao.impl.UsuarioDaoImpl;

public class UsuarioServiceImpl extends SuperServiceImpl<Usuario, Integer> implements UsuarioService {
	
	@Override
	public Usuario gravar(Usuario object) {
		if(!validar(object)){
			return null;
		}
		setDao(new UsuarioDaoImpl());
		try{
			object = getDao().gravar(object);
			DadosContaUsuario dados= new DadosContaUsuario();
			dados.setUsuario(object);
			dados.setIdContaUsuario(object.getIdUsuario());
			DadosContaUsuarioService contaservice = new DadosContaUsuarioServiceImpl();
			contaservice.gravar(dados);			
			object.setDadosContaUsuario(null);
			object.setSenha(null);
			object.setMensagemList(null);
			object.setDenunciaList(null);
			object.setTipoUsuario(null);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao gravar Usuario.");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensagem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public Usuario atualizar(Usuario object) {
		if(!validar(object)){
			return null;
		}
		setDao(new UsuarioDaoImpl());
		try{
			object = getDao().atualizar(object);
			object.setDadosContaUsuario(null);
			object.setSenha(null);
			object.setMensagemList(null);
			object.setDenunciaList(null);
			object.setTipoUsuario(null);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar Usuario");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensagem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(Usuario object) {
		setDao(new UsuarioDaoImpl());
		getDao().excluir(object);
	}

	@Override
	public ArrayList<Usuario> consultarTodos(Usuario object) {
		setDao(new UsuarioDaoImpl());
		ArrayList<Usuario> arrayUsuarios = new ArrayList<Usuario>();
		try{
			arrayUsuarios = new  ArrayList<Usuario>(getDao().consultarTodos(object));
			for(Usuario user : arrayUsuarios){
				//teste...
				user.setTipoUsuario(null);
				user.setSenha(null);
				user.setDadosContaUsuario(null);
				user.setDenunciaList(null);
				user.setMensagemList(null);
				if(user.getTipoUsuario() != null){
					user.getTipoUsuario().setUsuarioList(null);
				}
			}
			return arrayUsuarios;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todos Usuarios.");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensagem :" + e.getMessage());
			return arrayUsuarios;
		}		
	}

	@Override
	public Usuario consultarObjetoId(int entityID) {
		setDao(new UsuarioDaoImpl());
		Usuario user = new Usuario();
		try{
			user = getDao().consultarObjetoId(entityID);	
			if(user != null){
				user.setSenha(null);
				user.setDadosContaUsuario(null);
				user.setDenunciaList(null);
				user.setMensagemList(null);
				if(user.getTipoUsuario() != null){
					user.getTipoUsuario().setUsuarioList(null);
				}
			}
			return user;
		}catch(Exception e){
			System.out.println("Erro ao consultar Usuarios por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensagem :" + e.getMessage());
			return user;
		}
	}

	private boolean validar(Usuario object) {
		if(object.getEmail() != null && 
			object.getNome() != null &&
			object.getSenha() != null){
			return true;
		}
		return false;
	}

	@Override
	public Usuario logar(Usuario usuario) throws Exception {
		if(usuario.getEmail() == null || usuario.getSenha() == null){
			return null;
		}
		UsuarioDao userDao = new UsuarioDaoImpl();
		return userDao.logar(usuario.getEmail(), usuario.getSenha());
	}

	@Override
	public boolean validarEmail(String email) throws Exception {
		UsuarioDao userDao = new UsuarioDaoImpl();
		return userDao.validarEmail(email);
	}

	@Override
	public boolean relebrarEmail(String email) {
		UsuarioDao userDao = new UsuarioDaoImpl();
		Usuario user = userDao.consultarPorEmail(email);
		if(user != null){
			String mensagem = "";
			mensagem = mensagem + "Muito obrigado por usar o NullPointer.";
			mensagem = mensagem + "Sua senha é : " + user.getSenha();
			UtilEmail.mandaEmail(email, mensagem);
			return true;
		}
		return false;
	}

	@Override
	public String proximoIdUsuario() {
		UsuarioDao userDao = new UsuarioDaoImpl();
		return userDao.proximoIdUsuario();
	}
}

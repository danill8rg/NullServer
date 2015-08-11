package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.MensagemDao;
import dao.impl.MensagemDaoImpl;
import model.ImagemDenuncia;
import model.Mensagem;
import service.MensagemService;

public class MensagemServiceImpl extends SuperServiceImpl<Mensagem, Integer> implements MensagemService {
	
	@Override
	public Mensagem gravar(Mensagem object) {
		if(!validar(object)){
			return null;
		}
		setDao(new MensagemDaoImpl());
		try{
			object = getDao().gravar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao gravar Mensagem");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public Mensagem atualizar(Mensagem object) {
		if(!validar(object)){
			return null;
		}
		setDao(new MensagemDaoImpl());
		try{
			object = getDao().atualizar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar Mensagem");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(Mensagem object) {
		setDao(new MensagemDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao excluir Mensagem");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
	}

	@Override
	public ArrayList<Mensagem> consultarTodos(Mensagem object) {
		setDao(new MensagemDaoImpl());
		try{
			ArrayList<Mensagem> arrayMensage = new  ArrayList<Mensagem>(getDao().consultarTodos(object));
			return arrayMensage;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas as Mensagem");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return  new ArrayList<Mensagem>();
		}		
	}

	@Override
	public Mensagem consultarObjetoId(int entityID) {
		setDao(new MensagemDaoImpl());
		try{
			Mensagem mensagem = getDao().consultarObjetoId(entityID);
			return mensagem;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas as Mensagem");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return  new Mensagem();
		}
	}

	private boolean validar(Mensagem mensagem) {
		if(mensagem != null && mensagem.getTexto() != null && mensagem.getDenuncia() != null &&  mensagem.getDenuncia().getIdDenuncia() != 0
				&& mensagem.getUsuario() != null && mensagem.getUsuario().getIdUsuario() != 0){
			return true;
		}
		return false;
	}

	@Override
	public List<Mensagem> consultarPorDenuncia(int id) {
		MensagemDao mensagemDao = new MensagemDaoImpl();
		try{
			ArrayList<Mensagem> arrayMensagem = new  ArrayList<Mensagem>(mensagemDao.consultarPorDenuncia(id));
			for(Mensagem mensagem : arrayMensagem){
				if(!mensagem.getAtivo()) arrayMensagem.remove(mensagem);
			}			
			return arrayMensagem;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas as imagem Denuncias");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return  new  ArrayList<Mensagem>();
		}		
	}
}

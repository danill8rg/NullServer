package service.impl;

import java.util.ArrayList;
import java.util.List;

import model.ImagemDenuncia;
import service.ImagemDenunciaService;
import dao.ImagemDenunciaDao;
import dao.impl.ImagemDenunciaDaoImpl;

public class ImagemDenunciaServiceImpl extends SuperServiceImpl<ImagemDenuncia, Integer> implements ImagemDenunciaService {
	
	@Override
	public ImagemDenuncia gravar(ImagemDenuncia object) {
		if(!validar(object)){
			return null;
		}
		setDao(new ImagemDenunciaDaoImpl());
		try{
			object = getDao().gravar(object);
			if(object != null ){
				return object;
			}else{
				System.out.println("Erro ao gravar ImagemDenuncia");
				System.out.println("Erro imagem Nao foi gravada....");
				return new ImagemDenuncia();
			}			
		}catch(Exception e){
			System.out.println("Erro ao gravar ImagemDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public ImagemDenuncia atualizar(ImagemDenuncia object) {
		if(!validar(object)){
			return null;
		}
		setDao(new ImagemDenunciaDaoImpl());
		try{
			object = getDao().atualizar(object);
			return object;
		}catch(Exception e){
			System.out.println("Erro ao atualizar ImagemDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return null;
		}		
	}

	@Override
	public void excluir(ImagemDenuncia object) {
		setDao(new ImagemDenunciaDaoImpl());
		try{
			getDao().excluir(object);
		}catch(Exception e){
			System.out.println("Erro ao excluir ImagemDenuncia");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
		}		
	}

	@Override
	public ArrayList<ImagemDenuncia> consultarTodos(ImagemDenuncia object) {
		setDao(new ImagemDenunciaDaoImpl());
		try{
			ArrayList<ImagemDenuncia> arrayImagem = new  ArrayList<ImagemDenuncia>(getDao().consultarTodos(object));
			return arrayImagem;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas as imagem Denuncias");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return  new ArrayList<ImagemDenuncia>();
		}		
	}

	@Override
	public ImagemDenuncia consultarObjetoId(int entityID) {
		setDao(new ImagemDenunciaDaoImpl());
		try{
			ImagemDenuncia imagem = getDao().consultarObjetoId(entityID);	
			return imagem;
		}catch(Exception e){
			System.out.println("Erro ao consultar Usuarios por id");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return new ImagemDenuncia();
		}
	}

	private boolean validar(ImagemDenuncia imagem) {
		if(imagem != null && imagem.getCaminho() != null && !imagem.getCaminho().equalsIgnoreCase("")
				&& imagem.getDenuncia() != null && imagem.getDenuncia().getIdDenuncia() != 0){
			return true;
		}
		return false;
	}

	@Override
	public List<ImagemDenuncia> consultarPorDenuncia(int idDenuncia) {
		ImagemDenunciaDao imageDao = new ImagemDenunciaDaoImpl();
		try{
			ArrayList<ImagemDenuncia> arrayImagem = new  ArrayList<ImagemDenuncia>(imageDao.consultarPorIddenuncia(idDenuncia));
			for(ImagemDenuncia image : arrayImagem){
				if(!image.getAtivo()) arrayImagem.remove(image);
			}			
			return arrayImagem;
		}catch(Exception e){
			System.out.println("Erro ao consultar Todas as imagem Denuncias");
			System.out.println("Erro :" + e);
			System.out.println("Erro Mensgem :" + e.getMessage());
			return  new ArrayList<ImagemDenuncia>();
		}		
	}

	@Override
	public String proximoIdImagem() {
		ImagemDenunciaDao imageDao = new ImagemDenunciaDaoImpl();
		return imageDao.proximoIdImagem();
	}

}

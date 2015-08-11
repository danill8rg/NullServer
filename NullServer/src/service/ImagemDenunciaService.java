package service;

import java.util.List;

import model.ImagemDenuncia;

public interface ImagemDenunciaService extends SuperService<ImagemDenuncia, Integer> {

	public List<ImagemDenuncia> consultarPorDenuncia(int idDenuncia);
	
	public String proximoIdImagem();
}

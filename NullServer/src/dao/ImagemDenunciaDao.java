package dao;

import java.util.List;

import model.ImagemDenuncia;

public interface ImagemDenunciaDao extends SuperDao<ImagemDenuncia, Integer> {

	public List<ImagemDenuncia> consultarPorIddenuncia(int idDenuncia);
	
	public String proximoIdImagem();
}

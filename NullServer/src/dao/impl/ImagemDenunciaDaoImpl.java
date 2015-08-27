package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.ImagemDenuncia;
import model.Usuario;
import dao.ImagemDenunciaDao;

public class ImagemDenunciaDaoImpl extends SuperDaoImpl<ImagemDenuncia, Integer> implements ImagemDenunciaDao {


	@Override
	public List<ImagemDenuncia> consultarPorIddenuncia(int idDenuncia) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDenuncia", idDenuncia); 
        return super.consultarPorNamedQueryEParametros("ImagemDenuncia.findByIdDenuncia", parameters);
	}

	@Override
	public String proximoIdImagem() {
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<ImagemDenuncia> lista  = super.consultarPorNamedQueryEParametros("ImagemDenuncia.findUltimo", parameters);
		if(lista != null){
			ImagemDenuncia imagem = lista.get(0);
			return "" + (imagem.getIdImagemDenuncia() + 1);
		}
		return "1";
	}

	@Override
	public String caminhoPrimeiraImagem(int idDenuncia) {
		Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("idDenuncia", idDenuncia); 
        ImagemDenuncia image = consultarObjeto("ImagemDenuncia.findByIdDenuncia", parameters);
        if( image == null){
        	return null;
        }
        return image.getCaminho();
	}

}

package service;

import java.util.List;

import model.Denuncia;

public interface DenunciaService extends SuperService<Denuncia, Integer> {

	List<Denuncia> consultarParaMapa();
	
}

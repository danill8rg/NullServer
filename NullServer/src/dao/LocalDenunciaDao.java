package dao;

import model.Denuncia;
import model.LocalDenuncia;

public interface LocalDenunciaDao extends SuperDao<LocalDenuncia, Integer> {
	public LocalDenuncia consultarIdDenuncia(Denuncia denuncia);
}
